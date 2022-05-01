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
  public static void permutacionesCadena(String cadena) {}

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

  /**-------------------------------------------------------Problema 4-------------------------------------------------------*/
  public static void N_Reinas(int N) {}

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
    pedirDatosBinariSearch();
  }
}
