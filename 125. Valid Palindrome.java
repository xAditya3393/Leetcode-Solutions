class Solution {
    public boolean isPalindrome(String s) {
        
        StringBuilder input = new StringBuilder();
        
        for(char c: s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                if(Character.isDigit(c)) input.append(c);
                else input.append(Character.toLowerCase(c));
            }
        }
        
        
        
        for(int start = 0, end = input.length()-1; start < end; start++, end--){
            char one = input.charAt(start);
            char two = input.charAt(end);
            
            if(one != two) return false;
        }
        
        return true;
    }
}