package edd.src.Estructuras;

public class Practica4 {

  public static void main(String[] args) {
    ArbolAVL miArbolito = new ArbolAVL();
    miArbolito.add(49);

    System.out.println("Mi arbolito \n" + miArbolito);

    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(45);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(44);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(100);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(0);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(83);

    System.out.println("Mi arbolito \n" + miArbolito);

    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(590);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.revisarBalance(miArbolito.raiz);
    miArbolito.add(2);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(9);
    System.out.println("Mi arbolito \n" + miArbolito);
    //System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.revisarBalance(miArbolito.raiz);
  }
}
