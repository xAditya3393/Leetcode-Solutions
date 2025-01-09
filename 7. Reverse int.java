/*
Important questions 
what the input int limits - if Integer.MIN_VALUE <= inoutInt <= Integer.MAX_VALUE, we need to consider for overflow/underflow


Brute Force 
convert input into string and reverse 


Improvement
We can extract reminder and quotient with 10 division and recursive formulate our reverse digit 

Implementation
1. perform mod with 10 - get reminder
2. get the quotient with 10 - for next iteration
3. result will be previous result multiplied by 10 plus current reminder
4. repeat process until we reach zero 


ex: 234 
dry run
result initialised to zero 
1. 234 -> reminder - 4, quotient - 23, result - 0*10 + 4
2. 23 -> reminder - 3, quotient - 2, result - 4*10 + 3
3. 2 -> reminder - 2, quotient - 0, result - 43*10 + 2
4. stop, final answer - 432

*/


class Solution {
    public int reverse(int x) {
        
        long result = 0;
        int num = Math.abs(x);

        while(num != 0){

            int reminder = num%10;
            num = num/10;
            result = result*10 + reminder;

        }

        result = (x < 0) ? -1*result : result;
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        
        return (int)result;
    }
}