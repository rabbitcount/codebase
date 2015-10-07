package com.ocelot.codebase.sort.quicksort;

import static com.ocelot.codebase.sort.CommonUtils.*;

/**
 * 参考 Data Structures and Algorithm Analysis in Java --- Chapter 7.7 QuickSort
 *
 * 关注[仅简单列出]:
 *************************************************************
 * 1. 选择枢纽元
 *   目标 --> 接近选出N个数的中位数,使数组被平均分割.
 *   [x] 选择第一个元素作为枢纽元\选取前两互异元素关键字较大者:
 *       输入是预排序或反序时,将花费二次元时间,实际却没干任何事;
 *   [x] 随机选取枢纽元
 *       一种安全策略;
 *       但随机数生成开销较大,无法降低算法其余部分的平均运行时间;
 *   [v] 三数中值分割法(可以消除预排序输入的最坏情形):
 *       使用左端,右端和中心位置上德三个元素的中值作为枢纽元;
 *************************************************************
 * 2. 划分不相交集合(将小元素划分到数组的左边,将大元素划分到数组右边,小和大相对于枢纽元而言)
 *   以下是一种证明可行的分割策略
 *   1) 通过将枢纽元与最后的元素交换,使得枢纽元离开要被分割的数据段;
 *   2) 当i在j的左边时
 *      NOTE: 将i右移,移过那些小于枢纽元的元素; --- 停止时,i指向>=pivot的元素
 *      NOTE: 将j左移,移过那些大于枢纽元的元素; --- 停止时,j指向<=pivot的元素
 *      当i和j停止时,i指向一个大元素而j指向一个小元素;
 *   3-1) 如果i在j的左边,
 *        那么将两个元素互换(效果为将大元素推向右边而把小元素推向左边)
 *   3-2) 当i在j的右边(i和j交错后)
 *        将枢纽元与i所指向的元素交换
 *      <Q> 为什么枢纽元与i交换,而不是枢纽元与j交换
 *      <A> i指向小于等于枢纽元的元素,j指向大于等于枢纽元的元素
 *          当i与j交错时 --> ... j(<= pivot), i(>= pivot) ... pivot
 *          pivot与i交换 --> ... pivot, i(>= pivot) ... j(<= pivot) --> 不符合排序
 *          pivot与j交换 --> ... j(<= pivot), pivot ... i(>= pivot) --> 与排序要求相符
 *************************************************************
 * 3. 如何处理与枢纽元相等的元素
 *   <Q> 当i遇到一个等于枢纽元的元素,及j遇到一个等于枢纽元的元素两种情况下,是否应该停止
 *   i和j的处理策略应该相同,否则可能会造成分割偏向一边
 *   考虑极端情况,所有元素相同
 *   [x] i和j中,一个停止,另一个不停止
 *       分割子数组不均匀,偏向一侧
 *   [x] i和j都不停止
 *       根据现有实现策略,会产生两个非常不均衡的子数组;
 *       在所有关键字都相同的情况下,运行时间将为O(N2);
 *   [v] i和j都停止
 *       在相等的元素间将有很多次交换
 *       --> 虽然无意义,但是最终i和j将在中间交错,
 *           当枢纽元被替换时,将建立两个大小基本相等的子数组
 *       --> 根据归并排序的分析,运行时间为 O(NlogN)
 *************************************************************
 *
 */
public class QuickSort {

    private static final int CUTOFF = 10;

    /**
     * Quicksort algorithm.
     * @param a an array of Comparable items.
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    /**
     * return median of left, center, and right
     * Order these and hide the pivot
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    private static <AnyType extends Comparable<? super AnyType>>
        AnyType median3(AnyType[] a, int left, int right){
        int center = (left + right)/2;
        if(isLessThan(a[center], a[left]))
//        if(a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if(isLessThan(a[right], a[left]))
//        if(a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if(isLessThan(a[right], a[center]))
//        if(a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Use median-of-three partitioning and a cutoff of 10.
     *
     * pivot: create a pivot with median3
     * for()
     *   i: stop when larger than pivot
     *   j: stop when smaller than pivot
     *   if(i < j) swap
     *   if(i > j) break
     * and then exchange pivot and i.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray. including
     * @param right the right-most index of the subarray.
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a, int left, int right){
        if(left + CUTOFF <= right){
            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for(;;){
                while(isLessThan(a[++i], pivot)) { } // i: stop when finding the one which is larger than pivot
                while(isLargerThan(a[--j], pivot)) { } // j: stop when finding the one which is less than pivot
//                while(a[++i].compareTo(pivot) < 0) { } // i: stop when finding the one which is larger than pivot
//                while(a[--j].compareTo(pivot) > 0) { } // j: stop when finding the one which is less than pivot
                if(i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            // Restore pivot
            swapReferences(a, i, right - 1);

            quicksort(a, left, i - 1);  // Sort small elements
            quicksort(a, i + 1, right); // Sort large elements
        }
        else // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }

    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
        void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for(j = p; j > left && isLessThan(tmp, a[j - 1]); j--)
//            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

}
