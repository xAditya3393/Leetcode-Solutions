/*

Important Question
Confirm the input -> the values it can take 


Book states input to be non-negative decimal integer array of digits


Brute Force
We can convert the given arrays to integers -> add one -> convert back to array by extracting digits


Observation
Notice how we can have an issue of overflow with addition


Implementation - carry over
We can solve the problem of overflow by performing the operation on the array without conversion
- If the addition results is value less than 10 -> we add and return 
- If the addition results is value equal to 10 -> update to zero and carry over to next value

Time - O(n), where n is the number of digits
Space - O(n), if new array as part of result is accounted   

    Implementation
    - Pre increments the current index to see if the value is greater than 10 
      - if yes, we set it zero and continue
      - if no, we have our result and return
    - If we go through the entire input array, all the inputs must be array input of 9's
      - we create a new array with one at its most significant bit

*/




class Solution {
    public int[] plusOne(int[] digits) {
    
        for(int currentIndex=digits.length-1; currentIndex >=0; currentIndex--){
            if(++digits[currentIndex] < 10){
                return digits;
            }
            digits[currentIndex] = 0;
        }
    
        int[] overflowResult = new int[digits.length+1];
        overflowResult[0] = 1;
        return overflowResult;
    }
}