package org.example;

import java.util.Scanner;

public class AccessLogParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст и нажмите <Enter>: ");
        String input = scanner.nextLine();
        System.out.println("Длина введённого текста: " + input.length());
    }
}
