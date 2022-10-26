package Lab2;

public class Point2d {
    // Координаты x, y точки
    private double xCoord;
    private double yCoord;

    // Создание объекта с тремя значениями с плавающей точкой
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    // Создание объекта со значениями (0.0, 0.0) по умолчанию
    public Point2d() {
        this(0, 0 );
    }

    // Методы получения значений координат по отдельности
    public double getX() {
        return xCoord;
    }
    public double getY() {
        return yCoord;
    }

    // Методы изменения значений координат по отдельности
    public void setX(double val) {
        xCoord = val;
    }
    public void setY(double val) {
        yCoord = val;
    }
}
