package Task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("1.\n");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println("\n2.\n");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("\n3.\n");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("\n4.\n");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
        System.out.println("\n5.\n");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println("\n6.\n");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("\n7.\n");
        System.out.println(longestNonRepeatingSubstring("abcabcbb"));
        System.out.println(longestNonRepeatingSubstring("aaaaaa"));
        System.out.println(longestNonRepeatingSubstring("abcde"));
        System.out.println(longestNonRepeatingSubstring("abcda"));
        System.out.println("\n8.\n");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println("\n9.\n");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("\n10.\n");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
    }
    private static int bell(int n) {
        int S = 0;
        for (int m = 1; m <= n; m++) {
            int b = 0;
            for (int i = 0; i <= m; i++)
                b += Math.pow(-1, m + i) * factorial(m) * Math.pow(i, n) / (factorial(i) * factorial(m - i));
            S += b / factorial(m);
        }
        return S;
    }
    private static int factorial(int x) {
        int s = 1;
        for (int i = 1; i <= x; i++)
            s *= i;
        return s;
    }

    private static String translateWord(String word) {
        if (word.equals(""))
            return "";
        if (word.charAt(0) == word.replaceAll("[^eEyYuUiIoOaA]", "").charAt(0))
            return word + "yay";
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).matches("[eyuioa]")) {
                if (word.charAt(0) == word.toUpperCase().charAt(0))
                    return word.substring(i, i + 1).toUpperCase() + word.substring(i + 1) + word.toLowerCase().substring(0, i) + "ay";
                else
                    return word.substring(i) + word.toLowerCase().substring(0, i) + "ay";
            }
        }
        return "";
    }

    private static String translateSentence(String sentence) {
        String word = "";
        String newSentence = "";
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.substring(i, i + 1).matches("[ -,.?!]")) {
                newSentence += translateWord(word) + sentence.charAt(i);
                word = "";
            }
            else
                word += sentence.charAt(i);
        }
        return newSentence;
    }

    private static boolean validColor(String s) {
        String[] str = s.substring(s.indexOf("(") + 1, s.indexOf(")")).split(",");
        if ((s.contains("rgba") && str.length == 4) || (s.contains("rgb") && str.length == 3)) {
            for (int i = 0; i < str.length; i++) {
                if (str[i].equals(""))
                    return false;
                if (i == 3 && str[i].contains(".")) {
                    double b = Double.parseDouble(str[i]);
                    return !(b < 0.0) && !(b > 1.0);
                }
                int a = Integer.parseInt(str[i]);
                if (a < 0 || a > 255)
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    private static String stripUrlParams(String url, String[] ... b) {
        if (!url.contains("?"))
            return url;
        else {
            StringBuilder res = new StringBuilder();
            String[] s = url.split("\\?");
            res.append(s[0]).append("?");
            String[] p = s[1].split("&");
            HashMap<String, String> map = new HashMap<>();
            for (String i : p) {
                String[] val = i.split("=");
                map.put(val[0], val[1]);
            }
            if (b.length != 0)
                for (String i : b[0])
                    map.remove(i);
            for (String i : map.keySet()) {
                res.append(i).append("=").append(map.get(i)).append("&");
            }
            res.deleteCharAt(res.lastIndexOf("&"));
            return res.toString();
        }
    }

    private static String[] getHashTags(String a) {
        a = a.replaceAll(",", "");
        String[] arr = a.split(" ");
        int k = 0;
        int maxLenght = 0;
        StringBuilder maxWord = new StringBuilder();
        StringBuilder res = new StringBuilder();
        if (arr.length < 3) {
            for (String word : arr)
                res.append("#").append(word.toLowerCase()).append(",");
        }
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j].length() != maxLenght) {
                        if(arr[j].length() >= maxLenght){
                            maxLenght = arr[j].length();
                            maxWord = new StringBuilder(arr[j].toLowerCase());
                            k = j;
                        }
                    }
                }
                arr[k] = " ";
                res.append("#").append(maxWord).append(",");
                maxLenght = 0;
            }
        }
        String[] resArr = res.toString().split(",");
        return resArr;
    }

    private static int ulam(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int i;
        int j;
        for (i = 3, j = 2; j < n; i++) {
            int m = 0;
            for (int k = 0; k < list.size() - 1; k++) {
                for (int l = k + 1; l < list.size(); l++) {
                    if (list.get(k) + list.get(l) == i)
                        m++;
                    if (m > 1)
                        break;
                }
                if (m > 1)
                    break;
            }
            if (m == 1) {
                list.add(i);
                j++;
            }
        }
        return i - 1;
    }

    private static String longestNonRepeatingSubstring(String a) {
        StringBuilder temp;
        String res = "";
        for (int i = 0; i < a.length() - 1; i++) {
            if (res.length() >= a.length() - i)
                break;
            temp = new StringBuilder();
            temp.append(a.charAt(i));
            for (int j = i + 1; j < a.length(); j++) {
                if (temp.indexOf(a.substring(j, j + 1)) == -1)
                    temp.append(a.charAt(j));
                else break;
            }
            if (temp.length() > res.length())
                res = temp.toString();
        }
        return res;
    }

    private static String convertToRoman(int n) {
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            if (n >= 1000) {
                n -= 1000;
                res.append("M");
            } else if (n >= 900) {
                n -= 900;
                res.append("CM");
            } else if (n >= 500) {
                n -= 500;
                res.append("D");
            } else if (n >= 400) {
                n -= 400;
                res.append("CD");
            } else if (n >= 100) {
                n -= 100;
                res.append("C");
            } else if (n >= 90) {
                n -= 90;
                res.append("XC");
            } else if (n >= 50) {
                n -= 50;
                res.append("L");
            } else if (n >= 40) {
                n -= 40;
                res.append("XL");
            } else if (n >= 10) {
                n -= 10;
                res.append("X");
            } else if (n == 9) {
                n -= 9;
                res.append("IX");
            } else if (n >= 5) {
                n -= 5;
                res.append("V");
            } else if (n == 4) {
                n -= 4;
                res.append("IV");
            } else {
                n -= 1;
                res.append("I");
            }
        }
        return res.toString();
    }

    private static boolean formula(String s) {
        String[] a = s.split("=");
        boolean flag = false;
        for (int i = 0; i < a.length - 1; i++) {
            int k = calculate(a[i]);
            if (k == calculate(a[i + 1]))
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    private static int calculate(String i) {
        if (i.charAt(0) == ' ')
            i = i.substring(1);
        String[] b = i.split(" ");
        if (b.length < 3)
            return Integer.parseInt(b[0]);
        if (b[1].equals("*"))
            return Integer.parseInt(b[0]) * Integer.parseInt(b[2]);
        if (b[1].equals("/"))
            return Integer.parseInt(b[0]) / Integer.parseInt(b[2]);
        if (b[1].equals("+"))
            return Integer.parseInt(b[0]) + Integer.parseInt(b[2]);
        else
            return Integer.parseInt(b[0]) - Integer.parseInt(b[2]);
    }

    private static boolean palindromeDescendant(int n) {
        boolean palindrome = false;
        while (n > 9) {
            if (palindrome(n)) {
                palindrome = true;
                break;
            }
            n = sumDigits(n);
        }
        return palindrome;
    }

    public static boolean palindrome(int num) {
        int reverse = 0;
        int n = num;
        if (n < 0)
            n *= -1;
        while (n != 0) {
            reverse *= 10;
            reverse += n % 10;
            n /= 10;
        }
        return (reverse == num);
    }

    public static int sumDigits(int n) {
        String strN = Integer.toString(n);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < strN.length() - 1; i += 2) {
            int sum = Integer.parseInt(Character.toString(strN.charAt(i))) + Integer.parseInt(Character.toString(strN.charAt(i+1)));
            res.append(Integer.toString(sum));
        }
        return Integer.parseInt(res.toString());
    }
}
