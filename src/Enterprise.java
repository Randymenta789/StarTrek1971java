public class Enterprise {
    private double fechaEstelar;
    private double diasRestantes=15;
    private double energia;
    private double escudos;
    private int torpedos;
    private int cuadranteX, cuadranteY;
    private int sectorX, sectorY;

    public Enterprise(double fEst, double ener, double esc, int torp, int cX, int cY, int sX, int sY) {
        setFechaEstelar(fEst);
     
        setEnergia(ener);
        setEscudos(esc);
        setTorpedos(torp);
        setCuadranteX(cX);
        setCuadranteY(cY);
        setSectorX(sX);
        setSectorY(sY);
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
    public void mostrarMapa() {
    System.out.println("\n--------------------------------");
    for (int y = 0; y < 8; y++) {
        System.out.print("| ");     
        for (int x = 0; x < 8; x++) {
            if (x == getSectorX() && y == getSectorY()) {
                System.out.print("<*> "); 
            } else {
                System.out.print(" .  "); 
            }
        }
        System.out.println("|"); 
    }
    System.out.println("---------------------------------");
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