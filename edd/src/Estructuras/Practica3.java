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
      if(listaSoluciones.size() > 2){
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
 * 
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
    //combinacionesSuma(N, S, P, numerosPrimos);
  }

  /**
   * Algoritmo de la criba de eratostenes, calcula lo numero primos menores a s, pero
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
        //System.out.println(p);
        if (p > nPrimo) {
          numerosPrimos.add(p);
        }
        //numerosPrimos.add(p);
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

  private static void generarSuma(int total, int N, int suma, int indice) {
    // add prime.get(index)
    // to set vector
    //System.out.print("combinaciones 1 -->");
    //display();

    if (total == suma && combinaciones.size() == N) {
      tieneSolucion = true;
      System.out.println("Solucion");
      System.out.println(combinaciones);
      return;
    }
    if (
      total > suma ||
      indice == numerosPrimos.size() ||
      combinaciones.size() >= N
    ) return;

    combinaciones.add(numerosPrimos.get(indice));
    //System.out.print("combinaciones 2 -->");
    //display();
    // include the (index)th
    // prime to total
    //System.out.println("Indice "+prime.get(index));
    generarSuma(total + numerosPrimos.get(indice), N, suma, indice + 1);

    // remove element
    // from set vector
    /*for (int a : set) {
      System.out.println("este es set -->"+a);
    }*/
    combinaciones.remove(combinaciones.size() - 1);
    /*for (int a : set) {
      System.out.println("este es set 2.0 -->"+a);
    }*/
    // exclude (index)th prime
    generarSuma(total, N, suma, indice + 1);
  }

  private static boolean esPrimo(int n) {
    // El 0, 1 y 4 no son primos
    if (n == 0 || n == 1 || n == 4) {
      return false;
    }
    for (int x = 2; x < n / 2; x++) {
      // Si es divisible por cualquiera de estos números, no
      // es primo
      if (n % x == 0) return false;
    }
    // Si no se pudo dividir por ninguno de los de arriba, sí es primo
    return true;
  }

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

  public static void N_Reinas(int N) {}

  public static void main(String[] args) {
    if (args.length > 1) {
      pedirDatosSuma(args);
    } else {
      pedirDatosSumaPrimos();
    }
  }
}
