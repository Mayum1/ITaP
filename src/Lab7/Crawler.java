package Lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

public class Crawler 
{
    static LinkedList<URLDepthPair> findLink = new LinkedList<URLDepthPair>();
    static LinkedList<URLDepthPair> viewedLink = new LinkedList<URLDepthPair>();

	// Выводит ссылку и её глубину
    public static void showResult(LinkedList<URLDepthPair> viewedLink)
    {
        for (URLDepthPair c : viewedLink)
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

    public static void request(PrintWriter out, URLDepthPair pair) throws MalformedURLException
    {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }

    public static void Process(String pair, int maxDepth) throws IOException {
        findLink.add(new URLDepthPair(pair, 0));
        while (!findLink.isEmpty()) {
            URLDepthPair currentPair = findLink.removeFirst();
            if (currentPair.depth < maxDepth) {
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
                    while ((line = in.readLine()) != null) {
                        // Если строка содержит URL
                        if (line.indexOf(currentPair.URL_PREFIX) != -1 && line.indexOf('"') != -1) {
                            StringBuilder currentLink = new StringBuilder();
                            int i = line.indexOf(currentPair.URL_PREFIX);
                            while (line.charAt(i) != '"' && line.charAt(i) != ' ') {
                                if (line.charAt(i) == '<') {
                                    currentLink.deleteCharAt(currentLink.length() - 1);
                                    break;
                                }
                                else {
                                    currentLink.append(line.charAt(i));
                                    i++;
                                }
                            }

                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.depth + 1);

                            if (currentPair.check(findLink, newPair) && currentPair.check(viewedLink, newPair) && !currentPair.URL.equals(newPair.URL)) {
                                findLink.add(newPair);
                            }
                        }
                    }
                    // Закрываем сокет
                    socket.close();
                }
                catch (SocketTimeoutException e) {
                    socket.close();
                }
            }
            viewedLink.add(currentPair);
        }
        showResult(viewedLink);
    }

    public static void main(String[] args)
    {
    	// Вводим ссылку и глубину
        args = new String[]{"http://consultant.ru/", "4"};
        if (args.length == 2 && checkDigit(args[1])) {
            try {
                Process(args[0], Integer.parseInt(args[1]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("usage: java Crawler <URL> <depth>");
        }
    }
}