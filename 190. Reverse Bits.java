/* 

Brute Force 
We can do the following:
- get ith bit 
- store the result 
- left shift result and keep extracting bits until input is zero 
Time - O(n), where n is length of the word/the number of bits in the word
Space - O(1) 



First Observation 
Observe how the bits in the reverse result appear
Lets say a,b,c,d represent equal segments of input word -> the output would be rev(d), rev(c), rev(b), rev(a)

Improvement 
We can store the reverse of all possible segments in a cache and have computation reduce based on grouping size 
Time - O(n/k), n is the length of the word & k is the length of the group
Space - O(2^k) - This would be the cache required to store all possible combination of grouping size k along with their reverse 



Second Observation 
if we further divide the segments down to one bit, we can do it in place without cache 

Improvement
https://leetcode.com/problems/reverse-bits/discuss/54741/O(1)-bit-operation-C%2B%2B-solution-(8ms)
for 8 bit binary number abcdefgh, the process is as follow:
abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
Time - O(1)
Space - O(1)


*/

public class Solution {
    
    public int reverseBits(int n) {
         
        
        int result = 0;
        int mask = 1;
        
        for(int pos=0; pos<32; pos++){
            result |= (n&mask);
            n>>=1;
            if(pos != 31) result <<= 1;
        }
        
        return result;
    }
}


public class Solution {
    //NOTE - java by default is signed bit integer
    public int reverseBits(int n) {
         
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
        
        return n;
    }
}