/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ferrr
 */
public class Song implements Serializable {
    
    //Declaraciones.
    String titulo;
    private int anno;
    private String duracion;
    ArrayList<String> interpretes = new ArrayList<>();

    Song(String titulo, int anno, String duracion, ArrayList<String> interpretes){
        this.titulo = titulo;
        this.anno = anno;
        this.duracion = duracion;
        this.interpretes = interpretes;
    }


    Song(String titulo, int anno, ArrayList<String> interpretes) { //Constructor utilizado al añadir de albumes.txt y al añadir canciones manualmente.
        this.titulo = titulo;
        this.anno = anno;
        this.interpretes = interpretes;
    }

    
    public String asPlainTableRowSong(){
        return String.format("| %-50s | %4d | %-14s | %-110s |",
                this.titulo,
                this.anno,
                this.duracion,
                this.interpretes);
    } //End of asPlainTableRow

    
    /* Getters y setters */
    public String getTitulo() {
        return titulo;
    }

    public int getAnno() {
        return anno;
    }
    
    public static Song getCancionRandom(List<Song> cancionesImportadas){
        Random rand = new Random();
        Song song = cancionesImportadas.get(rand.nextInt(cancionesImportadas.size()));  
        return song;
    }
    
    
}
