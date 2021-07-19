package domain;

import java.io.Serializable;
import java.util.*;
import test.*;

public class Driver implements Serializable {

    String name;
    int driverId, firstPlaceCount = 0, secondPlaceCount = 0, thirdPlaceCount = 0;
    List<Integer> victoryRecord = new ArrayList();

    public Driver() {
    }

    public Driver(String name, int driverIdCount) {
        this.name = name;
        this.driverId = driverIdCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getFirstPlaceCount() {
        return firstPlaceCount;
    }

    public void setFirstPlaceCount(int firstPlaceCount) {
        this.firstPlaceCount = firstPlaceCount;
    }

    public int getSecondPlaceCount() {
        return secondPlaceCount;
    }

    public void setSecondPlaceCount(int secondPlaceCount) {
        this.secondPlaceCount = secondPlaceCount;
    }

    public int getThirdPlaceCount() {
        return thirdPlaceCount;
    }

    public void setThirdPlaceCount(int thirdPlaceCount) {
        this.thirdPlaceCount = thirdPlaceCount;
    }

    public List<Integer> getVictoryRecord() {
        return victoryRecord;
    }

    public void setVictoryRecord(List<Integer> victoryRecord) {
        this.victoryRecord = victoryRecord;
    }

    @Override
    public String toString() {
        return "Driver{" + "name=" + name + ", driverId=" + driverId + ", firstPlaceCount=" + firstPlaceCount + ", secondPlaceCount=" + secondPlaceCount + ", thirdPlaceCount=" + thirdPlaceCount + ", victoryRecord=" + victoryRecord + '}';
    }

}
