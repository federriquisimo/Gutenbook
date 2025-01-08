package com.icaro.gutenbook.main;



import com.icaro.gutenbook.model.Datos;
import com.icaro.gutenbook.model.DatosLibro;
import com.icaro.gutenbook.service.ConsumoAPI;
import com.icaro.gutenbook.service.ConvierteDatos;
import com.icaro.gutenbook.service.LibroService;
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
    
     private LibroService servicioLibro;

    public Principal(LibroService service) {
        this.servicioLibro = service;
    }
    
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
                buscarLibroWeb();
                esEntero = false;
                break;
                
              case 2:
                System.out.println("Si es opcion 2");
                listarLibros();
                esEntero = false;                
                break;
                
              case 3:
                System.out.println("Si es opcion 3");
                listarAutores();
                esEntero = false;
                break;
                
              case 4:
                System.out.println("Si es opcion 4");
                listarAutoresVivos();
                esEntero = false;
                break;
                
               case 5:
                System.out.println("Si es opcion 5");
                listarLibroPorIdioma("es");
                esEntero = false;
               break;  
               
                 case 6:
                System.out.println("Si es opcion 6");
                listarLibroPorIdioma("en");
                esEntero = false;
                break;
                
                 case 7:
                System.out.println("Si es opcion 7");
                 listarLibroPorIdioma("de");
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
    
     //Busqueda de libros por nombre en API
    private void buscarLibroWeb() {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var titulo = teclado.nextLine();
        servicioLibro.buscarLibroPorTitulo(titulo);
        
         
    }
    
    // Listar libros de la BD
    
     private void listarLibros() {
        servicioLibro.listaLibros();
    }
    
     // Listar autores de la BD
     
      private void listarAutores() {
        servicioLibro.listaAutores();
    }
      
    // Autores vivos en un años determinado
      private void listarAutoresVivos() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa el tal  año");
        int agnio = Integer.parseInt(teclado.nextLine());
        servicioLibro.listaAutoresVivos(agnio);
    }
     
    
      // Libros por idioma
       private void listarLibroPorIdioma(String idioma) {
        
       servicioLibro.ListaLibrosPorIdioma(idioma);
    }
    
    
    
    
}
