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
public class Album implements Serializable {
    
    //Declaraciones. 
    String titulo;
    ArrayList<String> interpretes;
    int anno;
    String duracion;
    int numeroDeCanciones;
    String tipo; //(album o sencillo).
    ArrayList<Song> canciones;
    
    
    //Contructores
    //Este es de sencillo.
    Album(String titulo, ArrayList<String> interpretes, int anno, String duracion, int numeroDeCanciones, String tipo) {
        this.titulo = titulo;
        this.interpretes = interpretes;
        this.anno = anno;
        this.duracion = duracion;
        this.numeroDeCanciones = numeroDeCanciones;
        this.tipo = tipo;
    }
    
    
    //Este es de álbum con más de 1 canción.
    
    Album(String titulo, ArrayList<String> interpretes, int anno, String duracion, int numeroDeCanciones, String tipo, ArrayList<Song> canciones) {
        this.titulo = titulo;
        this.interpretes = interpretes;
        this.anno = anno;
        this.duracion = duracion;
        this.numeroDeCanciones = numeroDeCanciones;
        this.tipo = tipo;
        this.canciones = canciones;
    }
    

    Album() {
        
    }

    
    


    public static Album instanceFromString(String linea){
        Album album = new Album();
        
        String[] columnaAlbum = linea.split("\t");
        album.titulo = columnaAlbum[0];
        
        ArrayList<String> interpretesLista = new ArrayList<>(); //Futura colección con intérpretes.
        String[] arrayInterpretes = columnaAlbum[1].split(";");
        for (String arrayInterprete : arrayInterpretes) {
            interpretesLista.add(arrayInterprete);
        }
        //No se puede addAll...
        
        album.interpretes = interpretesLista;
        album.anno = Integer.parseInt(columnaAlbum[2]);
        album.duracion = columnaAlbum[3];
        album.numeroDeCanciones = Integer.parseInt(columnaAlbum[4]);
        album.tipo = columnaAlbum[5];
        
        /*
        Ahora las canciones se guardan como una colección de objetos canción. Esto sólo sucede
        con las canciones y no con los interpretes o los títulos en artista.
        */
        ArrayList<Song> canciones = new ArrayList<>();
        if(columnaAlbum[5].equals("sencillo")){
           Song song = new Song(columnaAlbum[0], album.anno, album.duracion, interpretesLista); 
           canciones.add(song);
        }else{
            String[] arrayCanciones = columnaAlbum[6].split(";");
            Random random = new Random();
            for (String Cancion : arrayCanciones) {
                Song song = new Song(Cancion, album.anno, interpretesLista);
                //Suponemos que las canciones tienen el mismo año que el álbum para que la opción 6 quede mejor al ordenar.
                //Aún así, como las duraciones son String, ese campo lo dejamos en blanco.
                canciones.add(song);
            }
        }
        album.canciones = canciones;
        return album;
    }


    
    public String asTableRow(){
        String resultado;
        resultado = String.format("<TR>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>"
                + "</TR>",
                this.titulo,
                this.interpretes,
                this.anno,
                this.duracion,
                this.numeroDeCanciones,
                this.tipo
        );
        return resultado;
    }



    
    /**********************************************************************************
     *          Getters y setters.*
     * @return
     **********************************************************************************/
    
    public int getAnno() {
        return anno;
    }

    public String asPlainTableRowAlbum(){
        return String.format("| %-40s | %-110s | %4d | %-14s | %-20d | %-10s |",
                this.titulo,
                this.interpretes,
                this.anno,
                this.duracion,
                this.numeroDeCanciones,
                this.tipo);
    }
    
    
    public String asPlainTableRowAlbumConCanciones(List canciones){

        return String.format("| %-40s | %-110s | %4d | %-14s | %-20d | %-10s | %-250s |",
                this.titulo,
                this.interpretes,
                this.anno,
                this.duracion,
                this.numeroDeCanciones,
                this.tipo,
                canciones);
    }
    
    
    
}
