package Lab8;

import java.net.MalformedURLException;
import java.util.LinkedList;

public class Crawler 
{
	// Выводит ссылку и её глубину
    public static void showResult(LinkedList<URLDepthPair> resultLink) 
    {
        for (URLDepthPair c : resultLink)
        {
            System.out.println("Depth: " + c.getDepth() + "\tURL: " + c.getURL());
        }
    }
    
    // Проверяем введённые данные на то, что они являются числом
    public static boolean checkDigit(String line)
    {
        boolean isDigit = true;
        for (int i = 0; i < line.length() && isDigit; i++)
        {
            isDigit = Character.isDigit(line.charAt(i));
        }
        return isDigit;
    }

    public static void main(String[] args) throws MalformedURLException 
    {
    	// Вводим ссылку, глубину и количество потоков
        args = new String[]{"http://www.consultant.ru/", "4", "100"};
        if (args.length == 3 && checkDigit(args[1]) && checkDigit(args[2]))
        {
            String lineUrl = args[0];
            int numThreads = Integer.parseInt(args[2]);
            // Инициализируем пул адресов
            URLPool pool = new URLPool(Integer.parseInt(args[1]));
            // Формируем введённый пользователем элемент как начальный с глубиной = 0 и добавляем в пул
            pool.addPair(new URLDepthPair(lineUrl, 0));
            // Запускаем потоки
            for (int i = 0; i < numThreads; i++) 
            {
            	// Передаём пул адресов в каждый созданный поток
                CrawlerTask c = new CrawlerTask(pool);
                Thread t = new Thread(c);
                t.start();
            }
            // Ожидаем завершения работы всех потоков
            while (pool.getWait() != numThreads) 
            {
                try 
                {
                    Thread.sleep(500);
                } 
                catch (InterruptedException e) 
                {
                    System.out.println("Игнорирование InterruptedException");
                }
            }
            try 
            {
            	// Выводим результат в консоль
                showResult(pool.getResult());
            } 
            catch (NullPointerException e) 
            {
                System.out.println("Не ссылка");
            }
            System.exit(0);
        } 
        else 
        {
        	// Выводим ошибку некорректно введённого значения
            System.out.println("usage: java Crawler <URL> <depth> <threads>");
        }
    }
}