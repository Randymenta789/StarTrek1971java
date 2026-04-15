
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // nota: las coordenadas están en base 0(0,7)
        // hay que sumar 1 para el usuario final(1,8)
        Scanner sc = new Scanner(System.in);

        long randomSeed = System.currentTimeMillis();
        Enterprise miNave = new Enterprise(randomSeed);

        ///////////////// ///////////////// /////////////////menu 1
        ///
        ///
        int inicio;
        do {

            mostrarMenuPrincipal();
            inicio = sc.nextInt();

            switch (inicio) {

                case 1:
                    Instrucciones(sc);
                    pausar(sc);
                    break;

                case 2:
                    miNave.mostrarMapa();
                    System.out.println("Nuestra ubicación: " + miNave.getPosicionActual());
                    pausar(sc);
                    break;

                default:
                    System.out.println("Valor incorrecto");
                    break;
            }

        } while (inicio != 2);
        ///
        ///
        /////////////////////////////////////////////////////////////////
       
        
        
        
        
        
        ///////////////////////////////////////////////////////////////Comandos
        ///
        /// 
        int op = 0;
        do {
            sheet();
            op = sc.nextInt();
            switch (op) {

                //Curso
                case 0:
                    System.out.println("Curso:(1-9)");
                    double cursoJugador = sc.nextDouble();
                    System.out.println("Warp:(0-8)");
                    double warpJugador = sc.nextDouble();
                    miNave.mover(cursoJugador, warpJugador);
                    System.out.println("Nuestra ubicación: " + miNave.getPosicionActual());
                    pausar(sc);
                    break;
                //Mapacorto    
                case 1:
                    miNave.mostrarSector();
                    System.out.println(miNave.getPosicionActual());
                    pausar(sc);
                    break;
                //Mapatotal    
                case 2:
                    miNave.Comando2();
                    miNave.mostrarMapa();
                    System.out.println(miNave.getPosicionActual());
                    pausar(sc);
                    break;
                //Phasers    
                case 3:
                    System.out.println("Energía disponible: " + miNave.getEnergia());
                    System.out.println("¿Cuánta energía desea usar en phasers capitán?");
                    double energiaPhasers = sc.nextDouble();
                    miNave.dispararPhasers(energiaPhasers);
                    pausar(sc);
                    break;
                //Torpedos    
                case 4:
                    System.out.println("Torpedos restantes: " + miNave.getTorpedos());
                    System.out.println("Curso del torpedo (1-9):");
                    double cursoTorpedo = sc.nextDouble();
                    miNave.dispararTorpedo(cursoTorpedo);
                    pausar(sc);
                    break;
                //Escudos
                case 5:
                    System.out.println("¿Qué cantidad de escudos desea, capitán?");
                    miNave.escudos(sc.nextDouble());
                    System.out.println("Nuevos escudos: " + miNave.getEscudos() + "Energia restante: " + miNave.getEnergia());
                    break;
                //Daños
                case 6:
                    miNave.mostrarReporteDanos();
                    pausar(sc);
                    break;
                //Computadora
                case 7:
                    System.out.println("Computadora inteligente funcionando ¿qué opción desea?");
                    System.out.println("0 = Estado de la misión");
                    System.out.println("1 = Calcular curso para torpedo");
                    int otroop = sc.nextInt();
                    switch (otroop) {
                        case 0:
                            System.out.println(miNave.reporteEstado());
                            break;
                        case 1:
                            System.out.println("Coordenadas del objetivo:");
                            System.out.print("Y (1-8): ");
                            int ty = sc.nextInt() - 1;
                            System.out.print("X (1-8): ");
                            int tx = sc.nextInt() - 1;
                            System.out.println(miNave.calcularCursoTorpedo(tx, ty));
                            break;
                    }
                    pausar(sc);
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;

            }
        } while (miNave.verificarGameOver());
    

    ////
    ////
    ////
    ///////////////////////////////////////////////////////////////////////
        
       
        
        
        
    }
    //limpiarbuffer
    //
     public static void pausar(Scanner sc) {
        System.out.println("\n[ENTER]");
        sc.nextLine();
        sc.nextLine();
    }

    ///
     ///
 
     
     
     
     
     ////////////////////////////////////////////////////////Menusdeljuego
     ///
     ///
     ///
     ///

    public static void mostrarMenuPrincipal() {
        System.out.println("--------------Port de Star Trek de 1971--------------");
        System.out.println("------------Creado por Nicolás Sotelo S.--------------");
        System.out.println("Elija una opción: \n");
        System.out.println("1: Instrucciones");
        System.out.println("2: Jugar");
        System.out.println(" ");
    }

    public static void Instrucciones(Scanner sc) {
        System.out.println("Instrucciones: \n");
        System.out.println("<*> = Enterprise");
        System.out.println("Su misión como capitán de la Enterprise es derrotar a todos los Klingons en un cierto tiempo.");
        System.out.println("La galaxia está dividida en un mapa de 8x8 Cuadrantes (64 zonas en total). A su vez, cada cuadrante es un mapa propio de 8x8 Sectores.\n");
        System.out.println("Puede perder el juego si su energía pasa a 0 o se acaban los días");
        sc.nextLine();
        sc.nextLine();
        System.out.println("Comandos: \n");
        System.out.println("Comando 0 = Elegir curso de la nave \n");
        System.out.println("El curso se define como se muestra a continuación\n");
        System.out.println("E = Enterprise");
        System.out.println("4        3         2");
        System.out.println("  ↖      ⇡      ↗  ");
        System.out.println("     ↖   ⇡   ↗     ");
        System.out.println("5    ⇠   E   ⇢    1");
        System.out.println("     ↙   ⇣   ↘     ");
        System.out.println("  ↙      ⇣      ↘  ");
        System.out.println("6        7         8\n");
        System.out.println("En el curso que escoja puede escoger enteros y decimales, por ejemplo 1.5 sería el punto medio entre 1 y 2");
        System.out.println("El vector 9 no está definido pero su curso se puede aproximar a este valor (8.9)\n");
        System.out.println("Un warp (1 warp) es el tamaño de un cuadrante, por lo tanto, para ir del cuadrante 5,6 al 5,5 usarías Curso 3, Warp 1");
        System.out.println("La notación que se usa en este juego es X,Y con X del 1 al 8 de izquierda a derecha y Y del 1 al 8 de arriba a abajo.\n");
        sc.nextLine();
        System.out.println("Comando 1 = Escaneo corto\n");
        System.out.println("Sirve para ver el sector en el que se encuentra, es decir, donde se encuentra en el momento\n");
        sc.nextLine();
        System.out.println("Comando 2 = Escaneo largo\n");
        System.out.println("A diferencia del corto, es para ver en qué cuadrante se encuentra la nave y ver los alrededores de esta");
        System.out.println("Si la nave no ha pasado por cierto lugar se verán '???' pero una vez que pase apareceran números\n");
        System.out.println("El primer número es para denotar a los Klingons, el segundo es para las bases estelares y el tercero son para las estrellas o asteroides");
        System.out.println("Ejemplo: 217 = 2 Klingons, 1 base estelar, 7 estrellas\n");
        sc.nextLine();
        System.out.println("Comando 3 = Disparar phasers\n");
        System.out.println("Se usa para intentar atacar a los Klingons, se tiene que poner un costo de energía y  se restarán escudos de los Klingons dependiendo de la energía usada\n");
        sc.nextLine();
        System.out.println("Comando 4 = Disparar los torpedos de fotones\n");
        System.out.println("Un arma potente para matar a los Klingons de un solo tiro, el costo es que hay pocos y además hay que apuntas correctamente");
        System.out.println("Si se desconoce el cálculo se puede usar el comando 7 para saber exactamente a dónde se debe lanzar\n");
        sc.nextLine();
        System.out.println("Comando 5 = Control de escudos\n");
        System.out.println("Escudos que sirven para los ataques de los Klingons, si no tiene escudo y recibe un golpe, la nave será destruida\n");
        sc.nextLine();
        System.out.println("Comando 6 = Reporte de daños\n");
        System.out.println("Un reporte que muestra todos sus dispositivos y sus estados");
        System.out.println("Se le avisará cuando un sistema está dañado, cuando estén dañados dejarán de hacer su función hasta que se reparen\n");
        sc.nextLine();
        System.out.println("Comando 7 = Llamar libreria de computadora\n");
        System.out.println("La computadora inteligente de la Enterprise cuenta con:\n");
        System.out.println("0 = Estado de la misión, que mostrará información vital");
        System.out.println("1 = Calcular curso para torpedo, para ayudarle a dirigir el torpedo correctamente");
        sc.nextLine();

    }

    public static void sheet() {
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
