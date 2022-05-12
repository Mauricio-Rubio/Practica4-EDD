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

  public void rotarDMau(Vertice vertice){
    Vertice aux = vertice;
    aux.padre = aux.izquierdo;
    aux.izquierdo = aux.padre.derecho;
    aux.padre.derecho = aux;
  }

  public void rotarD(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    if (verti.hayDerecho()) {
      aux.derecho = verti.derecho;
      verti.derecho.padre = aux;
    }

    if (verti.izquierdo.hayDerecho()) {
      aux.izquierdo = verti.izquierdo.derecho;
      aux.izquierdo.padre = aux;
    }

    if (verti.padre.izquierdo.get().compareTo(verti.elemento) == 0) {
      verti = verti.izquierdo;
      if (verti.padre.hayPadre()) {
        verti.padre = verti.padre.padre;
      }

      verti.padre.izquierdo = verti;
    } else if (verti.padre.derecho.get().compareTo(verti.elemento) == 0) {
      verti = verti.izquierdo;
      if (verti.padre.hayPadre()) {
        verti.padre = verti.padre.padre;
      }

      verti.padre.derecho = verti;
    }

    //
    verti.derecho = aux;
    aux.padre = verti;
    actualizarAlturas(this.raiz);
  }

  public void add(T elemento) {
    Vertice verti = nuevoVertice(elemento);
    if (this.raiz == null) {
      this.raiz = verti;
    } else {
      super.insert(this.raiz, elemento);
    }
    revisarBalanceInv(ultimoDerecho(this.raiz));

    actualizarAlturas(this.raiz);
  }

  //public void rebalancear(Vertice vert, int hIzq, int hDer) {}

  public void rebalancear(Vertice vert, int hIzq, int hDer) {
    System.out.println(
      "El vertice desde donde hacemos balanceo" +
      vert +
      " hDer " +
      hDer +
      " hIzq " +
      hIzq
    );
    desbalanceIzquierda(vert, hIzq, hDer);
    /*if (hDer == hIzq + 2 || ((!vert.hayIzquierdo()) && hDer == hIzq + 1)) {
      vert = vert.derecho;
      System.out.println("ENTRAMOS");
      System.out.println("Vertice " + vert);
      VerticeAVL vertD = convertirAVL(vert.derecho);

public void rebalancear(Vertice vert, int hIzq, int hDer){
 
  if(hDer==hIzq+2){
    
   
    vert=vert.derecho;
   
    VerticeAVL  vertD= convertirAVL(vert.derecho);
   
   
    int wd = vertD.altura()-1;
  
   if(wd==hIzq+1){
    
    
      rotarI(vert);
    
    actualizarAlturas(this.raiz);
   }

   if(wd==hIzq){
    Vertice padre = vert.padre;
    rotarD(vert);
    rotarI(padre);
    
    actualizarAlturas(this.raiz);
   }
  }else{
   // desbalanceIzquierda(vert,hIzq,hDer);
  }
  actualizarAlturas(this.raiz);
 // revisarBalance(this.raiz);
}


 
     

        actualizarAlturas(this.raiz);
      }
    }*/
  }

  public boolean revisarBalance(Vertice verti) {
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
      if (verti.hayDerecho()) {
        revisarBalance(verti.derecho);
      }
      if (verti.hayIzquierdo()) {
        //System.out.println("REVISANDO HIJO IZQ");
        revisarBalance(verti.izquierdo);
      } else if (verti.hayDerecho() && verti.derecho.altura() - 1 == 2) {
        if (izq + 1 == der) {
          rebalancear(verti.padre, izq, der + 1);
        }
      }
    } else {
      // System.out.println("REBALANCE");
      rebalancear(verti, izq, der);
    }

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
        aux.izquierdo.padre = aux;
        // verti.izquierdo.padre=aux;

      }

      // verti.padre=verti.padre;

      if (verti.derecho.hayIzquierdo()) {
        aux.derecho = verti.derecho.izquierdo;

        aux.derecho.padre = aux;
      }
      // verti.padre=verti;
      //if()
      verti = verti.derecho;

      if (verti.padre.hayPadre()) {
        System.out.println("Ojoooooooo" + verti);
        System.out.println("Ojooooooxxxoo" + aux);

        verti.padre = verti.padre.padre;
        verti.padre.derecho = verti;
        System.out.println("Verti padre  def" + verti.padre);
      }

      verti.izquierdo = aux;
      aux.padre = verti;
    }
    actualizarAlturas(this.raiz);
  }

  public void desbalanceIzquierda(Vertice vertice, int kIzq, int k) {
    //int k = vertice.derecho.altura;
    Vertice HIzq = vertice.izquierdo;
    System.out.println("Quien es vertice: " + vertice);
    System.out.println("Hijo izquierdo: " + HIzq);
    System.out.println("Hijo derecho: " + vertice.derecho);
    if (HIzq.altura() >= k + 2) {
      System.out.println("Se necesita balance izquierdo");
    }
    System.out.println("Tu perro arbol \n" + this);
    if (HIzq.hayIzquierdo()) {
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);
      WderAVL.setAltura();
      System.out.println(
        "Altura de esta prra mmada: " +
        WizqAVL +
        " este es k " +
        k +
        " este es k + 1: " +
        (k + 1) + 
        " Altura de otra prra mmada: " +
        WderAVL
      );
      System.out.println("Iniciando balanceo");
      if (WizqAVL.altura == k + 1) {
        System.out.println("Caso 1: Linea recta");
        rotarD(HIzq);
        actualizarAlturas(vertice);
        //Recalculo de las alturas y rebalanceo
      } else if (WizqAVL.altura == k && WderAVL.altura == k + 1) {
        System.out.println("Caso 2: ZIG ZAG");
        rotarI(HIzq);
        rotarD(vertice);
      } else {
        //System.out.println("CTPM");
      }
      actualizarAlturas(vertice);
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
