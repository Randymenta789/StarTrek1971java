import java.util.Random;
public final class Enterprise {
    private double fechaEstelar;
    private double diasRestantes=15;
    private double energia;
    private double escudos;
    private int torpedos;
    private int cuadranteX, cuadranteY;
    private int sectorX, sectorY;
    private int[][] galaxia = new int[8][8];
    private int[][] galaxiaActual = new int[8][8];
    private long semilla;
    private Random rd;
    

    public Enterprise(long seed, double fEst, double ener, double esc, int torp, int cX, int cY, int sX, int sY) {
        setSemilla(seed);
        setFechaEstelar(fEst);    
        setEnergia(ener);
        setEscudos(esc);
        setTorpedos(torp);
        setCuadranteX(cX);
        setCuadranteY(cY);
        setSectorX(sX);
        setSectorY(sY);
        //galaxia
        for(int i=0; i<8; i++) {
          for(int j=0; j<8; j++) {
          this.galaxia[i][j] = -1; 
        //
        }
        this.rd = new Random(this.semilla);
        this.galaxiaActual = new int[8][8];
        this.cuadranteX = rd.nextInt(8);
        this.cuadranteY = rd.nextInt(8);
        this.nuevoUniverso();
      }
    }

    public long getSemilla() {
        return semilla;
    }
    
    
    public void setSemilla(long seed) {
        this.semilla = seed;
        
    }

    
    
    public double getFechaEstelar() {
        return fechaEstelar;
    }

    public final void setFechaEstelar(double fEst) {
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
        this.escudos = (esc<0) ? 0 : esc;
    }

    public int getTorpedos() {
        return torpedos;
    }

    public final void setTorpedos(int torp) {
        if(torp >= 0 ){
            this.torpedos = torp;
        }else {
            this.torpedos=0;
        }
        
    }

    public int getCuadranteX() {
        return cuadranteX;
    }

    public final void setCuadranteX(int cX) {
    if (cX >= 0 && cX <= 7) {
        this.cuadranteX = cX;
    }
   
    }

    public int getCuadranteY() {
        return cuadranteY;
       
    }

    public final void setCuadranteY(int cY) {
       if (cY >= 0 && cY <= 7) {
        this.cuadranteY = cY;
        
    }
    }

    public int getSectorX() {
        return sectorX;
    }

    public final void setSectorX(int sX) {
    if (sX >= 0 && sX <= 7) {
        this.sectorX = sX;
    } else {
        this.sectorX = (sX < 0) ? 0 : 7;
    }
    }

    public int getSectorY() {
        return sectorY;
    }

    public final void setSectorY(int sY) {
        if (sY >= 0 && sY <= 7) {
        this.sectorY = sY;
    } else {
        this.sectorY = (sY < 0) ? 0 : 7;
    }
        
    }
    
