/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ferrr
 */
public class Playlist implements Serializable {
    
    //Declaraciones
    String titulo;
    List<Song> canciones;
    
    public Playlist(String titulo, List<Song> canciones) {
        this.titulo = titulo;
        this.canciones = canciones;
    }

}
