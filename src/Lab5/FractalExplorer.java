package Lab5;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {

    private int size;

    private JImageDisplay imageDisplay;
    private FractalGenerator[] fractalGenerator;
    private Rectangle2D.Double range;
    private int fractalNumber;

    public static void main(String[] args) {
        FractalExplorer app = new FractalExplorer(600);
        app.createAndShowGUI();
        app.drawFractal();
    }

    private FractalExplorer(int s)
    {
        size = s;
        fractalGenerator = new FractalGenerator[]{new Mandelbrot(), new Tricorn(), new BurningShip()};
        range = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator[fractalNumber].getInitialRange(range);
    }

    private void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());

        JLabel fractalLabel = new JLabel("Fractal: ");
        String[] items = {
                "Mandelbrot",
                "Tricorn",
                "Burning Ship"
        };
        JComboBox fractalComboBox = new JComboBox(items);
        JPanel fractalPanel = new JPanel();
        fractalPanel.add(fractalLabel);
        fractalPanel.add(fractalComboBox);
        fractalComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fractalNumber = fractalComboBox.getSelectedIndex();
                fractalGenerator[fractalNumber].getInitialRange(range);
                drawFractal();
            }
        });

        contentPane.add(fractalPanel, BorderLayout.NORTH);

        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MouseHandler());

        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton saveFractalButton = new JButton("Save Image");
        saveFractalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int returnVal = chooser.showSaveDialog(imageDisplay);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File outputFile = chooser.getSelectedFile();
                    try {
                        ImageIO.write(imageDisplay.image, "png", outputFile);
                    }
                    catch(IOException exception) {
                        JOptionPane.showMessageDialog(imageDisplay, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton resetFractalButton = new JButton("Reset Display");
        resetFractalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fractalGenerator[fractalNumber].getInitialRange(range);
                drawFractal();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveFractalButton);
        buttonPanel.add(resetFractalButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++) {
                int i = fractalGenerator[fractalNumber].numIterations(FractalGenerator.getCoord(range.x, range.x + range.width,
                        size, x), FractalGenerator.getCoord(range.y, range.y + range.height, size, y));
                if (i == -1)
                    imageDisplay.drawPixel(x, y, 0);
                else {
                    float hue = 0.7f + (float) i / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        imageDisplay.repaint();
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double y = FractalGenerator.getCoord(range.y, range.y + range.height, size, e.getY());

            fractalGenerator[fractalNumber].recenterAndZoomRange(range, x, y, 0.5);

            drawFractal();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}