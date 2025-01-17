package com.universalsorter.model;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс машина {@link Car} с полями <b>author</b> and <b>title</b> and <b>numberOfPages</b>
 * {@code @autor} EvgenSharapov, Andreas, Lemonsqz, Aleksandr Bogin
 * @version 1.1
 */
public class Car implements Storable,Comparable<Car>, Comparator<Car> {

    /**
     * Модель машины
     * --GETTER--
     *  Return модель машины.
     * */
    private final String model;
    /**
     * Мощность машины
     * --GETTER--
     *  Return мощность машины.
     * */
    private final Double power;
    /**
     * Год выпуска Машины
     * --GETTER--
     *  Return год выпуска машины.
     * */
    private final Integer yearOfProduction;


    /**
     * Конструктор - создание нового объекта класса
     * @param model - модель машины
     * @param power - мощность машины
     * @param yearOfProduction - год выпуска машины
     * @see Car#Car(String model, Double power, Integer yearOfProduction)
     */
    private Car(String model, Double power, Integer yearOfProduction) {
        this.model = model;
        this.power = power;
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * Метод для создания объекта Car {@link Car}
     * @return возвращает название объект класса {@link Car}
     */
    public static Car.Builder builder() {
        return new Car.Builder();
    }


    /**
     *  Переопределенный метод интерфейса Comparable {@link Comparable}
     * @param car объект для сравнения
     * @return отрицательное целое число, ноль или положительное целое число, поскольку этот объект меньше, равен или больше указанного объекта
     *
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compareTo(Car car) {
            if (car == null) {
                throw new NullPointerException("Сравниваемый объект не может быть null");
            }

            // Сравнение по model (модель автомобиля)
            int modelComparison = this.model.compareTo(car.model);
            if (modelComparison != 0) {
                return modelComparison;
            }

            // Если модели одинаковые, сравниваем по power (мощность)
            int powerComparison = this.power.compareTo(car.power);
            if (powerComparison != 0) {
                return powerComparison;
            }

            // Если мощность одинаковая, сравниваем по yearOfProduction (год выпуска)
            return this.yearOfProduction.compareTo(car.yearOfProduction);

    }

    /**
     *  Переопределенный метод интерфейса Comparator {@link Comparator}
     * @param car1 первая машина для сравнения.
     * @param car2 вторая машина для сравнения.
     * @return отрицательное целое число, ноль или положительное целое число, поскольку первый аргумент меньше, равен или больше второго.
     * @throws NullPointerException  если в параметры передается null
     */
    @Override
    public int compare(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            throw new NullPointerException("Сравниваемые объекты не могут быть null");
        }
        int modelComparison = car1.getModel().compareTo(car2.getModel());
        if (modelComparison != 0) {
            return modelComparison;
        }
        int powerComparison = car1.getPower().compareTo(car2.getPower());
        if (powerComparison != 0) {
            return powerComparison;
        }
        return car1.getYearOfProduction().compareTo(car2.getYearOfProduction());
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

    /**
     * Метод для получения значения поля {@link Car#model}
     * @return модель машины {@link String}.
     */
    public String getModel() {
        return model;
    }

    /**
     * Метод для получения значения поля {@link Car#power}
     * @return мощность машины {@link Double}.
     */
    public Double getPower() {
        return power;
    }
    /**
     * Метод для получения значения поля {@link Car#yearOfProduction}
     * @return мощность машины {@link Integer}.
     */
    public Integer getYearOfProduction() {
        return yearOfProduction;
    }


    /**
     *  Переопределенный метод интерфейса {@link Storable} для сохранения объектов класса {@link Car}.
     * @return строковое представление {@link String} сохраняемого объекта {@link Car}.
     */
    @Override
    public String serialize() {
        return String.format(Locale.US,"Car,%s,%.2f,%d", model, power, yearOfProduction);
    }


    /**
     *  Переопределенный метод интерфейса {@link Storable} восстановления сохраненного объекта класса {@link Car}.
     * @return объект класса {@link Car}
     */
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

    /**
     * Указывает, равен ли какой-либо другой объект этому объекту, класс объектов {@link Car}. Этот метод должен подчиняться общему договору {@link Object#equals(Object)}.
     * @param o объект для сравнения.
     * @return {@code true} только если указанный объект также {@link Car} и имеет одинаковые поля.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(power, car.power) && Objects.equals(yearOfProduction, car.yearOfProduction);
    }

    /**
     * Переопределенный метод класса {@link Object}, который генерирует и возвращает хэш-кода для объекта {@link Car}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает значение хэш-кода для этого объекта класса {@link Car}
     */
    @Override
    public int hashCode() {
        return Objects.hash(model, power, yearOfProduction);
    }

    /**
     * Переопределенный метод класса {@link Object}, возвращает строковое представление объекта. {@link Car}. Этот метод должен подчиняться общему договору {@link Object#hashCode()}.
     * @return возвращает строковое представление объекта класса {@link Car}
     */
    @Override
    public String toString() {
        return "Модель: " + model + "\n" +
                "Мощность: " + power + "\n"+
                "Год выпуска: " + yearOfProduction+"\n";
    }
}
