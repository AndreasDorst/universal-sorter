package com.universalsorter.utils;

import java.util.Random;
import java.util.Scanner;

public class InputUtils {
    private Scanner scanner;
    private int arraySize;

    public InputUtils() {
        scanner = new Scanner(System.in);
    }

    public void execute(){
        int selection = fillingTheArray();
        menuSelection(selection);
        arraySize=takeArraySize();
        //создание массива

        getArrayElement();


    }

    private int fillingTheArray(){
        int selection=0;
        while (true) {
            String temp = getInput("Выберите вариант заполнения массива: \n1 - Из файла\n2 - Случайно\n3 - Вручную\n0 - Выход");
            if (temp.matches("^[0-3]$")) {
                selection=Integer.parseInt(temp);
                break;
            } else {

                displayError("Неправильный ввод. Пожалуйста, введите 0, 1, 2 или 3.");
            }
        }
        return selection;
    }

    private void menuSelection(int i){
        int select=0;
        Random random=new Random();
        int rand=random.nextInt(3)+1;
        switch (i){
            case 1:break;//loadFromFile();Загрузка из файла
            case 2:loadCustomClass(rand);break;
            case 3:manualLoad();break;
            default:displayError("Неизвестная операция.");
        }

    }

    private int takeArraySize() {
        int size=0;
        while (true) {
            String temp = getInput("Выберите длину заполняемого массива :");

            if (temp.matches("^[1-9][0-9]*$")) {
                size=Integer.parseInt(temp);
                break;
            } else {

                displayError("Неправильный ввод. Пожалуйста, введите целое неотрацательное число(первая цифра не должна начинаться с 0).");
            }
        }
        return size;
    }

    private void getArrayElement(){
        while (true) {
            System.out.print("Выберите элемент массива: \n");
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.next();
            if (operation.matches("^(0|[1-9][0-9]*)$")){
                int element = Integer.parseInt(operation);//номер элемента массива
               //метод для получения элемента массива ;
                }
                else{
                    displayError("Неизвестная операция");
                }
        }
    }

    private String getInput(String message){
        System.out.println(message);
        return scanner.next();
    }


    private void manualLoad(){
        int selection=0;
        while (true) {
            String temp = getInput("Выберите, какими данными заполнить массив: \n1 - Book\n2 - Car\n3 - RootVegetable\n0 - Назад");
            if (temp.matches("^[0-3]$")) {
                selection=Integer.parseInt(temp);
                if(selection==0){execute();}
                break;
            } else {

                displayError("Неправильный ввод. Пожалуйста, введите 0, 1, 2 или 3.");
            }
        }
        loadCustomClass(selection);
    }

    private void loadCustomClass(int choice){
        switch (choice){
            case 1 :
                System.out.println("Выбран класс Book");break;//loadBookClass();
            case 2:
                System.out.println("Выбран класс Car");break; //loadCarClass();
            case 3:
                System.out.println("Выбран класс RootVegetable");break;//loadRootVegetableClass();
            default:
                displayError("Неизвестная операция.");
        }
    }
    private void displayError(String error) {
        System.out.println("Ошибка : "+error);
    }

}


