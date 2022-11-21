package Lab1;

public class Palindrome {                           // Проверка слова на палиндром
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];                     // Присвоить строке s аргумент i
            System.out.println(isPalindrome(s));    // Вывод true/false в зависимости палиндром или нет
        }
    }
    public static String reverseString(String s) {
        String revS = "";                           // Создание пустой строки
        for (int i = s.length() - 1; i >= 0; i--)   // Цикл от последней буквы слова до первой
            revS += s.charAt(i);                    // Добавить текущую букву к строке revS
        return revS;                                // Возвратить обратное слово
    }
    public static boolean isPalindrome(String s) {
        String revS = reverseString(s);             // Создание строки с обратным словом
        if (s.equals(revS))                         // Если слово равно обратному слову
            return true;                            // вернуть true
        else return false;                          // Иначе вернуть false
    }
}