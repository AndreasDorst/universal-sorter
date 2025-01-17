package com.universalsorter.model;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс корнеплод {@link RootVegetable} с полями <b>type</b> and <b>weight</b> and <b>color</b>
 * {@code @autor} EvgenSharapov, Andreas, Lemonsqz, Aleksandr Bogin
 * @version 1.1
 */
public class RootVegetable implements Storable,Comparable<RootVegetable>, Comparator<RootVegetable> {

    /**
     * Тип корнеплода
     * --GETTER--
     *  Return тип корнеплода
     * */
    private final String type;
    /**
     * Вес корнеплода
     * --GETTER--
     *  Return вес корнеплода
     * */
    private final Double weight;
    /**
     * Цвет корнеплода
     * --GETTER--
     *  Return цвет корнеплода
     * */
    private final String color;

    /**
     * Конструктор - создание нового объекта класса
     * @param type - тип корнеплода
     * @param weight - вес корнеплода
     * @param color - цвет корнеплода
     * @see RootVegetable#RootVegetable(String type, Double weight, String color)
     */
    private RootVegetable(String type, Double weight, String color) {
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    /**
     * Метод для создания объекта RootVegetable {@link RootVegetable}
     * @return возвращает объект класса {@link RootVegetable}
     */
    public static RootVegetable.Builder builder() {
        return new RootVegetable.Builder();
    }

    /**
     *  Переопределенный метод интерфейса Comparable {@link Comparable}
     * @param rootVegetable объект для сравнения
     * @return отрицательное целое число, ноль или положительное целое число, поскольку этот объект меньше, равен или больше указанного объекта
     *
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compareTo(RootVegetable rootVegetable) {
        if (rootVegetable == null) {
            throw new NullPointerException("Сравниваемый объект не может быть null");
        }

        // Сравнение по type (тип корнеплода)
        int typeComparison = this.type.compareTo(rootVegetable.type);
        if (typeComparison != 0) {
            return typeComparison;
        }

        // Если типы одинаковые, сравниваем по weight (вес)
        int weightComparison = this.weight.compareTo(rootVegetable.weight);
        if (weightComparison != 0) {
            return weightComparison;
        }

        // Если вес одинаковый, сравниваем по color (цвет)
        return this.color.compareTo(rootVegetable.color);
    }

    /**
     *  Переопределенный метод интерфейса Comparator {@link Comparator}
     * @param veg1 первый корнеплод для сравнения.
     * @param veg2 второй корнеплод для сравнения.
     * @return отрицательное целое число, ноль или положительное целое число, поскольку первый аргумент меньше, равен или больше второго.
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compare(RootVegetable veg1, RootVegetable veg2) {
        int typeComparison = veg1.getType().compareTo(veg2.getType());
        if (typeComparison != 0) {
            return typeComparison;
        }
        int weightComparison = veg1.getWeight().compareTo(veg2.getWeight());
        if (weightComparison != 0) {
            return weightComparison;
        }
        return veg1.getColor().compareTo(veg2.getColor());
    }


    public static class Builder {
        private String type;
        private Double weight;
        private String color;

        private void validate(){
            final double MINIMUM_WEIGHT = 0; // минимальный вес корнеплода
            final double MAXIMUM_WEIGHT = 10000; // максимальный вес корнеплода
            final String ERROR_MESSAGE = "Введены некорректные данные"; // сообщение об ошибке
            if (type == null || color == null || type.isEmpty() || color.isEmpty() ||
                    weight <= MINIMUM_WEIGHT || weight > MAXIMUM_WEIGHT) {
                throw new IllegalStateException(ERROR_MESSAGE);
            }
        }

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
            validate();
            return new RootVegetable(type, weight, color);
        }
    }

    /**
     * Метод для получения значения поля {@link RootVegetable#type}
     * @return возвращает тип корнеплода {@link String}.
     */
    public String getType() {
        return type;
    }

    /**
     * Метод для получения значения поля {@link RootVegetable#weight}
     * @return возвращает вес корнеплода {@link Double}.
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Метод для получения значения поля {@link RootVegetable#type}
     * @return возвращает цвет корнеплода {@link String}.
     */
    public String getColor() {
        return color;
    }


    /**
     *  Переопределенный метод интерфейса {@link Storable} для сохранения объектов класса {@link RootVegetable}.
     * @return строковое представление {@link String} сохраняемого объекта {@link RootVegetable}
     */
    @Override
    public String serialize() {
        return String.format(Locale.US,"RootVegetable,%s,%.2f,%s", type, weight, color);
    }

    /**
     *  Переопределенный метод интерфейса {@link Storable} восстановления сохраненного объекта класса {@link RootVegetable}.
     * @return объект класса {@link RootVegetable}
     */
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

    /**
     * Указывает, равен ли какой-либо другой объект этому объекту, класс объектов {@link RootVegetable}. Этот метод должен подчиняться общему договору {@link Object#equals(Object)}.
     * @param o объект для сравнения.
     * @return {@code true} только если указанный объект также {@link RootVegetable} и имеет одинаковые поля.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootVegetable that = (RootVegetable) o;
        return Objects.equals(type, that.type) && Objects.equals(weight, that.weight) && Objects.equals(color, that.color);
    }

    /**
     * Переопределенный метод класса {@link Object}, который генерирует и возвращает хэш-кода для объекта {@link RootVegetable}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает значение хэш-кода для этого объекта класса {@link RootVegetable}
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, weight, color);
    }

    /**
     * Переопределенный метод класса {@link Object}, возвращает строковое представление объекта. {@link RootVegetable}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает строковое представление объекта класса {@link RootVegetable}
     */
    @Override
    public String toString() {
        return "Тип корнеплода: " + type + "\n" +
                "Вес: " + weight +"\n"+
                "Цвет: " + color + "\n";
    }
}
