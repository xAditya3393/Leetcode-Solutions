/*
Evaluate given requirements case by case, and remove in reverse order   
".." -> pop from stack
"." and "" -> do nothing 
anything else -> must be a valid directory, store in stack
Time O(n) Space O(n) 
*/

class Solution {
    public String simplifyPath(String path) {
 
        Deque<String> pLookup = new LinkedList<>();
        
        for(String directory: path.split("/")){
            
            if(directory.equals("..")){

                if(!pLookup.isEmpty()){
                    pLookup.pop();
                }

            }
            else if (!directory.equals(".") && !directory.isEmpty()) pLookup.push(directory);
        }
        
        
        StringBuilder result = new StringBuilder().append("");
        
        while(!pLookup.isEmpty()){
            result.append("/").append(pLookup.removeLast());
        }
        
        return (result.toString() == "") ? "/" : result.toString();
    }
}