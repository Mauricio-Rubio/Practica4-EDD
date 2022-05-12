package edd.src.Estructuras;

//import java.lang.Math;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

  protected class VerticeAVL extends Vertice {

    public int altura;
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
    /* public int alturaAVL() {
            
      int alturaIzquierdo = 0;
      int alturaDerecho = 0;
      
      if (hayIzquierdo()){
          alturaIzquierdo = izq.alturaAVL();
      }
      if (hayDerecho()){
          alturaDerecho = derec.alturaAVL();
      }

      return 1 + Math.max(alturaIzquierdo, alturaDerecho);
  }*/
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
  Vertice vertic= search(this.raiz,elemento);
  System.out.println("RAIZ "+this.raiz +"HI"+ this.raiz.izquierdo+ "HD"+this.raiz.derecho);
  System.out.println("Vertic "+vertic +"HI"+ vertic.izquierdo+ "HD"+vertic.derecho+"P"+vertic.padre);
    actualizarAlturas(this.raiz);
    /* if(verti.hayPadre(
      if(verti.padre.hayPadre()){
   revisarBalance(verti.padre.padre);
      }
    }*/
    /* verti= search(this.raiz, elemento);
    if(verti.hayPadre()){
      if(verti.padre.hayPadre()){
    revisarBalance(verti.padre.padre);
      }else{
        revisarBalance(verti.padre);
      }
    }
    /*if(verti.hayPadre()){
    revisarBalance(verti.padre);
    }*/
    //revisarBalanceInv(search(this.raiz,elemento));
    //revisarBalanceInv(search(this.raiz,elemento));
  //  revisarBalanceInv(search(this.raiz,elemento));
    actualizarAlturas(this.raiz);
  //  System.out.println("ARBOL MOD \n"+this.toString());
   // revisarBalance(this.raiz);
    //revisarBalanceInv(ultimoDerecho(this.raiz));
    
    //revisarBalance(this.raiz);
  }

  //public void rebalancear(Vertice vert, int hIzq, int hDer) {}

  

