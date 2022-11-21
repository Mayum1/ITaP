package Lab4;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class FractalExplorer {

    private int size;

    private JImageDisplay imageDisplay;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range;

    public static void main(String[] args) {
        FractalExplorer app = new FractalExplorer(600);
        app.createAndShowGUI();
        app.drawFractal();
    }

    private FractalExplorer(int s)
    {
        size = s;
        fractalGenerator = new Mandelbrot();
        range = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(range);
    }

    private void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());

        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MouseHandler());

        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton resetFractalButton = new JButton("Reset Display");
        resetFractalButton.addActionListener(new ActionHandler());

        contentPane.add(resetFractalButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++) {
                int i = fractalGenerator.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width,
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

            fractalGenerator.recenterAndZoomRange(range, x, y, 0.5);

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

    public class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(range);
            drawFractal();
        }
    }
}