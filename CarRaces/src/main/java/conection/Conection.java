package conection;

import java.io.*;
import console.Console;

public class Conection {

    public static void save(Console console) throws FileNotFoundException, IOException {//este metodo guarda en un archivo externo .txt el objeto consola usado en la ejecucion
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("archivo.obj"));
        output.writeObject(console);
        output.close();
    }

    public static Console restore(Console console2) throws IOException, ClassNotFoundException {
        if (fileExist()) {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("archivo.obj"));
            Console console = (Console) input.readObject();
            input.close();
            return console;
        } else {
            save(console2);
            return console2;
        }
    }

    public static Boolean fileExist() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("archivo.obj"));
            Console console = (Console) input.readObject();
            input.close();
            return true;
        } catch (Exception nfe) {
            return false;
        }
    }
}
