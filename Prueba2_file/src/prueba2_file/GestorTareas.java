/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author alira
 */
public class GestorTareas {
    
    private static final String ARCHIVO= "tareas.txt";
    
    private Tarea[] tareas;
    private int cantidad;
    
    public GestorTareas(){
        tareas = new Tarea[10];
        cantidad = 0;
        cargarTareas();
        
    }
    
    public void agragarTareas(String descripcion){
        
        Tarea nueva = new Tarea(descripcion);
        
        agregarAlArreglo(nueva);
        
        guardarTareas();
        
        System.out.println("\n ^Tarea agregada:" +descripcion);
    }
    
    public void mostrarTareas(){
        
        if(cantidad == 0){
            System.out.println("\nNo hay tareas registradas.");
            return;
        }
        
        System.out.println("\nLISTA DE TAREAS");
        System.out.println("==================");
        
        for(int i = 0; i< cantidad; i++){
            String estado = tareas[i].isCompletada() ?  "[✓]" : "[ ]";
            
            System.out.println(
                    (i + 1) + ". "
                    + estado + " "
                    + tareas[i].getDescripcion());
        }
    }
    
    public void completarTarea(int numero) {

        if (numero < 1 || numero > cantidad) {
            System.out.println("Número de tarea inválido.");
            return;
        }

        Tarea tarea = tareas[numero - 1];

        if (tarea.isCompletada()) {
            System.out.println("La tarea ya está completada.");
            return;
        }

        tarea.setCompletada(true);

        guardarTareas();

        System.out.println(
                "\n✓ Tarea #" + numero
                + " completada: "
                + tarea.getDescripcion());
    }

    public int getCantidad() {
        return cantidad;
    }

    private void agregarAlArreglo(Tarea tarea) {

        if (cantidad == tareas.length) {

            Tarea[] nuevo = new Tarea[tareas.length * 2];

            for (int i = 0; i < cantidad; i++) {
                nuevo[i] = tareas[i];
            }

            tareas = nuevo;
        }

        tareas[cantidad] = tarea;
        cantidad++;
    }

    public void cargarTareas() {

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return;
        }

        FileReader lector = null;

        try {

            lector = new FileReader(archivo);

            StringBuilder linea = new StringBuilder();

            int caracter;

            while ((caracter = lector.read()) != -1) {

                char c = (char) caracter;

                if (c == '\n') {

                    procesarLinea(linea.toString());

                    linea.setLength(0);

                } else if (c != '\r') {

                    linea.append(c);
                }
            }

            if (linea.length() > 0) {
                procesarLinea(linea.toString());
            }

        } catch (IOException e) {

            System.out.println("Error al leer archivo.");

        } finally {

            try {

                if (lector != null) {
                    lector.close();
                }

            } catch (IOException e) {
                System.out.println("Error al cerrar archivo.");
            }
        }
    }

    private void procesarLinea(String texto) {

        texto = texto.trim();

        if (texto.isEmpty()) {
            return;
        }

        Tarea tarea = Tarea.desdeLinea(texto);

        if (tarea != null) {
            agregarAlArreglo(tarea);
        }
    }

    public void guardarTareas() {

        FileWriter escritor = null;

        try {

            escritor = new FileWriter(ARCHIVO);

            for (int i = 0; i < cantidad; i++) {

                escritor.write(tareas[i].aLinea());

                if (i < cantidad - 1) {
                    escritor.write("\n");
                }
            }

        } catch (IOException e) {

            System.out.println("Error al guardar archivo.");

        } finally {

            try {

                if (escritor != null) {
                    escritor.close();
                }

            } catch (IOException e) {
                System.out.println("Error al cerrar archivo.");
            }
        }
    }
}
    

