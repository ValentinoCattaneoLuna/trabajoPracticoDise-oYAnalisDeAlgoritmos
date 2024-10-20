package ArbolBinario;



public class ArbolB  implements TDAArbol {
    NodoArbol raiz;

    public ArbolB() {
        this.InicializaArbol();
    }

    public NodoArbol getNodo() {
        return this.raiz;
    }

    public void setNodo(NodoArbol raiz) {
        this.raiz = raiz;
    }

    @Override
    public boolean EsVacio() {
        return this.raiz == null;
    }

    @Override
    public void InicializaArbol() {
        this.raiz = null;
    }

    @Override
    public ArbolB HijoIzquierdo() {
        return this.raiz.getNodoIzq();
    }

    @Override
    public ArbolB HijoDerecho() {
        return this.raiz.getNodoDer();
    }

    @Override
    public int Raiz() {
        return this.raiz.getValor();
    }

    @Override
    public void Agregar(int x) {
        if (this.EsVacio()) {
            this.raiz = new NodoArbol(x);
        } else {
            if (x < this.Raiz()) {
                this.raiz.getNodoIzq().Agregar(x);

            } else {
                this.raiz.getNodoDer().Agregar(x);


            }
        }
    }

    private int mayor(ArbolB arbol) {
        if (arbol.HijoDerecho().EsVacio()) {
            return arbol.Raiz();
        } else {
            return mayor(arbol.HijoDerecho());
        }
    }

    private int menor(ArbolB arbol) {
        if (arbol.HijoIzquierdo().EsVacio()) {
            return arbol.Raiz();
        } else {
            return menor(arbol.HijoIzquierdo());
        }
    }

