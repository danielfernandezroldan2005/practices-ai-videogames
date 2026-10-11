package jarras;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstado;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jarra extends OverrideHashCode implements RepresentacionEstadoOptimizacion<Jarra> {
    // Capacidades máximas
    private static final int CAP_G = 4;
    private static final int CAP_P = 3;

    // Contenido actual de cada jarra
    private final int contenido_G;
    private final int contenido_P;

    // Constructor
    public Jarra(int contenido_G, int contenido_P) {
        this.contenido_G = contenido_G;
        this.contenido_P = contenido_P;
    }

    // Getters
    public int getContenido_G() {
        return contenido_G;
    }

    public int getContenido_P() {
        return contenido_P;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contenido_G, contenido_P);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() ) return false;
        Jarra jarra = (Jarra) obj;
        return contenido_G == ((Jarra) obj).contenido_G && contenido_P == ((Jarra) obj).contenido_P;
    }

    @Override
    public String toString() {
        return "(" + contenido_G + ", " + contenido_P + ")";
    }

    @Override
    public List<Jarra> calculaSucesores() {
        List<Jarra> sucesores = new ArrayList<>();

        // Llenar Jarras
        if (contenido_G < CAP_G) {
            sucesores.add(new Jarra(CAP_G, contenido_P));
        }
        if (contenido_P < CAP_P) {
            sucesores.add(new Jarra(contenido_G, CAP_G));
        }

        // Vaciar Jarras
        if (contenido_G > 0) {
            sucesores.add(new Jarra(0, contenido_P));
        }
        if (contenido_P > 0) {
            sucesores.add(new Jarra(contenido_G, 0));
        }

        // Verter agua de G a P
        if (contenido_G > 0 && contenido_P < CAP_P) {
            int capacidad = CAP_P - contenido_P;
            int vertido = Math.min(capacidad, contenido_G);
            sucesores.add(new Jarra(contenido_G - vertido, contenido_P + vertido));
        }

        // Verter agua de P a G
        if (contenido_P > 0 && contenido_G < CAP_G) {
            int capacidad = CAP_G - contenido_G;
            int vertido = Math.min(capacidad, contenido_P);
            sucesores.add(new Jarra(contenido_G + vertido, contenido_P - vertido));
        }

        return sucesores;
    }

    @Override
    public int costeArco(Jarra eDestino) {
        return 1;
    }
}
