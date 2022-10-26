package Lab1;

public class Primes {                       // Проверка является ли число простым
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i))                 // Если число - простое
                System.out.print(i + " ");      // Вывести его
        }
    }
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)                 // Если число делится нацело
                return false;               // Вернуть false
        return true;                        // Иначе вернуть true
    }
}
