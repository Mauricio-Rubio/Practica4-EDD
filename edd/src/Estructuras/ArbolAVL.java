package edd.src.Estructuras;

//import java.lang.Math;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

  protected class VerticeAVL extends Vertice {

    public int altura;
    public VerticeAVL raiz;
    public VerticeAVL padreAVL = null;
    /** El izquierdo del vértice. */
    // @Override
    public VerticeAVL izquierdo = null;
    /** El derecho del vértice. */
    //@Override
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

  protected VerticeAVL convertirAVL(VerticeArbolBinario<T> vertice) {
    return (VerticeAVL) vertice;
  }

  protected Vertice nuevoVertice(T elemento) {
    return new VerticeAVL(elemento);
  }

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
        //vertD.setAltura(vert.getAltura()-1);
        actualizarAlturas(vertD);
      }

      if (vert.hayIzquierdo()) {
        VerticeAVL vertI = convertirAVL(vertice.izquierdo);
        // vertI.setAltura(vert.getAltura()-1);
        actualizarAlturas(vertI);
      }
    }
  }

  public boolean delete(T elemento) {
    boolean k = super.delete(elemento);
    actualizarAlturas(this.raiz);
    revisarBalance(this.raiz);
    return k;
  }

  public void rotarD(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    System.out.println("AUX" + aux);
    if (this.raiz == verti) {
      System.out.println("CONTROLA" + verti.izquierdo.izquierdo);
      System.out.println("VERTI I" + verti.izquierdo);
      //verti.izquierdo.padre=this.raiz;
      this.raiz = verti.izquierdo;
      //this.raiz.izquierdo=null;
      //verti=this.raiz;
      // verti.izquierdo.izquierdo.padre=this.raiz;

      System.out.println("VERTI I 2" + verti.izquierdo);
      //verti.izquierdo.padre=verti;
      //verti.izquierdo.padre=this.raiz;
      System.out.println("CONTROLB");
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
      System.out.println("HOFKA" + this.toString());
    } else {
      if (verti.hayDerecho()) {
        aux.derecho = verti.derecho;
        verti.derecho.padre = aux;
      }
      System.out.println("CONTROL J");
      if (verti.izquierdo.hayDerecho()) {
        aux.izquierdo = verti.izquierdo.derecho;
        aux.izquierdo.padre = aux;
      }
      System.out.println("CONTROL K");
      if (verti.padre.hayIzquierdo()) {
        if (verti.padre.izquierdo.get().compareTo(verti.elemento) == 0) {
          verti = verti.izquierdo;
          if (verti.padre.hayPadre()) {
            verti.padre = verti.padre.padre;
          }
          System.out.println("CONTROL Z");
          verti.padre.izquierdo = verti;
        } else if (verti.padre.derecho.get().compareTo(verti.elemento) == 0) {
          verti = verti.izquierdo;
          if (verti.padre.hayPadre()) {
            verti.padre = verti.padre.padre;
          }

          verti.padre.derecho = verti;
        }
      } else if (verti.padre.derecho.get().compareTo(verti.elemento) == 0) {
        verti = verti.izquierdo;
        if (verti.padre.hayPadre()) {
          verti.padre = verti.padre.padre;
        }

        verti.padre.derecho = verti;
      }

      //verti=verti.izquierdo;
      verti.derecho = aux;
      aux.padre = verti;
      actualizarAlturas(this.raiz);
    }
  }

  public void add(T elemento) {
    Vertice verti = nuevoVertice(elemento);
    if (this.raiz == null) {
      this.raiz = verti;
    } else {
      super.insert(this.raiz, elemento);
    }

    actualizarAlturas(this.raiz);
    // revisarBalanceInv(ultimoIzquierdo(this.raiz));
    revisarBalance(this.raiz);
    actualizarAlturas(this.raiz);
    // revisarBalanceInv(ultimoDerecho(this.raiz));
  }

  //public void rebalancear(Vertice vert, int hIzq, int hDer) {}

  public void desbalanceDerecho(Vertice vert, int hIzq, int hDer) {
    // vert=vert.derecho;
    System.out.println(
      "DESBALANCEO DERECHO vERTI" + vert + " hIzq" + hIzq + "+hDer" + hDer
    );
    int wd = 0;
    if (vert.hayDerecho()) {
      //VerticeAVL  vertD= convertirAVL(vert.derecho);
      // System.out.println("VERTD"+vertD);
      System.out.println("CONTROL");
      if (vert.derecho.hayDerecho()) {
        wd = vert.derecho.derecho.altura() - 1;
      } else {
        //wd = vert.derecho.izquierdo.altura()-1;
      }
      System.out.println("WD" + wd);
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

  public void rebalancear(Vertice vert, int hIzq, int hDer) {
    if (hDer == hIzq + 2) {
      desbalanceDerecho(vert, hIzq, hDer);
    } else if (hIzq == hDer + 2) {
      desbalanceIzquierda(vert, hIzq, hDer);
    }
    actualizarAlturas(this.raiz);
    //revisarBalanceInv(ultimoDerecho(this.raiz));
  }

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

      //VerticeAVL aux = nuevoVertice(verti.e);

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
      // System.out.println("REBALANCE");
      rebalancear(verti, izq, der);
      //revisarBalanceInv(ultimoDerecho(this.raiz));
    }
    // revisarBalanceInv(ultimoDerecho(this.raiz));
    return true;
  }

  //SEPARADOR
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
    //System.out.println("DESB"+op);
    if (Math.abs(izq - der) < 2) {
      if (!verti.hayIzquierdo()) {
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
      }
      if (verti.hayPadre()) {
        revisarBalanceInv(verti.padre);
      }
    } else {
      rebalancear(verti, izq, der);
    }

    return true;
  }

  //SEPARADOOOR

  public void rotarI(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    //System.out.println("AUX AUX"+aux);
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
      //  verti=verti.derecho;

      if (verti.hayIzquierdo()) {
        aux.izquierdo = verti.izquierdo;
        verti.izquierdo.padre = aux;
      }

      // verti.padre=verti.padre;

      if (verti.derecho.hayIzquierdo()) {
        aux.derecho = verti.derecho.izquierdo;
        verti.derecho.izquierdo.padre = aux;
        aux.derecho.padre = aux;
      }
      // verti.padre=verti;
      //if()
      // verti=verti.derecho;
      //verti.derecho.padre=verti;

      verti = verti.derecho;

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
    if (HIzq.altura() >= k + 2) {
      System.out.println("Iniciando balanceo izquierdo");
    }
    System.out.println("Iniciando desde vertice "+vertice);
    System.out.println("Vertice izquierdo "+HIzq);
    System.out.println("No me mientas "+HIzq.izquierdo);
    System.out.println("Arbol a balancear\n" + this);
    if (HIzq.hayIzquierdo()) {
      System.out.println("Caso base");
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);
      
      /**Caso donde esta en forma de linea recta */
      if (WizqAVL.altura == k + 1) {
        System.out.println("Caso 1: Linea recta");
        rotarD(HIzq);
        actualizarAlturas(vertice);
        revisarBalanceInv(HIzq.izquierdo);
      } 
      /**Caso donde esta en forma zig zag */
      else if (WizqAVL.altura == k && WderAVL.altura == k + 1) {
        System.out.println("Caso 2: ZIG ZAG");
        rotarI(HIzq);
        rotarD(vertice);
      }
    }else if(HIzq.hayDerecho()){
      System.out.println("T ptmadre");
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);
    }
  }

  public boolean revisarB(Vertice verti) {
    System.out.println("REVISANDO " + verti);
    int izq = 0, der = 0;
    if (verti.hayIzquierdo()) {
      VerticeAVL vertI = convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq = vertI.altura() - 1;
      System.out.println(vertI);
      System.out.println("Altura izquierda" + izq);
    }

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der = vertD.altura() - 1;
      System.out.println(vertD);
      System.out.println("Altura derecha" + der);
    }

    int op = Math.abs(izq - der);
    System.out.println("DESB" + op);
    if (Math.abs(izq - der) < 2) {
      return true;
    } else {
      return false;
    }
  }
}
