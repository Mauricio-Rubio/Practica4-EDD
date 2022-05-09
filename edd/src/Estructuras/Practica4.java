package edd.src.Estructuras;
public class Practica4 {
    public static void main(String[] args) {
        ArbolAVL miArbolito = new ArbolAVL<>();
        miArbolito.add(5);
        miArbolito.add(4);
        miArbolito.add(11);
        miArbolito.add(2);
        miArbolito.add(6);
        
        System.out.println("Mi arbolito \n"+miArbolito);
        System.out.println("La raiz es: "+miArbolito.raiz);
        System.out.println("Altura de la raiz es: "+miArbolito.raiz.altura());
    }
}