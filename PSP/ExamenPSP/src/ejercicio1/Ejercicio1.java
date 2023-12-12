package ejercicio1;

import java.io.File;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        if (!comprobarParametros(args))
            throw new IllegalArgumentException("Introduce el directorio de trabajo por parámetro");

        String dir = args[0];

        System.out.println("dir=" + dir);

        try {
            Runtime.getRuntime().exec("pwd", null, new File(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean comprobarParametros(String[] args) {
        return args.length == 1;
    }
}