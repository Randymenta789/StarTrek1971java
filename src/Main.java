public class Main {
    public static void main(String[] args) {
        
        // nota: las coordenadas están en base 0(0,7)
        // hay que sumar 1 para el usuario final(1,8)
        Enterprise miNave = new Enterprise(1000, 0, 5, /* COORDENADAS(cX,cY,sX,sY) */0, 0, 0, 0 );
        
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(8.0, 9); 
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(1, 7); 
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(8.9, 0.125); 
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(8.8, 0.125); 
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(1, 0.125);
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
        miNave.mover(5, 0.125);
        miNave.mostrarMapa();
        System.out.println("Ubicación actual: " + miNave.getPosicionActual());
    }
}