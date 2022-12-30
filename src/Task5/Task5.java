package Task5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("1.\n");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println("\n2.\n");
        System.out.println(canMove(new String[]{"Rook", "A8", "H8"}));
        System.out.println(canMove(new String[]{"Bishop", "A7", "G1"}));
        System.out.println(canMove(new String[]{"Queen", "C4", "D6"}));
        System.out.println("\n3.\n");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("\n4.\n");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("\n5.\n");
        System.out.println(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"}));
        System.out.println("\n6.\n");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("\n7.\n");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909));
        System.out.println("\n8.\n");
        try {
            System.out.println(getSha256Hash("password123"));
            System.out.println(getSha256Hash("Fluffy@home"));
            System.out.println(getSha256Hash("Hey dude!"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n9.\n");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("\n10.\n");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
    }

    private static int[] encrypt(String s) {
        char[] c = s.toCharArray();
        int[] mass = new int[s.length()];
        mass[0] = c[0];
        for (int i = 1; i < c.length; i++)
            mass[i] = c[i]-c[i-1];
        return mass;
    }

    private static String decrypt(int[] a) {
        String s = "" + (char) a[0];
        int k = a[0];
        for (int i = 1; i < a.length; i++) {
            s += (char) (a[i] + k);
            k += a[i];
        }
        return s;
    }

    private static boolean canMove(String[] s) {
        char c1 = s[1].charAt(0);
        char c2 = s[2].charAt(0);
        int a1 = Integer.parseInt(Character.toString(s[1].charAt(1)));
        int a2 = Integer.parseInt(Character.toString(s[2].charAt(1)));
        if (s[0] == "Pawn")
            return (a1 == 7 && a2 == 5 && c1 == c2) || (a1 == 2 && a2 == 4 && c1 == c2) || (c1 == c2 && (a1 + 1 == a2 || a1 - 1 == a2));
        if (s[0] == "Knight")
            return ((int) c1 == (int) c2 - 1 || (int) c1 == (int) c2 + 1) && (a1 + 2 == a2 || a1 - 2 == a2) ||
                    ((int) c1 == (int) c2 - 2 || (int) c1 == (int) c2 + 2) && (a1 + 1 == a2 || a1 - 1 == a2);
        if (s[0] == "Bishop")
            return Math.abs((int) c1 - (int) c2) == Math.abs(a1 - a2);
        if (s[0] == "Rook")
            return (c1 == c2) || (a1 == a2);
        if (s[0] == "Queen")
            return (c1 == c2) || (a1 == a2) || (Math.abs((int) c1 - (int) c2) == Math.abs(a1 - a2));
        if (s[0] == "King")
            return Math.abs((int) c1 - (int) c2) <= 1 && Math.abs(a1 - a2) <= 1;
        return false;
    }

    private static boolean canComplete(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        String s = "";
        boolean flag = false;
        for (int i = 0; i < c2.length; i++) {
            for (int j = 0; j < c1.length; j++)
                if (c2[i] == c1[j]) {
                    flag = true;
                    c1[j] = ' ';
                    break;
                }
            if (flag)
                s += c2[i];
            flag = false;
        }

        return s.equals(s1);
    }

    private static int sumDigProd(int ... nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++)
            a += nums[i];
        char[] c = Integer.toString(a).toCharArray();
        while (c.length != 1) {
            a = 1;
            for (int i = 0; i < c.length; i++)
                a *= Integer.parseInt(Character.toString(c[i]));
            c = Integer.toString(a).toCharArray();
        }
        return a;
    }

    private static ArrayList<String> sameVowelGroup(String[] a) {
        ArrayList<String> result = new ArrayList<>();
        String[] b = new String[a.length];
        result.add(a[0]);
        for (int i = 0; i < a.length; i++)
            b[i] = a[i].replaceAll("[^eyuioa]", "");
        char[] c = b[0].toCharArray();
        boolean flag = true;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < c.length; j++) {
                if (!b[i].contains(Character.toString(c[j]))) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                result.add(a[i]);
            flag = true;
        }
        return result;
    }

    private static boolean validateCard(long a) {
        char[] c = Long.toString(a).toCharArray();
        int check = Integer.parseInt(Character.toString(c[c.length - 1]));
        String s = "";
        for (int i = c.length - 2; i >= 0; i--)
            s += c[i];
        c = s.toCharArray();
        int[] b = new int[c.length];
        int n;
        int sum = 0;
        for (int i = 0; i < c.length; i++) {
            n = Integer.parseInt(Character.toString(c[i]));
            if (i % 2 == 0) {
                if (n < 5)
                    b[i] = n * 2;
                else
                    b[i] = (n - 5) * 2 + 1;
                sum += b[i];
            }
            else {
                b[i] = n;
                sum += b[i];
            }
        }
        return 10 - (sum % 10) == check;
    }

    private static String numToEng(int a) {
        StringBuilder b = new StringBuilder();
        if (a == 0)
            return "zero";
        if (a / 100 > 0) {
            switch (a / 100) {
                case 1 -> b.append("one hundred ");
                case 2 -> b.append("two hundred ");
                case 3 -> b.append("three hundred ");
                case 4 -> b.append("four hundred ");
                case 5 -> b.append("five hundred ");
                case 6 -> b.append("six hundred ");
                case 7 -> b.append("seven hundred ");
                case 8 -> b.append("eight hundred ");
                case 9 -> b.append("nine hundred ");
            }
            a %= 100;
        }
        if (a / 10 > 0) {
            if (a / 10 == 1) {
                switch (a % 10) {
                    case 0 -> b.append("ten");
                    case 1 -> b.append("eleven");
                    case 2 -> b.append("twelve");
                    case 3 -> b.append("thirteen");
                    case 4 -> b.append("fourteen");
                    case 5 -> b.append("fifteen");
                    case 6 -> b.append("sixteen");
                    case 7 -> b.append("seventeen");
                    case 8 -> b.append("eighteen");
                    case 9 -> b.append("nineteen");
                }
                return b.toString();
            }
            else {
                switch (a / 10) {
                    case 2 -> b.append("twenty ");
                    case 3 -> b.append("thirty ");
                    case 4 -> b.append("forty ");
                    case 5 -> b.append("fifty ");
                    case 6 -> b.append("sixty ");
                    case 7 -> b.append("seventy ");
                    case 8 -> b.append("eighty ");
                    case 9 -> b.append("ninety ");
                }
                a %= 10;
            }
        }
        if (a > 0) {
            switch(a) {
                case 1 -> b.append("one");
                case 2 -> b.append("two");
                case 3 -> b.append("three");
                case 4 -> b.append("four");
                case 5 -> b.append("five");
                case 6 -> b.append("six");
                case 7 -> b.append("seven");
                case 8 -> b.append("eight");
                case 9 -> b.append("nine");
            }
        }
        return b.toString();
    }

    private static String numToRus(int a) {
        StringBuilder b = new StringBuilder();
        if (a == 0)
            return "ноль";
        if (a / 100 > 0) {
            switch (a / 100) {
                case 1 -> b.append("сто ");
                case 2 -> b.append("двести ");
                case 3 -> b.append("триста ");
                case 4 -> b.append("четыреста ");
                case 5 -> b.append("пятьсот ");
                case 6 -> b.append("шестьсот ");
                case 7 -> b.append("семьсот ");
                case 8 -> b.append("восемьсот ");
                case 9 -> b.append("девятьсот ");
            }
            a %= 100;
        }
        if (a / 10 > 0) {
            if (a / 10 == 1) {
                switch (a % 10) {
                    case 0 -> b.append("десять");
                    case 1 -> b.append("одиннадцать");
                    case 2 -> b.append("двенадцать");
                    case 3 -> b.append("тринадцать");
                    case 4 -> b.append("четырнадцать");
                    case 5 -> b.append("пятнадцать");
                    case 6 -> b.append("шестнадцать");
                    case 7 -> b.append("семнадцать");
                    case 8 -> b.append("восемнадцать");
                    case 9 -> b.append("девятнадцать");
                }
                return b.toString();
            }
            else {
                switch (a / 10) {
                    case 2 -> b.append("двадцать ");
                    case 3 -> b.append("тридцать ");
                    case 4 -> b.append("сорок ");
                    case 5 -> b.append("пятьдесят ");
                    case 6 -> b.append("шестьдесят ");
                    case 7 -> b.append("семьдесят ");
                    case 8 -> b.append("восемьдесят ");
                    case 9 -> b.append("девяносто ");
                }
                a %= 10;
            }
        }
        if (a > 0) {
            switch(a) {
                case 1 -> b.append("один");
                case 2 -> b.append("два");
                case 3 -> b.append("три");
                case 4 -> b.append("четыре");
                case 5 -> b.append("пять");
                case 6 -> b.append("шесть");
                case 7 -> b.append("семь");
                case 8 -> b.append("восемь");
                case 9 -> b.append("девять");
            }
        }
        return b.toString();
    }

    private static String getSha256Hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String correctTitle(String s) {
        StringBuilder a = new StringBuilder();
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '-' || s.charAt(i) == '.') {
                word += s.charAt(i);
                words.add(word.toLowerCase());
                word = "";
            }
            else word += s.charAt(i);
        }
        for (String c : words) {
            if (c.equals("and ") || c.equals("the ") || c.equals("of ") || c.equals("in "))
                a.append(c);
            else {
                c = Character.toUpperCase(c.charAt(0)) + c.substring(1, c.length());
                a.append(c);
            }
        }
        return a.toString();
    }

    private static String hexLattice(int n) {
        int i = 1;
        int k = 0;
        while (n > k) {
            k = 3 * i * (i - 1) + 1;
            i++;
        }
        if (n != k)
            return "Invalid";
        StringBuilder b = new StringBuilder();
        k = 1;
        i--;
        while (k <= i) {
            if (k < i) {
                b.append(" ".repeat(i - k));
                b.append("o ".repeat(i - 1 + k));
            }
            else {
                b.append("o ".repeat(2 * i - 1));
            }
            b.append("\n");
            k++;
        }
        k = i - 1;
        while (k > 0) {
            b.append(" ".repeat(i - k));
            b.append("o ".repeat(i - 1 + k));
            b.append("\n");
            k--;
        }
        return b.toString();
    }
}
