import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
        // nota: las coordenadas están en base 0(0,7)
        // hay que sumar 1 para el usuario final(1,8)
        Scanner sc = new Scanner(System.in);
        
        Enterprise miNave = new Enterprise(12, 2000, 1000, 0, 5, /* COORDENADAS(cX,cY,sX,sY) */0, 0, 0, 0 );
        int inicio;
        do {
            mostrarMenuPrincipal(); 
            inicio = sc.nextInt();
            sc.nextLine();

        switch (inicio) {
            case 1:
            Instrucciones();
            pausar(sc);
            break;

            case 2:
            miNave.mostrarMapa();
            System.out.println("Ubicación actual: " + miNave.getPosicionActual());
            pausar(sc);
            break;

        default:
            System.out.println("Valor incorrecto");
            break;
            }

         } while (inicio != 1 && inicio != 2);

       
        int op = 0;
        do { sheet();
            
            op = sc.nextInt();
            
            switch (op){
                
                case 0:
                    System.out.println("Curso:(1-9)");
                    double cursoJugador = sc.nextDouble();
                    System.out.println("Warp:(0-8)");
                    double warpJugador = sc.nextDouble();
                    miNave.mover(cursoJugador, warpJugador);
                    System.out.println("Ubicación actual: " + miNave.getPosicionActual());   
                    pausar(sc);
                    break;
                case 1:
                    miNave.mostrarSector();
                    pausar(sc);
                    break;
                case 2:
                    miNave.Comando2();
                    miNave.mostrarMapa();
                    break;
                case 5:
                    System.out.println("Qué cantidad de escudos desea?");
                    miNave.escudos(sc.nextDouble());
                    System.out.println("Nuevos escudos: " + miNave.getEscudos()+ "Energia restante: " + miNave.getEnergia());
                    break;
                case 6:
                    System.out.println("Dispositivo      Estado");
                    System.out.println("Motor de warp");
                    System.out.println("Sensor corto");
                    System.out.println("Sensor largo");
                    System.out.println("Sensor largo");
                    System.out.println("Phasers");
                    System.out.println("Tubos de torpedos");
                    System.out.println("Control de daños");
                    System.out.println("Control de escudos");
                    System.out.println("Computadora");
                    break;
                case 7: 
                    System.out.println("Computadora inteligente prendida ¿qué opción desea?");
                    System.out.println("0 = Reporte d estado; Muestra el número de klingons que hay, días restaentes y bases estelares que quedan");
                    System.out.println("1 = Hacer cálculo para usar torpedos");
                    int otroop = sc.nextInt();
                    switch(otroop){
                        case 0:
                            break;
                        case 1:
                            System.out.println("");
                            break;
                      
                    }
                    
                default:
                    System.out.println("Opción inválida");
                    break;
                    
            }
        } while (miNave.verificarGameOver());
       
        
       
    }
    
     public static void pausar(Scanner sc){
         System.out.println("\n[ Presione ENTER para continuar... ]");
         sc.nextLine(); 
         sc.nextLine(); 
     }
     
     
     public static void mostrarMenuPrincipal(){
        System.out.println("--------------Port de Star Trek de 1971--------------");
        System.out.println("Elija una opción: \n");
        System.out.println("1: Instrucciones");
        System.out.println("2: Jugar");
        System.out.println(" ");
        }
     
     public static void Instrucciones(){
        System.out.println("Instrucciones: \n");
        System.out.println("<*> = Enterprise");
        System.out.println("Su misión como capitán de la Enterprise es derrotar a todos los Klingons en un cierto tiempo.");
        System.out.println("La galaxia está dividida en un mapa de 8x8 Cuadrantes (64 zonas en total). A su vez, cada cuadrante es un mapa propio de 8x8 Sectores.\n");
        System.out.println("Comandos: \n");
        System.out.println("Comando 0 = Elegir curso de la nave \n");
        System.out.println("El curso se define en un arreglo vectorial númerico como se muestra a continuación\n");
        System.out.println("E=Enterprise");
        System.out.println("4        3         2" );
        System.out.println("  ↖      ⇡      ↗  " );
        System.out.println("     ↖   ⇡   ↗     "); 
        System.out.println("5    ⇠   E   ⇢    1" );
        System.out.println("     ↙   ⇣   ↘     " );
        System.out.println("  ↙      ⇣      ↘  " );
        System.out.println("6        7         8" );
        System.out.println("En el curso que escoja puede escoger enteros y decimales, por ejemplo 1.5 sería el punto medio entre 1 y 2");
        System.out.println("El vector 9 no está definido pero su curso se puede aproximar a este valor (8.9)");
        System.out.println("Un warp (1 warp) es el tamaño de un cuadrante, por lo tanto, para ir del cuadrante 5,6 al 5,5 usarías Curso 3, Warp 1");
        System.out.println("La notación que se usa en este juego es X,Y con X del 1 al 8 de izquierda a derecha y Y del 1 al 8 de arriba a abajo.");
        
        
    }
       public static void sheet(){
           System.out.println("0 = Elegir curso de la nave                4        3         2    ");
           System.out.println("1 = Sensor de corto alcance                  ↖      ⇡      ↗       ");
           System.out.println("2 = Sensor de largo alcance                     ↖   ⇡   ↗          ");
           System.out.println("3 = Disparar phasers                       5    ⇠   E   ⇢    1     ");
           System.out.println("4 = Disparar los torpedos de fotones            ↙   ⇣   ↘          ");
           System.out.println("5 = Control de escudos                       ↙      ⇣      ↘       ");        
           System.out.println("6 = Reporte de daños                       6        7         8     ");
           System.out.println("7 = Llamar libreria de computadora                                  ");
           
     }
}