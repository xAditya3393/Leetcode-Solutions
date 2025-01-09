/*

Important questions 
can input take negative and zero values


Brute Force
Convert integer to string -> take two counters and keep checking iteratively 

Time - O(n)
Space - O(n)
where n is the number of digits


Observation:
1. If we can find expressions to extract least and most significant digits of an integer 
we can avoid the extra space used in string conversion
2. Notice how a negative integer can never be a palindrome -121 -> 121-

Improvement - Implementaion
1. Find least significant digit
reminder of a given integer -> 542%10 - 2

2. Find most significant digit
Observe we need to find the number of digits ahead of time, then find quotient 
number of digits = floor(log10(x)) + 1 -> gives the number of digits
x/10^(number of digits-1) -> 542/10^&(3-1) - 5

3. remove most and least significant number if match otherwise return false

Time - O(n)
Space - O(1)
where n is the number of digits

*/

public class Solution{

    public boolean palindromeInt(int x){

        if(x < 0) return false;

        int numberOfDigits = (int)Math.floor(Math.log10(x)) + 1;
        int mostSignificantDivider = (int)Math.pow(10, numberOfDigits-1);

        //while(x != 0) -> this leads to an extra check for a single digit number, which is a palindrome 
        //we make use of the number of digits for the iterations
        for(int itr = 0; itr < (numberOfDigits/2); ++itr){

            if(x%10 != x/mostSignificantDivider){
                return false;
            }
            
            x %= mostSignificantDivider; //remove most significant digit
            x /= 10; //remove least significant digit 
            mostSignificantDivider /= 100;
        }

        return true;
    }

}