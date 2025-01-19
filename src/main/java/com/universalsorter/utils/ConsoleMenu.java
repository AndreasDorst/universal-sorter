package com.universalsorter.utils;
import java.io.IOException;
import java.util.Scanner;
import com.universalsorter.model.Book;
import com.universalsorter.model.Car;
import com.universalsorter.model.RootVegetable;
import com.universalsorter.model.SupportedTypes;

public class ConsoleMenu {
    private ArrayManager arrayManager;
    private Scanner scanner;
    private SupportedTypes arrayDataType;

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
            System.out.println("5. Убрать пустые секции массива");
            System.out.println("6. Очистить массив");
            System.out.println("7. Удалить массив");
            System.out.println("8. Выполнить поиск элемента");
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
        if (choice.matches("^[0-8]$")) {
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
                sortArrayMenu();
                break;
            case 6:
                arrayManager.shuffleArray();
                break;
            case 7:
                showArrayInfo();
                break;
            case 8:arrayManager.removeNullElements(true);break;
            case 9:
                arrayManager.clearArray();
                break;
            case 3,10:
                arrayManager.deleteArray();
                break;
            case 11:
            	System.out.println("\nЖдём реализацию по вводу элемента. Пока ищет дефолтное значение\n");
            	Comparable searchedElement = null;
            	switch (arrayDataType) {
            		case SupportedTypes.BOOK:
            			searchedElement = new Book.Builder().title("Война и мир").author("Лев Толстой").page(550).build();
            			break;
            		case SupportedTypes.CAR:
            			searchedElement = new Car.Builder().model("BMW M3").power(473.0).yearOfProduction(2022).build();
            			break;
            		case SupportedTypes.ROOT_VEGETABLE:
            			searchedElement = new RootVegetable.Builder().type("Репа").weight(175.0).color("Белый").build();
            			break;
            	}
    		System.out.println(findElement(searchedElement));

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
        System.out.println("3. Ввести данные вручную");
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

        int dataType = selectDataType();
        
        switch (selection) {
            case 1:
            	arrayManager.downloadDataFromStorage(dataType);
                break;
            case 2:
            	arrayManager.downloadDataFromFile(dataType);
                break;
            case 3:
            	System.out.println("\nФункционал находится в разработке.\n");
                break;
            case 0:
                break;
            default:
                System.out.println("\nНеверный выбор. Попробуйте снова.\n");
        }
    }

    private int selectDataType() {
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

            switch (selection) {
			    case 1:
			    	arrayDataType = SupportedTypes.BOOK;
			        break;
			    case 2:
			    	arrayDataType = SupportedTypes.CAR;
			    	break;
			    case 3:
			    	arrayDataType = SupportedTypes.ROOT_VEGETABLE;
			    	break;
    }

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 4.\n");
        }

        return selection;
    }


    private void saveDataToFile() throws IOException {

        arrayManager.saveToFile();
    }

    private void sortArrayMenu() {
        System.out.println("1. Естественный порядок сортировки");
        System.out.println("2. QuickSort");
        System.out.println("3. MergeSort");
        System.out.println("4. BubbleSort");
        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");

        String choice = scanner.next();
        int selection=0;
        if (choice.matches("^[0-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 4.\n");return;
        }

        switch (selection) {
            case 1:
                arrayManager.sortArray();
                break;
            case 2,3,4:
                arrayManager.setSortStrategy(selection);
                choiceComparatorOptions();
                break;
            case 0:
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");
        }
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

    private String findElement(Comparable element) {
    	return arrayManager.findElement(element);
    }
    
    private void printArrayElement() {
        System.out.printf("\nВыберите элемент массива(от 1 до %d): ",arrayManager.getArraySize());
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.next();
        arrayManager.getElement(operation);
    }

    private void choiceComparatorOptions(){
        String type= arrayManager.getArrayType();
        if(type.equals("Book")){
            System.out.println("\n1. Сортировать по названию книги");
            System.out.println("2. Сортировать по автору");
            System.out.println("3. Сортировать по количеству страниц");
        }
        else  if(type.equals("Car")){
            System.out.println("1. Сортировать по мощности автомобиля");
            System.out.println("2. Сортировать по марке автомобиля");
            System.out.println("3. Сортировать по дате выпуска автомобиля");
        }
        else {
            System.out.println("1. Сортировать по типу корнеплода");
            System.out.println("2. Сортировать по цвету корнеплода");
            System.out.println("3. Сортировать по весу корнеплода");
        }

        System.out.println("0. Назад");
        System.out.print("Выберите пункт меню: ");

        String choice = scanner.next();
        int selection=0;
        if (choice.matches("^[0-4]$")) {
            selection=Integer.parseInt(choice);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите значения от 0 до 4.\n");return;
        }
        switch (type){
            case "Book":arrayManager.comparatorBookOptions(selection);break;
            case "Car":arrayManager.comparatorCarOptions(selection);break;
            case "RootVegetable":arrayManager.comparatorRootVegetableOptions(selection);break;
            default:break;


        }

    }

}