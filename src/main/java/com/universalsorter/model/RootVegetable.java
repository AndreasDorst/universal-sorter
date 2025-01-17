package com.universalsorter.model;
import java.util.Locale;

public class RootVegetable implements Storable,Comparable {

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
        return 0;
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
        return new RootVegetable(parts[1], Double.parseDouble(parts[2]), parts[3]);
    }

    @Override
    public String toString() {
        return "Тип корнеплода: " + type + "\n" +
                "Вес: " + weight +"\n"+
                "Цвет: " + color + "\n";
    }
}
