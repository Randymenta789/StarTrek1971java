public class Main {
    public static void main(String[] args) {
    
        Enterprise miNave = new Enterprise();
        
        
        System.out.println("quieto" + miNave.cuadranteX + miNave.cuadranteY + miNave.sectorX + miNave.sectorY);
        miNave.mover(0,0); 

        System.out.println("Curso 2, 0.2");
        miNave.mover(2, 0.2);
        
        System.out.println("limite");
        miNave.mover(1, 10);
    }
}