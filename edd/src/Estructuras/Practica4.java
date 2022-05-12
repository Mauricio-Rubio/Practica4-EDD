package edd.src.Estructuras;

public class Practica4 {
    public static void main(String[] args) {
        ArbolAVL miArbolito = new ArbolAVL();
        miArbolito.add(50);
       // System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(47);
       // System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(57);
        //System.out.println("Mi arbolito BFS "+miArbolito.modBFS(miArbolito));
       // System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(46);
       // System.out.println("Mi arbolito BFS "+miArbolito.modBFS(miArbolito));
       // System.out.println("Mi arbolito \n"+miArbolito);
       // miArbolito.add(56);
        miArbolito.add(48);
        miArbolito.add(49);
       // miArbolito.add(-1);
        System.out.println("Mi arbolito JUJUY \n"+miArbolito);
       // miArbolito.rotarD(miArbolito.search(miArbolito.raiz,45));
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.revisarBalance(miArbolito.raiz);
       // miArbolito.rotarI(miArbolito.search(miArbolito.raiz,47));
       // miArbolito.rotarD(miArbolito.search(miArbolito.raiz,50));
        System.out.println("Mi arbolito \n"+miArbolito);
        /*miArbolito.add(15);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(17);
        System.out.println("Mi arbolito \n"+miArbolito);
       /* miArbolito.add(56);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.add(60);
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));
        miArbolito.add(61);
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.ptoString());
        miArbolito.add(89);
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));*/

       /*miArbolito.add(100);
       miArbolito.add(200);
       miArbolito.delete(61);
       miArbolito.delete(89);*/
       System.out.println("Mi arbolito \n"+miArbolito);
       System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));
       System.out.println("Mi arbolito \n"+miArbolito.ptoString());
       //miArbolito.add(-1);
      // miArbolito.add(0);
       System.out.println("Mi arbolito \n"+miArbolito);
       /*miArbolito.add(0);
       miArbolito.add(12);
       miArbolito.add(20);*/
      // miArbolito.add(200);
       /*miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,89));
       miArbolito.revisarBalance(miArbolito.raiz);
       miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,100));*/
     //  System.out.println("Mi arbolito \n"+miArbolito);
      // miArbolito.delete(61);
      // System.out.println("Mi arbolito \n"+miArbolito.modBFS(miArbolito));
      // miArbolito.revisarBalanceInv(miArbolito.search(miArbolito.raiz,200));
     // miArbolito.revisarBalance(miArbolito.raiz);
      // System.out.println("Mi arbolito \n"+miArbolito);
  }


}