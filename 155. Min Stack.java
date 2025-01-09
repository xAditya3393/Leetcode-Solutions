/*

Brute force 
Keep the track of minimum element as we get input, but each time we pop()
we need to scan the entire input to modify minimum
Time O(n^2) space O(n)

Improvement - Track minimum elements separately

Messy version - Maintain one stack for input & one stack for tracking minimum element
Note: We need to include duplicate minimums as well 
Disadvantage - track two stacks at the same time and 
also be aware of the duplicate elements which can also be minimum - edge case
Time O(n) Space O(n)

Refined Version - Maintain a single stack -> create a inner class and track min per element
Advantage - eliminates need to track duplicate elements and track single stack throughout
Time O(n) Space O(n) 
*/


//Messy version
class MinStack {
    
    private Deque<Integer> inputStack;
    private Deque<Integer> minStack;
    
    public MinStack() {
        inputStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }
    
    public void push(int val) {
        
        inputStack.push(val);
        //equal operator includes duplicates and eliminates edge case with this approach
        if(minStack.isEmpty() || minStack.peek() >= val) minStack.push(val);
    }
    
    public void pop() {
        int currentPoped = inputStack.pop();
        if(currentPoped == minStack.peek()) minStack.pop();
    }
    
    public int top() {
        return inputStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}


//Refined Version
class MinStack {

    //Notice how this class is private and not also static
    //For more, refer - https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class
    //https://stackoverflow.com/questions/20468856/is-it-true-that-every-inner-class-requires-an-enclosing-instance
    
    private class cacheMinPerElement{
        int element;
        int minimumValue;
        
        cacheMinPerElement(int e, int min){
           element = e;
           minimumValue = min; 
        }
    }
    
    private Deque<cacheMinPerElement> lookup; 
    
    public MinStack() {
        lookup = new LinkedList<cacheMinPerElement>();
    }
    
    public void push(int val) {
        
        int minimum = val;
        
        if(!lookup.isEmpty()) minimum = Math.min(val, lookup.peek().minimumValue);
        
        lookup.push(new cacheMinPerElement(val, minimum));
    }
    
    public void pop() {
        lookup.pop();
    }
    
    public int top() {
        return lookup.peek().element;
    }
    
    public int getMin() {
        return lookup.peek().minimumValue;
    }
}
