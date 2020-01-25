/*
 * Fernando Olivares Naranjo. 54126671N
 * Universidad de Salamanca.
 */
package view;

import static com.coti.tools.Esdia.*;
import controller.Controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

public class View implements Serializable {
    Controller c = new Controller();
    
    boolean salir = false;
    String option, optionExtra, teclaPress;
    
    /*
    En este menú, se incluyen las que podemos considerar como opciones
    secundarias. Hay que tener en cuenta que, por ejemplo, si utilizamos
    "altas" en álbumes, no quiere decir que inmediatamente después utilicemos
    "bajas". Por tanto, el menú siempre vuelve a su versión inicial (sin
    submenús).
    */
    public void runMenu(String menu) throws ParseException, IOException{
        do{
            option = readString_ne(menu).toLowerCase();
        
            switch (option) {
                case "1":
                    this.generacionAleatoria();
                    break;
                 
                      
                case "2":
                    System.out.printf("%nARCHIVOS:"
                                     + "%n1.- Exportar artistas."
                                     + "%n2.- Exportar álbumes.");
                    optionExtra = readString_ne("%nOpción: ");
                    switch (optionExtra) {
                        case "1":
                            this.exportarArtistas();
                            break;
                            
                        case "2":
                            this.exportarAlbumes();
                            break;
                            
                        default:
                            System.out.printf("Opción no válida.%n%n");
                            break;
                    }
                    break;
                
                    
                case "3":
                    System.out.printf("%nÁLBUM: "
                                     + "%n1.- Altas."
                                     + "%n2.- Bajas."
                                     + "%n3.- Modificaciones."
                                     + "%n4.- Consulta.");
                    optionExtra = readString_ne("%nOpción: ");
                    switch (optionExtra) {
                        case "1":
                            this.albumAdd();
                            break;
                            
                        case "2":
                            this.albumDel();
                            break;
                            
                        case "3":
                            this.albumMod();
                            break;
                            
                        case "4":
                            this.albumConsulta();
                            break;
                            
                        default:
                            System.out.printf("Opción no válida.%n%n");
                            break;
                    }
                    break;
                   
                      
                case "4":
                    System.out.printf("%nARTISTA: "
                                     + "%n1.- Altas."
                                     + "%n2.- Bajas."
                                     + "%n3.- Modificaciones."
                                     + "%n4.- Lista de álbumes.");
                    optionExtra = readString_ne("%nOpción: ");
                    switch (optionExtra) {
                        case "1":
                            this.artistaAdd();
                            break;
                            
                        case "2":
                            this.artistaDel();
                            break;
                            
                        case "3":
                            this.artistaMod();
                            break;
                            
                        case "4":
                            this.artistaListaAlbumes();
                            break;
                            
                        default:
                            System.out.printf("Opción no válida.%n%n");
                            break;
                    }
                    break;
                    
                    
                case "5":
                    System.out.printf("%nPLAYLIST: "
                                     + "%n1.- Altas."
                                     + "%n2.- Eliminar canción."
                                     + "%n3.- Añadir canción."
                                     + "%n4.- Mostrar playlists disponibles.");
                    optionExtra = readString_ne("%nOpción: ");
                    switch (optionExtra) {
                        case "1":
                            this.playlistAdd();
                            break;
                            
                        case "2":
                            this.playlistDelCancion();
                            break;
                            
                        case "3":
                            this.playlistAddCancion();
                            break;
                            
                        case "4":
                            this.mostrarPlaylistsDisponibles();
                            break;
                            
                        default:
                            System.out.printf("Opción no válida.%n%n");
                            break;
                    }
                    break;
                    
                     
                case "6":
                    this.mostrarListadoGeneralCanciones();
                    break;
                    
                    
                case "7":
                    System.out.printf("%n%nINSTRUCCIONES: %n");
                    System.out.println("");
                    System.out.println("En escritorio -> biblioteca.jar y carpeta musicfy con los datos.");
                    System.out.println("Programa -> musicfyProject (incluye.jar y makefile).");
                    System.out.println("");
                    System.out.println("El programa cargará una serie de datos desde el escritorio como colecciones");
                    System.out.println("y se utilizarán al estilo de una base de datos sobre música. Se incluyen");
                    System.out.println("canciones, álbumes, playlists, artistas y dos opciones extra (generación ");
                    System.out.println("aleatoria y exportar archivos) que aportan otros tipos de funcionalidades");
                    System.out.println("al programa.");
                    System.out.println("");
                    System.out.println("BIBLIOTECA.JAR, RUTAS Y LAS CADENAS DE IMPRIMIR: ");
                    System.out.println("Tanto las rutas como el imprimir con el formato de columnas se han realizado");
                    System.out.println("manualmente. Para las rutas, tomé la decisión al comienzo del programa y no");
                    System.out.println("comprendí a tiempo que podría haber usado Rutas.java (aunque sea un archivo");
                    System.out.println("dento de una carpeta, de una carpeta). En el caso de imprimir, las cadenas");
                    System.out.println("son extremadamente aleatorias (especialmente con la generación aleatoria) y");
                    System.out.println("y manualmente se muestran mucho mejor. Aún así, se ha procurado hacer uso de");
                    System.out.println("la biblioteca (especialmente con los readString y yesOrNo).");
                    System.out.println("");
                    System.out.println("EXPLICACIONES CONCRETAS:");
                    System.out.println("0.- Uso muchos println para intentar llevar una línea al final de estas explicaciones...");
                    System.out.println("1.- No se da de alta a los artistas de artistas.txt (consultado con el profesor).");
                    System.out.println("2.- Se permiten las repeticiones de nombres de álbumes y canciones pero no de");
                    System.out.println("    artistas. Además, se evitará que los artistas se repitan en un álbum. En");
                    System.out.println("    las playlists, lo que se añade y elimina es el objeto canción. Esto es, se");
                    System.out.println("    elimina una sola canción pero se podrá añadir una canción con el mismo");
                    System.out.println("    título (y datos como intérpretes, año, etc... distintos");
                    System.out.println("3.- AL importar de datos, se da por hecho que las canciones tienen el mismo");
                    System.out.println("    año que los álbumes. En generación aleatoria es totalmente aleatorio.");
                    System.out.println("4.- El programa posee una serie de \"límites razonables\" como por ejemplo un");
                    System.out.println("    máximo de 50 canciones por playlists o 4 intérpretes por álbum. Todos los");
                    System.out.println("    valores son modificables. Creo personalmente que todos los límites son lo");
                    System.out.println("    suficientemente amplios como para hacer pruebas de carga sin problemas al");
                    System.out.println("    programa.");
                    System.out.println("5.- Los sencillos, aunque son álbumes, se les ha incorporado la duración de las");
                    System.out.println("    canciones (por coherencia). Todas las duraciones son String y los años int.");
                    System.out.println("6.- En la generación de álbumes, el máximo se encuentra entre 500 y 1000 en las");
                    System.out.println("    siguientes ejecuciones. A tener en cuenta que las canciones multiplican ese");
                    System.out.println("    número por 6 aproximadamente. Estos máximos se pueden modificar en el modelo.");
                    System.out.println("7.- En ocasiones, System.err.printf imprime de manera extraña y se mezcla con");
                    System.out.println("    los .out. No es habitual.");
                    System.out.println("8.- Press enter para continuar habilita a que el menú no sea tan intrusivo.");
                    System.out.println("9.- Al compilar, me aparecen \"referencias rotas\" a biblioteca.jar y a otra carpeta.");
                    System.out.println("    comencé a hacer el programa en Windows y creo que se debe a eso. No he sido capaz");
                    System.out.println("    de arreglar la referencia rota desde Netbeans (aunque no afecte).");
                    System.out.println("MÁS IMPORTANTES: ");
                    System.out.println("10.-Si se ejecuta desde el terminal, es muy probable que el formato de columnas no se");
                    System.out.println("    aprecien bien al 100%.");
                    System.out.println("11.-Para artistas.col desactivar TEXT WRAPPING (en Gedit por ejemplo).");
                    System.out.println("12.-Se han añadido dos opciones extra. La número 7 para mostrar las instrucciones");
                    System.out.println("    y la 5.4. MOSTRAR PLAYLISTS. Esta es vital para conocer que playlists existen");
                    System.out.println("    dentro del programa sin entrar a añadir y eliminar canción.");
                    
                    System.out.printf("%n%n%n");
                    teclaPress = readString("Presione Enter para continuar...%n%n");
                    break;
                      
                case "q":
                    salir = yesOrNo("%nDesea salir?:");
                    break;
                    
                     
                default:
                    System.out.printf("Opción no válida.%n%n");
                    break;
            }
        }while(!salir);
    }
    
