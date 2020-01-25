/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author fernando
 */
public class RutasImportar implements Serializable {
    final String PATHBINARIO = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"binarios"+
                                    File.separator+"musicfy.bin";
    
    final String PATHALBUM = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"datos"+
                                    File.separator+"albumes.txt";
    
    final String PATHARTISTAS = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"datos"+
                                    File.separator+"artistas.txt";
    
    final String PATHARTISTASCOLUMNAS = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"salida"+
                                    File.separator+"artistas.col";
    
    final String PATHALBUMESHTML = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"salida"+
                                    File.separator+"albumes.html";
    
    final String PATHANNOS = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"annos.txt";
    
    final String PATHBIOGRAFIAS = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"biografias.txt";
    
    final String PATHDURACIONESALBUMES = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"duracionesAlbumes.txt";
    
    final String PATHDURACIONESCANCIONES = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"duracionesCanciones.txt";
    
    final String PATHFACEBOOK = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"facebook.txt";
    
    final String PATHINSTAGRAM = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"instagram.txt";
    
    final String PATHNOMBRESALBUMES = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"nombresAlbumes.txt";
    
    final String PATHNOMBRESARTISTAS =  System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"nombresArtistas.txt";
    
    final String PATHNOMBRESPLAYLISTS = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"nombresPlaylists.txt";
    
    final String PATHTITULOSCANCIONES = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"titulosCanciones.txt";
    
    final String PATHTWITTER = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"twitter.txt";
    
    final String PATHWIKIPEDIA = System.getProperty("user.home")+
                                    File.separator+"Desktop"+
                                    File.separator+"musicfy"+
                                    File.separator+"genAleatoria"+
                                    File.separator+"wikipedia.txt"; 
}
