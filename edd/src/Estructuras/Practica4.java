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

        /*miArbolito.add(46);
        miArbolito.add(47);
        miArbolito.add(48);*/

       /* miArbolito.insert(miArbolito.raiz,57);
        miArbolito.insert(miArbolito.raiz,45);
        miArbolito.insert(miArbolito.raiz,46);
       /* miArbolito.insert(miArbolito.raiz,5);
        miArbolito.insert(miArbolito.raiz,13);
        miArbolito.insert(miArbolito.raiz,47);*/
      /* System.out.println( miArbolito.revisarBalance(miArbolito.raiz));*/
       
        //System.out.println("El vertice es: " + miArbolito.search(miArbolito.raiz,4).toString());
//
      //  miArbolito.rotarI(miArbolito.search(miArbolito.raiz, 57));
       // System.out.println("Mi arbolito \n"+miArbolito);
       /* miArbolito.delete(57);

>>>>>>> 01a9818870d98138c924da0518bf63a1bf42e14d
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("Mi arbolito \n"+miArbolito.revisarBalance(miArbolito.raiz));
        
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
        System.out.println("La raiz es: "+miArbolito.raiz);*/
       // System.out.println("Altura de la raiz es: "+miArbolito.altura());
        //miArbolito.actualizarAlturas(miArbolito.raiz);
       /* System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.delete(4);
        System.out.println("Elimino a 4");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);
        miArbolito.delete(5);
        System.out.println("Elimino a 5");
       // miArbolito.insert(miArbolito.raiz, 0);
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(11);
        System.out.println("Elimino a 11");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(7);
        System.out.println("Elimino a 7");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(9);
        System.out.println("Elimino a 9");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        //miArbolito.insert(miArbolito.raiz,15);

        miArbolito.delete(13);
        System.out.println("Elimino a 13");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(57);
        System.out.println("Elimino a 11");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(91);
        System.out.println("Elimino a 91");
        //miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(0);
        System.out.println("Elimino a 0");
       // miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

       /* miArbolito.delete(0);
        System.out.println("Elimino a 0");
        miArbolito.actualizarAlturas(miArbolito.raiz);
        System.out.println("Mi arbolito \n"+miArbolito);

        miArbolito.delete(5);
        System.out.println("Elimino a 5");*/
        /*miArbolito.insert(miArbolito.raiz, 0);
        miArbolito.actualizarAlturas(miArbolito.raiz);*/


        //System.out.println("La raiz es: "+miArbolito.raizAVL);
    }
}