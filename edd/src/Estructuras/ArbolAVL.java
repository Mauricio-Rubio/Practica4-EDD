/**
 * @Author Mauricio Rubio Haro
 * @Author Kevin Isaac Alcántara Estrada
 */
package edd.src.Estructuras;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

  protected class VerticeAVL extends Vertice {

    public int altura;
    public VerticeAVL raiz;
    public VerticeAVL padreAVL = null;
    public VerticeAVL izquierdo = null;
    public VerticeAVL derecho = null;

    public VerticeAVL(T elemento) {
      super(elemento);
      altura = 0;
    }

    /**
     * Regresa una representación en cadena del vértice rojinegro.
     *
     * @return una representación en cadena del vértice rojinegro.
     */
    public String toString() {
      return super.toString() + " altura: " + this.altura;
    }

    /**
     * Compara el vértice con otro objeto. La comparación es
     * <em>recursiva</em>.
     *
     * @param o el objeto con el cual se comparará el vértice.
     * @return <code>true</code> si el objeto es instancia de la clase
     *         {@link VerticeAVL}, su elemento es igual al elemento de
     *         éste vértice, los descendientes de ambos son recursivamente
     *         iguales, y los colores son iguales; <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      @SuppressWarnings("unchecked")
      VerticeAVL vertice = (VerticeAVL) o;
      return altura == vertice.altura && super.equals(o);
    }

    public int getAltura() {
      return this.altura;
    }

    public void setAltura() {
      this.altura = super.altura() - 1;
    }
  }

  /**
   * Metodo para convertir vertice a verticeAVL
   */
  protected VerticeAVL convertirAVL(VerticeArbolBinario<T> vertice) {
    return (VerticeAVL) vertice;
  }

  /**
   * Crear un verticeAVL
   */
  protected Vertice nuevoVertice(T elemento) {
    return new VerticeAVL(elemento);
  }

  /**
   * Metodo para actualizar las alturas de cada vertices del arlcol
   * @param vertice
   */
  public void actualizarAlturas(Vertice vertice) {
    VerticeAVL vert = convertirAVL(vertice);
    if (this.raiz == null) {
      System.out.println("No puedo trabajar con un árbol vacio");
      return;
    }

    vert.setAltura();

    if (vert.hayDerecho() || vert.hayIzquierdo()) {
      if (vert.hayDerecho()) {
        VerticeAVL vertD = convertirAVL(vertice.derecho);
        actualizarAlturas(vertD);
      }

      if (vert.hayIzquierdo()) {
        VerticeAVL vertI = convertirAVL(vertice.izquierdo);
        actualizarAlturas(vertI);
      }
    }
  }

  /**
   * Metodo para eliminar un vertice
   */
  public boolean delete(T elemento) {
    boolean k = super.delete(elemento);
    actualizarAlturas(this.raiz);
    revisarBalance(this.raiz);
    return k;
  }

  /**
   * Rotacion Derecha
   * @param verti
   */
  public void rotarD(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    Vertice aux2 = verti.izquierdo;
    if (this.raiz == verti) {
      this.raiz = verti.izquierdo;
      if (verti.izquierdo.hayDerecho()) {
        aux.izquierdo = verti.izquierdo.derecho;
        verti.izquierdo.derecho.padre = aux;
        aux.izquierdo.padre = aux;
      }
      if (verti.hayDerecho()) {
        aux.derecho = verti.derecho;
        aux.derecho.padre = aux;
      }
      this.raiz.derecho = aux;
    } else {
      if (verti.hayDerecho()) {
        aux.derecho = verti.derecho;
        verti.derecho.padre = aux;
      }
      if (verti.izquierdo.hayDerecho()) {
        aux.izquierdo = verti.izquierdo.derecho;
        aux.izquierdo.padre = aux;
      }

      if (verti.padre.hayDerecho()) {
        if (verti.padre.derecho.get().compareTo(verti.elemento) == 0) {
          verti.padre.derecho = aux2;
          aux2.padre = verti.padre;
        }
      }

      if (verti.padre.hayIzquierdo()) {
        if (verti.padre.izquierdo.get().compareTo(verti.elemento) == 0) {
          verti.padre.izquierdo = aux2;
          aux2.padre = verti.padre;
        }
      }

      verti.derecho = aux;
      aux.padre = verti;
      actualizarAlturas(this.raiz);
    }
  }

  /**
   * Metodo para añadir elementos
   */
  public void add(T elemento) {
    Vertice verti = nuevoVertice(elemento);
    if (this.raiz == null) {
      this.raiz = verti;
    } else {
      super.insert(this.raiz, elemento);
    }

    actualizarAlturas(this.raiz);
    revisarBalance(this.raiz);
    actualizarAlturas(this.raiz);
  }

  /**
   * Desbalance Derecho
   * @param vert
   * @param hIzq
   * @param hDer
   */
  public void desbalanceDerecho(Vertice vert, int hIzq, int hDer) {
    int wd = 0;
    if (vert.hayDerecho()) {
      if (vert.derecho.hayDerecho()) {
        wd = vert.derecho.derecho.altura() - 1;
      } else {}
    } else {
      wd = 0;
    }

    if (wd == hIzq + 1) {
      rotarI(vert);

      actualizarAlturas(this.raiz);
    } else if (wd == hIzq) {
      Vertice padre = vert;
      rotarD(vert.derecho);
      rotarI(padre);

      actualizarAlturas(this.raiz);
    }
  }

  /**
   * Analisis de donde hay que rebalancear
   * @param vert
   * @param hIzq
   * @param hDer
   */
  public void rebalancear(Vertice vert, int hIzq, int hDer) {
    if (hDer == hIzq + 2) {
      //desbalanceDerecho(vert, hIzq, hDer);
    } else if (hIzq == hDer + 2) {
      desbalanceIzquierda(vert, hIzq, hDer);
    }
    actualizarAlturas(this.raiz);
  }

  /**
   * Metodo que revisa si esta desbalanceado desde arriba hacia abajo
   * @param verti
   * @return
   */
  public boolean revisarBalance(Vertice verti) {
    int izq = 0, der = 0;
    if (verti.hayIzquierdo()) {
      VerticeAVL vertI = convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq = vertI.altura;
    }

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der = vertD.altura;
    }

    int op = Math.abs(izq - der);

    if (Math.abs(izq - der) < 2) {
      if (verti.hayDerecho()) {
        revisarBalance(verti.derecho);
      }
      if (verti.hayIzquierdo()) {
        revisarBalance(verti.izquierdo);
      }

      VerticeAVL vert = convertirAVL(verti);
      if (!vert.hayIzquierdo()) {
        if (vert.hayDerecho() && vert.altura == 2) {
          if (izq + 1 == der) {
            rebalancear(vert, izq, der + 1);
          }
        }
      }
      if (!vert.hayDerecho()) {
        if (vert.hayIzquierdo() && vert.altura == 2) {
          if (izq == 1) {
            rebalancear(vert, izq, der);
          }
        }
      }
    } else {
      rebalancear(verti, izq, der);
    }
    return true;
  }

  /**
   * Metodo que revisa si esta desbalanceado desde abajo hacia arriba
   * @param verti
   * @return
   */
  public boolean revisarBalanceInv(Vertice verti) {
    int izq = 0, der = 0;
    if (verti.hayIzquierdo()) {
      VerticeAVL vertI = convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq = vertI.altura() - 1;
    }

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der = vertD.altura() - 1;
    }

    int op = Math.abs(izq - der);
    if (Math.abs(izq - der) < 2) {
      /*if (!verti.hayIzquierdo()) {
        if (verti.hayDerecho() && verti.derecho.altura() - 1 == 2) {
          if (izq + 1 == der) {
            rebalancear(verti.padre, izq, der + 1);
          }
        }
      }

      if (!verti.hayDerecho()) {
        if (verti.hayIzquierdo() && verti.altura() - 1 == 2) {
          if (der + 1 == izq) {
            rebalancear(verti.padre, izq + 1, der);
          }
        }
      }*/
      if (verti.hayPadre()) {
        revisarBalanceInv(verti.padre);
      }
    } else {
      rebalancear(verti, izq, der);
    }

    return true;
  }


  public void rotarDerecha(Vertice vertice){
    if( !vertice.hayIzquierdo() ||vertice == null){
      return;
    }else{
      Vertice v = this.vertice(vertice);
      Vertice vi = v.izquierdo;
      vi.padre = v.padre;
      if(v != this.raiz){
        this.search(vertice, v.elemento);
        if(esHijoIzq){
          vi.padre.izquierdo = vi;
        }else{
          vi.padre.derecho = vi;
        }
      }else{
        this.raiz = vi;
      }
      v.izquierdo = vi.derecho;
      if(vi.hayDerecho()){
        vi.derecho.padre = v;
      }
      v.padre = vi;
      vi.derecho = v;
    }
  }

  /**
   * Rotar a la izquierda
   * @param verti
   */
  public void rotarI(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    Vertice aux2 = verti.derecho;
    if (this.raiz == verti) {
      this.raiz = verti.derecho;
      if (verti.derecho.hayIzquierdo()) {
        aux.derecho = verti.derecho.izquierdo;
        aux.derecho.padre = aux;
      }
      if (verti.hayIzquierdo()) {
        aux.izquierdo = verti.izquierdo;
        aux.izquierdo.padre = aux;
      }
      this.raiz.izquierdo = aux;
    } else {
      if (verti.hayIzquierdo()) {
        aux.izquierdo = verti.izquierdo;
        verti.izquierdo.padre = aux;
      }

      if (verti.derecho.hayIzquierdo()) {
        aux.derecho = verti.derecho.izquierdo;
        verti.derecho.izquierdo.padre = aux;
        aux.derecho.padre = aux;
      }

      //verti = verti.derecho;
      if (verti.padre.hayDerecho()) {
        if (verti.padre.derecho.get().compareTo(verti.elemento) == 0) {
          verti.padre.derecho = aux2;
          aux2.padre = verti.padre;
        }
      }

      if (verti.padre.hayIzquierdo()) {
        if (verti.padre.izquierdo.get().compareTo(verti.elemento) == 0) {
          verti.padre.izquierdo = aux2;
          aux2.padre = verti.padre;
        }
      }

      if (verti.padre.hayPadre()) {
        verti.padre = verti.padre.padre;
        verti.padre.izquierdo = verti;
      }

      verti.izquierdo = aux;
      aux.padre = verti;
    }
    actualizarAlturas(this.raiz);
  }

  /**
   * Metodo que balancea cuando se encuentra desbalancea del lado izquierdo
   *
   * @param vertice
   * @param kIzq
   * @param k altura de hijo derecho de vertice
   */
  public void desbalanceIzquierda(Vertice vertice, int kIzq, int k) {
    Vertice HIzq = vertice.izquierdo;
    if (HIzq.hayIzquierdo()) {
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);

      /**Caso donde esta en forma de linea recta */
      if (WizqAVL.altura == k + 1) {
        System.out.println("Caso 1: Linea recta");
        rotarDerecha(HIzq);
        actualizarAlturas(vertice);
        revisarBalanceInv(HIzq.izquierdo);
      } /**Caso donde esta en forma zig zag */else if (
        WizqAVL.altura == k && WderAVL.altura == k + 1
      ) {
        System.out.println("Caso 2: ZIG ZAG");
        rotarI(HIzq);
        rotarDerecha(vertice);
      }
    }
  }

  /**
   * Revisa si un vertice esta desbalanceado
   * @param verti
   * @return
   */
  public boolean revisarB(Vertice verti) {
    int izq = 0, der = 0;
    if (verti.hayIzquierdo()) {
      VerticeAVL vertI = convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq = vertI.altura() - 1;
    }

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der = vertD.altura() - 1;
    }

    int op = Math.abs(izq - der);
    if (Math.abs(izq - der) < 2) {
      return true;
    } else {
      return false;
    }
  }
}
