package edd.src.Estructuras;

import java.util.Comparator;

public class ArbolBinarioOrdenado<T extends Comparable<T>>
  extends ArbolBinario {

    public ArbolBinarioOrdenado(Lista <T> lista, boolean isSorted){
        if(isSorted){
            buildSorted(lista);
        }else{
            buildUnsorted(lista);
        }
    }
  }
