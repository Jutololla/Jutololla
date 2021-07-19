package console;

import domain.*;
import java.io.Serializable;
import java.util.*;
import static test.main.*;

public class Console implements Serializable{

    int raceIdCount = 1;
    int driverIdCount = 1;
    int carIdCount = 1;
    List<Race> racesRecord = new ArrayList<Race>();
    static Map<Integer, Driver> driversRecord = new HashMap<Integer, Driver>();

    public Console() {
    }

    public int getRaceIdCount() {
        return raceIdCount;
    }

    public void setRaceIdCount(int raceIdCount) {
        this.raceIdCount = raceIdCount;
    }

    public int getDriverIdCount() {
        return driverIdCount;
    }

    public void setDriverIdCount(int driverIdCount) {
        this.driverIdCount = driverIdCount;
    }

    public int getCarIdCount() {
        return carIdCount;
    }

    public void setCarIdCount(int carIdCount) {
        this.carIdCount = carIdCount;
    }

    public List<Race> getRacesRecord() {
        return racesRecord;
    }

    public void setRacesRecord(List racesRecord) {
        this.racesRecord = racesRecord;
    }

    public Map<Integer, Driver> getDriversRecord() {
        return driversRecord;
    }

    public void setDriversRecord(Map driversRecord) {
        this.driversRecord = driversRecord;
    }

    
    
