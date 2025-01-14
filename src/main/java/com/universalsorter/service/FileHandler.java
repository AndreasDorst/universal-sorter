package com.universalsorter.service;

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
            writer.write(object.serialize());
            writer.newLine();
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
    public <T extends Storable> List<T> readFromFile(String fileName, T type) throws IOException {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T obj = (T) type.deserialize(line);
                objects.add(obj);
            }
        }
        return objects;
    }
}
