package com.universalsorter.service;

import com.universalsorter.model.Car;
import com.universalsorter.model.Storable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandler;
    private final String testFileName = "test_cars.txt";

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler();
    }

    @AfterEach
    void tearDown() {
        // Удаляем файл после теста
        File file = new File(testFileName);
        if (file.exists()) file.delete();
    }

    @Test
    void testWriteAndReadCarsFromFile() throws IOException {
        // Создаем список автомобилей
        Car car1 = new Car.Builder().model("Tesla Model S").power(1020.0).yearOfProduction(2020).build();
        Car car2 = new Car.Builder().model("BMW M3").power(473.0).yearOfProduction(2022).build();
        Car car3 = new Car.Builder().model("Audi RS7").power(591.0).yearOfProduction(2021).build();
        Car car4 = new Car.Builder().model("Mercedes-Benz E-Class").power(362.0).yearOfProduction(2023).build();
        Car car5 = new Car.Builder().model("Ford Mustang").power(450.0).yearOfProduction(2021).build();

        List<Car> carsToWrite = List.of(car1, car2, car3, car4, car5);

        // Записываем в файл
        for (Car car : carsToWrite) {
            fileHandler.writeToFile(testFileName, car);
        }

        // Читаем из файла
        List<Car> carsRead = fileHandler.readFromFile(testFileName);


        // Проверяем, что количество считанных объектов совпадает с ожидаемым
        assertEquals(carsToWrite.size(), carsRead.size());

        // Проверяем, что считанные объекты совпадают с исходными
        for (int i = 0; i < carsToWrite.size(); i++) {
            Car expectedCar = carsToWrite.get(i);
            Car actualCar = carsRead.get(i);
            assertEquals(expectedCar.getModel(), actualCar.getModel());
            assertEquals(expectedCar.getPower(), actualCar.getPower());
            assertEquals(expectedCar.getYearOfProduction(), actualCar.getYearOfProduction());
        }
    }
}
