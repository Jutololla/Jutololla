package domain;

import java.io.Serializable;


public class Car implements Serializable{
    Driver driver;
    int lane;
    int metersRunned;
    int carId;

    public Car(Driver pilot, int carIdCount, int lane) {
        this.driver = pilot;
        this.carId=carIdCount;
        this.lane=lane;
        this.metersRunned = 0;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getMetersRunned() {
        return metersRunned;
    }

    public void setMetersRunned(int metersRunned) {
        this.metersRunned = metersRunned;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    
    public void run(){
        int random_int = (int)(Math.random()*6)+1;
        this.metersRunned=metersRunned+random_int*100;
        //este metodo genera un numero al azar del 1 al 6 entero, el cual se multiplica por 100 y luego es sumado al valor actual de metersRunned  
    };

    @Override
    public String toString() {
        return "Car{" + "driver=" + driver + ", lane=" + lane + ", metersRunned=" + metersRunned + ", carId=" + carId + '}';
    }

    
    
    
}
