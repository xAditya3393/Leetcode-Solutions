/*

Intuition
for any given index - array[index] gives the max steps we can take 
in the window [index, index + array[index]] - we need the find an index through which we can take the maximum steps
With each iteration the current window keeps shifting from end of previous window to max steps possible


for ex - [3,4,1,0,1,1]
1. current window - [0, 3], maxStepIndex = 1(4 steps)
2. current window - [3, 5] -> no need to check maxStepIndex as the current window reaches the end

Time - O(n), subsequent windows never overlap
Space - O(1)


*/



class Solution {
    public int jump(int[] nums) {
        int windowMax = 0, windowStart = 0, windowEnd = 0, result = 0;

        while(windowEnd < nums.length-1){

            windowStart = windowEnd;
            windowEnd = Math.min(windowMax + nums[windowMax], nums.length-1);
            result++;

            if(windowEnd == nums.length-1) break;

            for(int windowIndex = windowStart; windowIndex<= windowEnd; windowIndex++){

                if(windowMax + nums[windowMax] < nums[windowIndex] + windowIndex){
                    windowMax = windowIndex;
                }

            }
        }

        return result;
    }
}