
class Solution {
    public String longestPalindrome(String s) {
        //dp - p(i,j) = p(i+1, j-1) && s[i] == s[j];
        
        int size = s.length();
        
        boolean[][] dp = new boolean[size][size];
        
        for(int index = 0; index< size; index++){
            dp[index][index] = true; 
        }
        
        
        String result = "";
        result += s.charAt(0);
        
        for(int start = size-1; start >= 0; start--){
            for(int end = start+1; end < size; end++){
                
                dp[start][end] = (s.charAt(start) == s.charAt(end) && (end-start < 3 || dp[start+1][end-1]));
                
                if(dp[start][end] && result.length() < (end-start+1)){
                    result = s.substring(start, end+1);
                }
            }
        }
        
        return result;
    }
}



class Solution {
    
    private int resultStart, resultLength, inputSize;
    private String input;
    
    public String longestPalindrome(String s) {
        
        input = s;
        inputSize = s.length();
        
        for(int start = 0; start < inputSize; start++){
            expandAroundCenter(start,start); //assume odd center
            expandAroundCenter(start,start+1); //assume even center
        }
        
        String result = s.substring(resultStart, resultStart+resultLength);
        
        return result;
    }
    
    private void expandAroundCenter(int start, int end){
        
        while(start >= 0 && end < inputSize && input.charAt(start) == input.charAt(end)){
            start--;
            end++;
        }
        
        if(resultLength < end-start-1){
            resultStart = start+1;
            resultLength = end-start-1;
        }
        
    }
}