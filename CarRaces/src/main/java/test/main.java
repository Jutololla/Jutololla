package test;

import conection.Conection;
import console.Console;
import domain.Driver;
import domain.Race;
import java.io.IOException;
import java.util.*;

public class main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Console console = new Console();    //se crea consola vacia
        console = Conection.restore(console);// se sobrescribe con la existente en archivo (si es que existe, sino pues queda vacia)
        Race auxRace = new Race();   // se crea carrera auxiliar vacia
        if (!console.getRacesRecord().isEmpty()) {
            auxRace = console.getRacesRecord().get(console.getRacesRecord().size() - 1); //se sobrescribe con la ultima carrera existente
        }
        String auxString;  //objeto auxiliar
        Scanner scanner = new Scanner(System.in); //objeto auxiliar

        do {
            System.out.println("\n\n\n----------------------------------------------------------------------------------------------------------------------------"
                    + "\nBienvenido al simulador de carreras de autos. Por favor seleccione una opcion: "
                    + "\n 1. Jugar"
                    + "\n 2. Consultar historico"
                    + "\n 3. Borrar datos históricos"
                    + "\n 4. Salir del simulador\n");
            auxString = scanner.nextLine();
            switch (auxString) {
                case "1" -> {
                    auxRace = console.createRace(console);
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
                                        Console.showDriverParameters(iterator.getValue(), console);
                                    //Console.showDriversAvailable(console);
                                    
                                }
                                    System.out.println("Oprima cualquier tecla para volver al menu anterior");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                                case "2" -> {
                                    //despliega el historico de todas las carreras hechas a traves del ArrayList raceRecord
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
                case "4" -> {
                    break;}
                    case "3" ->{
                        Conection.delete();
                        System.out.println("Se han borrado los datos históricos. \nSe cerrará el juego.");
                        auxString="4";
                        break;
                }
                
                default -> {
                    main.invalidOption();
                    new java.util.Scanner(System.in).nextLine();
                }
            }
        } while (!auxString.equals("4"));
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

    public static float convertToValid(String cad) {
        if (isFloat(cad) && Float.parseFloat(cad) > 0) {
            return Float.parseFloat(cad);
        }
        if (isInt(cad) && Integer.parseInt(cad) > 0) {
            float a = Integer.parseInt(cad);
            return a;
        } else {
            System.out.println("mensaje de error");
            float a = 0;
            return a;
        }
    }

    public static void invalidOption() {
        System.out.println("_______________________"
                + "|   Opción no válida   |"
                + "| Rectifique e intente |"
                + "|     nuevamente       |"
                + "          |______________________|");

    }
}
