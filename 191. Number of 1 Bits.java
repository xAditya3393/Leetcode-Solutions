/* 
Improvement
1. The problem here is if the length of the word is 64 bits - we will have 2^64 combinations of words
As each bit in the word can take either 0/1 - the size of the array to store this combinations would be 2 exabytes (1 exabyte - one billion gigabytes)

if there is a sufficiently smaller word - ex: 16 bits, we can have a cache of 2 bit lenght 
2 bit length cache - 00 -> 0, 01 -> 1, 10 -> 1, 11 -> 0
- group multiple non overlapping bits 
- separate them with mask containing all bits of 1's (this would preserve the word)
- shift the word appropriately to get all segments 
- perform xor on these groups 

Time - O(n/m), where 
n is length of the word/the number of bits in the word
m is the length of each group
Space - O(2^m) - size of the cache

Note:
1. Notice how the worst case is still O(n)
2. We are assuming the operations for mask + shifting takes O(1) 



Third Observation 
Instead of using mask + shift for getting each segment - we can do it in place and make use of xor associative property  

Improvement:
Assuming length of the word is known ahead of time
Notice - Doing it in place guarantees word is getting halfed and resulting opertion halts when only 1 bit is left 
Operation always involves xor between two halfs -> shifting inplace eliminates mask operation in mask + shifting operation
ex: 8 bit word -> first 4 bits ^ second set of 4 bits - result 4 bits
4 bit word -> first 2 bits ^ second set of 2 bits - result 2 bits
2 bit word -> first 1 bits ^ second set of 1 bits - result 1 bits 


Time - O(logn), where n is length of the word/the number of bits in the word
Space - O(1) - need one variable each to store the result at the end of each process and mask for grouping

Note:
Lookup table here is unnecessary - the store for all possible is too large 
limiting the combination to only a certain word length is completely unnecessary - computation is done here  
*/


//Brute Force

public int scanAllBits(int word){

    int result = 0;

    while(word > 0){
        result ^= word&1;
        word >>>= 1; 
    }

    return result;
}


//Improvement 1


class Solution {
    public int hammingWeight(int word) {
    
        int result = 0;
        while(word > 0){
            result++;
            word &= (word-1);
        }
        return result;
        
    }
}


//Improvement 2 - assuming word length 16 in binary

public int hammingWeightWithCache (int word){

    //will contain all posible combinations for 4 bit words and their parity
    Map<Integer, Integer> parityLookup = new HashMap<>();

    int mask = 15; //4 bit binary 
    int groupSize = 4; 


    return (
    parityLookup.get((word>>>(3*groupsize))& mask) //first set four bits 
    ^ parityLookup.get((word>>>(2*groupsize))& mask) //second set four bits
    ^ parityLookup.get((word>>>(1*groupsize))& mask) //third set four bits
    ^ parityLookup.get((word & mask)) //fourth set four bits
    ) ;
}




//Improvement 3 - assuming word length 16 in binary

public int hammingWeightInPlaceWithoutCache (int word){
    // get first set of 8 bits with right shift, second set from original word - store result in word
    // notice second set of grouping in each iteration contains result - rest of the bits are none of our concern
    word ^=(word >>> 8); 
    word ^=(word >>> 4); 
    word ^=(word >>> 2); 
    word ^=(word >>> 1); // the last bit contains the result  

    return word&1 ;
}


