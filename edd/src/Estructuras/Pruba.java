package edd.src.Estructuras;

import java.io.*;
import java.util.*;

public class Pruba {

  // vector to store prime
  // and N primes whose sum
  // equals given S
  static ArrayList<Integer> set = new ArrayList<Integer>();
  static ArrayList<Integer> prime = new ArrayList<Integer>();

  static Lista<Integer> numerosPrimos;
  static Lista<Integer> combinaciones = new Lista<>();

  // function to check
  // prime number
  static boolean isPrime(int n) {
    // square root of x
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

  // function to display N
  // primes whose sum equals S
  static void display() {
    System.out.println("Solucion");
    System.out.println(combinaciones);
    //System.out.println();
  }

  static void display2() {
    int length = prime.size();
    for (int i = 0; i < length; i++) System.out.print(prime.get(i) + " ");
    System.out.println();
  }

  // function to evaluate
  // all possible N primes
  // whose sum equals S
  static void primeSum(int total, int N, int S, int index) {
    // if total equals S
    // And total is reached
    // using N primes
    if (total == S && combinaciones.size() == N) {
      // display the N primes
      display();
      return;
    }

    // if total is greater
    // than S or if index
    // has reached last
    // element
    // or if set size reached to maximum or greater than maximum
    if (
      total > S || index == numerosPrimos.size() || combinaciones.size() >= N
    ) return;

    // add prime.get(index)
    // to set vector
    //System.out.print("combinaciones 1 -->");
    //display();
    combinaciones.add(numerosPrimos.get(index));
    //System.out.print("combinaciones 2 -->");
    //display();
    // include the (index)th
    // prime to total
    //System.out.println("Indice "+prime.get(index));
    primeSum(total + numerosPrimos.get(index), N, S, index + 1);

    // remove element
    // from set vector
    /*for (int a : set) {
      System.out.println("este es set -->"+a);
    }*/
    combinaciones.eliminarIndice0(combinaciones.size() - 1);
    /*for (int a : set) {
      System.out.println("este es set 2.0 -->"+a);
    }*/
    // exclude (index)th prime
    primeSum(total, N, S, index + 1);
  }

  // function to generate
  // all primes
  static void allPrime(int N, int s, int nPrimo) {
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
    System.out.println("Tamiz eratostenes");
    //prime = numerosPrimos;
    primeSum(0, N, s, 0);
  }

  // Driver Code
  public static void main(String args[]) {
    int S = 28, N = 2, P = 7;
    allPrime(N, S, P);
    /*ArrayList<Integer> miArray = new ArrayList<>();
    Lista<Integer> miLista = new Lista<>();
    for (int i = 0; i < 10; i++) {
      miLista.add(i);
      miArray.add(i);
    }
    System.out.println("Lista " + miLista);
    System.out.println("Array " + miArray);

    for (int i = 0; i < 5; i++) {
      System.out.println("Lista " + miLista.get(i));
      System.out.println("Array " + miArray.get(i));
    }

    for (int i = 0; i < 5; i++) {
      System.out.println(i);
      miArray.remove(i);
      miLista.remove(i);
    }
    System.out.println("Lista " + miLista);
    System.out.println("Array " + miArray);*/
  }
}
