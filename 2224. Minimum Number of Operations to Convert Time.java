/*

This is a basic implementation problem, not really an interview question 

One can calculate the hours and minutes of both current and correct time, compute difference and operations 
but it will fail certain test cases - 9:50 & 10:49 
accomodating such test cases will increase the code implementation, which is unnecessary


Correct Approach - convert the time into a single unit
1. Convert current and correct time to a single unit -> minutes
2. Calculate the difference 
3. Compute the number of operations

Time - dictated by parsing string into integer -> O(n)
Space - O(1), requires fixed number of variables

*/


class Solution {
    public int convertTime(String current, String correct) {
        
        int currentHour =  Integer.parseInt(current.substring(0, 2));
        int currentMin =  Integer.parseInt(current.substring(3));
        
        int correctHour =  Integer.parseInt(correct.substring(0, 2));
        int correctMin =  Integer.parseInt(correct.substring(3));
        
        int minDiff = (correctHour*60 + correctMin) - (currentHour*60 + currentMin);
        int result = 0;
        
        while (minDiff > 0){
            
            if(minDiff >= 60){
                minDiff -= 60;
            }
            else if(minDiff >= 15){
                minDiff -= 15;
            }
            else if(minDiff >= 5){
                minDiff -= 5;
            }
            else{
                minDiff -= 1;
            }
            
            result++;
        }
        
         
        return result;
    }
}