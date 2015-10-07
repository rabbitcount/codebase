package com.ocelot.codebase.sort;

public class CommonUtils {

    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * is first less than second
     * @param first
     * @param second
     * @param <AnyType>
     * @return true: first less than second
     */
    public static <AnyType extends Comparable<? super AnyType>> boolean isLessThan(AnyType first, AnyType second){
        return first.compareTo(second) < 0;
    }

    /**
     * is first larger than second
     * @param first
     * @param second
     * @param <AnyType>
     * @return true: fist larger than second
     */
    public static <AnyType extends Comparable<? super AnyType>> boolean isLargerThan(AnyType first, AnyType second){
        return first.compareTo(second) > 0;
    }
}
