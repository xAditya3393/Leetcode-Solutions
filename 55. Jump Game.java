/*

Intuition:
As each index denotes the maximum step we can take, we might be tempted to take the first encounter maximum
for ex: for an array [3,4,1,0,1,1]
When we start with index 0 -> we can reach to index 3 -> with 0 steps on index 3 we can no longer go further 
we need to also evaluate if any index between 0-3 has a possibility for greater step count


Implementation
- go through each index and keep record of max step that can be reached, let it be maxStepIndex
- maxStepIndex updates if i+ array[i] > maxStepIndex -> this indicates we can take a larger step 
- We also need an additional condition to check if we can take the step at any current index before computing i+ array[i]
for ex: for an array [3,3,1,0,0,1]
at index 1 - max = 0+3 = 3
at index 2 - current = 3 + 1 = 4, which is valid because index 1 < max = 3 (this denotes the maximum possible step that can be taken)
We can only advance upto index 4 
Notice how at index 5 - current = 5 + 1 > max = 4 -> but this step can never be taken 

*/

class Solution {
    public boolean canJump(int[] nums) {
        int maxStepIndex = 0;
        int lastStep = nums.length-1;

        for(int currentStep = 0; currentStep <= maxStepIndex && maxStepIndex < lastStep; currentStep++){
            maxStepIndex = Math.max(maxStepIndex, currentStep + nums[currentStep]);
        }
        return maxStepIndex >= lastStep;
    }
}