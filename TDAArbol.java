package ArbolBinario;

public interface TDAArbol {

    //Indica si el arbol está vacio
    boolean EsVacio();    

    //Crea un arbol vacio
    void InicializaArbol();

    //Devuelve el sub-Arbol izquierdo
    TDAArbol HijoIzquierdo();

    //Devuelve el sub-Arbol derecho
    TDAArbol HijoDerecho();

    //Devuelve la raiz de un árbol, si es que existe.
    int Raiz();

    // agrega un elemento x
    void Agregar(int x);

    // elimina un elemento x
    void Eliminar(int x);

}
