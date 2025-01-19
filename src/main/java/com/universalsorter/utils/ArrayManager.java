package com.universalsorter.utils;
import com.universalsorter.model.Book;
import com.universalsorter.model.Car;
import com.universalsorter.model.RootVegetable;
import com.universalsorter.model.Storable;
import com.universalsorter.repository.BookRepository;
import com.universalsorter.repository.CarRepository;
import com.universalsorter.repository.RootVegetableRepository;
import com.universalsorter.service.*;
import java.io.File;

import java.io.IOException;
import java.time.Year;
import java.util.*;
import java.util.Optional;
import java.util.Random;
import java.util.Arrays;




public class ArrayManager {


    private Comparable[] array;
    private final BookRepository bookRepository;
    private final CarRepository carRepository;
    private final RootVegetableRepository rootVegetableRepository;
    private final FileHandler fileHandler;
    private final String fileForWrite = "FileWrite.txt";
    private final String fileForReadBook = "src/main/recources/book.txt";
    private final String fileForReadCar = "src/main/recources/car.txt";
    private final String fileForReadRootVegetable = "src/main/recources/rootVegetable.txt";
    SortingContext<Comparable> context;
    private String typeSort;





    public ArrayManager() {
        bookRepository = new BookRepository();
        carRepository = new CarRepository();
        rootVegetableRepository = new RootVegetableRepository();
        fileHandler=new FileHandler();
        context=new SortingContext<>(new BubbleSort<>());
        typeSort="BubbleSort";

    }


    public void createNewArray(String size) {
        int selection = 0;
        if (size.matches("^0|[1-9][0-9]*$")) {
            selection = Integer.parseInt(size);

        } else {
            System.out.println("\nНеправильный ввод. Пожалуйста, введите корректное значение.\n");
            return;
        }

        this.array = new Comparable[selection];
        System.out.println("Массив создан. Размер: " + size + "\n");

    }

    // Загрузка данных в массив из классов
    public void downloadDataFromStorage(int choice) {

        if(choice==4){
            Random random=new Random();
            choice=random.nextInt(3)+1;//от 1 до 3
        }

        switch (choice){
            case 1:
                fillArrayBookTypeFromStorage();
                break;
            case 2:fillArrayCarTypeFromStorage();break;
            case 3:fillArrayRootVegetableTypeFromStorage();break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");
        }
    }

    private void fillArrayBookTypeFromStorage(){
        for(int i=0;(i<array.length&&i<bookRepository.getSizeBookList());i++){
            array[i]= bookRepository.getBook(i);
        }
        removeNullElements(false);
            System.out.println("\nМассив заполнен данными типа Book\n");
    }

    private void fillArrayCarTypeFromStorage(){
        for(int i=0;(i<array.length&&i<carRepository.getSizeCarList());i++){
            array[i]= carRepository.getCar(i);
        }
        removeNullElements(false);
            System.out.println("\nМассив заполнен данными типа Car\n");
    }

    private void fillArrayRootVegetableTypeFromStorage(){
        for(int i=0;(i<array.length&&i<rootVegetableRepository.getSizeRootList());i++){
            array[i]= rootVegetableRepository.getRootVegetable(i);
        }
        removeNullElements(false);
            System.out.println("\nМассив заполнен данными типа RootVegetable\n");
    }


    public void saveToFile() throws IOException {
        for (Comparable st : array) {
            fileHandler.writeToFile(fileForWrite, (Storable) st);
        }
        System.out.println("\nДанные из массива сохранены в файл.\n");
    }


    public void sortArray() {
        Comparable[] temp=new Comparable[array.length];
        removeNullElements(false);
        long start = System.currentTimeMillis();
        Arrays.sort(array);
        long stop = System.currentTimeMillis();
        for(int i=0;i<array.length;i++){
            temp[i]=array[i];
        }
        array=temp;
        System.out.printf("\nМассив отсортирован за:\t%d ms\n\n", (stop - start));
    }



    public void shuffleArray() {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        System.out.println("\nМассив перемешан.\n");

    }
    public void clearArray(){
        array=new Comparable[array.length];
        System.out.println("\nМассив очищен.\n");
    }

    public void deleteArray(){
        array=null;
        System.out.println("\nМассив удалён.\n");
    }

    public int getArraySize() {

        return array.length;

    }


