package com.ocelot.codebase;

/**
 * Created by gaoxiang on 15/8/25.
 */
public class Sort {

    /**
     * Simple insertion sort.
     * @param a an array of Comparable items
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a){

        int j;
        for(int p = 1; p < a.length; p++){
            AnyType tmp = a[p];
            for(j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    /**
     * Shellsort, using Shell's (poor) increment
     * @param a
     * @param <AnyType>
     */
    @Deprecated
    public static <AnyType extends Comparable<? super AnyType>> void shellsortWithPoorShellIncrement(AnyType[] a){
        int j;

        for(int gap = a.length/2; gap > 0; gap /= 2)
            for(int i  = gap; i < a.length; i++){
                AnyType tmp = a[i];
                for(j = i; j > 0 && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }


}

