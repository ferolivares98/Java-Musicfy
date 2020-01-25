/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package data;

import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import static com.coti.tools.Esdia.yesOrNo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author fernando
 */
public class Model implements Serializable {
    //Declaraciones
    Musicfy musicfy = new Musicfy();
    RutasImportar ruta = new RutasImportar();
    
    
    
    /*
    Carga de archivo binario o datos desde los .txt de la carpeta datos.
    */
    public void importarArchivo() {
        Path pBinario = Paths.get(ruta.PATHBINARIO);
        
        if(!Files.exists(pBinario)){
            System.out.printf("Binario inexistente. Cargando datos a partir de ficheros...%n");
            Path pAlbumes = Paths.get(ruta.PATHALBUM);
            Path pArtistas = Paths.get(ruta.PATHARTISTAS);

            List<String> tmp = null;
            //Primero cargaremos los albumes y luego cargaremos los artistas.
            try{
                tmp = Files.readAllLines(pAlbumes);
            }catch (IOException e){
                System.err.printf("%nNo es posible importar los datos del fichero albumes.txt%n%n");
                System.exit(1);
            }
            if(tmp != null && !tmp.isEmpty()){
                musicfy.albumes = new ArrayList<>();
                musicfy.canciones = new ArrayList<>();
                for (String lineaAlbum : tmp){
                    if(!lineaAlbum.isEmpty()){
                        Album album = Album.instanceFromString(lineaAlbum);
                        if(album != null){
                            musicfy.albumes.add(album);
                            album.canciones.forEach((Song song) -> { //Variable canciones del album 
                                musicfy.canciones.add(song);        //Y aquí la del modelo. En algunos puntos utilizo operaciones funcionales por probar.
                            });
                        }
                    }
                }
            }
            //Ahora artistas.txt
            try{
                tmp = Files.readAllLines(pArtistas);
            }catch (IOException e){
                System.err.printf("%nNo es posible importar los datos del fichero artistas.txt%n%n");
                System.exit(1);
            }
            if(tmp != null && !tmp.isEmpty()){
                musicfy.artistas = new ArrayList<>();
                for (String lineaArtista : tmp){
                    if(!lineaArtista.isEmpty()){
                        Artist artist = Artist.instanceFromString(lineaArtista);
                        if(artist != null){
                            musicfy.artistas.add(artist);
                            //Los álbumes no se añaden por orden del enunciado.
                        }
                    }
                }
            }
            musicfy.playlists = new ArrayList<>();
        }else{
            //musicfy.bin existe.
            System.out.printf("Binario existente. Cargando datos...%n");
            try{
                FileInputStream fis = new FileInputStream(pBinario.toFile());
                BufferedInputStream bis = new BufferedInputStream(fis);
                try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                    musicfy = (Musicfy) ois.readObject();
                    ois.close();
                }
                System.out.printf("Lectura realizada correctamente.%n%n");
            }catch (IOException | ClassNotFoundException ex){
                System.err.println("FATAL ERROR: Error en la lectura del fichero binario.");
                System.err.println("musicfy.bin corrupto. Elimine manualmente el fichero y reinicie la aplicación.");
                System.err.println("Se cargarán automáticamente una serie de datos renovados a partir de la carpeta datos.");
                System.exit(1);
            }
        }
    }
    
    
    
    /*
    Guardado de los datos generados a lo largo de la ejecución del programa en el
    binario musicfy.bin.
    */
    public void guardarArchivo() {
        Path pBinario = Paths.get(ruta.PATHBINARIO);
        
        if(!Files.exists(pBinario)){
            System.out.printf("Se creará el fichero binario y se usará en posteriores ejecuciones...%n"); //Arreglar.
            guardadoBinario(pBinario);
        }else{
            System.out.printf("Binario existente. Sobreescribiendo...%n");
            guardadoBinario(pBinario);
        }
    }

    public void guardadoBinario(Path pBinario) {
        try {
            FileOutputStream fos = new FileOutputStream(pBinario.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(musicfy);
                oos.close();
            }
            System.out.println("Guardado realizado correctamente.");
        } catch (IOException ex) {
            System.err.println("No fue posible guardar el archivo binario");
        }
    }


    
    
    /***********************************************************************************************
     *                  Opción Generación aleatoria.*
     * @throws java.io.IOException
     ***********************************************************************************************/
    
    public void generacionAleatoria() throws IOException {
        //El orden será álbum - intérpretes - canciones - playlists.
        //Se permitirán repeticiones de álbumes y canciones pero no de grupos de artistas.
        musicfy.albumes.clear();
        musicfy.artistas.clear();
        musicfy.canciones.clear();
        musicfy.playlists.clear();
        
        //Declaraciones sobre la marcha...
        Random rand = new Random();
        Path pTitulosCanciones = Paths.get(ruta.PATHTITULOSCANCIONES);
        List<String> titulosCanciones = Files.readAllLines(pTitulosCanciones);
        Path pAnnos = Paths.get(ruta.PATHANNOS);
        List<String> annos = Files.readAllLines(pAnnos);
        Path pDuracionCanciones = Paths.get(ruta.PATHDURACIONESCANCIONES);
        List<String> duracionCanciones = Files.readAllLines(pDuracionCanciones);
        Path pTitulosAlbumes = Paths.get(ruta.PATHNOMBRESALBUMES);
        List<String> titulosAlbumes = Files.readAllLines(pTitulosAlbumes);
        Path pDuracionAlbumes = Paths.get(ruta.PATHDURACIONESALBUMES);
        List<String> duracionAlbumes = Files.readAllLines(pDuracionAlbumes);
        Path pNombresArtista = Paths.get(ruta.PATHNOMBRESARTISTAS);
        List<String> nombresArtista = Files.readAllLines(pNombresArtista);
        Path pBiografias = Paths.get(ruta.PATHBIOGRAFIAS);
        List<String> biografias = Files.readAllLines(pBiografias);
        Path pInstagram = Paths.get(ruta.PATHINSTAGRAM);
        List<String> instagram = Files.readAllLines(pInstagram);
        Path pTwitter = Paths.get(ruta.PATHTWITTER);
        List<String> twitter = Files.readAllLines(pTwitter);
        Path pFacebook = Paths.get(ruta.PATHFACEBOOK);
        List<String> facebook = Files.readAllLines(pFacebook);
        Path pWikipedia = Paths.get(ruta.PATHWIKIPEDIA);
        List<String> wikipedia = Files.readAllLines(pWikipedia);
        Path pNombresPlaylist = Paths.get(ruta.PATHNOMBRESPLAYLISTS);
        List<String> nombresPlaylist = Files.readAllLines(pNombresPlaylist);
        
        //Boolean
        boolean repetido = false;
        boolean artistaRepetido = false;
        boolean cancionCompletaRepetida = false;
        
        int maxAlbumes = 1000;
        int minAlbumes = 500;
        int maxCanciones = 12;
        int minCanciones = 1;
        int maxInterpretes = 3;//Se permititrán hasta 4 para evitar una serie de repeticiones...
        int minInterpretes = 1;
        int maxPlaylists = 15;
        int minPlaylists = 4;
        int maxCancionesPorPlaylist = 50;
        int minCancionesPorPlaylist = 5;
        
        int randomNumAlbumes = (int)(Math.random() * maxAlbumes + minAlbumes);
        
        //List<String> titulosAlbum = new ArrayList<>();
        
        for(int i = 0; i < randomNumAlbumes; i++){
            String tituloAlbum = getStringRandom(titulosAlbumes);
            int annoAlbum = Integer.parseInt(getStringRandom(annos));
            String duracionAlbum = getStringRandom(duracionAlbumes);
            int numeroDeCanciones = (int)(Math.random() * maxCanciones + minCanciones);
            
            //Vamos con intérpretes.
            int numeroDeInterpretes = (int)(Math.random() * maxInterpretes + minInterpretes);
            ArrayList<String> interpreteList = new ArrayList<>();
            do{
                repetido = false;
                for(int j = 0; j < numeroDeInterpretes; j++){
                    String interprete = getStringRandom(nombresArtista);
                    if((!interpreteList.contains(interprete))){
                        interpreteList.add(interprete);
                    }
                }
                if(!(musicfy.albumes.isEmpty())){
                    for(Album a : musicfy.albumes){
                        if(a.interpretes.containsAll(interpreteList)){
                            repetido = true;
                            break;
                        }
                    }
                }
            }while(repetido);
            
            for(String interpreteUnico : interpreteList){
                String biografiaUnico = getStringRandom(biografias);
                String instagramUnico = getStringRandom(instagram);
                String twitterUnico = getStringRandom(twitter);
                String facebookUnico = getStringRandom(facebook);
                String wikipediaUnico = getStringRandom(wikipedia);
                artistaRepetido = false;
                
                for(Artist artistaLoop : musicfy.artistas){
                    if(interpreteUnico.equals(artistaLoop.nombre)){
                        if(!(artistaLoop.albumes.contains(tituloAlbum))){
                            artistaLoop.albumes.add(tituloAlbum); //A un artista no le va a salir dos veces el mismo título.
                        }
                        artistaRepetido = true;
                    }
                }

                if(!artistaRepetido){
                    //No existe el artista.
                    ArrayList<String> auxiliarArrayAlbumTituloParaArtista = new ArrayList<>();
                    auxiliarArrayAlbumTituloParaArtista.add(tituloAlbum);
                    Artist artist = new Artist(interpreteUnico, biografiaUnico, instagramUnico, 
                            twitterUnico, facebookUnico, wikipediaUnico, auxiliarArrayAlbumTituloParaArtista);
                    musicfy.artistas.add(artist);
                }
            }
            
            //Vamos con las canciones.
            String tipo;
            if(numeroDeCanciones == 1){
                tipo = "sencillo";
            }else{
                tipo = "álbum";
            }
            if(tipo.equals("sencillo")){
                int annoCancion = Integer.parseInt(getStringRandom(annos));
                String duracionCancion = getStringRandom(duracionCanciones);
                try{
                    Song song = new Song(tituloAlbum, annoCancion, duracionCancion, interpreteList);
                    musicfy.canciones.add(song);
                }catch(Exception e){
                    System.err.println("Error al añadir el sencillo a la lista de canciones de Musicfy.");
                }
                Album album = new Album(tituloAlbum, interpreteList, annoAlbum, duracionCancion, numeroDeCanciones, tipo); //Sin lista final.
                try{
                    musicfy.albumes.add(album);
                }catch (Exception ex){
                    System.out.println("Error al añadir el sencillo a la colección de álbumes de Musicfy.");
                }

            }else{
                ArrayList<Song> cancionesNuevoAlbum = new ArrayList<>();
                Song song;
                for(int k = 0; k < numeroDeCanciones; k++){
                    do{
                        cancionCompletaRepetida = false;
                        String cancionAAnnadir = getStringRandom(titulosCanciones);
                        int annoCancion = Integer.parseInt(getStringRandom(annos));
                        String duracionCancion = getStringRandom(duracionCanciones);
                        song = new Song(cancionAAnnadir, annoCancion, duracionCancion, interpreteList);

                        if(musicfy.canciones.contains(song)){ //Exactamente igual al 100%.
                            System.out.println("Una canción con exactamente estos atributos (todos ellos) ya existe."
                                    + "Por favor, inténtelo de nuevo.");
                            cancionCompletaRepetida = true; //Complicado...
                        }
                    }while(cancionCompletaRepetida);

                    try{
                        musicfy.canciones.add(song);
                    }catch (Exception e){
                        System.err.println("Error al añadir la canción a la colección de canciones de Musicfy.");
                    }
                    cancionesNuevoAlbum.add(song);
                }
                Album album = new Album(tituloAlbum, interpreteList, annoAlbum, duracionAlbum, numeroDeCanciones, tipo, cancionesNuevoAlbum);
                try{
                    musicfy.albumes.add(album);
                }catch (Exception ex){
                    System.out.println("Error al añadir el álbum a la colección de álbumes de Musicfy.");
                }
            }
        }
        
        
        /*
        En este momento se han creado los álbumes. A partir de ellos, se han generado canciones y artistas. 
        Vamos a generar un pequeño número de playlists.
        */
        int numeroDePlaylists = (int)(Math.random() * maxPlaylists + minPlaylists);
        for(int l = 0; l < numeroDePlaylists; l++){
            int numeroDeCanciones;
            String tituloNuevaPlaylist;
            List<Song> cancionesDePlaylist = new ArrayList<>();
            Song song;
            boolean flagPlaylistRepetido = false;
            
            do{
                tituloNuevaPlaylist = getStringRandom(nombresPlaylist);

                if(!musicfy.playlists.isEmpty()){
                    flagPlaylistRepetido = false;
                    for(Playlist s : musicfy.playlists){                    
                        if(s.titulo.equals(tituloNuevaPlaylist)){
                            flagPlaylistRepetido = true;
                        }
                    }
                }
            }while(flagPlaylistRepetido);

            numeroDeCanciones = (int)(Math.random() * maxCancionesPorPlaylist + minCancionesPorPlaylist);

            for(int m = 0; m < numeroDeCanciones; m++){
                do{
                    song = Song.getCancionRandom(musicfy.canciones);
                }while(cancionesDePlaylist.contains(song));
                cancionesDePlaylist.add(song);
            }

            Playlist playlist = new Playlist(tituloNuevaPlaylist, cancionesDePlaylist);
            musicfy.playlists.add(playlist);
        }
        
        System.out.println("Generación aleatoria realizada correctamente.");
        System.out.printf("%n%n%n");
    }
    
    
    
    private String getStringRandom(List<String> list) {
        Random rand = new Random();
	return list.get(rand.nextInt(list.size()));
    }
    //Lo aprovecho también para integers.
    
    
    
    
    /***********************************************************************************************
     *                  Opción exportar archivos de columnas y HTML.                               *
     ***********************************************************************************************/
    public void tablaArtistasCol() {
        Path path = Paths.get(ruta.PATHARTISTASCOLUMNAS);
        StringBuilder sb= new StringBuilder();
        
        sb.append("-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------").append("\n");
        sb.append("| NOMBRE                                         | BIOGRAFÍA                                     "
                + "                                                                                                 "
                + "                                                                                                 "
                + "                                                                                                 "
                + "                         "
                + "    | INSTAGRAM                                     | TWITTER              "
                + "                         | FACEBOOK                                      | WIKIPEDIA             "
                + "                                                                | ÁLBUMES                    "
                + "                                                                                                 "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                    "
                + "                                                                                                 "
                + "                                                                                |").append("\n");
        sb.append("-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------").append("\n");
        for(Artist a : musicfy.artistas){
            sb.append(a.tabbedDescriptionArtistas()).append("\n");
            sb.append("-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------").append("\n");
        }
        try {
            Files.writeString(path, sb);
        } catch (IOException ex) {
            System.out.println("Error al intentar escribir en fichero artistas.col");
        }
    }
    
    public void crearHTMLDeAlbumes() throws FileNotFoundException {
                
        try (PrintWriter pw = new PrintWriter(ruta.PATHALBUMESHTML)){
            pw.printf("<!DOCTYPE html>%n<HTML>%n<HEAD><meta charset=\"UTF-8\"><H1>ÁLBUMES</H1><style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 15px;\n" +
            "}\n" +
            "</style></HEAD>%n<BODY>%n");
            pw.printf("<TABLE BORDER=0>%n");
            pw.printf("<tr>\n" +
            "<th>Título</th>\n" +
            "<th>Intérpretes</th> \n" +
            "<th>Año</th>\n" +
            "<th>Duración</th>\n" +
            "<th>Número de canciones</th> \n" +
            "<th>Tipo</th>\n" +
            "</tr>");
            musicfy.albumes.forEach((f) -> {
                pw.printf("%s%n", f.asTableRow());
            });
            pw.println("</TABLE");
            pw.println("</BODY>\n<HTML>");
            pw.close();
        }
    }
    
    
    
    
    
    
    /***********************************************************************************************
     *                  Opción álbum                                                               * 
     ***********************************************************************************************/
    public void albumAdd() {
        String tituloNuevoAlbum, duracion, duracionCancion, tipo, interpreteAAnnadir, cancionAAnnadir;
        int anno, numeroDeCanciones, annoCancion;
        Song song;
        Album album;
        Artist artist;
        ArrayList<String> interpretesNuevoAlbum = new ArrayList<>();
        ArrayList<Song> cancionesNuevoAlbum = new ArrayList<>();
        boolean flagRepetido = false;
        boolean salir = false;
        boolean duracionOpcional = false;
        boolean encontrado = false;
        
        do{
            tituloNuevoAlbum = readString_ne("Introduzca el nombre del álbum: ");

            flagRepetido = false;
            for(Album a : musicfy.albumes){                    
                if(a.titulo.equals(tituloNuevoAlbum)){
                    System.out.println("Nombre de álbum no válido. Inténtelo de nuevo...");
                    flagRepetido = true;
                    break;
                }
            }
        }while(flagRepetido);
        
        do{
            interpreteAAnnadir = readString_ne("%nIntroduzca un intérprete: ");
            interpretesNuevoAlbum.add(interpreteAAnnadir);
            
            salir = yesOrNo("%nDesea añadir más intérpretes?: ");
        }while(salir);
        
        do{
            anno = Integer.parseInt(readString_ne("Introduzca el año (1500 hasta 2020): "));
        }while(anno < 1500 || anno > 2020);
        //Límites razonables.
        
        duracion = readString_ne("Introduzca la duración. Formato [xx min xx seg]: ");
        /*
        Las duraciones se tratarán como cadenas por simplicidad, aunque posteriormente existan errores lógicos
        entre las duraciones de las canciones y de los álbumes.
        */
        
        do{
            numeroDeCanciones = Integer.parseInt(readString_ne("Número de canciones del álbum (1(sencillo) hasta 50): "));
            //Límites razonables.
        }while(numeroDeCanciones < 1 || numeroDeCanciones > 50);
        
        if(numeroDeCanciones == 1){
            tipo = "sencillo";
            System.out.println("Tipo \"sencillo\" asignado automáticamente.");
        }else{
            tipo = "álbum";
            System.out.println("Tipo \"álbum\" asignado automáticamente.");
        }
        
        if(tipo.equals("sencillo")){
            try{
                song = new Song(tituloNuevoAlbum, anno, duracion, interpretesNuevoAlbum);
                musicfy.canciones.add(song);
            }catch(Exception e){
                System.err.println("Error al añadir el sencillo a la lista de canciones de Musicfy.");
            }
            album = new Album(tituloNuevoAlbum, interpretesNuevoAlbum, anno, duracion, numeroDeCanciones, tipo); //Sin lista final.
            try{
                musicfy.albumes.add(album);
            }catch (Exception ex){
                System.out.println("Error al añadir el sencillo a la colección de álbumes de Musicfy.");
            }
            
        }else{
            System.out.println("Se introducirán las canciones en función del número de canciones del álbum.");
            for(int i = 0; i < numeroDeCanciones; i++){
                do{
                    flagRepetido = false;
                    cancionAAnnadir = readString_ne("%nIntroduzca un título de canción: ");
                    do{
                        annoCancion = Integer.parseInt(readString_ne("%nIntroduzca el año de la canción: "));
                    }while(annoCancion > anno || annoCancion < 1500); //Límites.
                    duracionOpcional = yesOrNo("Introducir la duración es opcional. Desea introducirla?");
                    if(duracionOpcional){
                        duracionCancion = readString_ne("Introduzca la duración. Formato [xx min xx seg]: ");
                        song = new Song(cancionAAnnadir, annoCancion, duracionCancion, interpretesNuevoAlbum);
                    }else{
                        song = new Song(cancionAAnnadir, annoCancion, interpretesNuevoAlbum);
                    }
                    
                    if(musicfy.canciones.contains(song)){ //Exactamente igual al 100%.
                        System.out.println("Una canción con exactamente estos atributos (todos ellos) ya existe."
                                + "Por favor, inténtelo de nuevo.");
                        flagRepetido = true;
                    }
                }while(flagRepetido);
                
                try{
                    musicfy.canciones.add(song);
                }catch (Exception e){
                    System.err.println("Error al añadir la canción a la colección de canciones de Musicfy.");
                }
                cancionesNuevoAlbum.add(song);
            }
            album = new Album(tituloNuevoAlbum, interpretesNuevoAlbum, anno, duracion, numeroDeCanciones, tipo, cancionesNuevoAlbum);
            try{
                musicfy.albumes.add(album);
            }catch (Exception ex){
                System.out.println("Error al añadir el álbum a la colección de álbumes de Musicfy.");
            }
        }
        System.out.println("Canción/es creadas correctamente.");
        /*
        Las canciones ya están añadidas a la colección general.
        Antes de acabar, vamos a añadir los intérpretes con el álbum. Si no existen, se crean y se les añade el álbum.
        Si existen, solo se les añade el álbum.
         */
        //auxiliarArrayAlbumTituloParaArtista.add(tituloNuevoAlbum);
        
        for(String interprete : interpretesNuevoAlbum){
            encontrado = false;
            for(Artist a : musicfy.artistas){
                if(interprete.equals(a.nombre)){
                    if(!(a.albumes.contains(tituloNuevoAlbum))){ //Se creó manualmente y se puso el nombre del álbum sin dar de alta.
                        a.albumes.add(tituloNuevoAlbum);
                    }
                    encontrado = true;
                }
            }
            
            if(!encontrado){
                //No existe el artista.
                ArrayList<String> auxiliarArrayAlbumTituloParaArtista = new ArrayList<>();
                auxiliarArrayAlbumTituloParaArtista.add(tituloNuevoAlbum);
                artist = new Artist(interprete, auxiliarArrayAlbumTituloParaArtista);
                musicfy.artistas.add(artist);
            }
        }
        
        System.out.println("Artista/s creados y actualizados correctamente.");
        
        System.out.println("Álbum creado correctamente.");
        System.out.printf("%n%n%n");
    }
    
    
    public void albumDel() {
        String nombreAlbumAEliminar, artistaDelAlbumAEliminar;
        ArrayList<String> artistasLista = new ArrayList<>();
        boolean albumEncontrado = false;
        boolean salir = false;
        boolean inexistente = false;
        
        nombreAlbumAEliminar = readString_ne("Nombre del álbum a eliminar: ");
        do{
            salir = false;
            artistaDelAlbumAEliminar = readString_ne("Artista del álbum a eliminar: ");
            artistasLista.add(artistaDelAlbumAEliminar);
            salir = yesOrNo("El álbum posee más artistas?: ");
        }while(salir);
        
        for(Album a : musicfy.albumes){
            if(albumEncontrado)
                break;
                        
                if(a.titulo.equals(nombreAlbumAEliminar) && artistasLista.containsAll(a.interpretes)){
                    albumEncontrado = true;
                    System.out.println("Álbum encontrado. Se eliminarán sus canciones y la referencia en artistas.");
                    if(a.tipo.equals("sencillo")){
                        for(Song x : musicfy.canciones){
                            if(x.titulo.equals(a.titulo) && x.interpretes.containsAll(a.interpretes)){
                                musicfy.canciones.remove(x);
                                //Hay que hacer todo esto porque los sencillos no tienen nada en el ArrayList de canciones.
                                break;
                            }
                        }
                    }else{
                        try{
                            musicfy.canciones.removeAll(a.canciones); //Aquí ya van los intérpretes(son objetos Song) :)
                        }catch (Exception e){
                            System.err.println("Error al eliminar las canciones de Musicfy.");
                        }
                    }
                    for(Artist interprete : musicfy.artistas){
                        if(interprete.albumes.contains(nombreAlbumAEliminar) && artistasLista.contains(interprete.nombre)){
                            try{
                                interprete.albumes.remove(nombreAlbumAEliminar);
                            }catch(Exception ex){
                                System.out.println("Error al eliminar el álbum de la referencia en un artista.");
                            }
                        }
                    }

                    try{
                        musicfy.albumes.remove(a);
                        System.out.printf("%nÁlbum eliminado correctamente.%n%n%n");
                        inexistente = true;
                    }catch (Exception ex){
                            System.err.println("Error al eliminar el álbum de la colección de álbumes.");
                    }
                    break;
                }
        
            
        }
        if(!inexistente){
                System.err.printf("%nÁlbum inexistente.%n%n%n");
        }
    }
    
    
    public void albumMod() {
        String nombreAlbumAModificar, anno, duracion, artistaDelAlbumAModificar;
        ArrayList<String> artistasLista = new ArrayList<>();
        boolean mod = false;
        boolean albumEncontrado = false;
        boolean salir = false;
        
        nombreAlbumAModificar = readString_ne("Nombre del álbum a modificar: ");
        do{
            salir = false;
            artistaDelAlbumAModificar = readString_ne("Artista del álbum a modificar: ");
            artistasLista.add(artistaDelAlbumAModificar);
            salir = yesOrNo("El álbum posee más artistas?: ");
        }while(salir);
        
        for(Album a : musicfy.albumes){
            if(a.titulo.equals(nombreAlbumAModificar) && a.interpretes.containsAll(artistasLista)){
                System.out.println("Álbum encontrado. Se proceden a modificar sus datos.");
                albumEncontrado = true;
                
                mod = yesOrNo("Desea modificar el año?");
                if(mod){
                    anno = readString_ne("Nuevo anno: ");
                    a.anno = Integer.parseInt(anno);
                }
                
                mod = yesOrNo("Desea modificar la duración?");
                if(mod){
                    duracion = readString_ne("Nueva duración. Formato [xx min xx seg]: ");
                    a.duracion = duracion;
                }
            }
        }             
        if(albumEncontrado){
            System.out.println("Álbum modificado correctamente. Puede comprobar los cambios realizados en albumes.html.");
            System.out.println("Debe tener en cuenta que el resto de valores de los álbumes (número de canciones, tipo, etc...)");
            System.out.println("Son dependientes de la colección de canciones del álbum (y por ello no se modifican).");
        }else{
            System.err.println("Álbum inexistente."); //Problema con el .err. y los .out
        }
        System.out.printf("%n%n%n");
    }
    
    
    public ArrayList<Album> getAlbumConcreto() {
        String nombreAlbum = readString_ne("%nNombre del album: ");
        ArrayList<Album> tmp = new ArrayList<>();
        
        for(Album a : musicfy.albumes){
            if(a.titulo.equals(nombreAlbum)){
                tmp.add(a);
            }
        }
        if(tmp.isEmpty()){
            System.err.printf("%nÁlbum inexistente%n%n%n");
        }
        return tmp;
    }
    
    public ArrayList<String> getTitulosCancionesAlbumConcreto(Album album) {
        ArrayList<String> tmp = new ArrayList<>();
        
        if(album.tipo.equals("álbum")){
            for(Song s : album.canciones){
                tmp.add(s.titulo);
            }
        }
        
        return tmp;
    }
    
    
    
    
    
    
    /***********************************************************************************************
     *                  Opción artista                                                             *
     ***********************************************************************************************/
    public void artistaAdd() {
        String nombreNuevoArtista, biografia, instagram, twitter, facebook, wikipedia, tituloAlbum;
        ArrayList<String> albumesNuevoArtista = new ArrayList<>();
        boolean flagRepetido = false;
        boolean salir = true;
        
        do{
            nombreNuevoArtista = readString_ne("Introduzca el nombre del artista: "); //Cubrimos el isEmpty con ne.

            flagRepetido = false;
            for(Artist a : musicfy.artistas){                    
                if(a.nombre.equals(nombreNuevoArtista)){
                    System.out.println("Nombre de artista no válido. Inténtelo de nuevo...");
                    flagRepetido = true;
                    break;
                }
            }
        }while(flagRepetido);
        
        biografia = readString("%nBiografía: "); //Se permiten cadenas vacías.
        instagram = readString("%nInstagram: ");
        twitter = readString("%nTwitter: ");
        facebook = readString("%nFacebook: ");
        wikipedia = readString("%nWikipedia: ");
        
        System.out.printf("%nA continuación, los álbumes. Tenga en cuenta que no se comprobará la%n");
        System.out.println("existencia de estos ni se darán de alta en la base de datos.");
        
        do{
            tituloAlbum = readString_ne("%nIntroduzca un título de álbum: ");
            albumesNuevoArtista.add(tituloAlbum); //Sin comprobaciones.
            
            salir = yesOrNo("%nDesea añadir más albumes?: ");
        }while(salir);
        
        Artist artist = new Artist(nombreNuevoArtista, biografia, instagram, twitter, facebook, wikipedia, albumesNuevoArtista);
        musicfy.artistas.add(artist);
        System.out.printf("%nArtista creado correctamente.%n%n%n");
    }
    
    
    public void artistaDel() {
        String nombreArtistaAEliminar;
        boolean albumExistente = false;
        boolean artistaEncontrado = false;
        ArrayList<String> yaImpresos = new ArrayList<>();
        nombreArtistaAEliminar = readString_ne("Nombre del artista a eliminar: ");
        
        for(Artist a : musicfy.artistas){
            if(a.nombre.equals(nombreArtistaAEliminar)){
                artistaEncontrado = true;
                System.out.println("Artista encontrado. Comprobando si tiene álbumes dados de alta...");
                for(String s : a.albumes){
                    for(Album album : musicfy.albumes){
                        if(s.equals(album.titulo)){ //Quiere decir que está dado de alta.
                            if(!yaImpresos.contains(s)){
                                System.out.printf("%s%n", s);
                                yaImpresos.add(s);
                            }
                            albumExistente = true;
                        }
                    }
                }
                if(albumExistente){
                    System.out.printf("Si desea eliminar a este artista, elimine los álbumes dados de alta.%n%n%n");
                }else{
                    musicfy.artistas.remove(a);
                    System.out.printf("%nNo tiene álbumes en la base de datos dados de alta. Artista eliminado correctamente.%n%n%n");
                }
                break;
            }
        }
        
        if(!artistaEncontrado){
            System.err.printf("%nArtista inexistente.%n%n%n");
        }
           
    }
    
    
    public void artistaMod() {
        String nombreArtistaAModificar;
        String biografia, instagram, twitter, facebook, wikipedia;
        boolean mod = false;
        boolean artistaEncontrado = false;
        nombreArtistaAModificar = readString_ne("Nombre del artista a modificar: ");
        
        for(Artist a : musicfy.artistas){
            if(a.nombre.equals(nombreArtistaAModificar)){
                System.out.println("Artista encontrado. Se proceden a modificar sus datos.");
                artistaEncontrado = true;
                
                mod = yesOrNo("Desea modificar la biografía?");
                if(mod){
                    biografia = readString_ne("Nueva briografía: ");
                    a.biografia = biografia;
                }
                
                mod = yesOrNo("Desea modificar el Instagram?");
                if(mod){
                    instagram = readString_ne("Nuevo Instagram: ");
                    a.instagram = instagram;
                }
                
                mod = yesOrNo("Desea modificar el Twitter?");
                if(mod){
                    twitter = readString_ne("Nuevo Twitter: ");
                    a.twitter = twitter;
                }
                
                mod = yesOrNo("Desea modificar el Facebook?");
                if(mod){
                    facebook = readString_ne("Nuevo Facebook: ");
                    a.facebook = facebook;
                }
                
                mod = yesOrNo("Desea modificar el link a Wikipedia?");
                if(mod){
                    wikipedia = readString_ne("Nuevo link a Wikipedia: ");
                    a.wikipedia = wikipedia;
                }
            }
        }             
        if(artistaEncontrado){
            System.out.println("Artista modificado correctamente. Puede comprobar los cambios realizados en artistas.col.");
        }else{
            System.out.println("Artista inexistente."); //Problema con el .err.
        }
        System.out.printf("%n%n%n");
    }
    
    
    public List<Album> getAlbumesArtista(){
        String nombreArtista = readString_ne("%nNombre del artista: ");
        List<Album> tmp = new ArrayList<>();
        
        for(Album a : musicfy.albumes){
            if(a.interpretes.contains(nombreArtista)){
                tmp.add(a);
            }
        }
        return tmp;
    }
    
    
    
    
    
    /***********************************************************************************************
     *                  Opción playlist                                                            *
     ***********************************************************************************************/
    public void playlistAdd(){
        String tituloNuevaPlaylist, lecturaNumeroCanciones;
        Integer numeroDeCanciones;
        List<Song> cancionesDePlaylist = new ArrayList<>();
        Song song;
        boolean flagRepetido = false;
        Random rand = new Random();
        
        do{
            tituloNuevaPlaylist = readString_ne("Introduzca un título: "); //Cubrimos el isEmpty con ne.

            if(!musicfy.playlists.isEmpty()){
                flagRepetido = false;
                for(Playlist s : musicfy.playlists){                    
                    if(s.titulo.equals(tituloNuevaPlaylist)){
                        System.out.println("Título de playlist no válido. Inténtelo de nuevo...");
                        flagRepetido = true;
                    }
                }
            }
        }while(flagRepetido);
        
        
        do{
            lecturaNumeroCanciones = readString_ne("Introduzca el número de canciones: ");
            numeroDeCanciones = Integer.parseInt(lecturaNumeroCanciones);  
        }while(numeroDeCanciones <= 0 || numeroDeCanciones >= 50); //Un límite razonable para las playlists. Ampliable.
        
        for(int i = 0; i < numeroDeCanciones; i++){
            do{
                song = Song.getCancionRandom(musicfy.canciones);
            }while(cancionesDePlaylist.contains(song));
            cancionesDePlaylist.add(song);
        }
        
        Playlist playlist = new Playlist(tituloNuevaPlaylist, cancionesDePlaylist);
        musicfy.playlists.add(playlist);
        
        System.out.printf("%n%n");
    }
    
    
    public void playlistDelCancion(){
        String tituloPlaylist, tituloCancionAEliminar;
        boolean encontrado = false;
        
        tituloPlaylist = readString_ne("%nIntroduzca un título de playlist: ");

        for(Playlist s : musicfy.playlists){
            if(s.titulo.equals(tituloPlaylist)){
                System.out.printf("%nCanciones de la playlist: %n");
                for(Song x : s.canciones){
                    System.out.printf("%s%n", x.titulo);
                }
            }
        }

        tituloCancionAEliminar = readString_ne("%nCanción a eliminar: ");

        for(Playlist s : musicfy.playlists){
            if(s.titulo.equals(tituloPlaylist)){
                for(Song x : s.canciones){
                    if(x.titulo.equals(tituloCancionAEliminar)){
                        s.canciones.remove(x);
                        System.out.printf("Canción eliminada correctamente.%n");
                        encontrado = true;
                        break;
                    }
                }
            }
        }
        
        if(!encontrado){
            System.err.println("Playlist o canción inexistente.");
        }

        for(Playlist s : musicfy.playlists){
            if(s.titulo.equals(tituloPlaylist)){
                System.out.printf("%nCanciones de la playlist actualizadas: %n");
                for(Song x : s.canciones){
                    System.out.printf("%s%n", x.titulo);
                }
            }
        }
        System.out.printf("%n%n");
    }
    
    
    public void playlistAddCancion(){
        String tituloPlaylist, tituloCancionAAnnadir;
        boolean encontrado = false;
        
        tituloPlaylist = readString_ne("%nIntroduzca un título de playlist: ");
        
        for(Playlist s : musicfy.playlists){
            if(s.titulo.equals(tituloPlaylist)){
                tituloCancionAAnnadir = readString_ne("%nCanción a añadir: ");
                for(Song x : musicfy.canciones){
                    if(x.titulo.equals(tituloCancionAAnnadir)){
                        encontrado = true;
                        if(s.canciones.contains(x)){
                            System.out.println("Canción ya disponible en la playlist."); //Se pueden añadir dos canciones con el mismo título pero distintos intérpretes.
                            break;
                        }else{
                            s.canciones.add(x);
                            System.out.printf("%nCanción añadida correctamente.%n%n");
                            break;
                        }
                    }
                }
            }   
        }
        
        if(!encontrado){
            System.err.println("Playlist o canción inexistente.");
        }
        
        for(Playlist s : musicfy.playlists){
            if(s.titulo.equals(tituloPlaylist)){
                System.out.printf("%nCanciones de la playlist actualizadas: %n");
                for(Song x : s.canciones){
                    System.out.printf("%s%n", x.titulo);
                }
            }
        }
        
        System.out.printf("%n%n");
    }
    
    
    public void mostrarPlaylistsDisponibles() {      
        System.out.printf("%nPlaylists disponibles: %n"); //No existe otra forma de saber que playlists creó generaciónAleatoria.
        for(Playlist s: musicfy.playlists){
            System.out.printf("%s%n", s.titulo);
        }
        System.out.printf("%n%n");
    }
    
    
    
    
    /***********************************************************************************************
     *                  Opción Canciones.*
     * @return canciones
     ***********************************************************************************************/
    public List<Song> getCanciones() {
        return musicfy.canciones;
    }
    
    
}
