/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ferrr
 */
public class Artist implements Serializable {
    
    //Declaraciones.
    String nombre;
    String biografia;
    String instagram;
    String twitter;
    String facebook;
    String wikipedia;
    ArrayList<String> albumes;
    
    Artist(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, ArrayList<String> albumes) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albumes = albumes;
    }
    
    Artist(String nombre, ArrayList<String> albumes) {
        this.nombre = nombre;
        this.albumes = albumes;
    }
    
    private Artist() {
        
    }
    
    
    public static Artist instanceFromString(String linea){
        Artist artist = new Artist();
        
        String[] columnaArtist = linea.split("#");
        artist.nombre = columnaArtist[0];
        artist.biografia = columnaArtist[1];
        artist.instagram = columnaArtist[2];
        artist.twitter = columnaArtist[3];
        artist.facebook = columnaArtist[4];
        artist.wikipedia = columnaArtist[5];
        
        ArrayList<String> albumesLista = new ArrayList<>(); //Futura colección con álbumes.
        String[] arrayAlbumes = columnaArtist[6].split(";");
        for (String Album : arrayAlbumes) {
            albumesLista.add(Album);
        }
        artist.albumes = albumesLista;

        return artist;
    }


    
    public String tabbedDescriptionArtistas(){
        
        String resultado = String.format("| %-40s\t | %-360s\t | %-40s\t | %-40s\t | %-40s\t | %-80s\t | %-1100s |",
                this.nombre,
                this.biografia,
                this.instagram,
                this.twitter,
                this.facebook,
                this.wikipedia,
                this.albumes
        );
        return resultado;
    }
}
