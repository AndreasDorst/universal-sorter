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
        CARS.add(new Car.Builder().model("Volkswagen Golf GTI").power(245.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Hyundai Sonata").power(180.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Mazda CX-5").power(187.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Subaru WRX STI").power(310.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Lexus LS 500").power(416.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Jaguar F-Type").power(444.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("McLaren 720S").power(710.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Bugatti Chiron").power(1500.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Aston Martin DB11").power(630.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Rolls-Royce Phantom").power(563.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Honda Civic Type R").power(320.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Volvo XC90").power(316.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Dodge Challenger").power(717.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Alfa Romeo Giulia").power(505.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Maserati Quattroporte").power(580.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Genesis G90").power(365.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Infiniti Q50").power(300.0).yearOfProduction(2022).build());
        CARS.add(new Car.Builder().model("Land Rover Defender").power(395.0).yearOfProduction(2021).build());
        CARS.add(new Car.Builder().model("Jeep Wrangler").power(285.0).yearOfProduction(2023).build());
        CARS.add(new Car.Builder().model("Mini Cooper S").power(189.0).yearOfProduction(2022).build());
    }

    public Car getCar(int i){
        if(i>=CARS.size()){return null;}
        return  CARS.get(i);
    }
    public int getSizeCarList(){
        return CARS.size();
    }
}
