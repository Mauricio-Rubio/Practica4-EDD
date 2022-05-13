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
    do{
      revisarBalance(this.raiz);
      actualizarAlturas(this.raiz);
      desbalanceado=revisarB(this.raiz);
      }while(desbalanceado==true);
    return k;
  }

  public void rotacionDerecha(Vertice vert){
    if(vert==null|| !vert.hayIzquierdo()){
      return;
    }

    Vertice aux = vert;
    Vertice vi = vert.izquierdo;
    vi.padre=aux.padre;
    if(aux!=this.raiz){
      if(aux.padre.hayIzquierdo()){
      if(aux.padre.izquierdo.get().compareTo(aux.elemento)==0){
        vi.padre.izquierdo=vi;
      }
    }else{
        vi.padre.derecho=vi;
      }
    }else{
      this.raiz=vi;
    }
    aux.izquierdo=vi.derecho;
    if(vi.hayDerecho()){
      vi.derecho.padre=aux;
    }
    aux.padre=vi;
    vi.derecho=aux;
  }

  public void rotacionIzquierda(Vertice vert){
    if(vert==null|| !vert.hayDerecho()){
      return;
    }

    Vertice v = vert;
    Vertice vd= vert.derecho;
    vd.padre=v.padre;
    if(v!=this.raiz){
      if(vd.padre.hayDerecho()){
      if(v.padre.derecho.get().compareTo(v.elemento)==0){
        vd.padre.derecho=vd;
      }
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
    actualizarAlturas(this.raiz);
    System.out.println("Desbalanceado"+revisarB(this.raiz));
    do{
     /* verti=search(this.raiz,elemento);
      if(verti.hayPadre()){
       revisarBalance(verti.padre);
        if(verti.padre.hayPadre()){
          revisarBalance(verti.padre.padre);
        }
      }else{*/ revisarBalance(this.raiz);//}
    actualizarAlturas(this.raiz);
    desbalanceado=revisarB(this.raiz);
    }while(desbalanceado==true);
  }

  /**
   * Desbalance Derecho
   * @param vert
   * @param hIzq
   * @param hDer
   */
  public void desbalanceDerecho(Vertice vert, int hIzq, int hDer) {
    System.out.println("DESBALANCE DERECHO "+vert);
    int wd = 0;
    if (vert.hayDerecho()) {
      if (vert.derecho.hayDerecho()) {
        wd = vert.derecho.derecho.altura() - 1;
      } else {
        rotacionDerecha(vert.derecho);
      actualizarAlturas(this.raiz);
        rotacionIzquierda(vert);
        actualizarAlturas(this.raiz);
        return;
        
      }
    }else{
      
    }

    if (wd == hIzq + 1) {
      rotacionIzquierda(vert);
      actualizarAlturas(this.raiz);
    } else if (wd == hIzq) {
      if(vert.hayIzquierdo()){
        Vertice padre= vert.padre;
        rotacionDerecha(vert.derecho);
        rotacionIzquierda(vert);
      }else{
        //rotacionIzquierda(vert);
      }
    
     actualizarAlturas(this.raiz);
     actualizarAlturas(vert);
      revisarBalance(this.raiz);
      actualizarAlturas(this.raiz);
    }else{
      if(vert.hayDerecho()){
      revisarBalance(vert.derecho);
    }
    if(vert.hayIzquierdo()){
      revisarBalance(vert.izquierdo);
    }
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
      if(verti.altura()-1==2 &&  !verti.hayIzquierdo() && verti.derecho.altura()-1==1&& !verti.derecho.hayDerecho()){
        System.out.println("PRUEBA +"+verti);
        System.out.println(this);
        System.out.println(modBFS(this).toString());
        System.out.println(verti.derecho.izquierdo.padre);
        rebalancear(verti, 0,2 );
      }else if(verti.altura()-1==2 &&  !verti.hayIzquierdo() && verti.derecho.altura()-1==1&& !verti.izquierdo.hayDerecho()){
        rotacionIzquierda(verti);
    }
      
     

      if (verti.hayDerecho()) {
        revisarBalance(verti.derecho);
      }
      if (verti.hayIzquierdo()) {
        revisarBalance(verti.izquierdo);
      }

   
    } else if(op==2){
      System.out.println("HAY DESBALANCE EN"+verti);
      System.out.println("PUTA VERGAAA\n"+this);

     
      rebalancear(verti, izq, der);
    }else{
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
      izq = vertI.altura;
    }

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der = vertD.altura;
    }

    int op = Math.abs(izq - der);

    if (Math.abs(izq - der) < 2) {
      if(verti.altura()-1==2 &&  !verti.hayIzquierdo() && verti.derecho.altura()-1==1&& !verti.derecho.hayDerecho()){
        System.out.println("PRUEBA +"+verti);
        System.out.println(this);
        System.out.println(modBFS(this).toString());
        System.out.println(verti.derecho.izquierdo.padre);
        rebalancear(verti, 0,2 );
      }else if(verti.altura()-1==2 &&  !verti.hayIzquierdo() && verti.derecho.altura()-1==1&& !verti.izquierdo.hayDerecho()){
        rotacionIzquierda(verti);
    }
    
      if (verti.hayPadre()) {
        revisarBalance(verti.padre);
      }
     
    } else if(op==2){
      System.out.println("HAY DESBALANCE EN"+verti);
      System.out.println("PUTA VERGAAA\n"+this);
      
      if(verti.hayDerecho()){
        revisarBalance(verti.derecho);
        }
        if(verti.hayIzquierdo()){
       revisarBalance(verti.izquierdo);
        }
      
      rebalancear(verti, izq, der);
    }else{
      if(verti.hayPadre()){
      revisarBalanceInv(verti.padre);
      }
    }
    return true;
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
    Vertice HIzq = vertice.izquierdo;
    if (HIzq.hayIzquierdo()) {
      Vertice WIzq = HIzq.izquierdo;
      Vertice WDer = HIzq.derecho;
      VerticeAVL WizqAVL = convertirAVL(WIzq);
      VerticeAVL WderAVL = convertirAVL(WDer);
      if (WizqAVL.altura == k + 1) {

        System.out.println("Caso 1: Linea recta");
       rotacionDerecha(vertice);
        
        actualizarAlturas(this.raiz);
        System.out.println(this);
       // revisarBalanceInv(HIzq);
        revisarBalance(this.raiz);
      } /**Caso donde esta en forma zig zag */else if (
        WizqAVL.altura == k && WderAVL.altura == k + 1
      ) {
        System.out.println("Caso 2: ZIG ZAG");
        if(vertice.hayDerecho()){
        rotacionIzquierda(vertice.izquierdo);
        rotacionDerecha(vertice);
        }else{
          rotacionDerecha(vertice);
        }
    
      actualizarAlturas(this.raiz);
      System.out.println(this);
      System.out.println(modBFS(this).toString());
        
      actualizarAlturas(vertice);
       
      }else{
       
      }
    }else{
      rotacionIzquierda(vertice.izquierdo);
      vertice.izquierdo.padre=vertice;
     
      actualizarAlturas(this.raiz);
    }
    if(vertice.hayDerecho()){
    revisarBalance(vertice.derecho);
    }else if(vertice.hayIzquierdo()){
      revisarBalance(vertice.izquierdo);
    }
    
  }

  /**
   * Revisa si un vertice esta desbalanceado
   * @param verti
   * @return
   */
  static boolean desbalanceado=false;
  public boolean revisarB(Vertice verti) {
    desbalanceado=false;
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
      if(verti.hayDerecho()){
      revisarB(verti.derecho);
      }
      if(verti.hayIzquierdo()){
        revisarB(verti.izquierdo);
        }
    } else {
      desbalanceado=true;
    }
    return desbalanceado;
  }
  
}