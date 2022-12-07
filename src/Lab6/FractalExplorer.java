package Lab6;

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
    JComboBox fractalComboBox;
    JButton resetFractalButton;
    JButton saveFractalButton;
    private int rowsRemaining;

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

    public void createAndShowGUI()
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
        fractalComboBox = new JComboBox(items);
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

        saveFractalButton = new JButton("Save Image");
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

        resetFractalButton = new JButton("Reset Display");
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
        enableUI(false);
        rowsRemaining = size;
        for (int y = 0; y < size; y++) {
            SwingWorker<Object, Object> worker = new FractalWorker(y);
            worker.execute();
        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining == 0) {
                double x = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
                double y = FractalGenerator.getCoord(range.y, range.y + range.height, size, e.getY());

                fractalGenerator[fractalNumber].recenterAndZoomRange(range, x, y, 0.5);

                drawFractal();
            }
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

    private class FractalWorker extends SwingWorker<Object, Object> {

        private int yCoord;
        private int[] colors;

        private FractalWorker(int y) {
            yCoord = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            colors = new int[size];
            for (int x = 0; x < size; x++) {
                int i = fractalGenerator[fractalNumber].numIterations(FractalGenerator.getCoord(range.x, range.x + range.width,
                        size, x), FractalGenerator.getCoord(range.y, range.y + range.height, size, yCoord));
                if (i == -1)
                    colors[x] = 0;
                else {
                    float hue = 0.7f + (float) i / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    colors[x] = rgbColor;
                }
            }
            return null;
        }

        @Override
        protected void done() {
            for (int x = 0; x < size; x++)
                imageDisplay.drawPixel(x, yCoord, colors[x]);
            imageDisplay.repaint(0, 0, yCoord, size, 1);
            rowsRemaining--;
            if (rowsRemaining == 0)
                enableUI(true);
        }
    }

    private void enableUI(boolean val) {
        saveFractalButton.setEnabled(val);
        resetFractalButton.setEnabled(val);
        fractalComboBox.setEnabled(val);
    }
}