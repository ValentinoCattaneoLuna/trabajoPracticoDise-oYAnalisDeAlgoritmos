package ArbolBinario;


import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArbolB arbol1 = new ArbolB();

        arbol1.Agregar(10);
        arbol1.Agregar(20);
        arbol1.Agregar(15);
        arbol1.Agregar(18);
        arbol1.Agregar(13);
        arbol1.Agregar(5);
        arbol1.Agregar(9);
        arbol1.Agregar(6);
        System.out.println(arbol1);


        ArrayList<Integer> listaPotenciada  = potenciarHijosUnicos(arbol1, 2);
        System.out.println(listaPotenciada);


    }



    public static boolean tieneSoloUnHijo(NodoArbol n) { // O(1)
        return (n.getNodoIzq().EsVacio() && !n.getNodoDer().EsVacio()) ||
                (!n.getNodoIzq().EsVacio() && n.getNodoDer().EsVacio());
    }


    public static Integer potenciar(int numero, int potencia) { // O( log(potencia) )
        return (int) Math.pow(numero, potencia);
    }

    public static ArrayList<Integer> potenciarHijosUnicos(ArbolB a, int potencia) {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        if(!a.EsVacio()){

            if (tieneSoloUnHijo(a.getNodo())){
                lista.add( potenciar(a.Raiz(),potencia));
            }

            lista.addAll(potenciarHijosUnicos( (ArbolB) a.HijoIzquierdo(), potencia));
            lista.addAll(potenciarHijosUnicos( (ArbolB) a.HijoDerecho(), potencia));

        }
        return lista;
    }

}
