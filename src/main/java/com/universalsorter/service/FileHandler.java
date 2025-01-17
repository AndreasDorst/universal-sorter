package com.universalsorter.service;

import com.universalsorter.model.Book;
import com.universalsorter.model.Car;
import com.universalsorter.model.RootVegetable;
import com.universalsorter.model.Storable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для работы с файлами.
 */
public class FileHandler {

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
     * @param type     Тип объекта для десериализации.
     * @param <T>      Обобщённый тип, реализующий интерфейс Storable.
     * @return Список объектов из файла.
     * @throws IOException Если произошла ошибка при чтении.
     */


//    public <T extends Storable> List<T> readFromFile(String fileName) throws IOException {
//        List<T> objects = new ArrayList<>();
//        T obj=null;
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] split = line.split(",");
//                if(split[0].equals("Book")){obj = (T) returnedType(Book.class);
//                }
//                else if(split[0].equals("Car")){obj= (T) returnedType(Car.class);
//                }
//                else if(split[0].equals("RootVegetable")){obj= (T) returnedType(RootVegetable.class);
//                }
//
//                // Десериализуем данные в объект
//                obj.deserialize(line);
//                objects.add(obj);
//            }
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        return objects;
//    }
//        private <T> T returnedType(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//           return  clazz.getDeclaredConstructor().newInstance();
//        }



    public <T extends Storable>List<T> readFromFile(String fileName) throws IOException {
        List<Storable> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                Storable obj;

                // Определяем тип объекта и создаем его
                switch (split[0]) {
                    case "Book":
                        obj = new Book.Builder().title(split[1]).author(split[2]).page(Integer.valueOf(split[3])).build();
                        break;
                    case "Car":
                        obj = new Car.Builder().model(split[1]).power(Double.valueOf(split[2])).yearOfProduction(Integer.valueOf(split[3])).build();
                        break;
                    case "RootVegetable":
                        obj = new RootVegetable.Builder().type(split[1]).weight(Double.valueOf(split[2])).color(split[3]).build();
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный тип: " + split[0]);
                }
                obj.deserialize(line);
                objects.add(obj);
            }
        } catch (IllegalArgumentException e) {
            throw new IOException("Ошибка при чтении данных из файла: " + e.getMessage(), e);
        }
        return (List<T>) objects;
    }

}
