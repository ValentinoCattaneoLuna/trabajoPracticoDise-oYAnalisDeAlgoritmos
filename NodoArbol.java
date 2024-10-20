package ArbolBinario;

public class NodoArbol {

    private  int valor;
    private ArbolB nodoIzq;
    private ArbolB nodoDer;
    private int difAltura;

    public NodoArbol(int valor) {
        this.valor = valor;
        this.nodoDer = new ArbolB();
        this.nodoIzq = new ArbolB();
        this.difAltura = 0;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ArbolB getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(ArbolB nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    public ArbolB getNodoDer() {
        return nodoDer;
    }

    public void setNodoDer(ArbolB nodoDer) {
        this.nodoDer = nodoDer;
    }

    public int getDifAltura() {
        return difAltura;
    }

    public void setDifAltura(int difAltura) {
        this.difAltura = difAltura;
    }

    public String toString(String prefix, boolean isTail) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(valor).append("\n");
        if (!nodoIzq.EsVacio() || !nodoDer.EsVacio()) {
            if (!nodoIzq.EsVacio()) {
                sb.append(nodoIzq.raiz.toString(prefix + (isTail ? "    " : "│   "), nodoDer.EsVacio()));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("└── null\n");
            }
            if (!nodoDer.EsVacio()) {
                sb.append(nodoDer.raiz.toString(prefix + (isTail ? "    " : "│   "), true));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("└── null\n");
            }
        }
        return sb.toString();
    }
}
