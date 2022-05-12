package edd.src.Estructuras;

public class Practica4 {
    public static void main(String[] args) {
        ArbolAVL miArbolito = new ArbolAVL();
        miArbolito.add(50);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(57);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(45);

        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(58);
        System.out.println("Mi arbolito \n"+miArbolito);
       // miArbolito.add(56);
        miArbolito.add(59);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(56);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(60);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(61);
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.ptoString());
        miArbolito.add(89);
        System.out.println("Mi arbolito \n"+miArbolito);

       miArbolito.add(100);
       miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,89));
       System.out.println("Mi arbolito \n"+miArbolito);
  }


}