     public void nuevoUniverso(){
         int klingons = 30;
         int basesEstelares = 10;
         
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 this.galaxiaActual[i][j] = this.rd.nextInt(9)+ 1;
             }
         }
         do {
               int filaRandom = this.rd.nextInt(8);
               int columnaRandom = this.rd.nextInt(8);
               if(this.galaxiaActual[filaRandom][columnaRandom]< 900){
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

     
 
     
    public void mover(double curso, double warp) {
    if (curso == 9 || curso==9.0){
        curso=1.0;
    }
    if (curso < 1.0 || curso > 9.0) {
        System.out.println("Ehm.. curso inválido capitán, el valor permitido es 1 entre 9");
        return;
    }    
    if (warp < 0.125 || warp > 8) {
        System.out.println("Ese warp es inexistente, señor.");
        return; 
    }
    double costoEnergia = warp * 100;
    if (this.energia <= costoEnergia) {
        System.out.println("No tenemos energía suficiente para hacer eso, capitán.");
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
    this.setFechaEstelar(this.fechaEstelar+warp);
    this.diasRestantes -= warp;

   
    this.setCuadranteX((int) (posicionFinalX / 8));
    this.setSectorX((int) (posicionFinalX % 8));
    
    this.setCuadranteY((int) (posicionFinalY / 8));
    this.setSectorY((int) (posicionFinalY % 8));
    
    } else {
    System.out.println("Warp insuficiente, añade más potencia capitán.");
    }
    verificarGameOver();
    }
    
   
    
    
    public String getPosicionActual() {
    return "Stardate:" + (int)getFechaEstelar() + "Días Restantes: " + (int)this.diasRestantes +
           "Cuadrante[" + (getCuadranteX() + 1) + "," + (getCuadranteY() + 1) + "] " +
           "Sector[" + (getSectorX() + 1) + "," + (getSectorY() + 1) + "]" +
           "Energia restante" + " " + getEnergia() + 
           "Escudos: " + getEscudos() + 
           "Torpedos: " + getTorpedos();
    }
    
    
        public void mostrarSector() {
        int valor = this.galaxiaActual[this.cuadranteX][this.cuadranteY];
        int k = valor / 100;
        int b = (valor % 100) / 10;
        int e = valor % 10; 
         
        String[][] mapeo = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               mapeo[i][j] = " . "; 
        }
    }
        Random rndSector = new Random(valor);
        mapeo[this.getSectorY()][this.getSectorX()] = "<*>";    
      
       
        
       for (int i = 0; i < 100 && k > 0; i++) {
        int r = rndSector.nextInt(8); int c = rndSector.nextInt(8);
        if (mapeo[r][c].equals(" . ")) { mapeo[r][c] = "+K+"; k--; }
    }
    for (int i = 0; i < 100 && b > 0; i++) {
        int r = rndSector.nextInt(8); int c = rndSector.nextInt(8);
        if (mapeo[r][c].equals(" . ")) { mapeo[r][c] = ">B<"; b--; }
    }
    for (int i = 0; i < 100 && e > 0; i++) {
        int r = rndSector.nextInt(8); int c = rndSector.nextInt(8);
        if (mapeo[r][c].equals(" . ")) { mapeo[r][c] = " * "; e--; }
    }
         
         System.out.println("\n--------------------------------");
         for (int y = 0; y < 8; y++) {
         System.out.print("| ");     
         for (int x = 0; x < 8; x++) {
            System.out.print(mapeo[y][x] + " ");
        }
        System.out.println("|"); 
    }
    System.out.println("---------------------------------");
    }
    
    
    public void mostrarMapa() {
    System.out.println("MAPA");
    System.out.println("--------------------------------");
    for (int y = 0; y < 8; y++) {
        System.out.print("| ");     
        for (int x = 0; x < 8; x++) {
            if (x == this.cuadranteX && y == this.cuadranteY) {
                System.out.print(" <*> "); 
            } else if (this.galaxia[x][y]== -1) {                     
                    System.out.print(" ??? ");
                }else{
                    System.out.printf(" %03d ", this.galaxia[x][y]);
                }
              
            
        }
        System.out.println("|"); 
    }
    System.out.println("---------------------------------");
    }

    
   
    public void Comando2(){
        int i = 0;
        int j = 0;
            for (i = -1; i <=1 ; i++) {
                for (j = -1; j <= 1; j++){
                int filaDestino = this.cuadranteX + i;
                int colDestino = this.cuadranteY + j;
                  
                if (filaDestino >= 0 && filaDestino < 8 && colDestino >= 0 && colDestino < 8){
                this.galaxia[filaDestino][colDestino] = this.galaxiaActual[filaDestino][colDestino];    
                }
                  
            }
        }
        
        
        
    }
    
    public void escudos(double esc){
        if(esc<this.energia){       
            this.energia -= (int)esc;
            this.escudos = (int)(this.escudos + esc);
        }else {
            System.out.println("Tiene muy poca energía para eso, señor");
        }
        
    }
    
    public boolean verificarGameOver(){
        if ((int)this.diasRestantes<=0){
        System.out.println("El tiempo se ha agotado señor... los Klingons han sometido a la federación...");
        System.out.println("Usted ha perdido.");
        return false;
    }    
        if (this.energia <=0){
            System.out.println("CAPITÁN, nuestra energía se ha agotado, nos hemos varado y pronto vendrán por nosotros");
            System.out.println("Los klingons nos acabarán pronto, ha sido un placer.");
            System.out.println("Usted ha perdido.");
            return false;
        } 
        return true;
    }
    
    
    
}
