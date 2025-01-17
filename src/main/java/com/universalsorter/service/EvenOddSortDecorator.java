package com.universalsorter.service;

import com.universalsorter.model.Car;

import java.util.*;

public class EvenOddSortDecorator {



    public static void sortCarsByYearOfProduction(List<Car> cars) {
        List<Car> evenCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYearOfProduction() % 2 == 0) {
                evenCars.add(car);
            }
        }
        Collections.sort(evenCars, Comparator.comparing(Car::getYearOfProduction));

        Iterator<Car> evenIterator = evenCars.iterator();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getYearOfProduction() % 2 == 0 && evenIterator.hasNext()) {
                cars.set(i, evenIterator.next());
            }
        }
    }
}
