package Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class CrawlerTask implements Runnable 
{
    URLPool urlPool;
    public static final String URL_PREFIX = "http://";

    
    public CrawlerTask(URLPool pool) 
    {
        this.urlPool = pool;
    }
    
  // Отправка http запроса
    public static void request(PrintWriter out,URLDepthPair pair) 
    {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }
    
    // Метод для посимвольного считывания строки и записи найденного URL
    public static void buildNewUrl(String str, int depth, URLPool pool) throws MalformedURLException
    {
        try 
        {
            int end_of_link = str.indexOf("\"", str.indexOf(URL_PREFIX));
            if (end_of_link == -1 || (str.indexOf("'", str.indexOf(URL_PREFIX)) != -1 && str.indexOf("'", str.indexOf(URL_PREFIX)) < end_of_link)) 
            {
                end_of_link = str.indexOf("'", str.indexOf(URL_PREFIX));
            }
            if (end_of_link == -1 || (str.indexOf("<", str.indexOf(URL_PREFIX)) - 1 != -1 && str.indexOf("<", str.indexOf(URL_PREFIX)) - 1 < end_of_link)) 
            {
                end_of_link = str.indexOf("<", str.indexOf(URL_PREFIX)) - 1;
            }
            String currentLink = str.substring(str.indexOf(URL_PREFIX), end_of_link);
            pool.addPair(new URLDepthPair(currentLink, depth + 1));
        } 
        catch (StringIndexOutOfBoundsException e) 
        {
        	
        }
    }
    
    @Override
    public void run() 
    {
        while (true) 
        {
        	// Получаем пару сайта, с которым будем работать
            URLDepthPair currentPair = urlPool.getPair();
            try 
            {
            	// Инициализируем сокет и отправляем запрос
                Socket socket = new Socket(currentPair.getHost(), 80);
                socket.setSoTimeout(1000);
                try 
                {
                	// Реализуем поток ввода (для чтения строк из сокета)
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    // Реализуем поток вывода (для отправки http запроса сокету)
                    BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // Отправляем запрос
                    request(out, currentPair);
                    String line;
                    // Пока сервер не завершил отправку веб-страницы
                    while ((line = in.readLine()) != null)
                    {
                    	// Если строка содержит URL
                        if (line.indexOf(currentPair.URL_PREFIX)!=-1) 
                        {
                        	// Посимвольно считываем строки и записываем найденный URL
                            buildNewUrl(line,currentPair.getDepth(),urlPool);
                        }
                    }
                    // Закрываем сокет
                    socket.close();
                } 
                catch (SocketTimeoutException e) 
                {
                    socket.close();
                }
            }
            catch (IOException e) 
            {
            	
            }
        }
    }
}