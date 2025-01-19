package com.universalsorter.utils;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleMenu {
    private ArrayManager arrayManager;
    private Scanner scanner;

    public ConsoleMenu() {
        arrayManager = new ArrayManager();
        scanner = new Scanner(System.in);
    }



    public void execute() throws IOException, InstantiationException, IllegalAccessException {

        while (true) {
            printMainMenu();
            String choice = scanner.next();
            handleMainMenu(choice);
        }
    }

    private void printMainMenu() {
        if(!arrayManager.isArrayCreated()){
            System.out.println("1. Создать новый массив");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");
        }
        else if(arrayManager.isEmptyArray()) {
            System.out.println("1. Загрузить данные в массив");
            System.out.println("2. Удалить массив");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");
        }
        else {
            System.out.println("1. Сохранить данные в файл");
            System.out.println("2. Отсортировать массив");
            System.out.println("3. Перемешать массив");
            System.out.println("4. Информация о массиве");
            System.out.println("5. Очистить массив");
            System.out.println("6. Удалить массив");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");
        }

    }


    private void handleMainMenu(String choice) throws IOException, InstantiationException, IllegalAccessException {

        int selection=0;
        if(!arrayManager.isArrayCreated()) {
            if (choice.matches("^[01]$")) {
                selection = Integer.parseInt(choice);

            } else {
                System.out.println("\nНеправильный ввод. Пожалуйста, введите значение 0 или 1.\n");
                return;
            }
        }
        else if(arrayManager.isEmptyArray()&&arrayManager.isArrayCreated()) {
            if (choice.matches("^[0-2]$")) {
                selection = Integer.parseInt(choice);
                if (selection >0) {
                    selection++;
                }

            } else {
                System.out.println("\nНеправильный ввод. Пожалуйста, введите значение от 0 до 2.\n");
                return;
            }
        }
        else
        if (choice.matches("^[0-6]$")) {
            selection=Integer.parseInt(choice);
            if(selection>0){
                selection=selection+3;
            }

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значение от 0 до 4.\n");return;
        }


        switch (selection) {
            case 1:
                createNewArray();
                break;
            case 2:
                loadDataIntoArray();
                break;
            case 4:
                saveDataToFile();
                break;
            case 5:
                arrayManager.sortArray();
                break;
            case 6:
                arrayManager.shuffleArray();
                break;
            case 7:
                showArrayInfo();
                break;
            case 8:
                arrayManager.clearArray();
                break;
            case 3,9:
                arrayManager.deleteArray();
                break;



            case 0:
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


    private void loadDataIntoArray() throws IOException{

        System.out.println("\n1. Загрузить данные из программы");
        System.out.println("2. Загрузить данные из файла");
        System.out.println("3. Загрузить данные вручную");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");
        String choice = scanner.next();

        int selection=0;
        if (choice.matches("^[0-3]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 3.\n");return;
        }

        switch (selection) {
            case 1:
                selectDataTypeFromStorage();
                break;
            case 2:
                selectDataTypeFromFile();
                break;
            case 3:
                selectDataTypeByHand();
                break;
            case 0:
                break;
            default:
                System.out.println("\nНеверный выбор. Попробуйте снова.\n");
        }
    }

    private void selectDataTypeByHand() {
        System.out.println("\n Введите тип данных:");
        System.out.println("1. Создать данные типа Book");
        System.out.println("2. Создать данные типа Car");
        System.out.println("3. Создать данные типа RootVegetable");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");

        String choice = scanner.next();

        int selection=0;
        if (choice.matches("^[0-3]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 3.\n");
            return;
        }
        if(selection>0){
            arrayManager.fillArrayWithChosenData(selection);
        }
        else
            System.out.println();
        return;
    }


    private void selectDataTypeFromStorage(){
        System.out.println("\n1. Загрузить данные типа Book");
        System.out.println("2. Загрузить данные типа Car");
        System.out.println("3. Загрузить данные типа RootVegetable");
        System.out.println("4. Загрузить данные случайного типа");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");
        String choice = scanner.next();

        int selection=0;
        if (choice.matches("^[0-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 4.\n");
            return;
        }
        if(selection>0){
            arrayManager.downloadDataFromStorage(selection);
        }
        else
            System.out.println();
            return;
        }

    private void selectDataTypeFromFile() throws IOException {
        System.out.println("\n1. Загрузить данные типа Book");
        System.out.println("2. Загрузить данные типа Car");
        System.out.println("3. Загрузить данные типа RootVegetable");
        System.out.println("4. Загрузить данные случайного типа");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");
        String choice = scanner.next();

        int selection=0;
        if (choice.matches("^[0-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 4.\n");
            return;
        }
        if(selection>0){
            arrayManager.downloadDataFromFile(selection);
        }
        else
            System.out.println();
        return;
    }



    private void saveDataToFile() throws IOException {

        arrayManager.saveToFile();
    }

    private void showArrayInfo() {
        System.out.println("\n1. Вывести размер массива");
        System.out.println("2. Вывести содержимое массива");
        System.out.println("3. Вывести элемент массива");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");

        String choice = scanner.next();
        int selection=0;
        if (choice.matches("^[0-3]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 3.\n");return;
        }

        switch (selection) {
            case 1:
                System.out.println("Размер массива: " + arrayManager.getArraySize()+"\n");
                break;
            case 2:
                System.out.println(arrayManager.getArrayContents());
                break;
            case 3:
                printArrayElement();
                break;
            case 0:
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