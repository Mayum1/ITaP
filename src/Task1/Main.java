package Task1;

public class Main {
    public static void main(String[] args) {        // Проверка всех тасков с выводом значений
        System.out.println("1.\n");                 // Таск №1
        System.out.println(remainder(1,3));
        System.out.println(remainder(3,4));
        System.out.println(remainder(-9,45));
        System.out.println(remainder(5,5));
        System.out.println("\n2.\n");               // Таск №2
        System.out.println(triArea(3, 2));
        System.out.println(triArea(7,4));
        System.out.println(triArea(10,10));
        System.out.println("\n3.\n");               // Таск №3
        System.out.println(animals(2, 3, 5));
        System.out.println(animals(1,2,3));
        System.out.println(animals(5,2,8));
        System.out.println("\n4.\n");               // Таск №4
        System.out.println(profitGamble(0.2,50,9));
        System.out.println(profitGamble(0.9,1,2));
        System.out.println(profitGamble(0.9,3,2));
        System.out.println("\n5.\n");               // Таск №5
        System.out.println(operation(24, 15, 9));
        System.out.println(operation(24,26,2));
        System.out.println(operation(15,11,11));
        System.out.println("\n6.\n");               // Таск №6
        System.out.println(ctoa('A'));
        System.out.println(ctoa('m'));
        System.out.println(ctoa('['));
        System.out.println(ctoa('\\'));
        System.out.println("\n7.\n");               // Таск №7
        System.out.println(addUpTo(3));
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7));
        System.out.println("\n8.\n");               // Таск №8
        System.out.println(nextEdge(8, 10));
        System.out.println(nextEdge(5,7));
        System.out.println(nextEdge(9,2));
        System.out.println("\n9.\n");               // Таск №9
        System.out.println(sumOfCubes(new int[]{1, 5, 9}));
        System.out.println(sumOfCubes(new int[]{3, 4, 5}));
        System.out.println(sumOfCubes(new int[]{2}));
        System.out.println(sumOfCubes(new int[]{}));
        System.out.println("\n10.\n");              // Таск №10
        System.out.println(abcmath(42,5,10));
        System.out.println(abcmath(5,2,1));
        System.out.println(abcmath(1,2,3));
    }
    public static int remainder(int a, int b) {     // Возврат остатка от деления
        return a % b;
    }
    public static int triArea(int a, int b) {       // Возврат площади треугольника
        return a * b / 2;
    }
    public static int animals(int chickens, int cows, int pigs) {   // Возврат количества ног всех животных
        return chickens * 2 + cows * 4 + pigs * 4;
    }
    public static boolean profitGamble(double prob, int prize, int pay) {   // Возврат шанса выиграть через true/false
        return (prob * prize > pay);
    }
    public static String operation(int N, int a, int b) {       // Возврат действия над двумя числами
        if (a + b == N)
            return "added";
        else if (a - b == N) {
            return "subtracted";
        }
        else if (a * b == N) {
            return "multiplied";
        }
        else if (a / b == N) {
            return "divided";
        }
        else return "none";
    }
    public static int ctoa(char a) {        // Возврат значения ASCII символа
        return a;
    }
    public static int addUpTo(int a) {      // Вовзрат суммы всех чисел до переданного значения, включая его
        int n = 0;
        for (int i = 1; i <= a; i++)
            n += i;
        return n;
    }
    public static int nextEdge(int a, int b) {  // Вовзрат максимального значения третьего ребра треугольника
        return a + b - 1;
    }
    public static int sumOfCubes(int[] a) {     // Возврат суммы кубов массива
        int n = 0;
        for (int i = 0; i < a.length; i++) {
            n += a[i] * a[i] * a[i];
        }
        return n;
    }
    public static boolean abcmath(int a, int b, int c) {    // Возврат true/false в зависимости от результата
        for (int i = 1; i <= b; i++)
            a += a;
        return (a % c == 0);
    }
}