package com.ocelot.codebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoxiang on 15/8/25.
 */
public class QuickSort {

    /**
     * A FAKE QUICK SORT
     * Arbitrarily choose any item, and then form three groups: those smaller than the chosen item, those equal to the chosen item,
     * and those larger than the chosen item.
     * Recursively sort the first and third groups, and the concatenate the three groups.
     * The results guaranteed by the basic principles of recursion to be a sorted arrangement of the original list.
     * @param items
     */
    @Deprecated
    public static void sort(List<Integer> items){
        if(items.size() > 1){
            List<Integer> smaller = new ArrayList<>();
            List<Integer> same = new ArrayList<>();
            List<Integer> larger = new ArrayList<>();

            Integer chosenItem = items.get(items.size() / 2);
            for(Integer i: items){
                if(i < chosenItem)
                    smaller.add(i);
                else if(i > chosenItem)
                    larger.add(i);
                else same.add(i);
            }

            sort(smaller);
            sort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }

        // Classic Quick Sort
    }
}
