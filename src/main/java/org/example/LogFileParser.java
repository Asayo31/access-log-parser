package org.example;
import java.io.File;
import java.util.Scanner;

public class LogFileParser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fileCounter = 0; // Счетчик верно указанных файлов

        // Бесконечный цикл
        while (true) {
            System.out.println("Введите путь к файлу:");
            String path = scanner.nextLine();

            // Создаем объект File на основе введенного пути
            File file = new File(path);

            // Проверяем, существует ли файл и является ли он файлом (а не директорией)
            boolean fileExists = file.exists();      // Существует ли файл
            boolean isFile = file.isFile();          // Является ли это именно файлом, а не директорией

            // Проверка на существование и тип файла
            if (!fileExists || !isFile) {
                System.out.println("Указанный путь не существует или это не файл. Попробуйте снова.");
                continue; // Возвращаемся в начало цикла
            }

            // Если файл существует и является файлом, выводим сообщение
            fileCounter++; // Увеличиваем счетчик
            System.out.println("Путь указан верно. Это файл номер " + fileCounter);
        }
    }
}
