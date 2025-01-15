package com.universalsorter.model;

public class Car implements Storable,Comparable {

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
        return String.format("Car,%s,%.2f,%d", model, power, yearOfProduction);
    }

    @Override
    public Storable deserialize(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4 || !"Car".equals(parts[0])) {
            throw new IllegalArgumentException("Некорректные данные для десериализации Car: " + data);
        }
        return new Car(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }

    @Override
    public String toString() {
        return "Модель: " + model + "\n" +
                "Мощность: " + power + "\n"+
                "Год выпуска: " + yearOfProduction+"\n";
    }
}
