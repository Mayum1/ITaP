package Lab2;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {

        // Добавление сканнера
        Scanner scanner = new Scanner(System.in);

        // Ввод координат первой точки
        System.out.print("x1 = ");
        double x1 = scanner.nextDouble();
        System.out.print("y1 = ");
        double y1 = scanner.nextDouble();
        System.out.print("z1 = ");
        double z1 = scanner.nextDouble();

        // Ввод координат второй точки
        System.out.print("x2 = ");
        double x2 = scanner.nextDouble();
        System.out.print("y2 = ");
        double y2 = scanner.nextDouble();
        System.out.print("z2 = ");
        double z2 = scanner.nextDouble();

        // Ввод координат третьей точки
        System.out.print("x3 = ");
        double x3 = scanner.nextDouble();
        System.out.print("y3 = ");
        double y3 = scanner.nextDouble();
        System.out.print("z3 = ");
        double z3 = scanner.nextDouble();

        // Создание трёх объектов Point3d с координатами, введеными ранее
        Point3d pointA = new Point3d(x1, y1, z1);
        Point3d pointB = new Point3d(x2, y2, z2);
        Point3d pointC = new Point3d(x3, y3, z3);

        // Сравнение точек друг с другом
        if (pointA.compareObj(pointB) || pointA.compareObj(pointC) || pointB.compareObj(pointC)) {
            // Вывод ошибки если некоторые точки совпадают
            System.out.println("Some points are the same.");
        }
        else {
            // Округление площади до 2 знаков после запятой
            String S = String.format("%.2f", computeArea(pointA, pointB, pointC));
            // Вывод площади
            System.out.println("S = " + S);
        }
    }

    // Метод вычисления площади треугольника
    public static double computeArea(Point3d obj1, Point3d obj2, Point3d obj3) {
        // Вычисление длин сторон треугольника методом distanceTo
        double a = obj1.distanceTo(obj2);
        double b = obj1.distanceTo(obj3);
        double c = obj2.distanceTo(obj3);

        // Вычисление полупериметра
        double p = (a + b + c) / 2;
        // Вычисление площади треугольника с помощью формулы Герона
        double S = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        // Возврат вычисленной площади
        return S;
    }
}
