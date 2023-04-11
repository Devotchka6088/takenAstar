package takenAstar;

public class Nodo implements Comparable<Nodo>{
    private int[][] tablero;
    private Nodo padre;
    private int distanciaRecorrida;
    private int distGlo;
    private int noRegreso;

    public Nodo(int[][] tablero, Nodo padre, int distanciaRecorrida, int distGlo, int noRegreso) {
        this.tablero = tablero;
        this.padre = padre;
        this.distanciaRecorrida = distanciaRecorrida;
        this.distGlo = distGlo;
        this.noRegreso = noRegreso;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public Nodo getPadre() {
        return padre;
    }

    public int getDistancia() {
        return distanciaRecorrida;
    }

    public int getDistGlo() {
        return distGlo;
    }

    public int getNoRegreso() {
        return noRegreso;
    }

    public int compareTo(Nodo o) {
        int f1 = this.distanciaRecorrida + this.distGlo;
        int f2 = o.distanciaRecorrida + o.distGlo;
        return Integer.compare(f1, f2);
    }
}