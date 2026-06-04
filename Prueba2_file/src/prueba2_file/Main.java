/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2_file;

import java.util.Scanner;

/**
 *
 * @author alira
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);

        GestorTareas gestor = new GestorTareas();

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero(teclado);

            switch (opcion) {

                case 1:
                    agregarTarea(teclado, gestor);
                    break;

                case 2:
                    gestor.mostrarTareas();
                    break;

                case 3:
                    completarTarea(teclado, gestor);
                    break;

                case 4:
                    System.out.println("\nPrograma finalizado.");
                    break;

                default:
                    System.out.println("\nOpción inválida.");
            }

        } while (opcion != 4);

        teclado.close();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println("GESTOR DE TAREAS");
        System.out.println("====================");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Mostrar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void agregarTarea(
            Scanner teclado,
            GestorTareas gestor) {

        System.out.print("Ingresa la nueva tarea: ");

        String descripcion = teclado.nextLine().trim();

        if (descripcion.isEmpty()) {

            System.out.println("La descripción está vacía.");
            return;
        }

        if (descripcion.contains("|")) {

            System.out.println("No se permite el carácter |");
            return;
        }

        gestor.agragarTareas(descripcion);
    }

    private static void completarTarea(
            Scanner teclado,
            GestorTareas gestor) {

        if (gestor.getCantidad() == 0) {

            System.out.println("\nNo hay tareas registradas.");
            return;
        }

        gestor.mostrarTareas();

        System.out.print("\nNúmero de tarea a completar: ");

        int numero = leerEntero(teclado);

        gestor.completarTarea(numero);
    }

    private static int leerEntero(Scanner teclado) {

        try {

            return Integer.parseInt(
                    teclado.nextLine().trim());

        } catch (NumberFormatException e) {

            return -1;
        }
    }
    
}
