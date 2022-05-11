package edd.src.Estructuras;

//import java.lang.Math;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

  protected class VerticeAVL extends Vertice {

    public int altura;
    //@Override
   // public VerticeAVL padreAVL;
    /** El izquierdo del vértice. */
   // @Override
   // public VerticeAVL izq;
    /** El derecho del vértice. */
    //@Override
   // public VerticeAVL derec;

   

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
      return super.toString()+" altura: "+this.altura;
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

    public int getAltura(){
        return this.altura;
    }
    
    

    public void setAltura(){
        this.altura = super.altura()-1;
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

public void actualizarAlturas(Vertice vertice){
VerticeAVL  vert = convertirAVL(vertice);
    if(this.raiz==null){
      System.out.println("No puedo trabajar con un árbol vacio");
      return;
    }
  
    
    vert.setAltura();
  
    if(vert.hayDerecho() || vert.hayIzquierdo()){
      if(vert.hayDerecho()){
        VerticeAVL  vertD = convertirAVL(vertice.derecho);
        //vertD.setAltura(vert.getAltura()-1);
        actualizarAlturas(vertD);
  
      }
  
      if(vert.hayIzquierdo()){
        VerticeAVL  vertI = convertirAVL(vertice.izquierdo);
       // vertI.setAltura(vert.getAltura()-1);
        actualizarAlturas(vertI);
      }
  
  
    }
  
  
    
  }

  public boolean delete(T elemento){
    boolean k =super.delete(elemento);
    actualizarAlturas(this.raiz);
    revisarBalance(this.raiz);
    return k;
  }


  public void rotarD(Vertice verti){
    Vertice aux = nuevoVertice(verti.get());
    if(verti.hayDerecho()){
    aux.derecho= verti.derecho;
    verti.derecho.padre=aux;
    }

    if(verti.izquierdo.hayDerecho()){
   aux.izquierdo= verti.izquierdo.derecho;
   aux.izquierdo.padre=aux;
    }

    if(verti.padre.izquierdo.get().compareTo(verti.elemento)==0){ 
      verti=verti.izquierdo;
      if(verti.padre.hayPadre()){
      verti.padre=verti.padre.padre;
      }

      verti.padre.izquierdo=verti;
    } else if(verti.padre.derecho.get().compareTo(verti.elemento)==0){ 
      verti=verti.izquierdo;
      if(verti.padre.hayPadre()){
      verti.padre=verti.padre.padre;
      }

      verti.padre.derecho=verti;
    }
    
    //
    verti.derecho=aux;
    aux.padre=verti;
   
  }

  public void add(T elemento){
    Vertice verti= nuevoVertice(elemento);
    if(this.raiz==null){
      this.raiz=verti;
    }else{
      super.insert(this.raiz, elemento);
    }
    actualizarAlturas(this.raiz);
   /* if(verti.hayPadre(
      if(verti.padre.hayPadre()){
   revisarBalance(verti.padre.padre);
      }
    }*/
    verti= search(this.raiz, elemento);
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
    revisarBalance(this.raiz);
  }


public void rebalancear(Vertice vert, int hIzq, int hDer){
  if(hDer==hIzq+2){
    vert=vert.derecho;
    System.out.println("ENTRAMOS");
    System.out.println("Vertice "+vert);
    VerticeAVL  vertD= convertirAVL(vert.derecho);

    int wd = vertD.altura()-1;

    System.out.println("Altura hijo derecho de vert"+wd);

   if(wd==hIzq+1){
    System.out.println("ENTRAMOS2");
    rotarI(vert);
    actualizarAlturas(this.raiz);
   }
  }

  
}

  public boolean revisarBalance(Vertice verti){
    System.out.println("REVISANDO "+verti);
    int izq=0, der=0;
    if(verti.hayIzquierdo()){
      VerticeAVL  vertI= convertirAVL(verti.izquierdo);
      vertI.setAltura();
      izq=vertI.altura()-1;
      System.out.println(vertI);
      System.out.println("Altura izquierda"+izq);
    }

    if(verti.hayDerecho()){
      VerticeAVL  vertD = convertirAVL(verti.derecho);
      vertD.setAltura();
      der=vertD.altura()-1;
      System.out.println(vertD);
      System.out.println("Altura derecha"+der);
    }
    
int op=Math.abs(izq-der);
System.out.println("DESB"+op);
    if(Math.abs(izq-der)<2){
      if(verti.hayDerecho()){
       // System.out.println("REVISANDO HIJO DER");
      revisarBalance(verti.derecho);
      }
      if(verti.hayIzquierdo()){
        //System.out.println("REVISANDO HIJO IZQ");
      revisarBalance(verti.izquierdo);
      }
          }else{
            System.out.println("REBALANCE");
      rebalancear(verti, izq, der);
    }

    return true;
  }

  public void rotarI(Vertice verti){
   Vertice aux = nuevoVertice(verti.get());
   /*System.out.println("Verti"+ verti);
   System.out.println("Aux"+ aux);
   System.out.println("Verti der" + verti.derecho);*/
  // System.out.println("Verti izq"+ verti,izquierdo);
    if(verti.hayIzquierdo()){
    aux.izquierdo= verti.izquierdo;
    verti.izquierdo.padre=aux;
    //System.out.println("Aux"+aux);
    }

   if(verti.derecho.hayIzquierdo()){
   aux.derecho= verti.derecho.izquierdo;
   aux.derecho.padre=aux;
   //System.out.println("Aux der"+aux.derecho);
    }
    verti=verti.derecho;
    //System.out.println("CONTROL");
    System.out.println(verti);
    if(verti.padre.hayIzquierdo()){
    if(verti.padre.izquierdo.get().compareTo(verti.elemento)==0){ 
      //System.out.println("IZQUIERDO");
      verti=verti.derecho;
      if(verti.padre.hayPadre()){
      verti.padre=verti.padre.padre;
      }
    }

     // verti.padre.izquierdo=verti;
     //System.out.println("CONTROL1");
    } else if(verti.padre.derecho.get().compareTo(verti.elemento)==0){ 
      //System.out.println("CONTROL2");
      //System.out.println("DERECHO");
      //System.out.println("Verti"+ verti);
   /*   verti=verti.derecho;
      System.out.println("Verti"+ verti);*/
      if(verti.padre.hayPadre()){
        //System.out.println("Vertice padre "+verti.padre);
      verti.padre=verti.padre.padre;
      //System.out.println("Vertice padre "+verti.padre);
      //System.out.println("CONTROL3");
      }
      //System.out.println("Vertice padre "+verti.padre);
      //System.out.println("Vertice derecho del padre de verti "+verti.padre.derecho);
      verti.padre.derecho=verti;
      //System.out.println("Vertice derecho del padre de verti "+verti.padre.derecho);
    }
    
    //
    //System.out.println("Vertice izquierdo verti "+verti.izquierdo);
    verti.izquierdo=aux;
    //System.out.println("AUX"+aux);
    //System.out.println("Vertice izquierdo verti "+verti.izquierdo);
    aux.padre=verti;

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
  for (int i = 0; i < l; i++) {
      if (a[i] == 1) {
          s = s + "│  ";
      } else {
          s = s + "   ";
      }
  }
  return s;
}*/
}
