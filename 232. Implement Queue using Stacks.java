/*
 **Brute Force**
 Have two stacks  
 First stack - holds all the incoming elements 
 Second stack - for queue pop operations - used as an auxillary stack holding elements from pop operations on first element
 1. insert into first stack for incoming elements 
 For pop/peek operations 
 1. pop all elements from first stack 
 2. place all the popped elements into second stack 
 3. remove/read  top element from second stack  
 4. add all the removed back to first stack (to maintain input insertion order)
 
 

 **Observation** 
 for example: 
 first stack: 1->2->3->4(top of stack) => (after pop operations) =>  second stack: 4->3->2->1(top of stack) - desired pop order for a queue
 1. while having elements in the second stack, we can see, we have all the elements lined up in the desired order 
 as if it were elements in a queue - we don't need to insert the elements back into the first stack
 2. Since we are implementing an abstract data structure queue (queue is an interface and we use all deque java implementations for programming), we are only concerned with user functional operations and not on internal implementing -> We can have it implemented however we want as long as we satisfy the given requirements => maintaining order is unnecessary, as it is not a requirement for the current question!   
 3. Meanwhile for peek operation, we see that it is the always the top of the second stack!
  

 **Improved solution**
 Have two stacks
 1. insert into first stack for incoming elements 
 2. On pop operations, pop all elements from first stack - only if the second stack is empty!
 3. remove top element from second stack for pop operations/ read the top for peek operation
 
 Time - O(n), by amortized analysis 
 Space - O(n), if two stacks are accounted here
 
 
**Amortized analysis**

 Usually we see the worst case for an algorithm and give a looser big O bound which is practical in most cases, 
 but in some scenarios, the worst case doesn't occur enough times to determine the overall complexity for an algorithm.
 Therefore, we instead see how algorithm performs overall - by considering all algorithm operations over an input
 
 Time complexity here is dictated by populating second stack if it is empty
 
 Consider the following input scenarios 

I.

			Input: push,push,push,push,pop,pop,pop,pop....(total n operations) 
			 
			 After 4 push operations
			 first stack - 1->2->3->4 (after 4 operations)
			 second stack - (empty)

			 After first pop 
			 first stack - (empty) 
			 second stack - 4->3->2->1 => 4->3->2, 1(popped)

 
 **Time complexity**
 
 4 push + 4 pop operations
 *4x1 operations for push +  4x1 operation for removing n elements from first + 4x1 operation for adding n elements into second + 4x1 operations for pop*
 
 For n/2 push and n/2 pop operations 
 *(n/2) operations for push +  (n-4/4) operation for removing n elements from first + (n-4/4) operation for adding n elements into second + n/2 operations for pop*
 => Overall O(n)!

 Deriving number of operations for removing all elements from first and placing them in second
 
 *Happens in the following sequence - 4, 8, 12, ... n-4, n (arithmetic sequence, with common difference 4)
 nth term of an arithmetic sequence -> first element + (number of elements)difference = last element
 4 +(number of elements)4 = n => number of elements = (n-4)/4;*
 
 ---------------------------------------------------------------------------------------------------------------------------

II.

 
	 Input: push,pop,push,pop,push,pop,push,pop....(total n operations)

	 After first push
	 first stack - 1 
	 second stack - (empty)

	 After first pop 
	 first stack - (empty) 
	 second stack - 1 => (empty),  1(popped)


	 After second push
	 first stack - 2 
	 second stack - (empty)

	 After second pop 
	 first stack - (empty) 
	 second stack - 2 => (empty),  2(popped)

	 After third push
	 first stack - 3 
	 second stack - (empty)

	 After third pop 
	 first stack - (empty) 
	 second stack - 3 => (empty),  3(popped)

	 After forth push
	 first stack - 4 
	 second stack - (empty)

	 After forth pop 
	 first stack - (empty) 
	 second stack - 4 => (empty),  4(popped)

 **Time complexity**
 
 Each push and pop operation combined 
*1 operations for push +  1 operation for removing 1 element from first + 1 operation for adding 1 element into second + 1 operations for pop*
 For n/2 operations of push and pop  
 *4 + 4 + 4 + 4 .... (n/2)times  (n/2 because half push and half pop operations) 
 4(1+1+1 .... (n/2)times)!* 
 => Overall O(n)
 
 I would recommend try out a few more inputs to get a hang on it!
 
***For More on amortized analysis, do check out the video  on dynamic array by Gaurav Sen***
https://www.youtube.com/watch?v=MTl8djZFWE0 


*/

class MyQueue {

    private Deque<Integer> push, pop;
    
    public MyQueue() {
        this.push = new ArrayDeque<>();
        this.pop = new ArrayDeque<>();
    }
    
    public void push(int x) {
        this.push.addFirst(x);
    }
    
    public int pop() {
        if(this.pop.isEmpty()){
            while(!this.push.isEmpty()){
                this.pop.addFirst(this.push.removeFirst());
            }
        }
        
        return this.pop.removeFirst();
    }
    
    public int peek() {
        if(this.pop.isEmpty()){
            while(!this.push.isEmpty()){
                this.pop.addFirst(this.push.removeFirst());
            }
        }
        
        return this.pop.peekFirst();
    }
    
    public boolean empty() {
        return (this.push.isEmpty() && this.pop.isEmpty());
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */