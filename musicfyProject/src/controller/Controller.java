/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package controller;

import data.Album;
import data.Model;
import data.Song;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller implements Serializable{
    Model m = new Model(); //Será nuestro modelo clásico.
    
    //Importar y exportar binarios.
    public void importarArchivo(){
        m.importarArchivo();
    }
    
    public void guardarArchivo(){
        m.guardarArchivo();
    }
    
    
    /***********************************************************************************************
     *                  Opción Generación aleatoria.*
     * @throws java.io.IOException
     ***********************************************************************************************/
    public void generacionAleatoria() throws IOException {
        m.generacionAleatoria();
    }
    
    
    
    /***********************************************************************************************
     *                  Opción exportar archivos de columnas y HTML.                               *
     ***********************************************************************************************/
    public void exportarArtistas(){
        m.tablaArtistasCol();
    }
    
    public void crearHTMLDeAlbumes() throws FileNotFoundException{
        m.crearHTMLDeAlbumes();
    }
    
    
    
    /***********************************************************************************************
     *                  Opción álbum                                                               *
     ***********************************************************************************************/
    public void albumAdd() {
        m.albumAdd();
    }
    
    public void albumDel() {
        m.albumDel();
    }
    
    public void albumMod() {
        m.albumMod();
    }
    
    
    public String albumConsulta() {
        ArrayList<Album> tmp = m.getAlbumConcreto();
        List<String> cancionesDeEsteAlbum = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        try{
            for(Album a : tmp){
                    cancionesDeEsteAlbum = m.getTitulosCancionesAlbumConcreto(a);  
                    sb.append(a.asPlainTableRowAlbumConCanciones(cancionesDeEsteAlbum)).append("\n");
            }    
        }catch (Exception e){
            System.err.printf("Álbum inexistente.%n%n");
        }
        
        return sb.toString();
    }
    
    
    
    /***********************************************************************************************
     *                  Opción artista                                                             *
     ***********************************************************************************************/
    public void artistaAdd() {
        m.artistaAdd();
    }
    
    public void artistaDel() {
        m.artistaDel();
    }
    
    public void artistaMod() {
        m.artistaMod();
    }
    
    
    public String artistaListaAlbumes(){
        StringBuilder sb = new StringBuilder();

        List<Album> tmp = m.getAlbumesArtista();
        tmp.sort(Comparator.comparing(Album::getAnno));
        tmp.forEach((s) -> {
            sb.append(s.asPlainTableRowAlbum()).append("\n");
        });

        return sb.toString();
    }
    
    /***********************************************************************************************
     *                  Opción playlist                                                            *
     ***********************************************************************************************/
    public void playlistAdd() {
        m.playlistAdd();
    }
    
    public void playlistDelCancion() {
        m.playlistDelCancion();
    }
    
    public void playlistAddCancion(){
        m.playlistAddCancion();
    }
    
    public void mostrarPlaylistsDisponibles() {
        m.mostrarPlaylistsDisponibles();
    }
    
    
    /***********************************************************************************************
     *                  Opción Canciones (respeta minúsculas).
     * @return canciones ordenadas.
     ***********************************************************************************************/
    public String getCanciones(){
        List<Song> tmp = m.getCanciones();
        tmp.sort(Comparator.comparing(Song::getAnno).thenComparing(Song::getTitulo));
        StringBuilder sb = new StringBuilder();
        tmp.forEach((s) -> {
            sb.append(s.asPlainTableRowSong()).append("\n");
        });
        return sb.toString();
    }
    
    
}
