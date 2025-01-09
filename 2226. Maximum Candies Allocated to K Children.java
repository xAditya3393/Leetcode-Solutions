/*

Given Conditions 
1. one child can have atmost one pile of candy
2. cannot merge two piles of candy
3. each child should have equal number of candies

Intuition 
Check the number of piles that can be obtained from the given input
- number of piles >= number of kids -> this allocation is possible 
- number of piles < number of kids -> this allocation is not possible
-> create a function which can validate this intuition


Observation
Given minimum allocation - 0 and maximum allocation - max, assume some number

Maximum can be calculated in two ways:
1. Total candies/Total number of kids 
Intuition - We can have maximum pile of total number of candies in the input
[2,8,3] can be [13] also!

2. Maximum pile in the input
Intuition - Each child can have atmost one pile of candy (given condition)



Time Complexity - Choosing a pile for validation +  Time for validating pile
1. Choosing a pile for validation 
-> two options here 
Going over every possible pile - linear complexity O(n) 
[or] 
Observe how the possible allocations are sorted -> Binary search to reduce search space! - Binary Seach O(logn)

2. Time for validating pile - involves calculating total piles for each input and adding them  -> O(n) - cannot be reduced





Brute Force - Since minimum number of candies per child can be 0, start with 1 pile of candy and see it can be alloted
Time - O(n^2)  Space - O(1) => causes TLE

Improved Solution - Do a binary search on possible allocation since they are sorted
Time - O(nlogn) Space - O(1)


Implementation Observations:

1. Break condition  
Always prefer left < right over left <= right
easier approach and intuitive of the two on break condition

2. Calculating mid

mid = (left + right + 1) / 2 Vs mid = (left + right) / 2

I. mid = (left + right) / 2 => to find first element valid
II. mid = (left + right + 1) / 2 => to find last element valid

Both can be implemented based on requirement, but first approach needs additional 
check on break condition  

when updating left and right pointers 
I. left - mid+1 / right - mid -> on exit answer can be left [or] left-1
II. left - mid / right - mid-1 -> on exit answer is left


For more information, please refer:
1. https://leetcode.com/problems/maximum-candies-allocated-to-k-children/discuss/1908888/JavaC%2B%2BPython-Binary-Search-with-Explanation
2. https://leetcode.com/problems/maximum-candies-allocated-to-k-children/discuss/1908797/Binary-Search-Solution-with-Explanation-or-Java-or-O(n-log(max(candies)))


*/


//Improved Solution, with max = Maximum pile in the input & Implementation approach II
class Solution {
    public int maximumCandies(int[] candies, long k) {
        
        int maxCandiesPossible = 0;
        for(int candyPile : candies){
            //Intuition - all the camdies in one pile
            //maxCandiesPossible += candyPile;
            
            //Intuition - since one kid can have atmost one pile, max will be maximum possible pile
            maxCandiesPossible = Math.max(maxCandiesPossible, candyPile);
        }
        
        int lowestPile = 0, maximumPile = maxCandiesPossible;
        
        
        while(lowestPile < maximumPile){
            
            int mid = (maximumPile + lowestPile + 1)/2;
            
            if(canAllocate(candies, k, mid)){
                lowestPile = mid;
            }
            else{
                maximumPile = mid-1;
            }
            
        }
        
        return lowestPile;
        
    }
    
    private boolean canAllocate(int[] candies, long numberOfKids, int pile){
        
        long totalPiles = 0;
        
        for(int candy: candies){
            totalPiles += (candy/pile);
            if(totalPiles >= numberOfKids) return true;
        }
        
        return false;
    }
    
}


//Improved Solution, with max = Total candies/Total number of kids & Implementation approach II  
class Solution {
    public int maximumCandies(int[] candies, long k) {
        
        long totalCandies = 0;
        for(int candy: candies){
            totalCandies += candy;
        }
        
        if(totalCandies < k) return 0;
        
        int lowestPile = 0, maximumPile = (int)(totalCandies/k);
        
        
        while(lowestPile < maximumPile){
            
            int mid = (maximumPile + lowestPile + 1)/2;
            
            if(canAllocate(candies, k, mid)){
                lowestPile = mid;
            }
            else{
                maximumPile = mid-1;
            }
            
        }
        
        return lowestPile;
        
    }
    
    private boolean canAllocate(int[] candies, long numberOfKids, int pile){
        
        long totalPiles = 0;
        
        for(int candy: candies){
            totalPiles += (candy/pile);
            if(totalPiles >= numberOfKids) return true;
        }
        
        return false;
    }
    
}


// Brute Force - TLE
class Solution {
    public int maximumCandies(int[] candies, long k) {
        
        int result = 0;
        
        int pileSize = 1;
        
        while(true){
            
            if(canAllocate(candies, k, pileSize)){
                result = pileSize;
                pileSize++;
            }
            else{
                break;
            }
            
        }
        
        return result;
        
    }
    
    private boolean canAllocate(int[] candies, long numberOfKids, int pile){
        
        int totalPiles = 0;
        
        for(int candy: candies){
            totalPiles += (candy/pile);
            //Optimization - exit as soon as condition is met
            if(totalPiles >= numberOfKids) return true;
        }
        
        //check condition after calculating the total piles
        //if(totalPiles >= numberOfKids) return true;
        return false;
    }
    
}