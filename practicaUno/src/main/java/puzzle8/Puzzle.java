package puzzle8;

import java.util.*;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

/**
 * Puzzle 2D de piezas deslizantes.
 * 
 * @authors Esteban Calderon Pinto, Jose Miguel Horcas Aguilera, Lorenzo Mandow
 *
 * @version 2013-10-08 // 2018
 */
public class Puzzle extends OverrideHashCode implements RepresentacionEstadoOptimizacion<Puzzle> {

	int [][] tab;           // Tablero.
    int fh;                 // Fila del hueco.
    int ch;                 // Columna del hueco.


	/**
     * Contructor privado que utilizaremos internamente.
     *
     * @param tablero: tablero con el que se inicializara el tablero.
     */
    
    public Puzzle (int[][] tablero) {
        this.tab = new int[tablero.length][tablero[0].length];
        this.copiarTablero(tablero);
        this.encuentraHueco();
    }

    /**
     * Constructor para el usuario.
     * @param nFilas    Numero de filas del tablero.
     * @param cColumnas Numero de columnas del tablero.
     */
    public Puzzle (int nFilas, int nColumnas) {
        this.tab = new int[nFilas][nColumnas];
        Scanner sc = new Scanner(System.in);

        sc.useDelimiter("[:\\s]");
        System.out.println("Introduzca las fichas separadas por espacios");
        System.out.println("Represente el hueco con un 0");
        System.out.println("Ejemplo: 1 2 0 3 4 5 6 7 8\representa\n1 2 0\n3 4 5\n6 7 8\n:");
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nColumnas; j++) {
                this.tab[i][j] = sc.nextInt();
            }//for j
        }//for i
        sc.close();
        this.encuentraHueco();
    }

    /**
     * Constructor.
     * Construye un nuevo puzzle dado el tablero y la posicion del hueco.
     * @param tablero       Tablero.
     * @param filaHueco     Fila del hueco.
     * @param columnaHueco  Columna del hueco.
     */
    public Puzzle (int[][] tablero, int filaHueco, int columnaHueco) {
        this.tab = new int[tablero.length][tablero[0].length];
        this.copiarTablero(tablero);
        this.fh = filaHueco;
        this.ch = columnaHueco;
    }
    
    private void copiarTablero (int[][] tablero) {      
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                this.tab[i][j] = tablero[i][j];
            }//for j
        }//for i
    }
    
    /**
     * Metodo que encuentra el hueco en el tablero e inicializa las variables fh y ch.
     */
    private void encuentraHueco() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == 0) {
                    this.fh = i;
                    this.ch = j;
                    return;
                }//if
            }//for j
        }//for i
    }

    /**
     * Metodo de consulta de la variable fh.
     *
     * @return la fila del hueco
     */
    public int fh() {
        return fh;
    }

    /**
     * Metodo de consulta de la variable ch.
     *
     * @return la columna del hueco
     */
    public int ch() {
        return ch;
    }

    /**
     *
     * @return numero de filas del tablero.
     */
    public int nf() {
        return tab.length;
    }

    /**
     * 
     * @return numero de columnas del tablero.
     */
    public int nc() {
        return tab[0].length;
    }
   
    /**
     * Intercambia los contenidos de las posiciones (f,c) y (nf,nc)
     * @param f
     * @param c
     * @param nf
     * @param nc
     */
    protected void permutaPosicion(int f, int c, int nf, int nc) {
        int aux;
        
        aux = tab[f][c];
        tab[f][c] = tab[nf][nc];
        tab[nf][nc] = aux;
        
        ch = nc;
        fh = nf;
    }

    /**
     * 
     * @param f
     * @param c
     * @return Cierto si (f,c) es una posicion del puzzle
     */
    protected boolean posicionValida(int f, int c) {
        return (f < tab.length) && (0 <= f) && (c < tab[0].length) && (0 <= c);
    }
    
    /**
     * @return Una lista con todos los puzzles directamente accesibles desde el actual
     * en un movimiento.
     */
    @Override
    public List<Puzzle> calculaSucesores() {

        List<Puzzle> h = new ArrayList<Puzzle>();
        Puzzle p;
        int f = fh;
        int c = ch;
        
        if (this.posicionValida(f, c+1)) {
            p = new Puzzle(tab,f,c);
            p.permutaPosicion(f, c, f, c+1);
            h.add(p);
        }

        if (this.posicionValida(f, c-1)) {
            p = new Puzzle(tab,f,c);
            p.permutaPosicion(f, c, f, c-1);
            h.add(p);
        }

        if (this.posicionValida(f+1, c)) {
            p = new Puzzle(tab,f,c);
            p.permutaPosicion(f, c, f+1, c);
            h.add(p);
        }

        if (this.posicionValida(f-1, c)) {
            p = new Puzzle(tab,f,c);
            p.permutaPosicion(f, c, f-1, c);
            h.add(p);
        }

        return h;
    }
    
    /**
     * @return Cadena que representa unívocamente cada puzzle
     */
    @Override
    public String toString () {
        StringBuilder b = new StringBuilder();
        
        b.append("\n");

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
               b.append(tab[i][j]);
               b.append(" ");
          }
            b.append("\n");
        }
        
        return b.toString();
    }
    
    /**
     * Muestra de forma legible un puzzle por pantalla.
     */
    public void ver () {
    	System.out.println(this.toString());
    }

    @Override
	public int costeArco(Puzzle e2) {
		return 1;
	}
	
	/**
	 * Ignoramos fh y ch en el cálculo del hashCode, pues el estado
	 * viene definido ya por el tablero. Esto es coherente con la
	 * definición de equals.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(tab);
		return result;
	}

	/**
	 * Ignoramos fh y ch en la comparación de igualdad, pues el estado
	 * viene definido ya por el tablero.
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puzzle other = (Puzzle) obj;
		if (!Arrays.deepEquals(tab, other.tab))
			return false;
		return true;
	}

	
}