    public Race createRace() {
        //este metodo inicialmente pide trackLength para crear un objeto Race. Luego interactua con el usuario pidiendole nombres para crear Driver y Car. 
        //Conforme van siendo creados son asociados al ArrayList initialCarsOrderInLanes, el cual define el orden de los carros en los carriles.

        Scanner scanner = new Scanner(System.in);
        String auxString;
        List auxInitialCarsOrderInLanes = new ArrayList<Car>();
        Float auxFloat;
        int lane = 1;

        do {
            System.out.println("Ingrese de cuantos Km tiene la pista");
            auxString = scanner.nextLine();

            auxFloat = isValid(auxString);
            if (auxFloat == 0) {
                System.out.println("No ingresó un valor valido. Este debe ser numérico y mayor que 0. Recuerde que el separador decimal es el punto (.)\n Rectifique e intente nuevamente. ");
            }
        } while (auxFloat == 0);

        //var auxFloat = Float.parseFloat(auxString)*1000;
        //System.out.println("No ingreso un valor numerico para los Km de la pista. Intente de nuevo");
        Race race = new Race(auxFloat * 1000, raceIdCount);
        raceIdCount++;
        do {
            System.out.println("Ingrese el nombre del conductor");
            auxString = scanner.nextLine();
            Driver driverAux = new Driver(auxString, driverIdCount);
            driverIdCount++;
            Car carAux = new Car(driverAux, carIdCount, lane);
            carIdCount++;
            lane++;
            auxInitialCarsOrderInLanes.add(carAux);
            do {
                System.out.println("Desea agregar otro conductor?\n 1. Si \n 2. No");
                auxString = scanner.nextLine();
                switch (auxString) {
                    case "1" -> {
                    }
                    case "2" -> {
                        break;
                    }
                    default -> {
                        System.out.println("\nOpcion invalida");
                        System.out.println("Oprima cualquier tecla para continuar\n\n");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
            } while (!auxString.equals("2") && !auxString.equals("1"));

        } while (!auxString.equals("2"));
        race.setInitialCarsOrderInLanes(auxInitialCarsOrderInLanes);
        return race;

    }

    public Race duplicateRace(Race race) {
        Race duplicatedRace = new Race(race.getTrackLength(), raceIdCount);
        raceIdCount++;
        duplicatedRace.setInitialCarsOrderInLanes(race.getInitialCarsOrderInLanes());
        return duplicatedRace;
    }

    public static void showRaceParameters(Race race) {
        //Este metodo muestra los datos que tiene un objeto Race.
        int auxCount = 1;
        System.out.println("\n\n\n\n= RACE'S INFORMATION ="
                + "\n|  RaceID: " + race.getRaceId() + "; Track length: " + race.getTrackLength() / 1000 + " km\n"
                + "=INITIAL POSITIONS=\n"
                + "|  Lane  CarID   Driver's name  1st place  2nd place  3rd place ");
        for (Car car : race.getInitialCarsOrderInLanes()) {
            System.out.println("|   " + car.getLane() + "      " + car.getCarId() + "     " + car.getDriver().getName() + " "
                    + "  " + car.getDriver().getFirstPlaceCount() + "    " + car.getDriver().getSecondPlaceCount() + "    " + car.getDriver().getThirdPlaceCount());
        }
        System.out.println("= RACE'S RESULTS ="
                + "\n|Position Lane   CarID   Driver's name )");
        for (Car car : race.getCarsFinishingOrderRecord()) {
            System.out.println("|   " + auxCount + "      " + car.getLane() + "      " + car.getCarId() + "       " + car.getDriver().getName());
            auxCount++;
        }
        System.out.println("= END OF REPORT =");
    }

    public void startRace(Race race) {
        //este metodo ejecuta las siguientes rondas: car.run() para los elementos de initialCarsOrdersInLane; ordena de mayor a menor el avance de los carros;
        //evalua si alguno termino, si si entonces lo registra en driverFinishingOrder y lo retira de initialCarsOrdersInLane.
        //Luego evalua que initialCarsOrdersInLane>1, si si reinicia el proceso desde car.run(), sino significa que solo queda un carro en la carrera entonces lo agrega al ArrayList del orden de finalizacion.
        //Finalizado el ciclo anterior, Muestra la tabla de posiciones en consola y a traves del metodo writter.saveSummary() guarda en un .txt el resumen de la carrera.
        //Luego almacena copia los objetos Race y Track y los almacena dentro de un objeto RaceRecord, que es almacenado en un ArrayList<RaceRecord>.
        //A los tres primeros Driver's se les aniade a su ArrayList Victory<> el objeto Victory que contiene el resumen de su victoria.
        boolean allCarsFinished;
        Object auxObject;

        do {
            allCarsFinished = true;
            for (Car car : race.getInitialCarsOrderInLanes()) { //evalua si el auto termino la carrera, si no lo ha hecho ejecuta .run()
                if (!race.isFinishedThisCar(car, race)) {
                    car.run();
                    allCarsFinished = false;
                } else {
                    if (!race.getCarsFinishingOrderRecord().contains(car)) {
                        race.getCarsFinishingOrderRecord().add(car);
                        //RaceRecord raceRecordAux = new RaceRecord();
                        //car.getPilot().getVictoryRecord().add(victoryAux);
                    }
                }
            }
        } while (!allCarsFinished);

        //A partir de aqui se almacenan los datos de la carrera en tres lugares:
        // 1. Se acutaliza el medallero de los pilotos ganadores, y se agrega su carrera ganada a su historico de victorias
        // 2. Se agrega la carrera corrida al record de carreras
        // 3. Se actualizan/agregan los datos de los pilotos al registro de pilotos
        {  //1.
            //System.out.println(race.getCarsFinishingOrderRecord().size());
            
            
            for (int i = 1; i <= race.getCarsFinishingOrderRecord().size(); i++) {
                if (i == 1) {
                    race.getCarsFinishingOrderRecord().get(0).getDriver().setFirstPlaceCount(race.getCarsFinishingOrderRecord().get(0).getDriver().getFirstPlaceCount() + 1);
                    auxObject=race.getCarsFinishingOrderRecord().get(0).getDriver().getVictoryRecord().add(race.getRaceId());
                }
                if (i == 2) {
                    race.getCarsFinishingOrderRecord().get(1).getDriver().setSecondPlaceCount(race.getCarsFinishingOrderRecord().get(1).getDriver().getSecondPlaceCount() + 1);
                  auxObject=race.getCarsFinishingOrderRecord().get(1).getDriver().getVictoryRecord().add(race.getRaceId());
                   
                }
                if (i == 3) {
                    race.getCarsFinishingOrderRecord().get(2).getDriver().setThirdPlaceCount(race.getCarsFinishingOrderRecord().get(2).getDriver().getThirdPlaceCount() + 1);
                    auxObject=race.getCarsFinishingOrderRecord().get(2).getDriver().getVictoryRecord().add(race.getRaceId());
                }
            }
//                switch (i) {
//                    case 1: {
//                        race.getCarsFinishingOrderRecord().get(0).getDriver().setFirstPlaceCount(race.getCarsFinishingOrderRecord().get(0).getDriver().getFirstPlaceCount() + 1);
//                        race.getCarsFinishingOrderRecord().get(0).getDriver().getVictoryRecord().add(race);
//                    }
//                    case 2: {
//                        race.getCarsFinishingOrderRecord().get(1).getDriver().setSecondPlaceCount(race.getCarsFinishingOrderRecord().get(1).getDriver().getSecondPlaceCount() + 1);
//                    race.getCarsFinishingOrderRecord().get(1).getDriver().getVictoryRecord().add(race);
//                    }
//                    case 3: {
//                        race.getCarsFinishingOrderRecord().get(2).getDriver().setThirdPlaceCount(race.getCarsFinishingOrderRecord().get(2).getDriver().getThirdPlaceCount() + 1);
//                    race.getCarsFinishingOrderRecord().get(2).getDriver().getVictoryRecord().add(race);
//                    }
//                }}

//            race.getCarsFinishingOrderRecord().get(0).getDriver().setFirstPlaceCount(race.getCarsFinishingOrderRecord().get(0).getDriver().getFirstPlaceCount() + 1);
//            race.getCarsFinishingOrderRecord().get(1).getDriver().setSecondPlaceCount(race.getCarsFinishingOrderRecord().get(1).getDriver().getSecondPlaceCount() + 1);
//            race.getCarsFinishingOrderRecord().get(2).getDriver().setThirdPlaceCount(race.getCarsFinishingOrderRecord().get(2).getDriver().getThirdPlaceCount() + 1);
        }
        {//2.
            racesRecord.add(race);
        }
        { //3.
            for (Car car : race.getCarsFinishingOrderRecord()) {
                if (driversRecord.containsKey(car.getDriver().getDriverId())) {
                    auxObject = driversRecord.replace(car.getDriver().getDriverId(), car.getDriver());
                    //System.out.println("auxObject = " + auxObject);
                } else {
                    auxObject = driversRecord.put(car.getDriver().getDriverId(), car.getDriver());
                    //System.out.println("auxObject = " + auxObject);
                }

            }
        }

        //driversRecord.
    }
    
    public static void showDriverParameters(Driver driver, Console console) {
        //Este metodo muestra los datos que tiene un objeto Driver.
        int auxCount = 1;
        System.out.println("\n\n\n\n= DRIVER'S INFORMATION ="
                + "\n|  DriverID: " + driver.getDriverId()+ "; Driver's name: " + driver.getName()
                + "\n=MEDALS WON=\n"
                + "1st place  "+driver.getFirstPlaceCount()
                        + "\n2nd place  "+driver.getSecondPlaceCount()
                        + "\n3rd place  "+driver.getThirdPlaceCount()
        +"\n =RACES WON=");
        for(Integer race:driver.getVictoryRecord()){
            System.out.println("RaceID = " + race);
            //System.out.println("\nPlace  "+console.getRacesRecord().get(race).getCarsFinishingOrderRecord().indexOf(driver.getDriverId())+"\n");
        }
        System.out.println("= END OF REPORT =");
    }
    
//    public static void showDriversRegistry(){
//        Driver aux= new Driver();
//        aux = driversRecord.get(1);
//        showDriverParameters(aux);
//        
//    }
}
