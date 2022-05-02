package edd.src.Estructuras;

import java.util.Comparator;
import java.util.Scanner;

public class Practica3 {

  static Scanner sc;
  static boolean aux;

  /**-------------------------------------------------------Problema 1-------------------------------------------------------*/
  /**
   * Metodo en el que vamos a calcular la suma mas cercana a un numero N, primero
   * ordenamos la lista de numeros con Merge Sort teniendo presente que la
   * complejidad de dicho algoritmo de ordenamiento es O(n logn). La estrategia
   * para resolver este problema es teniendo la referencia al primer y ultimo numero,
   * es decir, al numero mas pequeño y mas grande, si la suma de estos dos es menor al
   * numero que se desea, entonces aumentamos la referencia izquierda (primer numero), puesto
   * que necesitamos un numero mas grande, cuando la suma es mas pequeña, desplazamos la referencia
   * izquierda (a la izquierda) para obtener un numero mas pequeño.
   * recorriendo
   * @param lista
   * @param N
   */

  public static void sumaCercana(Lista lista, int N) {
    boolean auxPunteros = false;
    boolean tieneSolucion = false;
    System.out.println("Ordenando la lista");
    Lista<Lista<Integer>> listaSoluciones = new Lista<>();
    Lista<Integer> listaOrdenada = lista.mergeSort(
      new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o1 - o2;
        }
      }
    );
    Lista<Integer> listaOrdenadaAux = listaOrdenada;
    System.out.println("Lista ordenada --> " + listaOrdenadaAux);
    Integer punteroIzquierdo = listaOrdenadaAux.peek();
    Integer punteroDerecho = listaOrdenadaAux.peekInverse();
    System.out.println(
      "Puntero Izquierdo: " +
      punteroIzquierdo +
      "puntero Derecho: " +
      punteroDerecho
    );
    System.out.println("Calculando");
    while (punteroIzquierdo < punteroDerecho) {
      Lista<Integer> listaSolucionesAux = new Lista<>();
      listaSolucionesAux.add(punteroIzquierdo);
      listaSolucionesAux.add(punteroDerecho);
      listaSoluciones.add(listaSolucionesAux);
      if (punteroIzquierdo + punteroDerecho == N) {
        System.out.println(
          "resultado " + punteroIzquierdo + " + " + punteroDerecho + " = " + N
        );
        tieneSolucion = true;
        break;
      } else if (punteroIzquierdo + punteroDerecho < N) {
        listaOrdenadaAux.eliminarIndice(0);
        System.out.println("Lista delete " + listaOrdenadaAux);
        punteroIzquierdo = listaOrdenadaAux.peek();
        System.out.println("Puntero izq " + punteroIzquierdo);
      } else {
        listaOrdenadaAux.pop();
        punteroDerecho = listaOrdenadaAux.peekInverse();
        System.out.println("Lista delete " + listaOrdenadaAux);
        System.out.println("Puntero der " + punteroDerecho);
      }
    }

    if (!tieneSolucion) {
      System.out.println("Mostrando la lista " + listaSoluciones);
      Lista<Integer> soluciones = listaSoluciones.peek();
      if (listaSoluciones.size() > 2) {
        soluciones = listaSoluciones.pop();
      }
      System.out.println(
        "La solucion mas cercana es " +
        soluciones.peek() +
        "+ " +
        soluciones.peekInverse() +
        " = " +
        (soluciones.peek() + soluciones.peekInverse())
      );
    }
  }

  /**
   * Metodo que va a convertir el arreglo pasado como argumento del programa
   * a una lista, además de solicitar un numero entero y hacer su verificación
   * @param nums
   */
  private static void pedirDatosSuma(String[] nums) {
    sc = new Scanner(System.in);
    Lista<Integer> lista = new Lista<>();
    do {
      try {
        System.out.println("Ingresa un entero");
        int numero = sc.nextInt();
        if (numero < 0) {
          throw new Exception();
        }
        for (String n : nums) {
          lista.add(Integer.parseInt(n));
        }
        System.out.println("Lista -->" + lista);
        aux = true;
        sumaCercana(lista, numero);
      } catch (Exception e) {
        System.out.println("Ingresa una opcion valida");
        aux = false;
      }
    } while (!aux);
  }

  /**-------------------------------------------------------Problema 2-------------------------------------------------------*/
  /**
   * Metodo que devuelve el factorial de un numero
   * @author Alcantara Estrada Kevin
   * @author Rubio Haro Mauricio
   * @param numero Numero del cual se quiere el factorial
   * @return int
   */
    private static int factorialRecursivo(int numero) {
      //Caso base
      if (numero <= 1){
        return 1;
        //caso recursivo
      }else{
      return numero * factorialRecursivo(numero - 1);
      }
    }
      
    //Variables estaticas para no perderse al hacer recursion 
      static int k=0,c=0;
      //Lista estatica para guardar las permutaciones
     static  Lista<String> permutaciones = new Lista<String>();
  
     /**
      * Metodo que sirve para intercamiar de posicion dos elmentos (en este caso letras) de un arreglo
      *
      */
      private static String intercambiarLetras(char[] arreglo, int indice1, int indice2){
    String cadenaAux="";
          char temporal = arreglo[indice1];
          arreglo[indice1] = arreglo[indice2];
          arreglo[indice2] = temporal;
    
  
       for(int betha=0; betha<arreglo.length; betha++){
            cadenaAux+=arreglo[betha];
        
    }
  
        return cadenaAux;
      }
  
      static int count=0;
      public static void permutacionesCadena(String cadena){
      char[] letras = new char[cadena.length()];
    k=cadena.length()-1;
    //if(combinaciones.size()!=factorialRecursivo(cadena.length())){
      //count=combinaciones.size();
      if(permutaciones.size()<=factorialRecursivo(cadena.length())){
        //System.out.println(combinaciones.toString());
    // }else{
      for(int gamma=0; gamma<cadena.length(); gamma++){
        letras[gamma]=cadena.charAt(gamma);
    }
    //	ite.start();
    if(c==k){
         if(!permutaciones.contains(cadena)){
          permutaciones.add(cadena);
          //count++;
            }
    }else{
        // String  cadenaAux="";
              for (int i = c; i <=k; i++){
                
      cadena = intercambiarLetras(letras,c,i);
      c++;
      /*	if(c>=cadena.length()){
             c =0;
             }*/
      
                  permutacionesCadena(cadena);
      c--;
      cadena = intercambiarLetras(letras,c,i);
                  
              }
         
      }

      }else{
        System.out.println(permutaciones.toString());
      }
      if(count==0 && permutaciones.size()==factorialRecursivo(cadena.length())){
      System.out.println(permutaciones.toString());
      count++;
      }
      }

    

  static Lista<Integer> numerosPrimos;
  static Lista<Integer> combinaciones = new Lista<>();
  static boolean tieneSolucion = false;

  /**-------------------------------------------------------Problema 3-------------------------------------------------------*/

  /**
   * Metodo para iniciar los calculos
   * @param S
   * @param P
   * @param N
   */
  private static void primosQueSuman(int S, int P, int N) {
    System.out.println("Iniciando primos que suman");
    cribaEratostenes(N, S, P);
    System.out.println("Primos disponibles " + numerosPrimos);
    generarSuma(0, N, S, 0);
    if (!tieneSolucion) {
      System.out.println("No hay solucion");
    }
  }

  /**
   * Algoritmo de la criba de eratostenes, calcula los numeros primos menores a s, pero
   * mayor al numero nPrimo. Regresa una lista de dichos numeros
   * @param s
   * @param nPrimo
   * @return Lista<Integer>
   */
  private static void cribaEratostenes(int N, int s, int nPrimo) {
    numerosPrimos = new Lista<>();
    boolean primo[] = new boolean[s];
    primo[0] = false;
    for (int i = 1; i < s; i++) {
      primo[i] = true;
    }
    for (int p = 2; p <= s; p++) {
      // Si el primo no cambia, entonces es primo
      if (primo[p - 1] == true) {
        // Actualiza todos los múltiplos de p
        if (p > nPrimo) {
          numerosPrimos.add(p);
        }
        for (int j = p * p; j <= s; j += p) {
          primo[j - 1] = false;
        }
      }
    }
    if (numerosPrimos.size() < N) {
      System.out.println("No hay numeros primos disponibles para la operacion");
      return;
    }
  }

  /**
   * Metodo que hace uso del backtracking para encontrar la suma de numeros primos igual a suma, en donde N es
   * el numero de factores en la suma. Total es el acumulador de las sumas que vayamos haciendo. Suma es el resultado
   * que debe dar la operacion suma con N factores, indice nos irá diciendo el numero que ocupamos de la lista de primos
   * @param total
   * @param N
   * @param suma
   * @param indice
   */
  private static void generarSuma(int total, int N, int suma, int indice) {
    /*
     *   Caso base --> Si total = suma, significa que la suma que hemos hecho de primos es la indicada por el usuario
     *   Si el tamaño de combinaciones = N, significa que si ocupamos los N factores necesarios ingresados por el usuario
     *   para alcanzar la suma ingresada
     */
    if (total == suma && combinaciones.size() == N) {
      tieneSolucion = true;
      System.out.println("Solucion");
      System.out.println(combinaciones);
      return;
    }
    /*
     *   Estos casos son para descartar, es decir, cuado se cumpla algunas de estas condiciones significa que no hemos
     *   encontrado el numero por lo que acabara aqui las ejecuciones
     */
    if (
      total > suma ||
      indice == numerosPrimos.size() ||
      combinaciones.size() >= N
    ) return;

    /**
     *   Agregamos a combinaciones cada unos de los numeros primos de la lista numeros primos
     *   esto es porque tenemos que probar todos los numeros primos de la lista para encontrar
     *   las posibles soluciones
     */
    combinaciones.add(numerosPrimos.get(indice));

    /**
     *   Intentamos volver a ejecutar el metodo con el siguiente numero, es decir, para completar la suma intentando llegar a
     *   suma, a total le sumamos el numero primo que arriba tomamos (por eso es acumulador), mientras que intentamos probar el
     *   siguiente numero primo (por eso se le suma 1 a indice)
     */
    generarSuma(total + numerosPrimos.get(indice), N, suma, indice + 1);

    /**
     *   Vamos a ir removiendo los elementos, puesto que si la suma no es la deseada o no con los factores N, entonces no nos
     *   sirve tener dicho numero en la lista. En caso de que si haya funcionado, en la llamada anterior podrá entrar al primer
     *   if y en ese caso ser mostrados.
     */
    combinaciones.eliminarIndice0(combinaciones.size() - 1);

    /**
     *   Volver a intentar todo lo anterior pero partiendo desde el comienzo, aunque con el siguiente numero primo
     */
    generarSuma(total, N, suma, indice + 1);
  }

  private static boolean esPrimo(int n) {
    /**
     *   El 0, 1 y 4 no son primos
     */
    if (n == 0 || n == 1 || n == 4) {
      return false;
    }
    for (int x = 2; x < n / 2; x++) {
      /**
       *   Si es divisible por cualquiera de estos números, no
       *   es primo
       */

      if (n % x == 0) return false;
    }
    /**
     *   Si no se pudo dividir por ninguno de los de arriba, sí es primo
     *
     */
    return true;
  }

  /**
   * Metodo que pide al usuario N, P y S, todos numeros naturales, ademas de hacer la
   * verificacion de que P sea un numero primo
   */
  private static void pedirDatosSumaPrimos() {
    sc = new Scanner(System.in);
    aux = false;
    do {
      try {
        System.out.println("Ingresa un entero N");
        int n = sc.nextInt();
        System.out.println("Ingresa un primo P");
        int p = sc.nextInt();
        System.out.println("Ingresa la suma  S");
        int s = sc.nextInt();
        if (esPrimo(p)) {
          aux = true;
          primosQueSuman(s, p, n);
        } else {
          System.out.println(p + " no es un numero primo");
          throw new Exception();
        }
      } catch (Exception e) {
        System.out.println("Ingresa un numero valido");
        System.out.println(e.getMessage());
      }
    } while (!aux);
  }

  //-------------------------------------------------------Problema 4-------------------------------------------------------*/
  /** 
   * 
  *Metodo que verifica si el lugar en donde se quiere colocar la reina es valido o no
   *segun las reinas ya colocadas y su respectivo alcance
   *@author Alcantara Estrada Kevin
   *@author Rubio Haro Mauricio
  *@param arr  Arreglo bidimensional que representa el tablero
  *@param fila Representa la fila en donde se quiere colocar la reina
  *@param columna Representa la columa en donde se quiere colocar la reina
  *@param N es el numero de reinas
  *@return boolean
   */
  
  private static boolean validarPos(char[][] arr,int fila, int columna,int N){
    //Revisamos que no hay reinas donde deseamos colocar la reina
      if(arr[fila][columna]=='Q'){
        return false;
      }

      //Revisamos que la reina no esté al alcance de otra reina por estar en la misma fila hacia la derecha
      for(int k=columna+1; k<N; k++){
        if(arr[fila][k]=='Q'){
      return false;
      
        }
    }
  //Revisamos que la reina no esté al alcance de otra reina por estar en la misma fila hacia la izquierda
        for( k=columna-1; 0<=k; k--){
        if(arr[fila][k]=='Q'){
          return false;
        }
        }
  //Revisamos que la reina no esté al alcance de otra reina por estar en la misma columna hacia arriba
      for (k=fila-1; 0<=k; k--){
        if(arr[k][columna]=='Q'){
          return false;
        }
      }
  
    //Revisamos que la reina no esté al alcance de otra reina por estar en la misma columna hacia abajo
        for (k=fila+1; k<N; k++){
        if(arr[k][columna]=='Q'){
          return false;
        }
        
        }

        //Revisamos que la reina no esté al alcance de otra reina por estar en la misma diagonal hacia arriba a la izquierda
            int columnaAux=columna;
         for (k=fila-1; 0<=k; k--){
        columnaAux--;
        if(k==columnaAux || k == columnaAux-1  ||(k==columnaAux+1 && columnaAux>0)||((k-fila==columnaAux-columna)&&(k>=0&&columnaAux>=0))){
        if(arr[k][columnaAux]=='Q'){
          return false;
        }
        }
      }
         
         columnaAux=columna;
          //Revisamos que la reina no esté al alcance de otra reina por estar en la misma diagonal hacia abajo a la derecha
          for (k=fila+1; k<N; k++){
        columnaAux++;
        if(k==columnaAux || (k+1==columnaAux && columnaAux<N) |((k-fila==columnaAux-columna)&&(k>=0&&columnaAux<N))){
        if(arr[k][columnaAux]=='Q'){
      return false;
        }
        }
        
        
        }
  
  
  
           columnaAux=columna;
            //Revisamos que la reina no esté al alcance de otra reina por estar en la misma diagonal hacia arriba a la derecha
          for (k=fila-1; 0<=k; k--){
        columnaAux++;
            if(columnaAux<N){
        if(arr[k][columnaAux]=='Q'){
        return false;
           }
        }
        }
  
  
  
   //Revisamos que la reina no esté al alcance de otra reina por estar en la misma diagonal hacia abajo a la izquierda
           columnaAux=columna;
          for (k=fila+1; k<N; k++){
        
        columnaAux--;
        if(columnaAux>=0){
        if(arr[k][columnaAux]=='Q'){
      return false;
        }
        
        }
        }
  
  
       return true;
      }

      /**
       * Metodo que imprime en consola el tablero
       * @author Alcantara Estrada Kevin
       * @author Rubio Haro Mauricio
       * @param arr Tablero a pintar
       */
    private static void imprimirTablero(char[][] arr){
                for (int x=0; x < tablero.length; x++){
                   for (int y=0; y < tablero[x].length; y++)  {
               
                    System.out.print("[");System.out.print (tablero[x][y]); System.out.print("] ");
               
                       }
                      System.out.println();
                     }
                   }

                   //Tablero estatico para que no se reinicio durante la recursion
                   static char[][] tablero;
                   /**
                    * Metodo para colocar las reinas en el tablero hasta encontrar una solucion
                    * @author Alcantara Kevin Isaac
                    * @author Rubio Haro Mauricio
                    * @param tab Tablero a colocar la reinas
                    *@param fila Fila donde se va colocando la reina
                    * @param N numero de reinas
                    */
            public static void colocarReinas(char[][] tab, int fila,int N){
               //Condicion de freno por si ya recorrimos todo el tablero d izquierda a derecha
              if(fila<N){
                //Recorremos el tablero de arriba hacia abajo de izquierda a derecha
                  for(int i=0;i<N;i++){
                    //Verificamos que se pueda colocar la reina
                    if(validarPos(tab, i, fila, N)){
                      //La colocamos
                      tab[i][fila]='Q';
                      //Llamamos a recursion
                      colocarReinas(tab, fila+1,N);
                      //Si hemos colocado todas las reinas en posibles sitios 
                      //y no hay una en cada fila, realizamos el backtracking al cambiar la reina anterior de posicion
                      tab[i][fila]='*';
                    }
                  }
                }else{
                  //Rellenar el tablero con * por diseño
                  for(int alpha=0; alpha<N; alpha++){
                    for(int betha=0; betha<N; betha ++){

                      if(tablero[alpha][betha]!='Q'){
                        tablero[alpha][betha]='*';
                      }
                    }
                  }
                  System.out.println("SOLUCION");
                  //Imprimimos la solucion(es) encontrada(s)
                  imprimirTablero(tab);
                  System.out.println();
                }
              }


     
     /**
    * Metodo para encontrar las soluciones al problema de las N Damas o N Reinas usando backtracking
    * @author Alcantara Kevin Isaac
    * @author Rubio Haro Mauricio
    * @param N numero de reinas
    */
    public static void N_Reinas(int N){
      //Le damos tamaño al tablero
      tablero=new char[N][N];
     colocarReinas(tablero,0,N);
     
    }

  /**-------------------------------------------------------Problema 5-------------------------------------------------------*/
  static void sqrtBinariSearch(int x) {
    double solucion = 0;
    double margen = 10E-5;
    double intervaloIzq = 1;
    double intervaloDer = x;
    if (x == 1) {
      solucion = 1;
    } else if (x == 0) {
      solucion = 0;
    } else {
      /**
       * ¿Que intevalo debemos ocupar?
       * veamos que la raiz de cualquier numero (sin contar al 0 y al 1) es por lo menos mas grande a 1 por lo que 
       * el intervalo será (1 , ??]
       * para este caso consideraremos el intervalo derecho como x, que es el radicando
       */
      while ((intervaloDer - intervaloIzq) > margen) {
        double mitad = (intervaloDer + intervaloIzq) / 2;
        if(producto(mitad) < x){
          intervaloIzq = mitad;
        }else{
          intervaloDer = mitad;
        }
      }
      solucion = intervaloIzq;
    }
    System.out.println("Solucion: " + solucion);
  }

  static double producto(double numero){
    double aux = 1;
    for (int i = 1; i <= 2; i++) {
      aux = aux * numero;
    }
    return aux;
  }

  static void pedirDatosBinariSearch() {
    boolean aux = false;
    do {
      try {
        sc = new Scanner(System.in);
        System.out.println("Ingresa un numero");
        int x = sc.nextInt();
        if (x < 0) {
          System.out.println("No existen raices negativas");
          throw new Exception();
        }
        aux = true;
        sqrtBinariSearch(x);
      } catch (Exception e) {
        System.out.println("Ingresa una opcion valida");
      }
    } while (!aux);
  }

  public static void main(String[] args) {
    /*if (args.length > 1) {
      pedirDatosSuma(args);
    } else {
      pedirDatosSumaPrimos();
    }*/
    //pedirDatoinariSearch();
    Lista<Integer>  listu= new Lista<Integer>();
    /*for (int i = -7; i < 0; i++) {
      listu.add(i);
    }

    for (int i = 0; i < 7; i++) {
      listu.add(i);
    }*/
    ArbolBinarioBusqueda <Integer> miArbol = new ArbolBinarioBusqueda<Integer>();
   // ArbolBinario arbolix = ArbolBinario(listu);
    
   
    //System.out.println(arbolix);
    //System.out.println(miArbol.modBFS(arbolix).toString());
   
   // miArbol.add(-10);
   // miArbol.add(0);
    
    miArbol.pop();
    System.out.println(miArbol);

    ArbolBinarioBusqueda<Integer> ab= miArbol;
    ab.add(51);
    ab.insert(ab.raiz, 43);
    ab.insert(ab.raiz, 13);
    ab.insert(ab.raiz, 23);
    ab.insert(ab.raiz, 18);
    ab.insert(ab.raiz, 21);
    ab.insert(ab.raiz, 67);
    ab.insert(ab.raiz, 1);
    ab.insert(ab.raiz, 0);
    ab.insert(ab.raiz, 9);
    ab.insert(ab.raiz, -2);
    ab.insert(ab.raiz, 89);
    ab.insert(ab.raiz, 1203);
    ab.insert(ab.raiz, 325);
    ab.insert(ab.raiz, 3205);
    System.out.println(ab.toString());
    System.out.println(ab.ultimoDerecho(ab.raiz).padre.get());
    System.out.println(ab.ptoString());
/*

    ab.insert(ab.raiz, 134);
    ab.insert(ab.raiz, -21);
    ab.insert(ab.raiz, 8);
    ab.insert(ab.raiz, 3);

    a
    //System.out.println(miArbol);
   // ab.insert(t);
    
    //System.out.println(miArbol);
    */
  }
  }
 
