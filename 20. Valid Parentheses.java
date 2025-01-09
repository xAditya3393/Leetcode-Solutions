/*
Observe the order in which the brackets need to be closed
Use stack to keep track of the closing brackets that need to arrive in order for the expression to be valid
Time O(n) Space O(n)
*/
class Solution {
    public boolean isValid(String s) {
        
        Deque<Character> open = new LinkedList<>();
        
        for(char c: s.toCharArray()){
            if(c == '(') open.push(')');
            else if(c == '[') open.push(']');
            else if(c == '{') open.push('}');
            else {
                if(open.isEmpty() || open.pop() != c) return false;
            }            
        }
        
        return open.isEmpty();
    }
}