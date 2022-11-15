package Task3;

public class task3 {
    public static void main(String[] args) {
        System.out.println("1.\n");
        System.out.println(solutions(1, 0, -1));        // 2
        System.out.println(solutions(1,0,0));           // 1
        System.out.println(solutions(1,0,1));           // 0
        System.out.println("\n2.\n");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println("\n3.\n");
        System.out.println(checkPerfect(6));        // true
        System.out.println(checkPerfect(28));       // true
        System.out.println(checkPerfect(496));      // true
        System.out.println(checkPerfect(12));       // false
        System.out.println(checkPerfect(97));       // false
        System.out.println("\n4.\n");
        System.out.println(flipEndChars("Cat, dog, and mouse."));   // .at, dog, and mouseC
        System.out.println(flipEndChars("ada"));                    // Two's a pair.
        System.out.println(flipEndChars("Ada"));                    // adA
        System.out.println(flipEndChars("z"));                      // Incompatible.
        System.out.println("\n5.\n");
        System.out.println(isValidHexCode("#CD5C5C"));      // true
        System.out.println(isValidHexCode("#EAECEE"));      // true
        System.out.println(isValidHexCode("#eaecee"));      // true
        System.out.println(isValidHexCode("#CD5C58C"));     // false: Length exceeds 6
        System.out.println(isValidHexCode("#CD5C5Z"));      // false: Not all alphabetic characters in A-F
        System.out.println(isValidHexCode("#CD5C&C"));      // false: Contains unacceptable character
        System.out.println(isValidHexCode("CD5C5C"));       // false: Missing #
        System.out.println("\n6.\n");
        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));     // true: {1, 3, 4}, {2, 5, 7}
        System.out.println(same(new int[]{9, 8, 7 ,6}, new int[]{4, 4, 3, 1}));     // false: {9, 8, 7, 6}, {4, 3, 1}
        System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}));           // true: {2}, {3}
        System.out.println("\n7.\n");
        System.out.println(isKaprekar(0));      // true: 0 = 0 + 0
        System.out.println(isKaprekar(1));      // true: 1 = 0 + 1
        System.out.println(isKaprekar(3));      // false: 3 != 0 + 9
        System.out.println(isKaprekar(5));      // false: 5 != 2 + 5
        System.out.println(isKaprekar(297));    // true: 297 = 88 + 209
        System.out.println("\n8.\n");
        System.out.println(longestZero("01100001011000"));      // 0000
        System.out.println(longestZero("100100100"));           // 00
        System.out.println(longestZero("11111"));               // ""
        System.out.println("\n9.\n");
        System.out.println(nextPrime(12));      // 13
        System.out.println(nextPrime(24));      // 29
        System.out.println(nextPrime(11));      // 11
        System.out.println("\n10.\n");
        System.out.println(rightTriangle(3, 4, 5));         // true: 25 == 25
        System.out.println(rightTriangle(145, 105, 100));   // true: 21025 == 21025
        System.out.println(rightTriangle(70, 130, 110));    // false: 17000 != 16900
    }

    public static int solutions(int a, int b, int c) {
        int D = b * b - 4 * a * c;
        if (D > 0)
            return 2;
        else if (D == 0)
            return 1;
        else
            return 0;
    }

    public static int findZip(String a) {
        return a.indexOf("zip", a.indexOf("zip")+1);
    }

    public static boolean checkPerfect(int a) {
        int sum = 0;
        for (int i = 1; i < a; i++)
            if (a % i == 0)
                sum += i;
        return a == sum;
    }

    public static String flipEndChars(String a) {
        int lastChar = a.length() - 1;
        if (a.length() < 2)
            return "Incompatible.";
        if (a.charAt(0) == a.charAt(lastChar))
            return "Two's a pair.";
        return a.charAt(lastChar) + a.substring(1, lastChar) + a.charAt(0);
    }

    public static boolean isValidHexCode(String a) {
        a = a.toLowerCase();
        String hexAlphabet = "0123456789abcdef";
        if (a.charAt(0) == '#' && a.length() == 7) {
            for (int i = 1; i < a.length(); i++)
                if (hexAlphabet.indexOf(a.charAt(i)) == -1)
                    return false;
            return true;
        }
        return false;
    }

    public static boolean same(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        for (int i = 0; i < arr1.length; i++)
            for (int j = i + 1; j < arr1.length; j++)
                if (arr1[i] == arr1[j]) {
                    n1--;
                    break;
                }
        for (int i = 0; i < arr2.length; i++)
            for (int j = i + 1; j < arr2.length; j++)
                if (arr2[i] == arr2[j]) {
                    n2--;
                    break;
                }
        return n1 == n2;
    }

    public static boolean isKaprekar(int a) {
        String aStr = a * a + "";
        int numRight = aStr.length() - Math.floorDiv(aStr.length(), 2);
        int aLeft = a * a / (int) Math.pow(10, numRight);
        int aRight = a * a % (int) Math.pow(10, numRight);
        return aLeft + aRight == a;
    }

    public static String longestZero(String a) {
        int k = 0;
        int max = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0')
                k++;
            else k = 0;
            if (max < k)
                max = k;
        }
        return "0".repeat(max);
    }

    public static int nextPrime(int a) {
        int i = a;
        while (true) {
            int k = 0;
            for (int j = 2; j <= i; j ++)
                if (i % j == 0)
                    k++;
            if (k == 1)
                return i;
            i++;
        }
    }

    public static boolean rightTriangle(int x, int y, int z) {
        if (x > y && x > z)
            return x * x == y * y + z * z;
        if (y > x && y > z)
            return y * y == x * x + z * z;
        return z * z == x * x + y * y;
    }
}
