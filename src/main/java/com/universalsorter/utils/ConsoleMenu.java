package com.universalsorter.utils;

import com.universalsorter.model.Storable;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {
    private ArrayManager arrayManager;
    private Scanner scanner;

    public ConsoleMenu() {
        arrayManager = new ArrayManager();
        scanner = new Scanner(System.in);
    }


    public void execute() throws IOException {
        while (true) {
            printMainMenu();
            String choice = scanner.next();
            handleMainMenu(choice);
        }
    }

    private void printMainMenu() {
        System.out.println("1. Создать новый массив");
        System.out.println("2. Загрузить данные в массив");
        System.out.println("3. Сохранить данные в файл");
        System.out.println("4. Отсортировать массив");
        System.out.println("5. Перемешать массив");
        System.out.println("6. Информация о массиве");
        System.out.println("7. Выход");
        System.out.print("Выберите пункт меню: ");
    }


    private void handleMainMenu(String choice) throws IOException {

        int selection=0;
        if (choice.matches("^[1-7]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 1 до 7.\n");return;
        }

        switch (selection) {
            case 1:
                createNewArray();
                break;
            case 2:
                loadDataIntoArray();
                break;
            case 3:
                saveDataToFile();
                break;
            case 4:
                arrayManager.sortArray();
                break;
            case 5:
                arrayManager.shuffleArray();
                break;
            case 6:
                showArrayInfo();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор.\n");
        }
    }

    private void createNewArray() {
        System.out.print("\nВведите размер массива: ");
        String size = scanner.next();
        arrayManager.createNewArray(size);

    }


    private void loadDataIntoArray() throws IOException {

        System.out.println("\n1. Загрузить случайные данные");
        System.out.println("2. Загрузить данные из файла");
        System.out.println("3. Добавить элемент вручную");
        System.out.println("4. Назад");
        System.out.print("Выберите пункт меню: ");
        String choice = scanner.next();

        int selection=0;
        if (choice.matches("^[1-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 1 до 4.\n");return;
        }

        switch (selection) {
            case 1:
                arrayManager.loadRandomData();
                break;
            case 2:
                arrayManager.downloadDataFromFile();
                break;
            case 3:
                addElementManually();
                break;
            case 4:
                break;
            default:
                System.out.println("\nНеверный выбор. Попробуйте снова.\n");
        }
    }

    private void addElementManually() {
     //Добавление объект кастомного класса;
    }


    private void saveDataToFile() throws IOException {

        arrayManager.saveToFile();
    }

    private void showArrayInfo() {
        System.out.println("\n1. Вывести размер массива");
        System.out.println("2. Вывести содержимое массива");
        System.out.println("3. Вывести элемент массива");
        System.out.println("4. Назад");
        System.out.print("Выберите пункт меню: ");

        String choice = scanner.next();
        int selection=0;
        if (choice.matches("^[1-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 1 до 4.\n");return;
        }

        switch (selection) {
            case 1:
                System.out.println("Размер массива: " + arrayManager.getArraySize()+"\n");
                break;
            case 2:
                System.out.println("Содержимое массива: \n\n" + arrayManager.getArrayContents());
                break;
            case 3:
                printArrayElement();
                break;
            case 4:
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");
        }
    }

    private void printArrayElement() {
        System.out.printf("\nВыберите элемент массива(от 1 до %d): ",arrayManager.getArraySize());
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.next();
        arrayManager.getElement(operation);
    }

}