    private boolean esHoja() {
        if (this.HijoDerecho().EsVacio() && this.HijoIzquierdo().EsVacio()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void Eliminar(int x) {
        if (this.EsVacio()) {
            return;
        }
        if (this.Raiz() == x && this.esHoja()) {
            this.InicializaArbol();
        } else {
            if (this.Raiz() == x && this.HijoDerecho().EsVacio()) {
                int valorMayor = this.mayor(HijoIzquierdo());
                this.raiz.setValor(valorMayor);
                this.HijoIzquierdo().Eliminar(valorMayor);
            }
            if (this.Raiz() == x) {
                int valorMenor = this.menor(HijoDerecho());
                this.raiz.setValor(valorMenor);
                this.HijoDerecho().Eliminar(valorMenor);
            }

            if (this.Raiz() > x) {
                this.HijoIzquierdo().Eliminar(x);
            } else {
                this.HijoDerecho().Eliminar(x);
            }

        }
    }
    //    <>

    @Override
    public String toString() {
        if (this.raiz == null) {
            return "Empty tree";
        }
        return this.raiz.toString("", true);
    }


    //BALANCEO DE ARBOL BINARIO

    private void balancear(ArbolB a) {
        if (getFE(a) >= 2) {
            System.out.print("Rotacion ");
            if (getFE((ArbolB) a.HijoDerecho()) >= 0) {
                System.out.println("Simple a izquierda ");
                rotarIzq(a);
            } else {
                System.out.println("Doble derecha-izquierda");
                rotarDer((ArbolB) a.HijoDerecho());
                rotarIzq(a);
            }
        } else if (getFE(a) <= -2) {
            System.out.print("Rotacion ");
            if (getFE((ArbolB) a.HijoIzquierdo()) <= 0) {
                System.out.println("Simple a derecha ");
                rotarDer(a);
            } else {
                System.out.println("Doble izquierda-derecha");
                rotarIzq((ArbolB) a.HijoIzquierdo());
                rotarDer(a);
            }
        }

    }

    private int getFE(ArbolB a) {
        return a.getNodo().getDifAltura();

    }

    public int profundidad(ArbolB arbol) {
        if (arbol.EsVacio() || (arbol.HijoIzquierdo().EsVacio() && arbol.HijoDerecho().EsVacio())) {
            return 0;
        } else {
            return 1 + Math.max(
                    profundidad(arbol.HijoIzquierdo()),
                    profundidad(arbol.HijoDerecho())
            );
        }
    }

    public int cantidad(ArbolB arbol) {
        if (arbol.EsVacio()) {
            return 0;
        } else {
            return 1 + cantidad(arbol.HijoIzquierdo()) +
                    cantidad(arbol.HijoDerecho())
                    ;
        }
    }

    public int suma(ArbolB arbol) {
        if (arbol.EsVacio()) {
            return 0;
        } else {

            return arbol.Raiz() +
                    suma(arbol.HijoIzquierdo()) +
                    suma(arbol.HijoDerecho());
        }
    }

    public void preOrder(ArbolB arbol) {

        if (!arbol.EsVacio()) {
            System.out.print(arbol.Raiz() + " ");
            preOrder(arbol.HijoIzquierdo());
            preOrder(arbol.HijoDerecho());
        }
    }

    public void inOrder(ArbolB arbol) {

        if (!arbol.EsVacio()) {
            inOrder(arbol.HijoIzquierdo());
            System.out.print(arbol.Raiz() + " ");
            inOrder(arbol.HijoDerecho());
        }
    }


    public void postOrder(ArbolB arbol) {

        if (!arbol.EsVacio()) {
            inOrder(arbol.HijoIzquierdo());
            inOrder(arbol.HijoDerecho());

            System.out.print(arbol.Raiz() + " ");
        }
    }

    public void setDiferenciaAltura(ArbolB arbol) {

        if (!arbol.EsVacio()) {
            int altIzq = profundidad(arbol.HijoIzquierdo());
            int altDer = profundidad(arbol.HijoDerecho());
            int diferencia = altDer - altIzq;
            arbol.getNodo().setDifAltura(diferencia);

            if (!arbol.HijoIzquierdo().EsVacio()) {
                setDiferenciaAltura((ArbolB) arbol.HijoIzquierdo());
            }
            if (!arbol.HijoDerecho().EsVacio()) {
                setDiferenciaAltura((ArbolB) arbol.HijoDerecho());
            }

        }
    }

    private void rotarIzq(ArbolB a) {
        NodoArbol nuevoPadre = ((ArbolB) a.HijoDerecho()).getNodo();
//El hijo derecho pasa a ser raiz (nuevo padre)
        ArbolB hijoDerecho = (ArbolB) a.HijoDerecho();
        hijoDerecho.setNodo(nuevoPadre.getNodoIzq().getNodo());
// El hijo izquierdo de la nueva ra�z pasa a ser el hijo derecho del padre antiguo
        nuevoPadre.getNodoIzq().setNodo(a.getNodo());
//El padre antiguo se convierte hijo izquierdo de la nueva ra�z
        a.setNodo(nuevoPadre);
//Se reemplaza la raiz (el nodo padre) por el nuevo nodo padre
    }

    private void rotarDer(ArbolB a) {
        NodoArbol nuevoPadre = ((ArbolB) a.HijoIzquierdo()).getNodo();
//El hijo izquierdo pasa a ser el padre
        ((ArbolB) a.HijoIzquierdo()).setNodo(nuevoPadre.getNodoDer().getNodo());
// El hijo derecho del nuevo padre pasa a ser el izquierdo del padre antiguo
        nuevoPadre.getNodoDer().setNodo(a.getNodo());
//El padre se convierte en hijo derecho de la nueva raiz
        a.setNodo(nuevoPadre);
//Se reemplaza la raiz (el nodo padre) por el nuevo nodo padre
    }

//    public int contarHojas(ArbolB arbol) {
//        if (arbol.EsVacio()) {
//            return 0;
//        }
//        if (arbol.HijoIzquierdo().EsVacio() && arbol.HijoDerecho().EsVacio()) {
//            return 1;
//        }
//        return contarHojas((ArbolB) arbol.HijoIzquierdo()) + contarHojas((ArbolB) arbol.HijoDerecho());
//    }

}
