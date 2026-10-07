package main;

import utils.MetodosCSV;

public class Main {

    public static void main(String[] args) {

        String nombre = "cafeteria";

        System.out.println(" CSV A BINARIO ");
        MetodosCSV.fichero_CSV_To_Binario(nombre);

        System.out.println("\n BINARIO A CSV ");
        MetodosCSV.fichero_Binario_To_CSV(nombre);

        System.out.println("\n ORDENAR CSV ");
        MetodosCSV.ordenar_Archivo_CSV(nombre + ".csv");

        System.out.println("\n ORDENAR BINARIO ");
        MetodosCSV.ordenar_Archivo_Binario(nombre + ".dat");

        System.out.println("\n BINARIO A CSV ORDENADO ");
        MetodosCSV.fichero_Binario_To_CSV_Ordenado(nombre);

        System.out.println("\n FIN DE LAS PRUEBAS ");
    }
}