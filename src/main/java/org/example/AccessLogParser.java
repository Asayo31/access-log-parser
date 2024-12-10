package org.example;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AccessLogParser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Укажите путь к файлу как аргумент командной строки.");
            return;
        }

        String path = args[0];
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Файл не найден или путь указывает на папку.");
            return;
        }

        int totalLines = 0;
        int googleBotCount = 0;
        int yandexBotCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (line.length() > 1024) {
                    throw new RuntimeException("Обнаружена строка длиной более 1024 символов.");
                }

                // Парсинг строки, выделение User-Agent
                String userAgent = extractUserAgent(line);
                if (userAgent != null) {
                    String program = parseUserAgent(userAgent);
                    if ("Googlebot".equalsIgnoreCase(program)) {
                        googleBotCount++;
                    } else if ("YandexBot".equalsIgnoreCase(program)) {
                        yandexBotCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Расчёт и вывод долей запросов
        if (totalLines > 0) {
            double googleBotShare = (double) googleBotCount / totalLines * 100;
            double yandexBotShare = (double) yandexBotCount / totalLines * 100;
            System.out.printf("Доля запросов Googlebot: %.2f%%%n", googleBotShare);
            System.out.printf("Доля запросов YandexBot: %.2f%%%n", yandexBotShare);
        } else {
            System.out.println("Файл пуст или не содержит валидных данных.");
        }
    }

    // Метод для выделения User-Agent из строки
    private static String extractUserAgent(String line) {
        int start = line.indexOf("\"");
        int end = line.lastIndexOf("\"");
        if (start != -1 && end > start) {
            return line.substring(start + 1, end);
        }
        return null;
    }

    // Метод для парсинга User-Agent и выделения программы
    private static String parseUserAgent(String userAgent) {
        int openBracket = userAgent.indexOf('(');
        int closeBracket = userAgent.indexOf(')');
        if (openBracket != -1 && closeBracket > openBracket) {
            String firstBrackets = userAgent.substring(openBracket + 1, closeBracket);
            String[] parts = firstBrackets.split(";");
            if (parts.length >= 2) {
                String fragment = parts[1].trim(); // Очистка пробелов
                int slashIndex = fragment.indexOf('/');
                if (slashIndex != -1) {
                    return fragment.substring(0, slashIndex).trim();
                }
                return fragment.trim();
            }
        }
        return null;}

        /*предыдущая версия deprecated
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст и нажмите <Enter>: ");
        String input = scanner.nextLine();
        System.out.println("Длина введённого текста: " + input.length());
        */
        }
