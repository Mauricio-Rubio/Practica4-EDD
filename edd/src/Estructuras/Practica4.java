package edd.src.Estructuras;

public class Practica4 {

  public static void main(String[] args) {
    ArbolBinarioBusqueda miArbolito;
    Lista miLista = new Lista();
    for (int i = 0; i < 10; i++) {
       miLista.add(i); 
    }
    /**
     * Pruebas para delete
     */
    miArbolito = new ArbolBinarioBusqueda(miLista, true);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    System.out.println("Delete 4");
    miArbolito.delete(4);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    System.out.println("Delete 2");
    miArbolito.delete(2);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    System.out.println("Delete 5");
    miArbolito.delete(5);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    
    miArbolito.insert(miArbolito.raiz,2);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    
    System.out.println("Delete 3");
    miArbolito.delete(3);
    System.out.println("Este es mi arbolito \n"+miArbolito);
    /*miArbolito.add(10);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(8);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(12);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(7);
    System.out.println("Mi arbolito \n" + miArbolito);
    // miArbolito.add(13);
    miArbolito.add(9);
    System.out.println("Mi arbolito \n" + miArbolito);

    //System.out.println( miArbolito.rotarDerecha(miArbolito.search(miArbolito.raiz,10)).toString());
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.insert(miArbolito.raiz, 47);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(44);
    //miArbolito.rotarIzquierda(miArbolito.search(miArbolito.raiz,44));
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(48);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(120);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(110);
    System.out.println("Mi arbolito \n" + miArbolito);
    System.out.println("Mi arbolito \n" + miArbolito.modBFS(miArbolito));
    miArbolito.add(105);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(108);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(109);
    System.out.println("Mi arbolito \n" + miArbolito);
    System.out.println("Mi arbolito \n" + miArbolito.modBFS(miArbolito));
    miArbolito.add(-105);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(13);
    System.out.println("Mi arbolito \n" + miArbolito);
    System.out.println("Mi arbolito \n" + miArbolito.modBFS(miArbolito));
    miArbolito.add(19);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(-23);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(-3);
    System.out.println("Mi arbolito \n" + miArbolito);
    System.out.println("Mi arbolito \n" + miArbolito.modBFS(miArbolito));
    miArbolito.add(-15);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(-19);
    System.out.println("Mi arbolito \n" + miArbolito);
    miArbolito.add(5);
    System.out.println("Mi arbolito \n" + miArbolito);*/
    /* miArbolito.delete(-105);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(108);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(12);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(44);
      System.out.println("Mi arbolito \n" + miArbolito);

      miArbolito.delete(8);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(-15);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(47);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(-3);
      System.out.println("Mi arbolito \n" + miArbolito);
      /*
      miArbolito.add(10);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(75);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(178);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(1099);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(-15);
     /* System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(178);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.add(191);
      System.out.println("Mi arbolito \n" + miArbolito);
     /* miArbolito.delete(40);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(109);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(46);
      System.out.println("Mi arbolito \n" + miArbolito);
      miArbolito.delete(110);
      System.out.println("Mi arbolito \n" + miArbolito);
     /* miArbolito.add(10);
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(8);
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(15);
      System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(12);
      System.out.println("Mi arbolito \n"+miArbolito);
     // miArbolito.add(56);
      miArbolito.add(17);
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(11);*/
    /* System.out.println("Mi arbolito \n"+miArbolito);/
     miArbolito.rotarD(miArbolito.search(miArbolito.raiz,15));
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.rotarI(miArbolito.search(miArbolito.raiz,10));
      System.out.println("Mi arbolito \n"+miArbolito);*/

    /* System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(56);
      System.out.println("Mi arbolito \n"+miArbolito);
      miArbolito.add(60);
      System.out.println("Mi arbolito \n"+miArbolito);
      System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));
      miArbolito.add(61);
      System.out.println("Mi arbolito \n"+miArbolito);
      System.out.println("Mi arbolito \n"+miArbolito.ptoString());
      miArbolito.add(89);
      System.out.println("Mi arbolito \n"+miArbolito);
      System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));

     miArbolito.add(100);
     miArbolito.add(0);
     miArbolito.add(-1);
     miArbolito.add(2);
     miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,89));
     miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,59));
     System.out.println("Mi arbolito \n"+miArbolito);*/
  }
}
