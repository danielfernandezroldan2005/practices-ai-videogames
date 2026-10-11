package solitario;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Solitario extends OverrideHashCode implements RepresentacionEstadoOptimizacion<Solitario> {
    private int[] col1;
    private int[] col2;

    public Solitario() {
        this.col1 = col1.clone();
        this.col2 = col2.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(col1), Arrays.hashCode(col2));
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int costeArco(Solitario eDestino) {
        return 1;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public List<Solitario> calculaSucesores() {
        return List.of();
    }
}
