package domain;

import java.io.Serializable;
import java.util.*;

public class Race implements Serializable{
    int raceId;
    float trackLength=-1;
    List<Car> initialCarsOrderInLanes = new ArrayList<Car>();
    List<Car> carsFinishingOrderRecord = new ArrayList<Car>();

    public Race() {
    }
    
    public Race(float trackLength, int raceIdCount) {
        this.trackLength = trackLength;
        this.raceId=raceIdCount;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public List<Car> getInitialCarsOrderInLanes() {
        return initialCarsOrderInLanes;
    }

    public void setInitialCarsOrderInLanes(List initialCarsOrderInLanes) {
        this.initialCarsOrderInLanes = initialCarsOrderInLanes;
    }

    public List<Car> getCarsFinishingOrderRecord() {
        return carsFinishingOrderRecord;
    }

    public void setCarsFinishingOrderRecord(List carsFinishingOrderRecord) {
        this.carsFinishingOrderRecord = carsFinishingOrderRecord;
    }

    public float getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }
    
    
    public boolean isFinishedThisCar(Car car, Race race){
        //esta funcion compara los metros recorridos del carro que se ingresa con el largo de la pista. Si estos son iguales, o los metros recorridos mayores, entonces true, else false.
        return car.metersRunned>=race.trackLength;};

    @Override
    public String toString() {
        return "Race{" + "raceId=" + raceId + ", trackLength=" + trackLength + ", initialCarsOrderInLanes=" + initialCarsOrderInLanes + ", carsFinishingOrderRecord=" + carsFinishingOrderRecord + '}';
    }

  
    
    
    
    
    
    
    
}
