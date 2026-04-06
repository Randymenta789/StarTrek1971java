import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        // nota: las coordenadas están en base 0(0,7)
        // hay que sumar 1 para el usuario final(1,8)
        Scanner sc = new Scanner(System.in);
        
        Enterprise miNave = new Enterprise(2000, 20, 0, 5, /* COORDENADAS(cX,cY,sX,sY) */0, 0, 0, 0 );
        mostrarMenuPrincipal();
        Instrucciones();
        int op = 0;
        do {
            while (!sc.hasNextInt()) { 
                   System.out.println("Carácter inválido");
                   sc.next(); 
                   System.out.print("Intente de nuevo: ");
    }
            op = sc.nextInt();
            switch (op){
                
                case 0:
                    System.out.println("Curso:(1-9)");
                    double cursoJugador = sc.nextDouble();
                    System.out.println("Warp:(0-8)");
                    double warpJugador = sc.nextDouble();
                    miNave.mover(cursoJugador, warpJugador);
                    miNave.mostrarMapa();
                    System.out.println("Ubicación actual: " + miNave.getPosicionActual());
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
                    
            }
        } while (miNave.verificarGameOver());
       
        
       
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
}