package puzzle8;

import java.util.List;
/**
 * Pruebas para el puzzle 2D de piezas deslizantes.
 * 
 * @Jose Miguel Horcas Aguilera, Lorenzo Mandow
 *
 */
public class TestPuzzle {
    
    public static void main(String[] args) {
    
    	//testH0();
    	
    	// testDPB();

        testDescolocadas();
    }
    
    public static void testH0(){
    //    Puzzle s = new Puzzle(3,3);
    	int[][] ttt = {{1,2,0},{3,4,5},{6,7,8}};
        Puzzle s = new Puzzle(ttt);
		AgentePuzzleH0 problema = new AgentePuzzleH0(s);
		
		List<Puzzle> l =  problema.aMono();
		
		if (l != null) {
			System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
               e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }        
	}
	
	public static void testDPB() {
        Puzzle s = new Puzzle(3,3);
		AgentePuzzleBDP problema = new AgentePuzzleBDP(s, 5);
		
		List<Puzzle> l = problema.aMono();
		
		if (l != null) {
			System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
               e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }        
	}

    public static void testDescolocadas() {
        int[][] datos = {
                {1, 0, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        Puzzle estadoInicial = new Puzzle(datos);
        AgentePuzzleDescolocadas agente = new AgentePuzzleDescolocadas(estadoInicial);

        System.out.println("h inicial: " + agente.h(estadoInicial));

        System.out.println("\n--- Ejecutando A* con Descolocadas ---");
        List<Puzzle> solucion = agente.aMono();

        if (solucion != null) {
            System.out.println("¡Solución encontrada!");
            System.out.println("\n--- Secuencia de movimientos ---");

            int paso = 0;
            for (Puzzle p : solucion) {
                System.out.println("Paso " + paso + ":");
                System.out.println(p); // Llama automáticamente a p.toString()
                paso++;
            }
        } else {
            System.out.println("No se encontró solución.");
        }
    }
	
}
