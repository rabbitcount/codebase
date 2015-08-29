package com.ocelot.codebase;

/**
 * [DSAAJ] BinaryHeap in chapter 6
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;    // Number of elemtns in heap
    private AnyType[] array;

    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public BinaryHeap(AnyType[] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap();
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed
     * @param x the item to insert.
     */
    public void insert(AnyType x){
        if(currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // Percolate up
        int hole = ++currentSize;
        // 3th code start
        // put a reference to the inserted item in position 0 in order to make the loop terminate.
        // position 0 is used for test
        for(array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        // 3th code end
        // 2th code start
        // with an explicit test, e.g. hole > 1
        /* for(; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2]; */
        // 2th code end
        array[hole] = x;
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException, if empty.
     */
    public AnyType deletMin(){
        if(isEmpty())
            throw new UnderflowException();

        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin(){
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    /**
     * Internal method to percolate down in the heap
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown(int hole){
        int child;
        AnyType tmp = array[hole];

        // currentSize--including
        for(; hole * 2 <= currentSize; hole = child){
            child = hole * 2;
            // get the smaller one between left and right child.
            // A frequent implementation error in heaps occurs when there are an even number of
            //   elements in the heap, and the one node that has only one child is encountered.
            if(child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;
            if(array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        // means: it's not leaf.
        for(int i = currentSize / 2; i > 0; i-- )
            percolateDown(i);
    }

    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }
}