    //Inicio y final de programa.
    public void importarArchivo(){
        c.importarArchivo();
    }
    
    public void guardarArchivo(){
        c.guardarArchivo();
    }

    
    /*Opción 1: Generación aleatoria. */
    private void generacionAleatoria() throws IOException {
        c.generacionAleatoria();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    
    
    /*Opción 2: Archivos. */
    private void exportarArtistas() {
        try{
            c.exportarArtistas();
        }catch (Exception ex){
            System.err.printf("ERROR. No se pueden exportar artistas.");
        }
        System.out.printf("%nArchivo artistas.col creado correctamente.%n%n%n");
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void exportarAlbumes() {
        try {
            c.crearHTMLDeAlbumes();
        } catch(FileNotFoundException ex) {
            System.err.printf("Error al crear el HTML de álbumes.");
            return;
        }
        System.out.printf("%nArchivo albumes.html creado correctamente.%n%n");
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    
    
    /*Opción 3: Álbum. */
    private void albumAdd() {
        c.albumAdd();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void albumDel() {
        c.albumDel();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void albumMod() {
        c.albumMod();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void albumConsulta() {
        String tmp = c.albumConsulta();
        if(!tmp.isEmpty()){
            System.out.printf("%n%nDatos del álbum:%n"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "----------------------------"
                    + "-----------------------"
                    + "-------------------------------------------------------------------------------%n"
                    + "| TÍTULO                                   | INTÉPRETES                               "
                    + "                              "
                    + "                                        "
                    + "| AÑO  | DURACIÓN       | NÚMERO DE CANCIONES  | TIPO       | CANCIONES"
                    + "                                                                     "
                    + "                                                                     "
                    + "                                   "
                    + "                                                                     |"
                    + "                                                                                                    "
                    + "%n-------------------------------------------------------------------"
                    + "--------------------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "--------------------------------------------------------------------"
                    + "----------------------------"
                    + "-----------------------"
                    + "%n%s%n%n",tmp);
        }
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    
    
    /*Opción 4: Artista. */
    private void artistaAdd() {
        c.artistaAdd();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void artistaDel() {
        c.artistaDel();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void artistaMod() {
        c.artistaMod();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void artistaListaAlbumes() {
        String tmp = c.artistaListaAlbumes();

        if(!tmp.isEmpty()){
            System.out.printf("%nTener en cuenta que si no aparece alguno de sus álbumes, esto quiere decir que%n");
            System.out.println("este artista se ha añadido manualmente pero algunos de los álbumes no disponibles%n");
            System.out.println("no han sido inicializados.");
            System.out.printf("%nLista de Albumes%n"
                    + "--------------------------------------------------------------------"
                    + "----------------------------------------------------------------------"
                    + "-------------------------------------------------------------------------------%n"
                    + "| TÍTULO                                   | INTÉPRETES                               "
                    + "                                                                      "
                    + "| AÑO  | DURACIÓN       | NÚMERO DE CANCIONES  | TIPO       |"
                    + "%n-------------------------------------------------------------------"
                    + "----------------------------------------------------------------------"
                    + "--------------------------------------------------------------------------------"
                    + "%n%s%n%n",tmp);
        }else{
            System.err.printf("Esta artista no existe o no se ha inicializado ningún álbum. Si desea "
                    + "saber si existe, actualice y consulte artistas.col%n%n%n");
            //Con system.err se imprime mal. Buffer?
        }
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    
    
    /*Opción 5: Playlist. */
    private void playlistAdd() {
        c.playlistAdd();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void playlistDelCancion() {
        c.playlistDelCancion();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }

    private void playlistAddCancion() {
        c.playlistAddCancion();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    private void mostrarPlaylistsDisponibles() {
        c.mostrarPlaylistsDisponibles();
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
    
    
    /*Opción 6: Canciones. */
    private void mostrarListadoGeneralCanciones() {
        String tmp = c.getCanciones();
        System.out.printf("%n%nLista de Canciones%n"
                + "----------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------%n"
                + "| TÍTULO                                             | AÑO  | DURACIÓN       | INTÉRPRETES                         "
                + "                                                                           |"
                + "%n----------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------"
                + "%n%s%n%n",tmp);
        teclaPress = readString("Presione Enter para continuar...%n%n");
    }
    
}
