/*

Important questions:
what is the nature of the array - array is sorted (given here)
use of additional space [or] inplace required -> we can define list is inplace is required in method signature


Brute Force
We can have an result array (additional array) and keep inserting unique elements
Each time we insert a new element, we go through the elements in result array 

Time - O(n^2), where n is the number of unique elements 
Space - O(n)


Observation 
Given - array is sorted -> Make use of the property


Brute Force - Improvement
To achieve O(1) space 
1. we can have two pointers
One pointer marks the boundary of unique elements
Second pointer iterates through duplicate elements and finds the next unique element
2. left shift new element found 

Time - O(n^2), where n is the number of unique elements -> ith element takes i-1 left shifts
Space - O(1)


Observation:
The time complexity in brute force can be improved through two ways, when unique element is encounted:
1. swapping and shifting unique element boundary 
2. set the next element at the end of unique element boundary with new unique element found


Improvement
1. have two pointers
One pointer marks the boundary of unique elements
Second pointer iterates through duplicate elements and finds the next unique element
2. swap [or] set elements

Time - O(n), where n is the number of array elements 
Space - O(1)


*/

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;

        int uniqueBoundary = 1;

        for(int itr = 1; itr < nums.length; itr++){
            if(nums[itr] != nums[uniqueBoundary]) nums[uniqueBoundary++] = nums[itr];
        }

        return uniqueBoundary++;
    }
}