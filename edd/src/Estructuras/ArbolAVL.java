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
    actualizarAlturas(this.raiz);
    System.out.println("ARBOL KK"+this);
    if(revisarB(this.raiz)==false){
   // revisarBalance(this.raiz);
    }
    return k;
  }

  public void rotacionDerecha(Vertice vert){
    if(vert==null|| !vert.hayIzquierdo()){
      return;
    }

    Vertice v = vert;
    Vertice vi = vert.izquierdo;
    vi.padre=v.padre;
    if(v!=this.raiz){
      if(v.padre.izquierdo.get().compareTo(v.elemento)==0){
        vi.padre.izquierdo=vi;
      }else{
        vi.padre.derecho=vi;
      }
    }else{
      this.raiz=vi;
    }
    v.izquierdo=vi.derecho;
    if(vi.hayDerecho()){
      vi.derecho.padre=v;
    }
    v.padre=vi;
    vi.derecho=v;
  }

  public void rotacionIzquierda(Vertice vert){
    if(vert==null|| !vert.hayDerecho()){
      return;
    }

    Vertice v = vert;
    Vertice vd= vert.derecho;
    vd.padre=v.padre;
    if(v!=this.raiz){
      if(v.padre.derecho.get().compareTo(v.elemento)==0){
        vd.padre.derecho=vd;
      }else{
        vd.padre.izquierdo=vd;
      }
    }else{
      this.raiz=vd;
    }
    v.derecho=vd.izquierdo;
    if(vd.hayIzquierdo()){
      vd.izquierdo.padre=v;
    }
    v.padre=vd;
    vd.izquierdo=v;
  }

  /**
   * Rotacion Derecha
   * @param verti
   */
  public void rotarD(Vertice verti) {
    
    Vertice aux = nuevoVertice(verti.get());
    //Vertice aux2= verti.izquierdo;
    if (this.raiz == verti) {
     // if(verti.hayIzquierdo()){
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
    //}else{
      //rotarI(verti);
   // }
    } else {
      if(verti.hayIzquierdo()){
        Vertice aux2= verti.izquierdo;
      if (verti.hayDerecho()) {
        aux.derecho = verti.derecho;
        verti.derecho.padre = aux;
      }
      if (verti.izquierdo.hayDerecho()) {
        aux.izquierdo = verti.izquierdo.derecho;
        aux.izquierdo.padre = aux;
      }
      
      if(verti.padre.hayDerecho()){
        if(verti.padre.derecho.get().compareTo(verti.elemento)==0){
          verti.padre.derecho=aux2;
          aux2.padre=verti.padre;
        }
      }

      if(verti.padre.hayIzquierdo()){
        if(verti.padre.izquierdo.get().compareTo(verti.elemento)==0){
          verti.padre.izquierdo=aux2;
          aux2.padre=verti.padre;
        }
      }
         

      verti.derecho = aux;
      aux.padre = verti;
      actualizarAlturas(this.raiz);
    }
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
    //revisarBalanceInv(search(this.raiz,elemento));
    actualizarAlturas(this.raiz);
  }

  /**
   * Desbalance Derecho
   * @param vert
   * @param hIzq
   * @param hDer
   */
  public void desbalanceDerecho(Vertice vert, int hIzq, int hDer) {
    System.out.println("DESBALANCE DERECHO");
    int wd = -100;
    if (vert.hayDerecho()) {
      if (vert.derecho.hayDerecho()) {
        wd = vert.derecho.derecho.altura() - 1;
      } else {
        wd = vert.derecho.izquierdo.altura()-1;
      }
    }

    if (wd == hIzq + 1) {
      System.out.println("CASO 1");
      rotacionIzquierda(vert);
      //Vertice padre= vert.padre;
     /* if(vert.hayPadre()){
        if(vert.padre.hayDerecho()){
          if(vert.padre.derecho.get().compareTo(vert.elemento)==0){
    Vertice aux= rotarIzquierda(vert);
     aux.padre=vert.padre;
     vert.padre.derecho=aux;
          }
          System.out.println("JUJY\n"+this);
        }
      }*/
      //rotarI(vert);

      actualizarAlturas(this.raiz);
    } else if (wd == hIzq) {
      System.out.println("CASO 2 "+vert);
      System.out.println(this);
    //  Vertice der = vert.derecho;
      if(vert.altura()-1==2&!vert.hayIzquierdo()){
        System.out.println("AVISO");
        Vertice padre= vert.padre;
        rotacionIzquierda(vert);
        rotacionDerecha(padre);
       // vert=rotarIzquierda(vert);
       //vert.padre=vert.padre;
      }else{
   // vert.derecho=  rotarDerecha(vert.derecho);
   // vert.derecho.padre=vert;
    //vert.padre=vert.padre;
      }
    // vertice.izquierdo.padre=vertice;
     actualizarAlturas(this.raiz);
     System.out.println(this);
     System.out.println(modBFS(this).toString());
     /*  if(vert.derecho.padre.hayIzquierdo()){
     vert.padre=rotarIzquierda(vert.derecho.padre);
    }else{vert.padre.derecho=vert.derecho;}*/
     actualizarAlturas(vert);
     
      revisarBalance(this.raiz);
      

      actualizarAlturas(this.raiz);
    }else{
      revisarBalance(vert.derecho);
      revisarBalance(vert.izquierdo);
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
      desbalanceDerecho(vert, hIzq, hDer);
    } else if (hIzq == hDer + 2) {
      desbalanceIzquierda(vert, hIzq, hDer);
    }
    actualizarAlturas(this.raiz);
  }

  public Vertice rotarDerecha(Vertice v){
   /* if(v==this.raiz){
      rotarD(v);
      return v;
    }else{*/
    Vertice t2 = v.izquierdo.derecho;
    Vertice izq= v.izquierdo;
    izq.derecho=v;
    izq.padre=v.padre;
    v.padre=izq;
    
    
    v.izquierdo=t2;
    if(t2!=null){
      t2.padre=izq;
    }
    return izq;
    //}
  }

  public Vertice rotarIzquierda(Vertice v){
    Vertice t2 = v.derecho.izquierdo;
    Vertice der= v.derecho;
    der.izquierdo=v;
    v.padre=der;
    v.derecho=t2;
    if(t2!=null){
      t2.padre=v;
    }
    return der;
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

   /* VerticeAVL vert = convertirAVL(verti);
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
      }*/
    } else if(op==2){
      System.out.println("HAY DESBALANCE EN"+verti);
      System.out.println("PUTA VERGAAA\n"+this);
      
      rebalancear(verti, izq, der);
    }else{
      revisarBalance(verti.derecho);
      revisarBalance(verti.izquierdo);
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
    /* if (!verti.hayIzquierdo()) {
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
      System.out.println("HAY DESBALANCE EN"+verti);
      rebalancear(verti, izq, der);
    }

    return true;
  }

  /**
   * Rotar a la izquierda
   * @param verti
   */
  public void rotarI(Vertice verti) {
    Vertice aux = nuevoVertice(verti.get());
    Vertice aux2= verti.derecho;
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
      if(verti.padre.hayDerecho()){
        if(verti.padre.derecho.get().compareTo(verti.elemento)==0){
          verti.padre.derecho=aux2;
          aux2.padre=verti.padre;
        }
      }

      if(verti.padre.hayIzquierdo()){
        if(verti.padre.izquierdo.get().compareTo(verti.elemento)==0){
          verti.padre.izquierdo=aux2;
          aux2.padre=verti.padre;
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
    System.out.println("DESBALANCE IZQUIERDO"+vertice);
    System.out.println(modBFS(this).toString());
    Vertice HIzq = vertice.izquierdo;
    if (HIzq.hayIzquierdo()) {
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);
System.out.println(kIzq +"AND"+k);
      //Caso donde esta en forma de linea recta */
      if (WizqAVL.altura == k + 1) {

        System.out.println("Caso 1: Linea recta");
       /* if(verti==this.raiz){

          revisarBalance(vertice.derecho);
        }*/
       rotacionDerecha(vertice);
         //vertice= rotarDerecha(vertice);
         //vertice.padre=vertice.padre;
         // revisarBalanceInv(vertice);
          revisarB(this.raiz);
        

        
      
        /*if(vertice.padre.hayDerecho()){
        if(vertice.padre.derecho.get().compareTo(vertice.elemento)==0){
      vertice.padre.derecho=  rotarDerecha(vertice);
        }
        }else{
          if(vertice.padre.hayIzquierdo()){
            if(vertice.padre.izquierdo.get().compareTo(vertice.elemento)==0){
          vertice.padre.izquierdo=  rotarDerecha(vertice);
            }
          }
        }*/
      //  System.out.println(this);
        actualizarAlturas(this.raiz);
        System.out.println(this);
       // revisarBalanceInv(HIzq);
        revisarBalance(this.raiz);
      } /**Caso donde esta en forma zig zag */else if (
        WizqAVL.altura == k && WderAVL.altura == k + 1
      ) {
        System.out.println("Caso 2: ZIG ZAG");
        
      vertice.izquierdo=  rotarIzquierda(HIzq);
     // vertice.izquierdo.padre=vertice;
      actualizarAlturas(this.raiz);
      System.out.println(this);
      System.out.println(modBFS(this).toString());
        //BORRAR?
     vertice.padre= rotarDerecha(vertice.izquierdo.padre);
      actualizarAlturas(vertice);
       //vertice.izquierdo.padre=aux;
       //System.out.println(this);
      }else{
        /*revisarBalance(vertice.derecho);
      revisarBalance(vertice.izquierdo);*/
      }
    }else{
    // vertice.izquierdo= rotarIzquierda(vertice.izquierdo);
    // vertice.izquierdo.padre=vertice;
      actualizarAlturas(this.raiz);
    }
    revisarBalance(vertice.derecho);
     // revisarBalance(vertice.izquierdo);
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