package Lab2;

import java.util.Objects;

public class Point3d extends Point2d {
    // Координата z точки
    private double zCoord;

    // Создание объекта с тремя значениями с плавающей точкой
    public Point3d(double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }
    // Создание объекта со значениями (0.0, 0.0, 0.0) по умолчанию
    public Point3d() {
        this(0, 0 , 0);
    }

    // Методы получения значения координаты z
    public double getZ() {
        return zCoord;
    }

    // Метод изменения значения координаты z
    public void setZ(double val) {
        zCoord = val;
    }


    // Переопределение метода equals для проверки координат объекта, а не ссылок
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return Double.compare(point3d.getX(), getX()) == 0 && Double.compare(point3d.getY(), getY()) == 0 &&
                Double.compare(point3d.zCoord, zCoord) == 0;
    }

    // Метод сравнения двух объектов
    public boolean compareObj(Point3d objPoint) {
        return objPoint.equals(this);
    }

    // Метод высчитывание расстояния между двумя точками
    public double distanceTo(Point3d objPoint3d) {
        double distance = Math.sqrt(Math.pow(objPoint3d.getX() - this.getX(), 2) +
                Math.pow(objPoint3d.getY() - this.getY(), 2) + Math.pow(objPoint3d.zCoord - this.getZ(), 2));
        return distance;
    }

}
