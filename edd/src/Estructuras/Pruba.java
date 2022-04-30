package edd.src.Estructuras;

import java.io.*;
import java.util.*;

public class Pruba {

  // vector to store prime
  // and N primes whose sum
  // equals given S
  static ArrayList<Integer> set = new ArrayList<Integer>();
  static ArrayList<Integer> prime = new ArrayList<Integer>();

  // function to check
  // prime number
  static boolean isPrime(int x) {
    // square root of x
    int sqroot = (int) Math.sqrt(x);

    // since 1 is not
    // prime number
    if (x == 1) return false;

    // if any factor is
    // found return false
    for (int i = 2; i <= sqroot; i++) if (x % i == 0) return false;

    // no factor found
    return true;
  }

  // function to display N
  // primes whose sum equals S
  static void display() {
    int length = set.size();
    for (int i = 0; i < length; i++) System.out.print(set.get(i) + " ");
    System.out.println();
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
    if (total == S && set.size() == N) {
      // display the N primes
      display();
      return;
    }

    // if total is greater
    // than S or if index
    // has reached last
    // element
    // or if set size reached to maximum or greater than maximum
    if (total > S || index == prime.size() || set.size() >= N) return;

    // add prime.get(index)
    // to set vector
    //System.out.print("combinaciones 1 -->");
    //display();
    set.add(prime.get(index));
    //System.out.print("combinaciones 2 -->");
    //display();
    // include the (index)th
    // prime to total
    //System.out.println("Indice "+prime.get(index));
    primeSum(total + prime.get(index), N, S, index + 1);

    // remove element
    // from set vector
    /*for (int a : set) {
      System.out.println("este es set -->"+a);
    }*/
    set.remove(set.size() - 1);
    /*for (int a : set) {
      System.out.println("este es set 2.0 -->"+a);
    }*/
    // exclude (index)th prime
    primeSum(total, N, S, index + 1);
  }

  // function to generate
  // all primes
  static void allPrime(int N, int S, int P) {
    // all primes less
    // than S itself
    for (int i = P + 1; i <= S; i++) {
      // if i is prime add
      // it to prime vector
      if (isPrime(i)) prime.add(i);
    }

    // if primes are
    // less than N
    if (prime.size() < N) return;
    System.out.println("Lenght "+prime.size());
    primeSum(0, N, S, 0);
  }

  // Driver Code
  public static void main(String args[]) {
    int S = 23, N = 3, P = 2;
    allPrime(N, S, P);
  }
}
