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

        int j = 0;
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
    public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType[] a){

    }
}

