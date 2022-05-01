package edd.src.Estructuras;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolBinarioBusqueda<T extends Comparable<T>>
  extends ArbolBinario<T> {

  private class Iterador implements Iterator<T> {

    private Pila<Vertice> pila;

    public Iterador() {
      pila = new Pila<Vertice>();
      Vertice p = raiz;
      while (p != null) {
        pila.push(p);
        p = p.izquierdo;
      }
    }

    // falta hasNext
    public T next() {
      if (pila.isEmpty()) {
        throw new NoSuchElementException("vacio");
      }
      Vertice v = pila.pop();
      if (v.derecho != null) {
        Vertice u = v.derecho;
        while (u != null) {
          pila.push(u);
          u = u.izquierdo;
        }
      }

      return v.elemento;
    }

    public boolean hasNext() {
      return false;
    }
  }

  public boolean delete(T object) {
    return true;
  }

  public T pop() {
    return null;
  }

  public void add(T elemento) {
    if (elemento == null) {
      throw new IllegalArgumentException();
    }
    Vertice a = nuevoVertice(elemento);
    elementos++;
    if (isEmpty()) {
      raiz = a;
    } else {
      Vertice b = BFS();
      if (!b.hayIzquierdo()) {
        b.izquierdo = a;
        a.padre = b;
        return;
      }
      if (!b.hayDerecho()) {
        b.derecho = a;
        a.padre = b;
      }
    }
  }

  private Vertice BFS() {
    if (this.isEmpty()) {
      return null;
    }
    Cola<Vertice> a = new Cola<Vertice>();
    a.push(raiz);
    while (a.cabeza != null) {
      Vertice b = a.pop();
      if (b.hayIzquierdo()) {
        a.push(b.izquierdo);
      }
      if (b.hayDerecho()) {
        a.push(b.derecho);
      }
      if (!b.hayIzquierdo() || !b.hayDerecho()) {
        return b;
      }
    }
    return null;
  }

  private Vertice modBFS() {
    if (this.isEmpty()) {
      return null;
    }
    Cola<Vertice> a = new Cola<Vertice>();
    a.push(raiz);
    while (a.cabeza != null) {
      Vertice b = a.pop();
      if (b.hayIzquierdo()) {
        a.push(b.izquierdo);
      }
      if (b.hayDerecho()) {
        a.push(b.derecho);
      }
      if (!b.hayIzquierdo() && !b.hayDerecho()) {
        return b;
      }
    }
    return null;
  }

  public Vertice ultimoIzquierdo(Vertice verti) {
    //Vertice nuevo = nuevoVertice(verti.get());

    if (verti.hayIzquierdo()) {
      verti = verti.izquierdo;
      ultimoIzquierdo(verti);
      //return verti;
      // verti=verti.izquierdo;
    }

    /*if(verti==this.raiz){
        return null;
    }*/

    return verti;
  }

  public Vertice ultimoDerecho(Vertice verti) {
    //Vertice nuevo = nuevoVertice(verti.get());

    if (verti.hayDerecho()) {
      verti = verti.derecho;
      ultimoDerecho(verti);
      // verti=verti.derecho;
    }

    /*if(verti==this.raiz){
        return null;
    }*/

    return verti;
  }

  public void insert(T elemento) {
    Vertice verti = this.BFS();

    if (verti.get().compareTo(elemento) > 0) {
      if (!verti.hayIzquierdo()) {
        Vertice anadido = nuevoVertice(elemento);
        verti.izquierdo = anadido;
      } else {
        //verti=this.ultimoIzquierdo(verti);
        while (verti.hayIzquierdo()) {
          verti = verti.izquierdo;
          if (verti.get().compareTo(elemento) < 0 && !verti.hayDerecho()) {
            Vertice anadido = nuevoVertice(elemento);
            verti.derecho = anadido;
            return;
          }
        }
        if (verti.get().compareTo(elemento) > 0) {
          Vertice anadido = nuevoVertice(elemento);
          Vertice anadidoAux = verti.derecho;
          verti.izquierdo = anadido;
          verti.derecho = anadidoAux;
        } else {
          Vertice anadido = nuevoVertice(elemento);
          verti.derecho = anadido;
        }
      }
    } else {
      while (verti.hayDerecho()) {
        verti = verti.derecho;
      }
      Vertice anadido = nuevoVertice(elemento);
      verti.derecho = anadido;
    }
  }

  private void ordenarLista(Lista<T> lista, int inicio, int ultimo) {
    Lista<T> listaNueva = new Lista<T>();

    int indice_izq, indice_der;
    T pivote;
    //Caso base de nuestra recursión
    if (inicio > ultimo) {
      return;
    }
    //Asignación de valores a nuestras variables auxiliares
    indice_izq = inicio;
    indice_der = ultimo;
    //Primero se hará el ordenamiento tomando como base el valor de la variable pivote, que será el valor del primer elemento del arreglo
    pivote = lista.elemIndice(inicio);
    //El ciclo se realizará hasta que nuestras variables auxiliares sen encuentre
    while (indice_der > indice_izq) {
      // Buscamos un valor que sea menor que nuestro pivote desde el último elemento de nuestro arreglo y hacia la izquierda
      while (
        indice_izq < indice_der &&
        (pivote.compareTo(lista.elemIndice(indice_der)) <= 0)
      ) {
        indice_der--;
      }
      //Partiendo desde el primer elemento de nuestro arreglo, buscamos un elemento que sea mayor que el valor de nuestro pivote y seguimos la búsqueda hacia la derecha
      while (
        (pivote.compareTo(lista.elemIndice(indice_izq)) >= 0) &&
        indice_izq < indice_der
      ) {
        indice_izq++;
      }
      // Una vez que hemos encontrado los elementos que buscábamos, si el elemento que buscamos desde la izquierda esta a la derecha del que buscamos por la derecha, intercambiamos los valores de dichos elementos en el arreglo
      if (indice_izq < indice_der) {
        /*num_aux = array[indice_der];
                array[indice_der] = array[indice_izq];
                array[indice_izq] = num_aux;*/
        lista.intercambiar(indice_der, indice_izq);
      }
    }
    // Finalmente, al encontrarse nuestros indices auxiliares, intercambiamos el valor nuestro pivote (el valor del primer elemento del arreglo) por el valor del elemento del arreglo en el cual se encontraron nuestros indices auxiliares
    lista.intercambiar(inicio, indice_izq);
    //  lista.intercambiar(indice_izq,pivote);
    //NOTA: de esta manera ya tenemos el pivote ubicado en el lugar que le corresponde en el arreglo y, a su derecha, los elementos mayores a este y, a su izquierda, los valores menores a esta

    //COMENZAMOS LAS LLAMADAS RECURSIVAS

    //Ordenamos los números que están a la izquierda de nuestro pivote
    ordenarLista(lista, inicio, indice_der - 1);
    //Ordenamos los números que están a la derecha de nuestro pivote
    ordenarLista(lista, indice_der + 1, ultimo);
    // }

    //return lista;
  }

  public ArbolBinarioBusqueda<T> buildUnsorted(Lista<T> lista) {
    ArbolBinarioBusqueda<T> arbolinio = new ArbolBinarioBusqueda<T>();
    ordenarLista(lista, 0, lista.size() - 1);
    System.out.println(lista.toString());
    T elem = lista.elemIndice(lista.size() / 2);
    arbolinio.add(elem);
    lista.delete(elem);
    while (!lista.isEmpty()) {
      T elementoAgreg;
      int p = lista.size() / 2;
      if (p <= 0) {
        p = 2;
      }
      /* elementoAgreg= lista.elemIndice(p);
    arbolinio.add(elementoAgreg);
    lista.delete(elementoAgreg);

    elementoAgreg= lista.elemIndice(p);
    arbolinio.add(elementoAgreg);
    lista.delete(elementoAgreg);*/

    }
    return arbolinio;
    // Iterator<T> ite = new lista.iterator();
    //   System.out.println(ordenarLista(lista));

  }

  public ArbolBinarioBusqueda<T> buildSorted(Lista<T> lista) {
    T elemento = lista.peek();
    this.raiz = new Vertice(lista.peek());

    return null;
  }

  public ArbolBinarioBusqueda(Lista<T> lista, boolean isSorted) {
    if (isSorted) {
      
      buildSorted(lista);
    } else {
      System.out.println("Unsorted");
      buildUnsorted(lista);
    }
  }

  public ArbolBinarioBusqueda() {}

  /**
   * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
   *
   * @return un iterador para iterar el árbol.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterador();
  }
}
