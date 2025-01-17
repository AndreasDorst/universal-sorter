package com.universalsorter.model;
import java.util.Locale;

public class RootVegetable implements Storable,Comparable{

    private final String type;
    private final Double weight;
    private final String color;

    private RootVegetable(String type, Double weight, String color) {
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    public static RootVegetable.Builder builder() {
        return new RootVegetable.Builder();
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("Сравниваемый объект не может быть null");
        }
        if (!(o instanceof RootVegetable)) {
            throw new ClassCastException("Объект должен быть типа RootVegetable");
        }

        RootVegetable other = (RootVegetable) o;

        // Сравнение по type (тип корнеплода)
        int typeComparison = this.type.compareTo(other.type);
        if (typeComparison != 0) {
            return typeComparison;
        }

        // Если типы одинаковые, сравниваем по weight (вес)
        int weightComparison = this.weight.compareTo(other.weight);
        if (weightComparison != 0) {
            return weightComparison;
        }

        // Если вес одинаковый, сравниваем по color (цвет)
        return this.color.compareTo(other.color);
    }


    public static class Builder {
        private String type;
        private Double weight;
        private String color;


        public RootVegetable.Builder type(String type) {
            this.type = type;
            return this;
        }

        public RootVegetable.Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public RootVegetable.Builder color(String color) {
            this.color = color;
            return this;
        }

        public RootVegetable build() {
            if (type == null || weight <= 0 || color == null) {
                throw new IllegalStateException("Введены некорректные данные");
            }
            return new RootVegetable(type, weight, color);
        }
    }

    public String getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String serialize() {
        return String.format(Locale.US,"RootVegetable,%s,%.2f,%s", type, weight, color);
    }

    @Override
    public RootVegetable deserialize(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4 || !"RootVegetable".equals(parts[0])) {
            throw new IllegalArgumentException("Некорректные данные для десериализации RootVegetable: " + data);
        }
        return RootVegetable.builder()
                .type(parts[1])
                .weight(Double.valueOf(parts[2]))
                .color(parts[3])
                .build();
    }

    @Override
    public String toString() {
        return "Тип корнеплода: " + type + "\n" +
                "Вес: " + weight +"\n"+
                "Цвет: " + color + "\n";
    }
}
