package com.universalsorter.utils;
import com.universalsorter.model.Storable;
import com.universalsorter.repository.BookRepository;
import com.universalsorter.repository.CarRepository;
import com.universalsorter.repository.RootVegetableRepository;
import com.universalsorter.service.FileHandler;
import java.io.IOException;
import java.util.*;
import java.util.Optional;
import java.util.Random;
import java.util.Arrays;




public class ArrayManager {


    private Comparable[] array;
    private final BookRepository bookRepository = new BookRepository();
    private final CarRepository carRepository = new CarRepository();
    private final RootVegetableRepository rootVegetableRepository = new RootVegetableRepository();
    private final FileHandler fileHandler = new FileHandler();
    private final String fileForWrite = "FileWrite.txt";



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
            case 1:fillArrayBookTypeFromStorage();break;
            case 2:fillArrayCarTypeFromStorage();break;
            case 3:fillArrayRootVegetableTypeFromStorage();break;

        }
    }

    private void fillArrayBookTypeFromStorage(){
        for(int i=0;(i<array.length&&i<bookRepository.getSizeBookList());i++){
            array[i]= bookRepository.getBook(i);
        }
            System.out.println("\nМассив заполнен данными типа Book\n");
    }

    private void fillArrayCarTypeFromStorage(){
        for(int i=0;(i<array.length&&i<carRepository.getSizeCarList());i++){
            array[i]= carRepository.getCar(i);
        }
            System.out.println("\nМассив заполнен данными типа Car\n");
    }

    private void fillArrayRootVegetableTypeFromStorage(){
        for(int i=0;(i<array.length&&i<rootVegetableRepository.getSizeRootList());i++){
            array[i]= rootVegetableRepository.getRootVegetable(i);
        }
            System.out.println("\nМассив заполнен данными типа RootVegetable\n");
    }


    public void saveToFile() throws IOException {
        for (Comparable st : array) {
            fileHandler.writeToFile(fileForWrite, (Storable) st);
        }
        System.out.println("\nДанные из массива сохранены в файл.\n");
    }


    public void sortArray() {
        Arrays.sort(array, (Comparator<Object>) (o1, o2) -> {
            // Сравнение по типу объекта
            String type1 = o1.getClass().getSimpleName();
            String type2 = o2.getClass().getSimpleName();
            int typeComparison = type1.compareTo(type2);

            if (typeComparison != 0) {
                return typeComparison;
            }

            // Если типы одинаковые, используем compareTo
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable) o1).compareTo(o2);
            }

            throw new IllegalArgumentException("Объекты не поддерживают сравнение");
        });

        System.out.println("\nМассив отсортирован.\n");}



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

    //загрузка данных из файла с массив.
    public void downloadDataFromFile(int choice) throws IOException{

        if(choice==4){
            Random random=new Random();
            choice=random.nextInt(3)+1;//от 1 до 3
        }
        String file="";
        switch (choice){
            case 1:file= "src/main/recources/book.txt";break;
            case 2:file= "src/main/recources/car.txt";break;
            case 3:file= "src/main/recources/rootVegetable.txt";break;
        }

        List<Storable> objects = fileHandler.readFromFile(file);
        for (int i = 0; i < objects.size() && i < array.length; i++) {
            array[i] = (Comparable) objects.get(i);
        }
        switch (choice){
            case 1:
                System.out.println("\nМассив заполнен данными типа Book\n");
            case 2:
                System.out.println("\nМассив заполнен данными типа Car\n");
            case 3:
                System.out.println("\nМассив заполнен данными типа RootVegetable\n");

        }
        System.out.println("\nДанные из файла загружены\n");
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
}