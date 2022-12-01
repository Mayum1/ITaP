package Lab5;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    @Override
    public int numIterations(double x, double y)
    {
        double xi = x;
        double yi = y;
        int i = 0;
        while (i < MAX_ITERATIONS) {
            i++;
            double a = xi * xi - yi * yi + x;
            double b = 2 * xi * yi + y;
            xi = a;
            yi = b;
            if (xi * xi + yi * yi > 4)
                break;
        }
        if (i == MAX_ITERATIONS)
            return -1;
        return i;
    }
}
