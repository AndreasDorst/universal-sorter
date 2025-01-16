package com.universalsorter.model;
import java.util.Locale;

public class Car implements Storable,Comparable{

    private final String model;
    private final Double power;
    private final Integer yearOfProduction;

    private Car(String model, Double power, Integer yearOfProduction) {
        this.model = model;
        this.power = power;
        this.yearOfProduction = yearOfProduction;
    }


    public static Car.Builder builder() {
        return new Car.Builder();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public static class Builder {
        private String model;
        private Double power;
        private Integer yearOfProduction;


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
            if (model == null || power <= 0 || yearOfProduction < 1800) {
                throw new IllegalStateException("Введены некорректные данные");
            }
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
        return String.format(Locale.US,"Car,%s,%.2f,%d", model, power, yearOfProduction);
    }

    @Override
    public Car deserialize(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4 || !"Car".equals(parts[0])) {
            throw new IllegalArgumentException("Некорректные данные для десериализации Car: " + data);
        }
        return Car.builder()
                .model(parts[1])
                .power(Double.valueOf(parts[2]))
                .yearOfProduction(Integer.valueOf(parts[3]))
                .build();
    }

    @Override
    public String toString() {
        return "Модель: " + model + "\n" +
                "Мощность: " + power + "\n"+
                "Год выпуска: " + yearOfProduction+"\n";
    }
}
