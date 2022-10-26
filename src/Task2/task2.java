package Task2;

import java.util.Arrays;

public class task2 {
    public static void main(String[] args) {
        System.out.println("1.\n");
        System.out.println(Repeat("mice", 5));
        System.out.println(Repeat("hello", 3));
        System.out.println(Repeat("stop", 1));
        System.out.println("\n2.\n");
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[]{44, 32, 86, 19}));
        System.out.println("\n3.\n");
        System.out.println(isAvgWhole(new int[]{1, 3}));
        System.out.println(isAvgWhole(new int[]{1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[]{1, 5, 6}));
        System.out.println(isAvgWhole(new int[]{1, 1, 1}));
        System.out.println(isAvgWhole(new int[]{9, 2, 2, 5}));
        System.out.println("\n4.\n");
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, -2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println("\n5.\n");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));
        System.out.println("\n6.\n");
        System.out.println(Fibonacci(3));
        System.out.println(Fibonacci(7));
        System.out.println(Fibonacci(12));
        System.out.println("\n7.\n");
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939"));
        System.out.println("\n8.\n");
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
        System.out.println("\n9.\n");
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println("\n10.\n");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
    }

    public static String Repeat(String a, int n) {
        String b = "";
        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < n; j++)
                b += a.charAt(i);
        return b;
    }

    public static int differenceMaxMin(int[] a) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i])
                max = a[i];
            if (min > a[i])
                min = a[i];
        }
        return max - min;
    }

    public static boolean isAvgWhole(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return (sum % a.length == 0);
    }

    public static int[] cumulativeSum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] += sum;
            sum = a[i];
        }
        return a;
    }

    public static int getDecimalPlaces(String a) {
        int dot = a.indexOf(".");
        if (dot == -1)
            return 0;
        else
            return a.length() - dot - 1;
    }

    public static int Fibonacci(int a) {
        int n1 = 0;
        int n2 = 1;
        int n;
        for (int i = 0; i < a; i++) {
            n = n2;
            n2 += n1;
            n1 = n;
        }
        return n2;
    }

    public static boolean isValid(String a) {
        if (a.length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(a.charAt(i)))
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    public static boolean isStrangePair(String a, String b) {
        if (a.length() == 0 && b.length() == 0)
            return true;
        else
            return a.charAt(0) == b.charAt(b.length() - 1) && a.charAt(a.length() - 1) == b.charAt(0);
    }

    public static boolean isPrefix(String word, String prefix) {
        return word.substring(0, prefix.length() - 1).equals(prefix.replace("-", ""));
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.substring(word.length() - suffix.length() + 1, word.length()).equals(suffix.replace("-", ""));
    }

    public static int boxSeq(int a) {
        int n = 0;
        for (int i = 1; i <= a; i++) {
            if (i % 2 == 0)
                n -= 1;
            else
                n += 3;
        }
        return n;
    }
}