public void rebalancear(Vertice vert, int hIzq, int hDer){
  System.out.println("El vertice desde donde hacemos balanceo" + vert  + " hDer "+hDer + " hIzq "+hIzq );
  if(hDer==hIzq+2){
    //vert=vert.derecho;
    //vert=vert.derecho;
    System.out.println("ENTRAMOS");
    System.out.println("El vertice a mover" + vert  );
    vert=vert.derecho;
    //System.out.println("Vertice "+vert);
    //vert=vert.derecho;
    VerticeAVL  vertD= convertirAVL(vert.derecho);
    //if(vertD.hayDerecho()){ vertD=vertD.derecho;}
   // vert=vert.padre;
    System.out.println("Vertice vertD"+vertD);
    int wd = vertD.altura()-1;
    System.out.println("WD"+wd);
    System.out.println("Altura hijo derecho de vert"+wd);
   
   if(wd==hIzq+1){
    
    System.out.println("ENTRAMOS2");
    /*if(vert.hayPadre()){
    rotarI(vert.padre);
    }else{*/
      rotarI(vert);
    //}
    actualizarAlturas(this.raiz);
   }

   if(wd==hIzq){
    System.out.println("ENTRAMOS3");
    Vertice padre = vert.padre;
    rotarD(vert);
    rotarI(padre);
    
    actualizarAlturas(this.raiz);
   }
  }else{
    //desbalanceIzquierda(vert);
  }
  actualizarAlturas(this.raiz);
 // revisarBalance(this.raiz);
}


 
     

  public boolean revisarBalance(Vertice verti) {
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
      if (verti.hayDerecho()) {
        revisarBalance(verti.derecho);
      }
      if (verti.hayIzquierdo()) {
        //System.out.println("REVISANDO HIJO IZQ");
      revisarBalance(verti.izquierdo);

      }else if(verti.hayDerecho()&& verti.derecho.altura()-1==2){
        if(izq+1==der){
          rebalancear(verti.padre, izq, der+1);

        }
      }
    } else {
      System.out.println("REBALANCE");
      rebalancear(verti, izq, der);
    }

    return true;
  }


 

  //SEPARADOR
  public boolean revisarBalanceInv(Vertice verti){
    System.out.println("REVISANDO INV "+verti);
    int izq=0, der=0;
    if(verti.hayIzquierdo()){
      VerticeAVL  vertI= convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq=vertI.altura()-1;
      System.out.println(vertI);
      System.out.println("Altura izquierda"+izq);
    }
 

    if (verti.hayDerecho()) {
      VerticeAVL vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der=vertD.altura()-1;
      System.out.println(vertD);
      System.out.println("Altura derecha"+der);
    }
    
int op=Math.abs(izq-der);
System.out.println("DESB"+op);
    if(Math.abs(izq-der)<2){
     
if(!verti.hayIzquierdo()){
  if(verti.hayDerecho()&& verti.derecho.altura()-1==2){
    if(izq+1==der){
      rebalancear(verti.padre, izq, der+1);
    }
  }
}
      if(verti.hayPadre()){
        revisarBalanceInv(verti.padre);
      }
          }else{

            /*if((!verti.hayIzquierdo())&&verti.hayDerecho()&& verti.altura()-1==2){
              if(izq+1==der){
                rebalancear(verti, izq, der+1);
              }
            }*/
            System.out.println("REBALANCE");
      rebalancear(verti, izq, der);
    } 

    return true;
  }


  //SEPARADOOOR


  public void rotarI(Vertice verti){
    
   Vertice aux = nuevoVertice(verti.get());
System.out.println("AUX AUX"+aux);
   if(this.raiz==verti){
     this.raiz=verti.derecho;
     if(verti.derecho.hayIzquierdo()){
       aux.derecho= verti.derecho.izquierdo;
       aux.derecho.padre=aux;
     }
     if(verti.hayIzquierdo()){
       aux.izquierdo=verti.izquierdo;
       aux.izquierdo.padre=aux;
     }
     this.raiz.izquierdo=aux;
   }else{
  //  verti=verti.derecho;
    
    if(verti.hayIzquierdo()){
    aux.izquierdo= verti.izquierdo;
    aux.izquierdo.padre=aux;
    System.out.println("AUX AUX i"+aux.izquierdo);
   // verti.izquierdo.padre=aux;
  
    }
    
   // verti.padre=verti.padre;
    System.out.println("VERTI KK"+ verti);
   if(verti.derecho.hayIzquierdo()){
   aux.derecho= verti.derecho.izquierdo;
   System.out.println("AUX AUX D"+aux.derecho);
   aux.derecho.padre=aux;
  
    }
   // verti.padre=verti;
    //if()
   verti=verti.derecho;
    System.out.println("VERTI "+ verti);
    System.out.println("VERTI P"+ verti.padre);
    System.out.println("VERTI P SUP"+ verti.padre.padre);
    System.out.println("VERTI I"+ verti.izquierdo);
    System.out.println("VERTI D"+ verti.derecho);
    System.out.println("Aux "+ aux);
    System.out.println("Aux P"+ aux.padre);
    System.out.println("Aux I"+ aux.izquierdo);
    System.out.println("AuxD"+ aux.derecho);
    //verti=verti.derecho;
    
   
   // System.out.println(verti);
    //if(verti.padre.hayIzquierdo()){
    /*  System.out.println("Vertice i de padre"+verti.padre.izquierdo);
    if(verti.padre.izquierdo.get().compareTo(verti.elemento)==0){ 
      verti=verti.derecho;
      if(verti.padre.hayPadre()){
      verti.padre=verti.padre.padre;

      }*/
    //}else if(verti.padre.derecho.get().compareTo(verti.elemento)==0){ 
      //verti.izquierdo=verti.padre.izquierdo;
      
      //verti=verti.padre;
      //verti.derecho.padre=verti;
      //verti.izquierdo.padre=verti;
      if(verti.padre.hayPadre()){
         System.out.println("Ojoooooooo"+verti);
         System.out.println("Ojooooooxxxoo"+aux);
        
       verti.padre=verti.padre.padre;
       verti.padre.derecho=verti;
       System.out.println("Verti padre  def"+verti.padre);
       
       }
      /* System.out.println("Verti padre  osc"+verti.padre);
      verti.padre.derecho=verti;
      //verti.izquierdo=aux;
    System.out.println("Vertice d de padre"+verti.padre.derecho);
      }
     
    }else if(verti.padre.derecho.get().compareTo(verti.elemento)==0){ 
      
     if(verti.padre.hayPadre()){
        System.out.println("Ojoooooooo");
      verti.padre=verti.padre.padre;
      System.out.println("Verti padre  def"+verti.padre);
      
      }
      System.out.println("Verti padre  osc"+verti.padre);
      verti.padre.derecho=verti;
      
    }
    
    System.out.println("Verti padre  asp"+verti.padre);*/
   /* if(verti.padre.hayPadre()){
       
      verti.padre=verti.padre.padre;
      System.out.println("Verti padre  def"+verti.padre);
      
      }
      System.out.println("Verti padre  osc"+verti.padre);
      verti.padre.derecho=verti;*/
    verti.izquierdo=aux;
    aux.padre=verti;

    //
    //verti.izquierdo = aux;
    //aux.padre = verti;
  }
  actualizarAlturas(this.raiz);

  }

  public void desbalanceIzquierda(VerticeAVL vertice) {
    int k = vertice.derecho.altura;
    VerticeAVL HIzq = vertice.izquierdo;
    if (HIzq.altura == k + 2) {
      System.out.println("Se necesita balance izquierdo");
    }
    if (HIzq.hayIzquierdo()) {
      VerticeAVL WIzq = HIzq.izquierdo;
      if (WIzq.altura == k + 1) {
        System.out.println("Caso 1: Linea recta");
        rotarD(vertice);
        //Recalculo de las alturas y rebalanceo
      } else if (WIzq.altura == k) {
        rotarI(HIzq);
        rotarD(vertice);
      }
      actualizarAlturas(vertice);
    }
  }



    public boolean revisarB(Vertice verti){
      System.out.println("REVISANDO "+verti);
      int izq=0, der=0;
      if(verti.hayIzquierdo()){
        VerticeAVL  vertI= convertirAVL(verti.izquierdo);
        vertI.setAltura();
        izq=vertI.altura()-1;
        System.out.println(vertI);
        System.out.println("Altura izquierda"+izq);
      }
   
  
      if (verti.hayDerecho()) {
        VerticeAVL vertD = convertirAVL(verti.derecho);
        vertD.setAltura();
        der=vertD.altura()-1;
        System.out.println(vertD);
        System.out.println("Altura derecha"+der);
      }
      
  int op=Math.abs(izq-der);
  System.out.println("DESB"+op);
      if(Math.abs(izq-der)<2){
       
  
   
  
      return true;
    }else{
      return false;
    }
  }
  /*verti= verti.izquierdo;
    aux.izquierdo=verti.derecho;
    verti.derecho.padre=aux;
    //verti.derecho=aux;
   // aux.padre=verti;
  }

  public void add(T elemento){
    if(this.raiz==null){
      this.raiz = nuevoVertice(elemento);
    }else{
      super.insert(this.raiz, elemento);
    }
    actualizarAlturas(this.raiz);
  }
  //public VerticeAVL raizAVL;

 /* public void actualizarAlturas(Vertice vertice, int alt){
  VerticeAVL  vert = convertirAVL(vertice);
    if(this.raiz==null){
      System.out.println("No puedo trabajar con un árbol vacio");
      return;
    }
  
    if(alt>0){
    vert.setAltura(alt);
  
    if(vert.hayDerecho() || vert.hayIzquierdo()){
      if(vert.hayDerecho()){
        vert.derec.setAltura(vert.getAltura()-1);
        actualizarAlturas(vert.derec, vert.derec.getAltura());
  
      }
  
      if(vert.hayIzquierdo()){
        vert.izq.setAltura(vert.getAltura()-1);
        actualizarAlturas(vert.izq, vert.izq.getAltura());
      }
  
  
    }
  
  
    }
  }

  /*
  public boolean delet(T object) {
    VerticeAVL vertice = this.search(this.raizAVL, object);
   // VerticeAVL vertice = verti;
    if (vertice == null) {
      System.out.println("No se ha encontrado");
      return false;
    }
    if (vertice.izq != null && vertice.derec!= null) {
      VerticeAVL aux = vertice;
      VerticeAVL padre = vertice.padreAVL;
      VerticeAVL reemplazo = verticeReemplazo(aux);
      if (aux.equals(this.raiz)) {
        this.raizAVL = reemplazo;
      } else if (esHijoIzq) {
        padre.izq = reemplazo;
      } else {
        padre.derec = reemplazo;
      }
      reemplazo.izq = aux.izq;
      return true;
    }
    if (vertice.izq == null && vertice.derec == null) {
      if (vertice.equals(this.raiz)) {
        this.raizAVL = null;
        return true;
      }
      if (vertice.padreAVL.get().compareTo(vertice.elemento) < 0) {
        vertice.padreAVL.derec = null;
        return true;
      } else {
        vertice.padreAVL.izq = null;
      }
      return true;
    }
    if (vertice.izq == null || vertice.derec != null) {
      if (vertice.padreAVL.get().compareTo(vertice.elemento) < 0) {
        vertice.padreAVL.derec = vertice.derec;
      } else {
        vertice.padreAVL.izq = vertice.derec;
      }
      return true;
    }
    if (vertice.izq != null || vertice.derec == null) {
      if (vertice.padreAVL.get().compareTo(vertice.elemento) < 0) {
        vertice.padreAVL.derec = vertice.izq;
      } else {
        vertice.padreAVL.izq = vertice.izq;
      }
      //vertice = vertice.izquierdo;
      return true;
    }

    return true;
  }

  private boolean esHijoIzq = false;

  //@Override
  public VerticeAVL search(VerticeAVL vertice, T elemento) {
    if (vertice == null) {
      return null;
    }
    if (vertice.elemento == elemento) {
      return vertice;
    }
    if (vertice.get().compareTo(elemento) < 0) {
      return search(vertice.derec, elemento);
    }
    if (vertice.get().compareTo(elemento) > 0) {
      esHijoIzq = true;
      return search(vertice.izq, elemento);
    }
    return null;
  }

//@Override
  public VerticeAVL verticeReemplazo(VerticeAVL vertice) {
    VerticeAVL reemplazarPadre = vertice;
    VerticeAVL reemplazo = vertice;
    VerticeAVL aux = vertice.derec;
    while (aux != null) {
      reemplazarPadre = reemplazo;
      reemplazo = aux;
      aux = aux.izq;
    }
    if (!reemplazo.equals(vertice.derec)) {
      reemplazarPadre.izq = reemplazo.derec;
      reemplazo.derec = vertice.derec;
    }
    return reemplazo;
  }

 
  public void add(T elemento) {
    if (elemento == null) {
      throw new IllegalArgumentException();
    }
    Vertice a = nuevoVertice(elemento);
    elementos++;
    if (isEmpty()) {
      raizAVL = a;
    } else {
      VerticeAVL b = BFS();
      if (!b.hayIzquierdo()) {
        b.izq = a;
        a.padreAVL = b;
        return;
      }
      if (!b.hayDerecho()) {
        b.derec = a;
        a.padreAVL = b;
      }
    }
  }


  private VerticeAVL BFS() {
    if (this.isEmpty()) {
      return null;
    }
    Cola<VerticeAVL> a = new Cola<VerticeAVL>();
    a.push(this.raizAVL);
    while (a.cabeza != null) {
      VerticeAVL b = a.pop();
      if (b.hayIzquierdo()) {
        a.push(b.izq);
      }
      if (b.hayDerecho()) {
        a.push(b.derec);
      }
      if (!b.hayIzquierdo() || !b.hayDerecho()) {
        return b;
      }
    }
    return null;
  }

  /*
                 // @Override


  public ArbolAVL(){
      super();
  }

                 




@Override
public String toString() {
    if (this.raizAVL == null) {
        return "";
    }
    int[] a = new int[this.raizAVL.alturaAVL() + 1];
    for (int i = 0; i < a.length; i++) {
        a[i] = 0;
    }
    return toString(this.raizAVL, 0, a);
}

private String toString(VerticeAVL v, int l, int[] m) {
  String s = v.toString() + "\n";
  m[l] = 1;

  if (v.izq != null && v.derec != null) {
      s = s + dibujaEspacios(l, m, m.length);
      s = s + "├─›";
      s = s + toString(v.izq, l + 1, m);
      s = s + dibujaEspacios(l, m, m.length);
      s = s + "└─»";
      m[l] = 0;
      s = s + toString(v.derec, l + 1, m);
  } else if (v.izq != null) {
      s = s + dibujaEspacios(l, m, m.length);
      s = s + "└─›";
      m[l] = 0;
      s = s + toString(v.izq, l + 1, m);
  } else if (v.derec!= null) {
      s = s + dibujaEspacios(l, m, m.length);
      s = s + "└─»";
      m[l] = 0;
      s = s + toString(v.derec, l + 1, m);
  }
  return s;
}

private String dibujaEspacios(int l, int[] a, int n) {
  String s = "";
  for (int i = 0; i < l; i++) {void
          s = s + "   ";
      }
  }
  return s;
}*/
}
