package Lab6;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class JImageDisplay extends JComponent
{
    public BufferedImage image;
    private int width;
    private int height;
    private Dimension dimension;

    public JImageDisplay(int width, int height)
    {
        this.width = width;
        this.height = height;

        this.image = new BufferedImage(width, height, TYPE_INT_RGB);

        this.dimension = new Dimension(width, height);

        super.setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage()
    {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                image.setRGB(x, y, 0);
    }

    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
}
