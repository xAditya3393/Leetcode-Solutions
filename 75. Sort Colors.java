
/*

Colors - Red(0)- start, White(1) - middle, Blue(2) - end

Brute Force 
Have extra space - arrays each with less, equal and greater elements  
iterate and assemble


Improvement - Two Pass
Taking partition function into account for quick sort - Sorting-Algorithms - S1
Since we know the elements, we can choose pivot 1 here 
first iteration - bring all the elements less than pivot to begining
Second iteration - bring all the elements greater than pivot to end 
The resulting array will have all 1's in the middle

Notice how this can be applied for any general pivot - with athe following additional steps
For any random pivotElement and pivotIndex:
- choose a random index as pivotIndex and element here is pivotElement
- make initial swap to move pivotElement to end 

Time - 2n, n for each pass -> O(n)
Space - O(1)

Notice how we can have the same implementation 
by taking into consideration array property where we can operate both ends efficiently
- we can have one pointer marking the end of the desired list which other iterates the entire array
- two passes, one to place all the smaller & second pass for all the larger elements

Improvement - One Pass
Instead of having two pass, we can combine in one pass by having two pointers 
with added implementation complexity

Time - n , only one pass -> O(n)
Space - O(1)

*/


/*

Two Pass solution 
First pass brings all zero's to the begining of the array
Second pass brings all the two's to the end of the array

Time - O(n)
Space - O(1)

*/

class Solution {
    public void sortColors(int[] nums) {
        
        //Bring all zero's to the begining of the array
        int smallIndex = 0;

        for(int i = 0; i<nums.length; i++){
            if(nums[i] < 1){
                swap(smallIndex++, i, nums);
            }
        }


    
        //Bring all two's to the end of the array
        int largeIndex = nums.length-1;

        for(int i = nums.length-1; i>=0; i--){
            if(nums[i] > 1){
                swap(largeIndex--, i, nums);
            }
        }
        
        
    }

    public void swap(int currentIndex, int swapIndex, int[] input){
        int temp = input[currentIndex];
        input[currentIndex] = input[swapIndex];
        input[swapIndex] = temp;
    }
}


/*

One Pass solution 

We maintain following three pointers:
zeroIndex - before this index all the elements are zero, also marks the last index where the element is zero
oneIndex - marks the index where every element between zeroIndex and this index are one, also used as iterator for processing the current element 
twoIndex - marks all elements equal to two between oneIndex and twoIndex

Time - O(n)
Space - O(1)

*/

class Solution {
    public void sortColors(int[] nums) {
        
        int zeroIndex = -1;
        int oneIndex = 0;
        int twoIndex = nums.length;
        
        while(oneIndex < twoIndex){
            
            if(nums[oneIndex] == 0){
                swap(nums, oneIndex++, ++zeroIndex);
            }
            else if(nums[oneIndex] == 1){
                oneIndex++;
            }
            else if(nums[oneIndex] == 2){
                swap(nums, oneIndex, --twoIndex);
            }
            
        }
        
    }
    
    private void swap(int[] nums, int currentIndex, int swapIndex){
        int currentNum = nums[currentIndex];
        nums[currentIndex] = nums[swapIndex];
        nums[swapIndex] = currentNum;
    }
}