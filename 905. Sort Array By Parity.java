/*

    Refer to EPI 5.0 - Array Bootcamp Problem

*/

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        
        int evenIndex = 0, oddIndex = nums.length-1;
        
        while(evenIndex < oddIndex){
            
            if(nums[evenIndex] % 2 != 0){
                swap(nums, evenIndex, oddIndex--);
            }
            else evenIndex++;
            
        }
         
        return nums;
    }
    
    private void swap(int[] nums, int currentIndex, int swapIndex){
        int currentNum = nums[currentIndex];
        nums[currentIndex] = nums[swapIndex];
        nums[swapIndex] = currentNum;
    }
}