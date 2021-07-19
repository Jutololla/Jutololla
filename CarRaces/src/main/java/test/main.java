package test;

import conection.Conection;
import console.Console;
import domain.Driver;
import domain.Race;
import java.io.IOException;
import java.util.*;

public class main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Console console = new Console();    //se crea consola
        console=Conection.restore(console);//
        Race auxRace = new Race();   // se crea carrera auxiliar
        
        //int raceIdCount = 1;
        //int driverIdCount = 1;
        //int carIdCount = 1;
        String auxString;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n\n\n----------------------------------------------------------------------------------------------------------------------------"
                    + "\nBienvenido al simulador de carreras de autos. Por favor seleccione una opcion: "
                    + "\n 1. Jugar"
                    + "\n 2. Consultar historico"
                    + "\n 3. Salir del simulador\n");
            auxString = scanner.nextLine();
            switch (auxString) {
                case "1" -> {
                    System.out.println("case 1");
                    auxRace = console.createRace();
                    console.startRace(auxRace);
                    console.showRaceParameters(auxRace);
                    Conection.save(console);//Se guarda el objeto Consola en un archivo externo para que persistan los datos

                    do {

                        System.out.println("\nMenu:"
                                + "\n 1. Volver al menu inicial"
                                + "\n 2. Correr de nuevo");

                        auxString = scanner.nextLine();
                        switch (auxString) {
                            case "2" -> {
                                System.out.println("Se va a correr de nuevo\n");
                                auxRace = console.duplicateRace(auxRace);
                                console.startRace(auxRace);
                                console.showRaceParameters(auxRace);
                                Conection.save(console);//Se guarda el objeto Consola en un archivo externo para que persistan los datos
                            }
                            case "1" -> {
                                break;
                            }
                            default -> {
                                System.out.println("\nOpcion invalida");
                                System.out.println("Oprima cualquier tecla para continuar\n\n");
                                new java.util.Scanner(System.in).nextLine();
                            }
                        }
                    } while (!auxString.equals("1"));
                    //while (auxString!='3');
                }
                case "2" -> {
                    if (console.getRaceIdCount() == 1) {
                        System.out.println("No hay datos historicos registrados aun");
                        break;
                    } else {
                        do {
                            System.out.println("Consultar:"
                                    + "\n 1. Volver al menu anterior \n 2. Carreras \n 3. Pilotos");
                            auxString = scanner.nextLine();
                            switch (auxString) {
                                case "3" -> {
                                    Map<Integer, Driver> map = console.getDriversRecord();

                                    for (Map.Entry<Integer, Driver> iterator : map.entrySet()) {
                                        //System.out.print(iterator.getKey() + " - " + iterator.getValue());
                                        Console.showDriverParameters(iterator.getValue(),console);
                                        
                                    }
                                    System.out.println("Oprima cualquier tecla para volver al menu anterior");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                                case "2" -> {
                                    //despliega el historico de todas las carreras hechas a traves del ArrayList raceRecord
                                    //System.out.println("despliega el historico de todas las carreras hechas a traves del ArrayList raceRecord");

                                    for (int i = 0; i < console.getRacesRecord().size(); i++) {
                                        Race auxRace2 = new Race();
                                        auxRace2 = console.getRacesRecord().get(i);
                                        console.showRaceParameters(auxRace2);

                                    }

                                    System.out.println("Oprima cualquier tecla para volver al menu anterior");
                                    new java.util.Scanner(System.in).nextLine();
                                }

                                case "1" -> {
                                    break;
                                }
                                default -> {
                                    System.out.println("\nOpcion invalida");
                                    System.out.println("Oprima cualquier tecla para continuar\n\n");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }

                        } while (!auxString.equals("1"));
                    }
                }

                case "3" -> {
                    break;
                }
                default -> {
                    System.out.println("\nOpcion invalida");
                    System.out.println("Oprima cualquier tecla para continuar\n\n");
                    new java.util.Scanner(System.in).nextLine();
                }
            }

        } while (!auxString.equals("3"));
    }

    public static boolean isFloat(String cad) {
        try {
            Float.parseFloat(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isInt(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static float isValid(String cad) {

        if (isFloat(cad) && Float.parseFloat(cad) > 0) {
            return Float.parseFloat(cad);
        }
        //if (a > 0) {
        //  return a;

        if (isInt(cad) && Integer.parseInt(cad) > 0) {
            float a = Integer.parseInt(cad);
            return a;

        } else {
            System.out.println("mensaje de error");
            float a = 0;
            return a;
        }
    }
}
