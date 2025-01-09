/*
Start - starting element of the queue
End - index for the next incoming element, end-1 is the last index of the queue
numberOfElements - storing the total number of elements simplifies empty and full checks
as their computation with start and end (incrementing over the size - actual index = index%size) becomes difficult


Resizing implementation - resizing can happen when already full queue has a enqueue operation
keep a variable scalingfactor for new array capacity 
Collections.rotate(array, -start) - will rotate the array
see documentation - https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#rotate(java.util.List,%20int)
Array.copyOf(existingArray, newLenght) - will copy the old values to new array of length newLenght
see documentation - https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOf(T[],%20int) 

Thread safe implementation 
When the same code gets accessed by multiple threads, there may be scenarios 
where data might get updated incorrectly due to race conditions
Locks - https://www.baeldung.com/java-concurrent-locks
ReentrantLock - https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html

*/

class MyCircularQueue {

    private int start, end, size, numberOfElements;
    private int[] elements;
    
    public MyCircularQueue(int k) {
        elements = new int[k];
        start = 0;
        end = 0;
        size = k;
    }
    
    public boolean enQueue(int value) {
        
        if(!isFull()){
            elements[end%size] = value;
            end++;
            numberOfElements++;
            return true;
        }
        
        return false;
    }
    
    public boolean deQueue() {
        if(!isEmpty()) {
            start++;
            numberOfElements--;
            return true;
        }
        
        return false;
    }
    
    public int Front() {
        return (this.isEmpty()) ? -1 : elements[start%size];
    }
    
    public int Rear() {
        return (this.isEmpty()) ? -1 : elements[(end-1)%size];
    }
    
    public boolean isEmpty() {
        return numberOfElements == 0;
    }
    
    public boolean isFull() {
        return numberOfElements == size;
    }
}
