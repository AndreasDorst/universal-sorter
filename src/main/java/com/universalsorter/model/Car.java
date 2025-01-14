package com.universalsorter.model;




public class Car {


    private final String model;
    private final Double power;
    private final Integer yearOfProduction;

    public Car(String model, Double power, Integer yearOfProduction) {
        this.model = model;
        this.power = power;
        this.yearOfProduction = yearOfProduction;
    }



    public static Book.Builder builder() {
        return new Book.Builder();
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
            if (model == null || power <=0 || yearOfProduction <1800) {
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
}
