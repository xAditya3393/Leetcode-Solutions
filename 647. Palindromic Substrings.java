
class Solution {
    public int countSubstrings(String s) {
        
        //dp -> p(i,j) = p(i+1,j-1) + s(i) == s(j)
        //Edge cases -> 1 - true, 2 - s(0) == s(1)
        
        int size = s.length(), result = 0;
        
        boolean[][] dp = new boolean[size][size];
        for(int index = 0; index < size; index++){
            dp[index][index] = true;
            result++;
        }
        
        
        for(int start = size-1; start >=0; start--){
            for(int end = start+1; end < size; end++){
                dp[start][end] = (s.charAt(start) == s.charAt(end) && (end-start < 3 || dp[start+1][end-1]));
                if(dp[start][end]) result++;
            }
        }
        
        return result;
    }
}


class Solution {
    private int inputSize;
    private String input;
    
    public int countSubstrings(String s) {
        
        int result = 0; 
        inputSize = s.length();
        input = s;
        
        for(int start = 0; start < inputSize; start++){
            result += expandAroundCenter(start, start); //assume even center
            result += expandAroundCenter(start, start+1); //assume odd center            
        }
        
        return result;
    }
    
    private int expandAroundCenter(int start, int end){
        
        int currentPalidromes = 0;
        
        while(start >= 0 && end < inputSize && (input.charAt(start) == input.charAt(end))){
            start--;
            end++;
            currentPalidromes++;
        }
        
        return currentPalidromes;
    }
}