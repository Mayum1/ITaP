package Lab6;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.height = 4;
        range.width = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        double zx = x;
        double zy = y;
        int i = 0;
        while (i < MAX_ITERATIONS) {
            i++;
            double xtemp = zx * zx - zy * zy + x;
            zy = -2 * zx * zy + y;
            zx = xtemp;
            if (zx * zx + zy * zy > 4)
                break;
        }
        if (i == MAX_ITERATIONS)
            return -1;
        return i;
    }
}
