/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package musicfy;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import view.View;


public class MusicfyMain implements Serializable {


    public static void main(String[] args) throws ParseException, IOException {
        View v = new View();
        
        //Carga de ficheros
        v.importarArchivo();
        
        v.runMenu("******************************"
                + "%n* Menú principal de Musicfy. *"
                + "%n******************************"
                + "%n1.- Generación aleatoria."
                + "%n2.- Archivos."
                + "%n3.- Álbum."
                + "%n4.- Artista."
                + "%n5.- Playlist."
                + "%n6.- Canciones."
                + "%n7.- Instrucciones."
                + "%nq.- Salir."
                + "%nOpción: ");
        
        //Descarga de los ficheros en musicfy.bin (o incluso creación de este archivo en el
        //caso de que se cargaran los valores desde el .txt
        v.guardarArchivo();
        System.out.println("Saliendo...");
    }
    
}
