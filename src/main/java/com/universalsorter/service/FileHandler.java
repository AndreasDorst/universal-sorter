package com.universalsorter.service;
import com.universalsorter.model.Storable;
import com.universalsorter.repository.BookRepository;
import com.universalsorter.repository.CarRepository;
import com.universalsorter.repository.RootVegetableRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для работы с файлами.
 */
public class FileHandler {
    private final BookRepository bookRepository;
    private final CarRepository carRepository;
    private final RootVegetableRepository rootVegetableRepository;


    public FileHandler() {
        bookRepository=new BookRepository();
        carRepository=new CarRepository();
        rootVegetableRepository=new RootVegetableRepository();
    }

    /**
     * Запись объекта в файл.
     *
     * @param fileName Имя файла.
     * @param object   Объект, реализующий интерфейс Storable.
     * @throws IOException Если произошла ошибка при записи.
     */
    public void writeToFile(String fileName, Storable object) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (object!=null){
                writer.write(object.serialize());
                writer.newLine();}
        }
    }

    /**
     * Чтение объектов из файла.
     *
     * @param fileName Имя файла.
     * @param <T>      Обобщённый тип, реализующий интерфейс Storable.
     * @return Список объектов из файла.
     * @throws IOException Если произошла ошибка при чтении.
     */
    public <T extends Storable> List<T> readFromFile(String fileName) throws IOException {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String type;
            while((line = reader.readLine()) != null) {
                String[]split=line.split(",");
                type=split[0];
                T obj = null;
                switch (type){
                    case "Book":obj=(T)bookRepository.getBook(0).deserialize(line);break;
                    case "Car":obj=(T)carRepository.getCar(0).deserialize(line);break;
                    case "RootVegetable":obj=(T)rootVegetableRepository.getRootVegetable(0).deserialize(line);break;
                }
                objects.add(obj);
            }
        }
        return objects;
    }

}