    public String getArrayContents() {
        System.out.println("Содержимое массива:\n\n");
        StringBuilder sb = new StringBuilder();

        for (Comparable element : array) {

            if (element == null) {
                sb.append("\nПустой слот.\n");
            } else {
                sb.append(element).append("\n");
            }
        }
        return sb.toString();
    }

    public void getElement(String index) {
        if (index.matches("^(0|[1-9][0-9]*)$")) {
            int element = Integer.parseInt(index);
            if (element <= array.length && element != 0) {

                Optional<Comparable> temp = Optional.ofNullable(array[element - 1]);

                temp.ifPresentOrElse(v -> System.out.println(v), () -> System.out.println("Пустой слот.\n"));
            } else {
                System.out.println("Некорректный номер элемента массива.\n");
            }
        }
    }
    /**
     * Метод для валидации файла
     * Проверка, что файл не пустой
     * Проверка, что файл существует
     * Проверка, что файл является TXT
     */
    private void fileValidation(String filePath){
        File file = new File(filePath); // путь до файла
        boolean isValid = file.exists() && file.isFile() && filePath.endsWith(".txt") && file.length()>0; // проверка на валидность
        if (!isValid) {
            throw new IllegalArgumentException("Файл не существует или не является TXT");
        }
    }

    //загрузка данных из файла с массив.
    public void downloadDataFromFile(int choice) throws IOException{

        if(choice==4){
            Random random=new Random();
            choice=random.nextInt(3)+1;//от 1 до 3
        }
        String file="";
        switch (choice){
            case 1:fileValidation(fileForReadBook);file= fileForReadBook;break;
            case 2:fileValidation(fileForReadCar);file= fileForReadCar;break;
            case 3:fileValidation(fileForReadRootVegetable);file= fileForReadRootVegetable;break;
        }

        List<Storable> objects = fileHandler.readFromFile(file);
        for (int i = 0; i < objects.size() && i < array.length; i++) {
            array[i] = (Comparable) objects.get(i);
        }
        switch (choice){
            case 1:
                System.out.println("\nМассив заполнен данными типа Book\n");break;
            case 2:
                System.out.println("\nМассив заполнен данными типа Car\n");break;
            case 3:
                System.out.println("\nМассив заполнен данными типа RootVegetable\n");break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");

        }
        removeNullElements(false);
    }

    //ввод данных вручную.
    public void fillArrayWithChosenData(int selection) {

        Scanner scanner = new Scanner(System.in);
        String data = "";

        for (int i = 0; i < array.length; i++) {
            while (true) {
                try {

                    switch (selection) {
                        case 1: // книга
                            System.out.println("\nСоздание Book №" + (i+1));
                            System.out.println("Введите автора");
                            data = "Book,".concat(scanner.nextLine());

                            System.out.println("Введите название");
                            data = data.concat(",".concat(scanner.nextLine()));

                            System.out.println("Введите количество страниц (Integer)");
                            data = data.concat(",".concat(String.valueOf(Integer.parseInt(scanner.nextLine())))); // для проверки ввода
                            break;
                        case 2: // машина
                            System.out.println("\nСоздание Car №" + (i+1));
                            System.out.println("Введите модель");
                            data = "Car,".concat(scanner.nextLine());

                            System.out.println("Введите мощность (Double), (min = 0, max = 2000)");
                            data = data.concat(",".concat(String.valueOf(Double.parseDouble(scanner.nextLine())))); // для проверки ввода

                            System.out.println("Введите год выпуска (Integer), (min = 1800, max = ".concat(String.valueOf(Year.now().getValue())).concat(")"));
                            data = data.concat(",".concat(String.valueOf(Integer.parseInt(scanner.nextLine())))); // для проверки ввода
                            break;
                        case 3: // корнеплод
                            System.out.println("\nСоздание RootVegetable №" + (i+1));
                            System.out.println("Введите вид");
                            data = "RootVegetable,".concat(scanner.nextLine());

                            System.out.println("Введите вес (Double), (min = 0, max = 10000)");
                            data = data.concat(",".concat(String.valueOf(Double.parseDouble(scanner.nextLine())))); // для проверки ввода

                            System.out.println("Введите цвет");
                            data = data.concat(",".concat(scanner.nextLine()));
                            break;
                    }
                    String[] split = data.split(",");
                    String type = split[0];
                    Storable obj = null;
                    switch (type){
                        case "Book":obj = bookRepository.getBook(0).deserialize(data);break;
                        case "Car":obj = carRepository.getCar(0).deserialize(data);break;
                        case "RootVegetable":obj = rootVegetableRepository.getRootVegetable(0).deserialize(data);break;
                    }
                    array[i] = (Comparable) obj;
                    System.out.println("Объект типа ".concat(type).concat(" №".concat(String.valueOf(i+1))).concat(" создан успешно!"));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод, пожалуйста попробуйте еще раз");
                }
            }
        }

        System.out.println("Массив заполнен успешно!\n");
    }

