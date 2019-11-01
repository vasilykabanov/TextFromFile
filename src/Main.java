import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by yasup on 31.10.2019.
 */

public class Main {

    private static String fileName = "C:\\Users\\yasup\\Desktop\\file.txt"; // путь к файлу
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File file = new File(fileName);

        //Main.read("no_file.txt"); //Попытка прочитать несуществующий файл (проверка)

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println("Input text: ");
        String text = scanner.nextLine();

        Main.write(fileName, text);
        String textFromFile = Main.read(fileName);

        Main.countWords();

    }
    private static void countWords () throws FileNotFoundException {
        scanner = new Scanner(new File(fileName));
        Map<String, Integer> hashCount = new TreeMap<>();
        while (scanner.hasNext()) {
            String s = scanner.next().replaceAll("\\p{Punct}", "");
            Integer count = hashCount.get(s);
            if (count == null) {
                count = 0;
            }
            hashCount.put(s, ++count);
        }
        int maxQuantity = 0;
        String maxQuantityStr = "";
        for (Map.Entry<String, Integer> entry : hashCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            if (entry.getValue() > maxQuantity) {
                maxQuantity = entry.getValue();
                maxQuantityStr = entry.getKey();
            }
        }
        System.out.println("Больше всего раз повторилось: " + maxQuantityStr + ":  " + maxQuantity);
    }

    private static void write (String fileName, String text) throws FileNotFoundException{
        // Определяем файл
        File file = new File(fileName);
        try{
            //PrintWriter обеспечит возможности записи в файл
            PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());
            try{
                //записываем текст файла
                printWriter.print(text);
            } finally {
                //закрываем файл обязательно
                printWriter.close();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static String read (String fileName) throws FileNotFoundException {

        StringBuilder stringBuilder = new StringBuilder();

        //проверка на существование файла
        exists(fileName);

        try{
            //Объект для чтения файла в буфер
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            try{
                //В цикле построчно считываем файл
                String s; //объявляем переменную, куда поместим считываемые сторки с файла
                while ((s = bufferedReader.readLine()) != null) { //выполнять пока есть что считывать
                    stringBuilder.append(s + "\n");
                }
            } finally {
                //закрываем считыватель
                bufferedReader.close();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return stringBuilder.toString();
    }

    private static void exists(String fileName) throws FileNotFoundException{
        File file = new File(Main.fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
