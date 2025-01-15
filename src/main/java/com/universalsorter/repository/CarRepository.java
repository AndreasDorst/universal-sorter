package com.universalsorter.repository;
import com.universalsorter.model.Car;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private static final List<Car> CARS = new ArrayList<>();
    static {
        CARS.add(new Car.Builder().model("Tesla Model S").power(1020.0).yearOfProduction(2020).build());
        CARS.add(new Car.Builder().model("BMW M3").power(473.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Audi RS7").power(591.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Mercedes-Benz E-Class").power(362.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Ford Mustang").power(450.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("KIA Optima").power(150.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Porsche 911").power(640.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Toyota Supra").power(382.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Chevrolet Corvette").power(495.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Nissan GT-R").power(565.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Lamborghini Aventador").power(770.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Ferrari 488 GTB").power(661.0).yearOfProduction(2021).build());
    }

    public Car getCar(int i){
        if(i>=CARS.size()){return null;}
        return  CARS.get(i);
    }
    public int getSizeCarList(){
        return CARS.size();
    }
}
