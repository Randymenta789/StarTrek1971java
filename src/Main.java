public class Main {
    public static void main(String[] args) {
        
        // nota: las coordenadas están en base 0(0,7)
        // hay que sumar 1 para el usuario final(1,8)
        Enterprise miNave = new Enterprise(1000, 0, 5, /* COORDENADAS(cX,cY,sX,sY) */0, 0, 0, 0);
        
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(2, 8);
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(1, 1.25);
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(1, 0.25);
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
         miNave.mover(1, 0.25);
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(1, 0.24);
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
    
    }
}