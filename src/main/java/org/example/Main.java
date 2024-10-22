package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос и ввод первого числа
        System.out.print("Введите первое число: ");
        int firstNumber = scanner.nextInt();

        // Запрос и ввод второго числа
        System.out.print("Введите второе число: ");
        int secondNumber = scanner.nextInt();

        // Закрытие сканера
        scanner.close();

        // Вычисление операций
        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int product = firstNumber * secondNumber;
        double quotient = (double) firstNumber / secondNumber;

        // Вывод результатов
        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + difference);
        System.out.println("Произведение: " + product);
        System.out.println("Частное: " + quotient);
    }
}

