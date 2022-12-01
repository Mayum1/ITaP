package Task4;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("1.\n");
        System.out.println(essay(10,7,"hello my name is Bessie and this is my essay"));
        System.out.println("\n2.\n");
        System.out.println(Arrays.toString(split("()()()")));
        System.out.println(Arrays.toString(split("((()))")));
        System.out.println(Arrays.toString(split("((()))(())()()(()())")));
        System.out.println(Arrays.toString(split("((())())(()(()()))")));
        System.out.println("\n3.\n");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println("\n4.\n");
        System.out.println(overTime(new double[]{9, 17, 30, 1.5}));
        System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
        System.out.println(overTime(new double[]{9, 5, 30, 1.5}));
        System.out.println("\n5.\n");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println("\n6.\n");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println("\n7.\n");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println("\n8.\n");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println("\n9.\n");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println("\n10.\n");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));

    }
    public static String essay(int n, int k, String s) {
        int i = 0;
        int count = 0;
        String newS = "";
        String word = "";
        while (i < n - 1) {
            word = s.substring(0, s.indexOf(' '));
            s = s.substring(word.length() + 1);
            i++;
            count += word.length();
            if (count <= k)
                newS += word + ' ';
            else {
                newS += "\n" + word + ' ';
                count = word.length();
            }
        }
        if (count + s.length() <= k)
            return newS + s;
        else
            return newS + "\n" + s;
    }

    public static String[] split(String s) {
        String[] a = new String[5];
        int k = 0;
        int m = 0;
        int n = 0;
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                m++;
                word += '(';
            }
            else {
                n++;
                word += ')';
            }
            if (m == n) {
                a[k] = word;
                m = 0;
                n = 0;
                word = "";
                k++;
            }
        }
        return a;
    }

    public static String toCamelCase(String s) {
        String word = s.charAt(0) + "";
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '_')
                word += Character.toUpperCase(s.charAt(i));
            else
                word += s.charAt(i);
        }
        return word.replaceAll("_", "");
    }

    public static String toSnakeCase(String s) {
        for (int i = 0; i < s.length(); i++)
            if (Character.toUpperCase(s.charAt(i)) == s.charAt(i))
                s = s.replaceFirst(s.charAt(i) + "", "_" + Character.toLowerCase(s.charAt(i)));
        return s;
    }

    public static String overTime(double[] a) {
        double standartPay = 0;
        double overtimePay = 0;
        if (a[1] <= 9 && a[0] < 17 && a[0] > a[1])
            overtimePay = (7 + a[1]) * a[2] * (a[3] - 1);
        else if (a[1] <= 9 && a[0] < 17 && a[0] < a[1])
            overtimePay = (a[1] - a[0]) * a[2] * (a[3] - 1);
        else if (a[1] > 17)
            overtimePay = (a[1] - 17) * a[2] * (a[3] - 1);
        if (a[0] < a[1])
            standartPay = (a[1] - a[0]) * a[2];
        else
            standartPay = (24 - a[0] + a[1]) * a[2];
        return "$" + (standartPay + overtimePay);
    }

    public static String BMI(String weightS, String heightS) {
        double weightD = Double.parseDouble(weightS.split(" ")[0]);
        if (weightS.contains("pounds"))
            weightD *= 0.4535;
        double heightD = Double.parseDouble(heightS.split(" ")[0]);
        if (heightS.contains("inches"))
            heightD *= 0.0254;
        double BMI = weightD / (heightD * heightD);
        if (BMI < 18.5)
            return String.format("%.1f", BMI) + " Underweight";
        else if (BMI >= 25)
            return String.format("%.1f", BMI) + " Overweight";
        else
            return String.format("%.1f", BMI) + " Normal weight";
    }

    public static int bugger(int x) {
        int n = 0;
        while (Integer.toString(x).length() != 1) {
            char[] a = Integer.toString(x).toCharArray();
            x = 1;
            for (char i : a)
                x *= Integer.parseInt(Character.toString(i));
            n++;
        }
        return n;
    }

    public static String toStarShorthand(String s) {
        int k = 1;
        String word = "";
        char[] a = (s + " ").toCharArray();
        for (int i = 1; i <= s.length(); i++) {
            if (a[i] == a[i - 1]) {
                k += 1;
                continue;
            }
            else {
                if (k == 1)
                    word += a[i-1];
                else
                    word += a[i-1] + "*" + k;
                k = 1;
            }
        }
        return word;
    }

    public static boolean doesRhyme(String a, String b) {
        a = a.toLowerCase().substring(a.lastIndexOf(" ") + 1).replaceAll("[^eyuioa]", "");
        b = b.toLowerCase().substring(b.lastIndexOf(" ") + 1).replaceAll("[^eyuioa]", "");
        return a.equals(b);
    }

    public static boolean trouble(long num1, long num2) {
        int[] nums1 = new int[10];
        char[] s1 = Long.toString(num1).toCharArray();
        char[] s2 = Long.toString(num2).toCharArray();
        int n;
        for (int i = 2; i < s1.length; i++)
            if (s1[i] == s1[i-1] && s1[i] == s1[i-2]) {
                n = Integer.parseInt(Character.toString(s1[i]));
                nums1[n]++;
            }
        for (int i = 1; i < s2.length; i++)
            if (s2[i] == s2[i-1]) {
                n = Integer.parseInt(Character.toString(s2[i]));
                if (nums1[n] > 0)
                    return true;
            }
        return false;
    }

    public static String countUniqueBooks(String a, char b) {
        a = a.replaceAll(Character.toString(b), "1");
        return a;
        /*String[] group = a.split(Character.toString(b),-1);
        String unique = "";
        int i = 1;
        while (i < group.length - 1) {
            char[] chars = group[i].toCharArray();
            for (char ch : chars)
                if (unique.indexOf(ch) == -1)
                    unique += ch;
            i = i + 2;
        }
        return unique.length();*/
    }
}
