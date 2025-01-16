package com.universalsorter.utils;
import com.universalsorter.model.Storable;
import com.universalsorter.repository.BookRepository;
import com.universalsorter.repository.CarRepository;
import com.universalsorter.repository.RootVegetableRepository;
import com.universalsorter.service.FileHandler;
import java.io.IOException;
import java.util.*;

public class ArrayManager {
    private Comparable[] array;
    private final BookRepository bookRepository = new BookRepository();
    private final CarRepository carRepository = new CarRepository();
    private final RootVegetableRepository rootVegetableRepository = new RootVegetableRepository();
    private final FileHandler fileHandler = new FileHandler();
    private final String fileForWrite = "fileWrite.txt";
    private final String fileForRead = "fileRead.txt";


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

    // Загрузка случайных данных в массив
    public void loadRandomData() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
        Random random = new Random();
        int book = 0;
        int car = 0;
        int root = 0;
        for (int i = 0; i < array.length; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    array[i] = (Comparable) bookRepository.getBook(book);
                    book++;
                
                    if (bookRepository.getBook(book) == null) {
                        i--;
                    }
                    break;
                case 1:
                    array[i] = (Comparable) carRepository.getCar(car);
                    car++;
               
                    if (carRepository.getCar(car) == null) {
                        i--;
                    }
                    break;
                case 2:
                    array[i] = (Comparable) rootVegetableRepository.getRootVegetable(root);
                    root++;

                    if (rootVegetableRepository.getRootVegetable(root) == null) {
                        i--;
                    }
                    break;
            }
            if ((book + car + root) >= (bookRepository.getSizeBookList() + carRepository.getSizeCarList() + rootVegetableRepository.getSizeRootList())) {
                System.out.println("Массив частично заполнен случайными данными.\n");
                return;

            }


        }
        System.out.println("Массив заполнен случайными данными.\n");
    }


    public void addElement() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
            //логика добавления одиночного кастомного обьекта
        }
    }

    public void saveToFile() throws IOException {
        for (Comparable st : array) {
            fileHandler.writeToFile(fileForWrite, (Storable) st);
        }
    }

    public void sortArray() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
        //логика сортировки
        System.out.println("Массив отсортирован.\n");
    }

    public void shuffleArray() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
        Collections.shuffle(Arrays.asList(array));
        System.out.println("\nМассив перемешан.\n");
    }

    public int getArraySize() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
        return array.length;
    }

    public String getArrayContents() {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
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
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }
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
    public void downloadDataFromFile() throws IOException, InstantiationException, IllegalAccessException {
        if (array == null) {
            throw new IllegalStateException("Массив не создан. Сначала создайте массив.\n");
        }

        List<Storable> objects = fileHandler.readFromFile(fileForRead);
        for(int i=0;i<objects.size()&&i<array.length;i++){
            array[i]= (Comparable) objects.get(i);
        }

    }
}