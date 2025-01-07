package com.icaro.gutenbook.main;



import com.icaro.gutenbook.model.Datos;
import com.icaro.gutenbook.model.DatosLibro;
import com.icaro.gutenbook.service.ConsumoAPI;
import com.icaro.gutenbook.service.ConvierteDatos;
import com.icaro.gutenbook.view.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    
    // Variables iniciales
    
    private Menu menu = new Menu(); 
    boolean esEntero = false;
    boolean horaSalida = false;
    int opcion = 0;
    String idioma;
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
   // private final String API_KEY = "&apikey=4fc7c187";
    private ConvierteDatos conversor = new ConvierteDatos();
    
    public void muestraMenu(){
       
        // muestra el menu y escoge la opcion adecuada
        
        while (horaSalida==false){
        
        // imprime el menú
        menu.menu();
        
        while (esEntero==false){

               
               try{

                   Scanner input = new Scanner(System.in);  
                   opcion = input.nextInt();
                   
                   if (opcion<1 || opcion>8){
                       esEntero = false;
                       System.out.println("El valor debe ser un entero entre 1 y 8");
                   } else if (opcion==8){
                       System.exit(0);
                   }else{
                           esEntero = true;
                           System.out.println("La opcion escogida es la : "+ opcion);
                           }
             }catch(Exception e){
                 
                 System.out.println("El valor debe ser un entero entre 1 y 8");

             }

        }
        
        // Con la opcion escogida realiza la acción correspondiente
        
        

            switch(opcion) {
                
              case 1:
                System.out.println("Si es opcion 1");
                buscaLibroWeb();
                break;
                
              case 2:
                System.out.println("Si es opcion 2");
                esEntero = false;
                break;
                
              case 3:
                System.out.println("Si es opcion 3");
                esEntero = false;
                break;
                
              case 4:
                System.out.println("Si es opcion 4");
                esEntero = false;
                break;
                
               case 5:
                System.out.println("Si es opcion 5");
                idioma = "es";
                var jsonES = consumoApi.obtenerDatos(URL_BASE+"?languages=" + idioma);
                System.out.println(jsonES);
                esEntero = false;
               break;  
               
                 case 6:
                System.out.println("Si es opcion 6");
                idioma = "en";
                var jsonEN = consumoApi.obtenerDatos(URL_BASE+"?languages=" + idioma);
                System.out.println(jsonEN);
                esEntero = false;
                break;
                
                 case 7:
                System.out.println("Si es opcion 7");
                idioma = "de";
                var jsonDE = consumoApi.obtenerDatos(URL_BASE+"?languages=" + idioma);
                System.out.println(jsonDE);
                 esEntero = false;
                break;
                
                
              default:
                System.out.println("Nada de nada");
            }
        
        // Inicia otra consulta
             System.out.println("Presiona cualquier numero +  ENTER para otra consulta");
             Scanner entrada = new Scanner(System.in);   
             Object regresa = entrada.next();
        
        
        
       
    }
    }
    
    
     //Busqueda de libros por nombre
    private void buscaLibroWeb(){
        
        Scanner teclado = new Scanner(System.in);
                
                
                    System.out.println("Ingrese el nombre del libro que desea buscar");
                    var tituloLibro = teclado.nextLine();
                    var json = consumoApi.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
                    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
                    Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                            .findFirst();
                    if(libroBuscado.isPresent()){
                        System.out.println("Libro Encontrado ");
                        System.out.println("Título : "+libroBuscado.get().titulo());
                        System.out.println("Autores : " + libroBuscado.get().autores());
                        System.out.println("Idiomas : " + libroBuscado.get().idiomas()); 
                    }else {
                        System.out.println("Libro no encontrado");
                    }
                 
                    
                esEntero = false;
        
        
        
        
    }
}
