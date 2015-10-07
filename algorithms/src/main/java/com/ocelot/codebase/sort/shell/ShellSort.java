package com.ocelot.codebase.sort.shell;

import static com.ocelot.codebase.sort.CommonUtils.*;

public class ShellSort {

    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] a){

        int N = a.length;
        int h = 1;
        while(h < N / 3) h = 3 * h + 1;
        while(h >= 1){
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && isLessThan(a, ))
            }
        }
    }
}
