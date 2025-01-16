package com.universalsorter.model;

import java.time.Year;
import java.util.Locale;

public class Car implements Storable, Comparable {

    private final String model;
    private final Double power;
    private final Integer yearOfProduction;

    private Car(String model, Double power, Integer yearOfProduction) {
        this.model = model;
        this.power = power;
        this.yearOfProduction = yearOfProduction;
    }


    public static Book.Builder builder() {
        return new Book.Builder();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public static class Builder {
        private String model;
        private Double power;
        private Integer yearOfProduction;

        private void validate() {
            final double MINIMUM_POWER = 0; // минимальное количество лошадиных сил
            final double MAXIMUM_POWER = 2000; // максимальное количество лошадиных сил
            final int MIN_YEAR_OF_PRODUCTION = 1800; // минимальная дата производства
            final int MAX_YEAR_OF_PRODUCTION = Year.now().getValue(); // максимальная дата производства
            final String ERROR_MESSAGE = "Введены некорректные данные"; // сообщение об ошибке

            if (model == null || power == null || model.isEmpty() || power <= MINIMUM_POWER || power > MAXIMUM_POWER ||
                    yearOfProduction < MIN_YEAR_OF_PRODUCTION || yearOfProduction > MAX_YEAR_OF_PRODUCTION) {
                throw new IllegalStateException(ERROR_MESSAGE);
            }

        }


        public Car.Builder model(String model) {
            this.model = model;
            return this;
        }

        public Car.Builder power(Double power) {
            this.power = power;
            return this;
        }

        public Car.Builder yearOfProduction(Integer yearOfProduction) {
            this.yearOfProduction = yearOfProduction;
            return this;
        }

        public Car build() {
            validate();
            return new Car(model, power, yearOfProduction);
        }
    }

    public String getModel() {
        return model;
    }

    public Double getPower() {
        return power;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    @Override
    public String serialize() {
        return String.format(Locale.US, "Car,%s,%.2f,%d", model, power, yearOfProduction);
    }

    @Override
    public Car deserialize(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4 || !"Car".equals(parts[0])) {
            throw new IllegalArgumentException("Некорректные данные для десериализации Car: " + data);
        }
        return new Car(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }

    @Override
    public String toString() {
        return "Модель: " + model + "\n" +
                "Мощность: " + power + "\n" +
                "Год выпуска: " + yearOfProduction + "\n";
    }
}
