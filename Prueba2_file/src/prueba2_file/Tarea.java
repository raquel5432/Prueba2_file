/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_file;

/**
 *
 * @author alira
 */
public class Tarea {
    
    private String descripcion;
    private boolean completada;
            
            public Tarea(String descripcion){
                
                this.descripcion = descripcion;
                this.completada = false;
                
            }
            
            public Tarea(String descripcion, boolean completada){
                this.descripcion= descripcion;
                this.completada = completada;
            }
            
            public String getDescripcion(){
                return descripcion;
            }
            
            public void setDescripcio(String descripcion){
                this.descripcion = descripcion;
            }
            public boolean isCompletada(){
                return completada;
            }
            
            public void setCompletada(boolean completada){
                this.completada = completada;
            }
            
            public String aLinea(){
                return (completada?  "1" :  "0" ) + "|" + descripcion;
            }
            
            public static Tarea desdeLinea(String linea){
                int posicion = linea.indexOf('|');
                
                if (posicion == -1){
                    return null;
                }
                
                String estado = linea.substring(0, posicion).trim();
                String descripcion = linea.substring(posicion +1).trim();
                
                if(descripcion.isEmpty()){
                    return null;
                }
                
                boolean completada = estado.equals("1");
                        return new Tarea(descripcion,completada);
            }
                
    
}