    public boolean isArrayCreated() {

        return array != null;
    }

    public boolean isEmptyArray(){
        for (Comparable comparable : array) {
            if (comparable != null) {
                return false;
            }
        }
        return true;
    }

    public String findElement(Comparable item) {
    	return SortedArraySearch.getElement(this.array, item);
    }

    public void removeNullElements(Boolean showMessage) {
        // Подсчитываем количество не-null элементов
        int count = 0;
        for (Comparable element : array) {
            if (element != null) {
                count++;
            }
        }
        // Создаем новый массив нужного размера
        Comparable[]temp=new Comparable[count];

        // Заполняем новый массив не-null элементами
        for(int i=0,j=0;i<array.length;i++){
            if(array[i]!=null){
                temp[j]=array[i];
                j++;
            }
        }
        array=temp;
        if(showMessage){
        System.out.println("\nПустые секции удалены.\n");}
    }

    public String getArrayType(){
        if(array[0] instanceof Car){
            return "Car";}
            else if(array[0] instanceof Book){
                return "Book";
        }
            else return "RootVegetable";
        }

    public void comparatorBookOptions(int selection){
        long start = System.currentTimeMillis();
            switch (selection){
                case 1:
                    context.sortArray(array, Comparator.comparing(book -> ((Book) book).getTitle()));
                    break;
                case 2:
                    context.sortArray(array, Comparator.comparing(book -> ((Book) book).getAuthor()));
                    break;
                case 3:
                    context.sortArray(array, Comparator.comparingInt(book -> ((Book) book).getNumberOfPages()));
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.\n");
            }
        long stop = System.currentTimeMillis();
        System.out.printf("\nМассив отсортирован за:\t%d ms\n\n", (stop - start));
        }

    public void comparatorCarOptions(int selection){
        long start = System.currentTimeMillis();
        switch (selection){
            case 1:
                context.sortArray(array, Comparator.comparing(car -> ((Car) car).getPower()));
                break;
            case 2:
                context.sortArray(array, Comparator.comparing(car -> ((Car) car).getModel()));
                break;
            case 3:
                context.sortArray(array, Comparator.comparing(car -> ((Car) car).getYearOfProduction()));
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");
        }
        long stop = System.currentTimeMillis();
        System.out.printf("\nМассив отсортирован за:\t%d ms\n\n", (stop - start));
    }

    public void comparatorRootVegetableOptions(int selection){
        long start = System.currentTimeMillis();
        switch (selection){
            case 1:
                context.sortArray(array, Comparator.comparing(root -> ((RootVegetable) root).getType()));
                break;
            case 2:
                context.sortArray(array, Comparator.comparing(root -> ((RootVegetable) root).getColor()));
                break;
            case 3:
                context.sortArray(array, Comparator.comparing(root -> ((RootVegetable) root).getWeight()));
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.\n");
        }
        long stop = System.currentTimeMillis();
        System.out.printf("\nМассив отсортирован за:\t%d ms\n\n", (stop - start));
    }

    public void choiceTypeSorting(int choice){
        switch (choice){
            case 1:context.setStrategy(new QuickSortTemp<>());
                System.out.println("\nВыбран вариант сортировки QuickSort\n");
                typeSort="QuickSort";
                break;
            case 2:context.setStrategy(new MergeSortTemp<>());
                System.out.println("\nВыбран вариант сортировки MergeSort\n");
                typeSort="MergeSort";
                break;
            case 3:  context.setStrategy(new BubbleSort<>());
                System.out.println("\nВыбран вариант сортировки BubbleSort\n");
                typeSort="BubbleSort";
                break;

        }

    }
    public String getTypeSort() {
        return typeSort;
    }

}