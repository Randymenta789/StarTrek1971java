
import java.util.Random;

public final class Enterprise {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    private double fechaEstelar;
    private double diasRestantes;
    private double energia;
    private double escudos;
    private int torpedos;
    private int cuadranteX, cuadranteY;
    private int sectorX, sectorY;
    private int[][] galaxia = new int[8][8];
    private int[][] galaxiaActual = new int[8][8];
    private long semilla;
    private int klingonsTotales;
    private int basesTotales;
    private boolean acoplado;
    private Random rd;
    private double[] daños = new double[8];
    private final String[] NOMBRES_DISPOSITIVOS = {
        "Motor de Warp", "Sensor de corto alcance", "Sensor de largo alcance",
        "Phasers", "Tubos de torpedos", "Control de daños", "Control de escudos", "Computadora"
    };
    public final int WARP = 0, SENSOR_CORTO = 1, SENSOR_LARGO = 2, PHASERS = 3,
            TORPEDOS = 4, CONTROL_DANOS = 5, ESCUDOS_SYS = 6, COMPUTADORA = 7;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class Klingon {

        int x, y, escudos;

        public Klingon(int x, int y, int escudos) {
            this.x = x;
            this.y = y;
            this.escudos = escudos;
        }
    }
    private java.util.ArrayList<Klingon> klingonsEnSector;

    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    ///////////////////////////////////////////Constructor
    ///Setters y getters
    ///
    ///
    ///
    public Enterprise(long seed) {
        this.semilla = seed;
        this.rd = new Random(this.semilla);
        this.diasRestantes = 25 + rd.nextInt(21);
        this.fechaEstelar = 2000 + rd.nextInt(2001);
        this.energia = 1500 + rd.nextInt(1501);
        this.escudos = 50;
        this.torpedos = 5 + rd.nextInt(6);
        //galaxia
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.galaxia[i][j] = -1;
                //
            }
        }
        this.cuadranteX = rd.nextInt(8);
        this.cuadranteY = rd.nextInt(8);
        this.sectorX = rd.nextInt(8);
        this.sectorY = rd.nextInt(8);
        this.nuevoUniverso(5 + rd.nextInt(16));
        this.klingonsEnSector = new java.util.ArrayList<>();
        this.klingonsTotales = contarKlingonsTotales();
        this.basesTotales = contarBasesTotales();
        this.acoplado = false;
        actualizarKlingonsEnSector();
    }

    public long getSemilla() {
        return semilla;
    }

    private void setSemilla(long seed) {
        this.semilla = seed;

    }

    public double getFechaEstelar() {
        return fechaEstelar;
    }

    private final void setFechaEstelar(double fEst) {
        this.fechaEstelar = fEst;
    }

    public double getEnergia() {
        return energia;
    }

    public final void setEnergia(double ener) {
        this.energia = (ener <= 10) ? 0 : ener;

    }

    public double getEscudos() {
        return escudos;
    }

    public final void setEscudos(double esc) {
        this.escudos = (esc < 0) ? 0 : esc;
    }

    public int getTorpedos() {
        return torpedos;
    }

    public final void setTorpedos(int torp) {
        if (torp >= 0) {
            this.torpedos = torp;
        } else {
            this.torpedos = 0;
        }

    }

    public int getCuadranteX() {
        return cuadranteX;
    }

    private final void setCuadranteX(int cX) {
        if (cX >= 0 && cX <= 7) {
            this.cuadranteX = cX;
        }

    }

    public int getCuadranteY() {
        return cuadranteY;

    }

    private final void setCuadranteY(int cY) {
        if (cY >= 0 && cY <= 7) {
            this.cuadranteY = cY;

        }
    }

    public int getSectorX() {
        return sectorX;
    }

    private final void setSectorX(int sX) {
        if (sX >= 0 && sX <= 7) {
            this.sectorX = sX;
        } else {
            this.sectorX = (sX < 0) ? 0 : 7;
        }
    }

    public int getSectorY() {
        return sectorY;
    }

    private final void setSectorY(int sY) {
        if (sY >= 0 && sY <= 7) {
            this.sectorY = sY;
        } else {
            this.sectorY = (sY < 0) ? 0 : 7;
        }

    }

    ////////////////////////////////////////////////////
    ///
    ///
    ///
    
    
    
    
    
    //////////////////////inicio y creación
    ///
    ///
    ///
    ///
    ///
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    /// @param totalKlingons
    public void nuevoUniverso(int totalKlingons) {
        int klingons = totalKlingons;
        int basesEstelares = 4 + rd.nextInt(6);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.galaxiaActual[i][j] = this.rd.nextInt(9) + 1;
            }
        }
        do {
            int filaRandom = this.rd.nextInt(8);
            int columnaRandom = this.rd.nextInt(8);
            if (this.galaxiaActual[filaRandom][columnaRandom] < 900) {
                this.galaxiaActual[filaRandom][columnaRandom] += 100;
                klingons--;
            }

        } while (klingons > 0);

        do {
            int filaRandom = this.rd.nextInt(8);
            int columnaRandom = this.rd.nextInt(8);
            int basesActuales = (this.galaxiaActual[filaRandom][columnaRandom] % 100) / 10;
            if (basesActuales < 9) {
                this.galaxiaActual[filaRandom][columnaRandom] += 10;
                basesEstelares--;
            }

        } while (basesEstelares > 0);

    }

    private int contarKlingonsTotales() {
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                total += this.galaxiaActual[i][j] / 100;
            }
        }
        return total;
    }

    private int contarBasesTotales() {
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                total += (this.galaxiaActual[i][j] % 100) / 10;
            }
        }
        return total;
    }

    private void actualizarKlingonsEnSector() {
        klingonsEnSector.clear();
        int valor = this.galaxiaActual[this.cuadranteX][this.cuadranteY];
        int numKlingons = valor / 100;

        Random rndSector = new Random(valor);

        String[][] mapeo = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapeo[i][j] = " . ";
            }
        }
        for (int i = 0; i < 100 && numKlingons > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                mapeo[r][c] = "+K+";
                int escudosK = 200 + this.rd.nextInt(100);
                klingonsEnSector.add(new Klingon(c, r, escudosK));
                numKlingons--;
            }
        }
    }

    //////Ver alrededor y mapa completo
    ///
    ///
    ///
    public void Comando2() {
        int i = 0;
        int j = 0;
        for (i = -1; i <= 1; i++) {
            for (j = -1; j <= 1; j++) {
                int filaDestino = this.cuadranteX + i;
                int colDestino = this.cuadranteY + j;

                if (filaDestino >= 0 && filaDestino < 8 && colDestino >= 0 && colDestino < 8) {
                    this.galaxia[filaDestino][colDestino] = this.galaxiaActual[filaDestino][colDestino];
                }

            }
        }

    }
    
    ///
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    ///
    
    
    
    
    /////////////Movimiento_Comando0
    ///
    ///
    /// @param curso
    /// @param warp
    /////////////////////////// ///////////////////// ///////////////////// /////////////////////
    public void mover(double curso, double warp) {
        if (this.daños[WARP] > 0 && warp > 0.2) {
            System.out.println("ADVERTENCIA: El motor Warp está dañado, máximo impulso permitido: 0.2");
            return;
        }
        if (curso == 9 || curso == 9.0) {
            curso = 1.0;
        }
        if (curso < 1.0 || curso > 9.0) {
            System.out.println("Ehm.. curso inválido capitán, el valor permitido es 1 entre 9");
            return;
        }
        if (warp < 0.125 || warp > 8) {
            System.out.println("Ese warp es inexistente, señor");
            return;
        }
        double costoEnergia = warp * 100;
        if (this.energia <= costoEnergia) {
            System.out.println("No tenemos energía suficiente para hacer eso, capitán!");
            return;
        }

        //angulo en rad
        double angulo = (curso - 1.0) * (-Math.PI / 4.0);

        //calculo de trayecto por el mapa
        double distanciaSectores = warp * 8.0;
        int saltoX = (int) Math.round(Math.cos(angulo) * distanciaSectores);
        int saltoY = (int) Math.round(Math.sin(angulo) * distanciaSectores);

        //posicion dentro de los cuadrantes 
        double posicionFinalX = (this.cuadranteX * 8) + this.sectorX + saltoX;
        double posicionFinalY = (this.cuadranteY * 8) + this.sectorY + saltoY;

        //final de cuadrantes
        if (posicionFinalX < 0 || posicionFinalX >= 64 || posicionFinalY < 0 || posicionFinalY >= 64) {
            System.out.println("Si avanzamos más nos perderemos en el espacio, no podemos seguir por ahí!");
            return;
        }

        //actualización de datos
        if (saltoX != 0 || saltoY != 0) {
            setEnergia(this.energia - costoEnergia);
            avanzarTiempo(warp);

            this.setCuadranteX((int) (posicionFinalX / 8));
            this.setSectorX((int) (posicionFinalX % 8));

            this.setCuadranteY((int) (posicionFinalY / 8));
            this.setSectorY((int) (posicionFinalY % 8));

        } else {
            System.out.println("Warp insuficiente, añade más potencia capitán.");
        }
        actualizarKlingonsEnSector();
        verificarAcoplamiento();

        if (!klingonsEnSector.isEmpty()) {
            System.out.println("\n¡ALERTA ROJA! Klingons detectados en este sector!");
            ataqueLingons();
        }

        verificarGameOver();
    }
    
       /////////InfodePosicion
    ///
    ///
    public String getPosicionActual() {
        return "Stardate: " + (int) getFechaEstelar() + " Días Restantes: " + (int) this.diasRestantes
                + " Cuadrante [" + (getCuadranteX() + 1) + "," + (getCuadranteY() + 1) + "] "
                + " Sector[" + (getSectorX() + 1) + "," + (getSectorY() + 1) + "] "
                + " Energia restante " + getEnergia()
                + " Escudos: " + getEscudos()
                + " Torpedos: " + getTorpedos();
    }

    ///
    /////////////////////////// ///////////////////// ///////////////////// /////////////////////
    ///
    ///
    ///
    ///
    
    
    
    
    ///////// Mapa Local_Comando1
    ///
    ///
    ///
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    public void mostrarSector() {
        int valor = this.galaxiaActual[this.cuadranteX][this.cuadranteY];
        int k = valor / 100;
        int b = (valor % 100) / 10;
        int e = valor % 10;

        if (this.daños[SENSOR_CORTO] > 0) {
            System.out.println("ADVERTENCIA: Sensor de corto alcance dañado, datos no disponibles!");
            return;
        }

        String[][] mapeo = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapeo[i][j] = " . ";
            }
        }
        Random rndSector = new Random(valor);
        mapeo[this.getSectorY()][this.getSectorX()] = "<*>";

        for (int i = 0; i < 100 && k > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                mapeo[r][c] = "+K+";
                k--;
            }
        }
        for (int i = 0; i < 100 && b > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                mapeo[r][c] = ">B<";
                b--;
            }
        }
        for (int i = 0; i < 100 && e > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                mapeo[r][c] = " * ";
                e--;
            }
        }
        System.out.println("           MAPA LOCAL\n");
        System.out.println(" ---------------------------------");
        for (int y = 0; y < 8; y++) {
            System.out.print("| ");
            for (int x = 0; x < 8; x++) {
                System.out.print(mapeo[y][x] + " ");
            }
            System.out.println("|");
        }
        System.out.println(" ---------------------------------");
    }

    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    ///
    ///
    ///
    
   

    ///////////////////Mapa Global_Comando 2
    ///
    ///
     //////////////////////// ///////////////////// ///////////////////// /////////////////////
    public void mostrarMapa() {
        if (this.daños[SENSOR_LARGO] > 0) {
            System.out.println("ADVERTENCIA: Sensor de largo alcance inutilizado, Mapa de galaxia no disponible!");
            return;
        }
        System.out.println("                  MAPA GLOBAL\n");
        System.out.println(" -----------------------------------------");
        for (int y = 0; y < 8; y++) {
            System.out.print("| ");
            for (int x = 0; x < 8; x++) {
                if (x == this.cuadranteX && y == this.cuadranteY) {
                    System.out.print(" <*> ");
                } else if (this.galaxia[x][y] == -1) {
                    System.out.print(" ??? ");
                } else {
                    System.out.printf(" %03d ", this.galaxia[x][y]);
                }

            }
            System.out.println("|");
        }
        System.out.println(" -----------------------------------------");
    }

    ///
    ///
    ///
    //////////////////////// ///////////////////// ///////////////////// 
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
   
    
    
    
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    ///////////////////////////
    ///Phasers_Comando3
    /// @param energia
    ///
    ///
    public void dispararPhasers(double energia) {
        
        if (this.daños[PHASERS] > 0) {
        System.out.println("ERROR: Los Phasers están dañados. ¡No pueden disparar!");
        return;
        }
    
        if (klingonsEnSector.isEmpty()) {
            System.out.println("No hay Klingons en este sector, señor");
            return;
        }
        

        if (energia > this.energia) {
            System.out.println("No tenemos suficiente energía para eso, capitán");
            return;
        }

        if (energia <= 0) {
            System.out.println("Energía inválida, señor");
            return;
        }

        this.energia -= energia;
        double energiaPorKlingon = energia / klingonsEnSector.size();

        System.out.println("Phasers disparados con " + (int) energia + " unidades de energía.");

        java.util.ArrayList<Klingon> eliminados = new java.util.ArrayList<>();

        for (Klingon k : klingonsEnSector) {
            double distancia = Math.sqrt(Math.pow(k.x - this.sectorX, 2)
                    + Math.pow(k.y - this.sectorY, 2));
            int danio = (int) (energiaPorKlingon / (distancia + 1));

            k.escudos -= danio;

            System.out.println("Impacto en Klingon en [" + (k.y + 1) + "," + (k.x + 1)
                    + "] - " + danio + " unidades de daño");

            if (k.escudos <= 0) {
                System.out.println("*** ¡KLINGON DESTRUIDO! ***");
                eliminados.add(k);
                klingonsTotales--;
                // Actualizar galaxia
                this.galaxiaActual[this.cuadranteX][this.cuadranteY] -= 100;
            } else {
                System.out.println(" El Klingon tiene " + k.escudos + " escudos restantes");
            }
        }

        klingonsEnSector.removeAll(eliminados);

        avanzarTiempo(1);

        if (!klingonsEnSector.isEmpty()) {
            ataqueLingons();
        }

        verificarVictoria();
    }

    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    ///
    ///
    ///
    ///
    
    
    
    //////////////////////// ///////////////////// ///////////////////// /////////////////////
    //////////////////////// ///////////////////// ///////////////////// //////
    ///     DispararTorpedos_Comando 4
    ///
    /// @param curso
    ///
    public void dispararTorpedo(double curso) {
        if (this.daños[TORPEDOS] > 0) {
        System.out.println("ADVERTENCIA: Los tubos de torpedos están bloqueados por daños");
        return;
    
    }
        if (this.torpedos <= 0) {
            System.out.println("No tenemos torpedos, capitán!");
            return;
        }

        if (curso < 1.0 || curso >= 9.0) {
            System.out.println("Curso inválido para torpedo, señor");
            return;
        }

        this.torpedos--;
        System.out.println("Torpedo disparado! Torpedos restantes: " + this.torpedos);

        double angulo = (curso - 1.0) * (-Math.PI / 4.0);
        double dx = Math.cos(angulo);
        double dy = Math.sin(angulo);

        double x = this.sectorX;
        double y = this.sectorY;

        System.out.println("Trayectoria del torpedo:");

        for (int i = 0; i < 12; i++) {
            x += dx;
            y += dy;

            int ix = (int) Math.round(x);
            int iy = (int) Math.round(y);

            if (ix < 0 || ix >= 8 || iy < 0 || iy >= 8) {
                System.out.println("Torpedo perdido en el espacio...");
                break;
            }

            System.out.println("  [" + (iy + 1) + "," + (ix + 1) + "]");

            // Verificar impacto con Klingon
            Klingon impactado = null;
            for (Klingon k : klingonsEnSector) {
                if (k.x == ix && k.y == iy) {
                    impactado = k;
                    break;
                }
            }

            if (impactado != null) {
                System.out.println("*** ¡IMPACTO DIRECTO! KLINGON DESTRUIDO! ***");
                klingonsEnSector.remove(impactado);
                klingonsTotales--;
                this.galaxiaActual[this.cuadranteX][this.cuadranteY] -= 100;
                break;
            }
        }

        //this.fechaEstelar += 1;
        //this.diasRestantes -= 1;
        avanzarTiempo(1);

        if (!klingonsEnSector.isEmpty()) {
            ataqueLingons();
        }

        verificarVictoria();
    }

    /////////////////////////////// //////////////////////// ////////////////////////
    /////////////////////////// ////////////////////////
    ///
    ///
   
    
    
    
    
    //////////////// //////////////// ////////////////
    ///Escudos Comando_5
    ///
    ///
    ///
    /// @param esc
    ///
    public void escudos(double esc) {
        if (this.daños[ESCUDOS_SYS] > 0) {
        System.out.println("ADVERTENCIA: El control de escudos no responde... ¡No podemos transferir energía!");
        return;
        }
    
        if (esc < this.energia) {
            this.energia -= (int) esc;
            this.escudos = (int) (this.escudos + esc);
        } else {
            System.out.println("Tiene muy poca energía para eso, señor");
        }

    }

    //////////////// //////////////// //////////////// ////////////////
    ///  ////////////////
    ///
    ///
    ///
    
    
    
    
    
    ////////////////////////// ////////////////////////// //////////////////////////
    /// //////////////////////////   ReporteDaños Comando_6
    ///
    ///
    ///
    ///
    public void mostrarReporteDanos() {
        if (this.daños[COMPUTADORA] > 0) {
        System.out.println("ADVERTENCIA: La computadora de tiro está fuera de línea, Debe disparar manualmente");
        return;
    }
        System.out.println("\n=== REPORTE DE DAÑOS ===");
        System.out.println(String.format("%-28s %s", "Dispositivo", "Estado"));
        System.out.println("-------------------------------------------");
        for (int i = 0; i < 8; i++) {
            String estado;
            if (this.daños[i] <= 0) {
                estado = "Operativo";
            } else {
                // Formateamos para que muestre los días restantes de reparación
                estado = String.format("DAÑADO (Reparación en %.1f días)", this.daños[i]);
            }
            System.out.println(String.format("%-28s %s", NOMBRES_DISPOSITIVOS[i], estado));
        }
        System.out.println("===========================");
    }

    ////////////////////////////////////// ////////////////////////// 
    /// ////////////////////////// //////////////////////////
    ///
    ///
    ///
    ///
    
    
    
    ///////////// Reporte de mision, SUBComando_7
    ///
    ///
    //////// //////// //////// //////// //////// //////// //////// //////// ////////
    /// @return 
    public String reporteEstado() {
        if (this.daños[COMPUTADORA] > 0) {
        System.out.println("ADVERTENCIA: Base de datos inaccesible, computadora dañada");
    } 
        return """
               
               === ESTADO DE LA MISIÓN ===
               Klingons restantes: """ + klingonsTotales + "\n"
                + "Bases estelares: " + basesTotales + "\n"
                + "Días restantes: " + (int) diasRestantes + "\n"
                + "Stardate: " + (int) fechaEstelar + "\n"
                + "===========================";
    }

    //////// //////// //////// //////// //////// //////// //////// //////// ////////
    ///
    ///
    ///
    
    
    
    
    //////// //////// //////// //////// //////// //////// //////// //////// ////////
    /// calcular Torpedos SUBComando_7
    ///
    ///
    /// @param destinoX
    /// @param destinoY
    /// @return 
    ///
    public String calcularCursoTorpedo(int destinoX, int destinoY) {
        
        int dx = destinoX - this.sectorX;
        int dy = destinoY - this.sectorY;

        double angulo = Math.atan2(-dy, dx);
        double curso = (angulo * 4.0 / Math.PI) + 1.0;

        if (curso < 1.0) {
            curso += 8.0;
        }
        if (curso >= 9.0) {
            curso -= 8.0;
        }

        return String.format("Curso recomendado: %.2f", curso);
    }

    //////// //////// //////// //////// //////// //////// //////// //////// ////////
    ///
    ///
    ///
    
    
    
    ////////////////////////////////////Verificaciones
    ///
    ///
    ///
    //////////////////////////////   
    //////////////////Verificador de daño a jugador 
    private void ataqueLingons() {
        System.out.println("\n*** ¡LOS KLINGONS ATACAN! ***");

        for (Klingon k : klingonsEnSector) {
            double distancia = Math.sqrt(Math.pow(k.x - this.sectorX, 2)
                    + Math.pow(k.y - this.sectorY, 2));
            int danio = (int) (k.escudos / (distancia + 1)) / 2;

            System.out.println("Klingon en [" + (k.y + 1) + "," + (k.x + 1)
                    + "] dispara - " + danio + " de daño");

            if (this.escudos >= danio) {
                this.escudos -= danio;
                System.out.println("   Absorbido por escudos. Escudos: " + (int) this.escudos);
            }
            else if (this.escudos <= 0) {
                System.out.println("   ¡ALERTA CRÍTICA! Impacto directo en el casco sin escudos.");
                System.out.println("   La explosión destruye el puente de mando...");
                this.energia = 0; 
                break;
            } else {
                int danioRestante = (int) (danio - this.escudos);
                this.escudos = 0;
                this.energia -= danioRestante;
                System.out.println("   ¡ESCUDOS CAÍDOS! Daño a la nave: " + danioRestante);
                System.out.println("   Energía restante: " + (int) this.energia);

                if (this.rd.nextDouble() < 0.40) {
                    int sistemaDañado = this.rd.nextInt(8);
                    double tiempoDaño = 1.0 + (this.rd.nextDouble() * 4.0);
                    this.daños[sistemaDañado] += tiempoDaño;
                    System.out.println("   ¡ALERTA CRÍTICA! Impacto directo en " + NOMBRES_DISPOSITIVOS[sistemaDañado] + ".");
                }
            }
        }
    }
    
     private void avanzarTiempo(double tiempoTranscurrido) {
        this.fechaEstelar += tiempoTranscurrido;
        this.diasRestantes -= tiempoTranscurrido;

        // El equipo de reparación arregla los dispositivos con el tiempo
        for (int i = 0; i < 8; i++) {
            if (this.daños[i] > 0) {
                // Si el Control de Daños (índice 5) está operativo, reparan más rápido.
                double factorReparacion = (this.daños[CONTROL_DANOS] <= 0) ? 1.0 : 0.5;
                this.daños[i] -= (tiempoTranscurrido * factorReparacion);

                // Si ya terminó de repararse:
                if (this.daños[i] <= 0) {
                    this.daños[i] = 0;
                    System.out.println("\n*** Control de Daños informa: " + NOMBRES_DISPOSITIVOS[i] + " totalmente reparado. ***");
                }
            }
        }
    }
    
     private void reabastecer() {
        System.out.println("\n*** ACOPLADO A BASE ESTELAR ***");
        System.out.println("Reabastecimiento completo:");
        this.energia = 3000;
        this.torpedos = 10;
        this.escudos = 0;
        for (int i = 0; i < 8; i++) {
            this.daños[i] = 0;
        }
        System.out.println("  Energía: " + (int) this.energia);
        System.out.println("  Torpedos: " + this.torpedos);
        System.out.println("  Escudos bajados para reparaciones");
    }
     
     
     
    public void verificarAcoplamiento() {
        int valor = this.galaxiaActual[this.cuadranteX][this.cuadranteY];
        int bases = (valor % 100) / 10;

        if (bases == 0) {
            this.acoplado = false;
            return;
        }

        //recrear mapa para encontrar la base
        Random rndSector = new Random(valor);
        String[][] mapeo = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapeo[i][j] = " . ";
            }
        }

        mapeo[this.sectorY][this.sectorX] = "<*>";

        // saltar Klingons
        int k = valor / 100;
        for (int i = 0; i < 100 && k > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                mapeo[r][c] = "+K+";
                k--;
            }
        }

        // encontrar base
        for (int i = 0; i < 100 && bases > 0; i++) {
            int r = rndSector.nextInt(8);
            int c = rndSector.nextInt(8);
            if (mapeo[r][c].equals(" . ")) {
                // Verificar si estamos adyacentes
                if (Math.abs(r - this.sectorY) <= 1 && Math.abs(c - this.sectorX) <= 1) {
                    this.acoplado = true;
                    reabastecer();
                    return;
                }
                bases--;
            }
        }

        this.acoplado = false;
    }

    private void verificarVictoria() {
        if (klingonsTotales <= 0) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║ *** ¡VICTORIA! ***                  ║");
            System.out.println("║ ¡Has destruido a todos los Klingons!║");
            System.out.println("║ ¡La Federación está a salvo!        ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.exit(0);
        }
    }

    public boolean verificarGameOver() {
        if ((int) this.diasRestantes <= 0) {
            System.out.println("El tiempo se ha agotado señor... los Klingons han sometido a la federación...");
            System.out.println("Usted ha perdido.");
            return false;
        }
        if (this.energia <= 0) {
            System.out.println("CAPITÁN, nuestra energía se ha agotado, nos hemos varado y pronto vendrán por nosotros");
            System.out.println("Los klingons nos acabarán pronto, ha sido un placer.");
            System.out.println("Usted ha perdido.");
            return false;
        }
        return true;
    }
    ////////////////////////////////////
    ///
    ///
    ///
    //////////////////////////////  
}
