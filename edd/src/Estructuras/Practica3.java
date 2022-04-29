package edd.src.Estructuras;

import java.util.Comparator;
import java.util.Scanner;

public class Practica3 {

  static Scanner sc;
  static boolean aux;

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
      Lista<Integer> soluciones = listaSoluciones.pop();
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

  public static void permutacionesCadena(String cadena) {}

  public static void primosQueSuman(int S, int P, int N) {}

  private static boolean esPrimo(int n) {
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
        if (esPrimo(n)) {
          aux = true;
          //sumaCercana(lista, n);
        }
      } catch (Exception e) {
        System.out.println("Ingresa un numero valido");
      }
    } while (!aux);
  }

  public static void N_Reinas(int N) {}

  public static void main(String[] args) {
    if (args.length > 1) {
      pedirDatosSuma(args);
    }
  }
}
