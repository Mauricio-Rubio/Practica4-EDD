package edd.src.Estructuras;

public class Practica4 {

  public static void main(String[] args) {
    ArbolAVL miArbolito = new ArbolAVL();
    miArbolito.add(50);
    miArbolito.add(57);
    miArbolito.add(47);
    miArbolito.add(46);
    miArbolito.add(48);
    System.out.println("Mi arbolito mamalon \n" + miArbolito);
    miArbolito.add(49);
    //miArbolito.add(45);
    //miArbolito.add(44);
    System.out.println("Mi arbolito mamalon \n" + miArbolito);
    //miArbolito.add(48);
    //miArbolito.add(44);
    //miArbolito.add(43);
    /* miArbolito.insert(miArbolito.raiz,57);
        miArbolito.insert(miArbolito.raiz,45);
        miArbolito.insert(miArbolito.raiz,46);
       /* miArbolito.insert(miArbolito.raiz,5);
        miArbolito.insert(miArbolito.raiz,13);
        miArbolito.insert(miArbolito.raiz,47);*/
    //System.out.println("Mi arbolito mamalon \n" + miArbolito);
    //System.out.println(miArbolito.revisarBalance(miArbolito.raiz));
    //System.out.println("Mi arbolito \n" + miArbolito);
    //System.out.println("Hijo izquierdo: "+miArbolito.raiz.izquierdo);
    //System.out.println("El vertice es: " + miArbolito.search(miArbolito.raiz,4).toString());
    //miArbolito.rotarI(miArbolito.search(miArbolito.raiz, 45));
    //miArbolito.rotarI(miArbolito.search(miArbolito.raiz, 50));
    //miArbolito.delete(57);
    //System.out.println("Mi arbolito \n"+miArbolito.revisarBalance(miArbolito.raiz));
    //System.out.println("Mi arbolito \n"+miArbolito);

    /* miArbolito.insert(miArbolito.raiz,0);
        miArbolito.insert(miArbolito.raiz,1);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(2);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.delete(-2);
        System.out.println("Mi arbolito \n"+miArbolito);*/

    ///SEPARADOR
    /*  miArbolito.insert(miArbolito.raiz,7);
        miArbolito.insert(miArbolito.raiz,9);

        miArbolito.insert(miArbolito.raiz,57);
       
        miArbolito.insert(miArbolito.raiz,91);*/

    /* System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.ptoString());
        miArbolito.add(89);
        System.out.println("Mi arbolito \n"+miArbolito);

       miArbolito.add(100);
       miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,89));
       System.out.println("Mi arbolito \n"+miArbolito);*/
  }
}
