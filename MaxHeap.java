/**
 * 
 */
package a3;

import static org.junit.Assert.*;


/**
 * Name: Rahul Champaneria
 * Student ID: 017313296
 */
public class MaxHeap {

    //Initializing an Integer array called HeapArray
    private int[] HeapArray;

    //a function that returns the HeapArray
    public int[] getHeapArray() {
		return HeapArray;
	}

    //initializing a private intergers size and maxsize
	private int size; 
    private int maxsize; 

    //a final int called FRONT to set the front value in the HeapArray
    private static final int FRONT = 1; 

    //MaxHeap default function
    public MaxHeap(int maxsize) 
    {
        this.maxsize = maxsize; 
        this.size = maxsize;
        HeapArray = new int[maxsize]; 
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
        	HeapArray[i] = maxsize-i;
        }
    } 
  
    // Return the index of the parent of the node currently at pos 
    private int parent(int pos) 
    { 
        return (pos / 2); 
    } 
  
    // Return the index of the leftchild of the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
    private void maxHeapify(int i, int n) 
    {
        //setting the value of the nodes through heapify method for right and left nodes
        int left = leftChild(i);
        int right = rightChild(i);

        //setting the current largest value of the method
        int largest = i;

        //the heapify method to compare left and right nodes
        if(left < n && HeapArray[left] > HeapArray[largest]){
            largest = left;
        }
        if(right < n && HeapArray[right] > HeapArray[largest]){
            largest = right;
        }

        //if the largest value is not the same as i, swap the values and recursively call maxHeapify
        if(largest != i){
            swap(i, largest);
            maxHeapify(largest, n);
        }
    }

    //building a MaxHeap using the recursive method for maxHeapify
    public void buildMaxHeap() {
        for(int i = (size/2)-1; i >= 0; i--){
            maxHeapify(i,size);
        }
    }

    //Sorting the heap using the buildMaxHeap function and then using maxHeapify to sort the heap
    public void sort() {
        buildMaxHeap();
        for(int i  = size-1; i >=1; i--){
            swap(0, i);
            size = i;
            maxHeapify(0, size);
        }
    }

    public void minSum(){
        int C = HeapArray[0] + HeapArray[1];
        System.out.println("The minimum sum is: ");
        String message = String.format("%d + %d = %d",HeapArray[0], HeapArray[1], C);
        System.out.println(message);
    }
    
    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds) 
    { 
        int tmp; 
        tmp = HeapArray[first]; 
        HeapArray[first] = HeapArray[seconds]; 
        HeapArray[seconds] = tmp; 
    } 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 30;
		MaxHeap heap = new MaxHeap(SIZE);
		heap.sort();
		int[] heapArr = heap.getHeapArray();
		assertEquals(heapArr[0], 0);
		assertEquals(heapArr[1], 1);
		assertEquals(heapArr[2], 2);
		assertEquals(heapArr[SIZE-1], SIZE-1);
		assertEquals(heapArr.length, SIZE);
        heap.minSum();
	}

}
