package com.universalsorter.app;
import com.universalsorter.utils.ConsoleMenu;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        try {
            // Создаем объект меню и запускаем его выполнение
            ConsoleMenu consoleMenu = new ConsoleMenu();
            consoleMenu.execute();
        } catch (IOException e) {
            // Обработка исключения, связанного с вводом/выводом
            System.err.println("Произошла ошибка ввода/вывода: " + e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            // Обработка исключения, связанного с созданием объекта
            System.err.println("Ошибка при создании объекта: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // Обработка исключения, связанного с доступом к классу или его членам
            System.err.println("Ошибка доступа: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Общий блок для обработки всех остальных исключений
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
