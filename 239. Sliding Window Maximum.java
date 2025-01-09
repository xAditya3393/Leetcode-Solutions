/*
Brute Force 
Have two pointers -> scan all the numbers in the window -> add max of window to result
Time - O(n*k), where each element will be compared k times 
Space - O(1) (excluding output array)

Observation - notice how we repeatedly scan all the numbers. 
Following are the conditions which occur upon a new element encountered to be inserted into the window:
1. new element > current max -> all the elements - current max and other less elements are not eligible in future for being max => flush everthing out
2. new element <= current max -> the new element though less than current max, may be eligible for max when current max is no longer in the window => store potential candidates
Upon storing the elements based on the above condition, the elements in the queue will be a decreasing sequence with first element being greatest 


Improvement - have a queue tracking potential candidates
Upon two observations, the following is the algorithm for efficently tracing the maximum element
1. store incoming element based on the condition
- if greater than last queue element => flush out all values(current max at beginning + all other candidates), 
new elements becomes the max for the rest of the window unless greater element is encountered later
- if smaller than last queue element => keep it, may be potential candidate for max later 
2. call a cleanup function which flushes out max candidates based on window size
3. store the result for each window size
Time - O(n), each element is compared exactly once 
Space - O(k), max elements in queue at anytime is the size of the window (excluding output array)

*/

class Solution {
    
    private Deque<Integer> window;
    private int windowSize;
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        window = new LinkedList<Integer>();
        windowSize = k;
        
        List<Integer> result = new ArrayList<Integer>();
        
        
        for(int index = 0; index < nums.length; index++){
            decreasingQueue(nums, index); //maintain potential candidates
            cleanQueue(index); //cleanup candidates based on window size
            if(index >= k-1) result.add(nums[window.peekFirst()]); 
        }
        
        
        
        return resultArray(result);
    }

    private void decreasingQueue(int[] nums, int index){
    
        while(!window.isEmpty() && nums[window.peekLast()] < nums[index]){
            window.pollLast();
        }
            
        window.offerLast(index);
        
    }
    
    
    private void cleanQueue(int newIndex){
        
        int lastIndex = newIndex - (windowSize-1);
        
        while(window.peekFirst() < lastIndex){
            window.pollFirst();
        }
    }
    
    
    private int[] resultArray(List<Integer> result){
        
        int[] finalResult = new int[result.size()];
        for(int i =0; i< result.size(); i++){
            finalResult[i] = result.get(i);
        }
        
        return finalResult;
    }
    
}