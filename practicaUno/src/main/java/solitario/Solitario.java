package solitario;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Solitario extends OverrideHashCode implements RepresentacionEstadoOptimizacion<Solitario> {
    private int[] col1;
    private int[] col2;

    public Solitario(int[] col1, int[] col2) {
        this.col1 = col1.clone();
        this.col2 = col2.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Solitario solitario = (Solitario) o;
        return Objects.deepEquals(col1, solitario.col1) && Objects.deepEquals(col2, solitario.col2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(col1), Arrays.hashCode(col2));
    }

    @Override
    public int costeArco(Solitario eDestino) {
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < col1.length; i++) {
            sb.append(col1[i]).append("   ").append(col2[i]).append("\n");
        }
        return sb.toString();
    }

    private int[] rotar(int[] col) {
        int[] nueva = new int[col.length];

        // Las cartas desde el índice 1 hasta el final se adelantan una posición:
        for (int i = 1; i < col.length; i++) {
            nueva[i - 1] = col[i];
        }

        // La primera carta (índice 0) va a la última posición:
        nueva[nueva.length - 1] = col[0];

        return nueva;
    }

    @Override
    public List<Solitario> calculaSucesores() {
        List<Solitario> sucesores = new LinkedList<>();

        sucesores.add(new Solitario(rotar(col1), col2));

        sucesores.add(new Solitario(col1, rotar(col2)));

        // Intercambio primera carta de cada columna.
        int[] nuevaCol1 = this.col1.clone();
        int[] nuevaCol2 = this.col2.clone();
        int aux = nuevaCol1[0];
        nuevaCol1[0] = nuevaCol2[0];
        nuevaCol2[0] = aux;
        sucesores.add(new Solitario(nuevaCol1, nuevaCol2));

        return sucesores;
    }

    public boolean esObjetivo() {
        int m = col1.length;

        // Comprobamos la columna 1: valores de 1 a M
        for (int i = 0; i < m; i++) {
            if (col1[i] != i + 1) {
                return false;
            }
        }

        // Comprobamos la columna 2: valores de (M + 1) a 2M
        for (int i = 0; i < m; i++) {
            if (col2[i] != i + 1 + m) {
                return false;
            }
        }

        return true;
    }
}
