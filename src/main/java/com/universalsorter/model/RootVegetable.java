package com.universalsorter.model;
import lombok.Getter;



public class RootVegetable {

    private final String type;
    private final Double weight;
    private final Color color;

    public RootVegetable(String type, Double weight, Color color) {
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    public static RootVegetable.Builder builder() {
        return new RootVegetable.Builder();
    }

    public static class Builder {
        private String type;
        private Double weight;
        private Color color;


        public RootVegetable.Builder type(String type) {
            this.type = type;
            return this;
        }

        public RootVegetable.Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public RootVegetable.Builder color(Color color) {
            this.color = color;
            return this;
        }

        public RootVegetable build() {
            if (type == null || weight <=0 || color ==null) {
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

    public Color getColor() {
        return color;
    }


}
