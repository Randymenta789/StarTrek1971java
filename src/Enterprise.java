public class Enterprise {

    double energia;
    double escudos;
    int torpedos;
    int cuadranteX, cuadranteY;
    int sectorX, sectorY;

    public Enterprise() {
        energia    = 3000;   
        escudos    = 0;   
        torpedos   = 10;    
        this.cuadranteX = 2;      
        this.cuadranteY = 2;
        this.sectorX    = 2;     
        this.sectorY    = 2;
    }
    
    public void mover(double curso, double warp) {
    double costoEnergia = warp * 100;
    if (warp==0) {
        System.out.println("No nos movemos...");
        return;
    }
    if (this.energia < costoEnergia) {
        System.out.println("No tenemos más energía, capitán.");
        return;
    }
    
    //angulo en rad
    double angulo = (curso - 1.0) * Math.PI / 4;
    
    //calculo de trayecto por el mapa
    double distanciaSectores = warp * 8.0;
    double saltoX = Math.round(Math.cos(angulo)) * distanciaSectores;
    double saltoY = Math.round(Math.sin(angulo)) * distanciaSectores;
    
    
    
    //posicion dentro de los cuadrantes 
    double posicionFinalX = (this.cuadranteX * 8) + this.sectorX + saltoX;
    double posicionFinalY = (this.cuadranteY * 8) + this.sectorY + saltoY;
    
    
    //final de cuadrantes
    if (posicionFinalX < 0 || posicionFinalX >= 64 || posicionFinalY < 0 || posicionFinalY >= 64) {
        System.out.println("No podemos avanzar más por ese lado!");
        return;
    }
    //actualización de datos en pantalla
    this.energia -= costoEnergia;
    
    this.cuadranteX = (int) (posicionFinalX / 8);
    this.sectorX = (int) (posicionFinalX % 8);
    
    this.cuadranteY = (int) (posicionFinalY / 8);
    this.sectorY = (int) (posicionFinalY % 8);
    
    System.out.println("Navegación completada... Posición actual: \nCuadrante[" + (cuadranteX+1) + "," + (cuadranteY+1) + "] \nSector [" + (sectorX+1) + "," + (sectorY+1) + "]");
}
    
}