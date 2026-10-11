package pancakes;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pancake extends OverrideHashCode implements RepresentacionEstadoOptimizacion<Pancake> {

    public int[] tab;

    public Pancake(int[] tab) {
        this.tab = tab.clone(); // Importante: clone()
    }

    @Override
    public int costeArco(Pancake eDestino) {
        return 1;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.tab);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pancake other = (Pancake) obj;
        return Arrays.equals(this.tab, other.tab);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.tab);
    }

    @Override
    public List<Pancake> calculaSucesores() {
        List<Pancake> sucesores = new LinkedList<>();

        for(int k = 1; k < tab.length; k++) {
            int[] copia_tab = tab.clone();
            voltear(copia_tab, k);
            sucesores.add(new Pancake(copia_tab));
        }

        return sucesores;
    }

    private void voltear(int[] array, int hasta) {
        int izq = 0;
        int der = hasta;
        int aux;
        while(izq < der) {
            aux = array[izq];
            array[izq] = array[der];
            array[der] = aux;
            izq++;
            der--;
        }
    }

    public boolean esObjetivo() {
        for(int i = 0; i < tab.length; i++) {
            if(tab[i] != i+1) return false;
        }
        return true;
    }


}










