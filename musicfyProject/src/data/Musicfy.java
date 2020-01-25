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
public class Musicfy implements Serializable {
    List<Song> canciones;
    List<Album> albumes;
    List<Artist> artistas;
    List<Playlist> playlists;   
}