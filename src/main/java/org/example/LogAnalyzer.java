package org.example;

import java.io.*;

public class LogAnalyzer {
    public static void main(String[] args) {
        String filePath = "/mnt/data/access.log"; // Укажите путь к файлу

        // Переменные для анализа
        int totalLines = 0;
        int longestLine = 0;
        int shortestLine = Integer.MAX_VALUE;

        try {
            File file = new File(filePath);

            // Проверка существования файла и его типа
            if (!file.exists() || !file.isFile()) {
                throw new FileNotFoundException("Файл не найден или это не файл: " + filePath);
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    totalLines++;

                    // Проверка длины строки
                    if (length > 1024) {
                        throw new LineTooLongException("Строка слишком длинная: " + length + " символов");
                    }

                    // Обновление данных
                    longestLine = Math.max(longestLine, length);
                    shortestLine = Math.min(shortestLine, length);
                }
            }

            // Вывод результатов
            System.out.println("Общее количество строк: " + totalLines);
            System.out.println("Длина самой длинной строки: " + longestLine);
            System.out.println("Длина самой короткой строки: " + shortestLine);

        } catch (LineTooLongException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

