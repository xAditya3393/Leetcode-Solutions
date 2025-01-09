/*

Intuition
1. Store the player who have played at least one match and record their stats - player number, wins, losses and total matches
2. We need to define an order such that the stats are sorted by player number first followed by losses
3. Iterate over the order and accumulate result 

Solution during contest:
1. create inner private class for stats
2. create hashmap for storing the stat objects and updating them
3. create priority queue for sorting and filtering input using comparator

Observations & Corrections:
1. We dont need to store stats - wins and total matches, because each entry in lookup indicates player with atleast one match
=> We can just store player number and their losses in the lookup 
2. We dont need priority queue to filter and sort lookup data -> improper use of the data structure, since this is used mainly for comparator usage
=> Use treemap -> lookup + sorting key values based on natural ordering - no need to sort based on losses as result requires player number sorting, 
lowest player number for 0/1 loss appear in order

Improved solution:
1. Store player data in treemap -> natural ordering on player number - Integer data type
2. accumulate result

More Information: 
1. https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
2. https://www.baeldung.com/java-treemap - Comparison on hashMap, linkedHashMap, treeMap

*/

//Corrected solution
class Solution {
    
    public List<List<Integer>> findWinners(int[][] matches) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Map<Integer, Integer> lookup = new TreeMap<>();
        
        for(int[] match: matches){
            lookup.put(match[0], lookup.getOrDefault(match[0],0));
            lookup.put(match[1], lookup.getOrDefault(match[1],0)+1);
        }
                
        
        ArrayList<Integer> noLosses = new ArrayList<Integer>();
        ArrayList<Integer> oneLoss = new ArrayList<Integer>();
        
        
        
        for(Integer player : lookup.keySet()){
            
            int losses = lookup.get(player);
            
            if(losses == 1){
                oneLoss.add(player);
            }
            else if(losses == 0){
                noLosses.add(player);
            }
            else continue;
        }
        
        result.add(noLosses);
        result.add(oneLoss);
        
        return result;
    }
    
}


//Contest solution  
class Solution {
    
    private class playerInfo{
        
        private int playerNo;
        private int wins;
        private int losses;
        private int totalMatches;
        
        playerInfo(int pn){
            playerNo = pn;
            wins = 0;
            losses = 0;
            totalMatches = 0;  
        }
        
    }
    
    Map<Integer, playerInfo> lookup;
    
    public List<List<Integer>> findWinners(int[][] matches) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        lookup = new HashMap<>();
        
        for(int[] match: matches){
            updateLookup(match[0], 1, 0);
            updateLookup(match[1], 0, 1);
        }
        
        
        PriorityQueue<playerInfo> pq = new PriorityQueue<playerInfo>(lookup.size(), new Comparator<playerInfo>(){
            
            @Override
            public int compare(playerInfo one, playerInfo two){
                return (one.losses == two.losses) ? (one.playerNo - two.playerNo) : (one.losses - two.losses);
            }
        });
        
        
        for(playerInfo entry: lookup.values()){
            if(entry.totalMatches > 0 && entry.losses <= 1) pq.add(entry);
        }
        
        
        ArrayList<Integer> noLosses = new ArrayList<Integer>();
        ArrayList<Integer> oneLoss = new ArrayList<Integer>();
        
        while(!pq.isEmpty()){
            playerInfo current = pq.poll();
            
            if(current.losses == 0) noLosses.add(current.playerNo);
            else oneLoss.add(current.playerNo);
        }
        
        result.add(noLosses);
        result.add(oneLoss);
        
        return result;
    }
    
    
    private void updateLookup(int playerNo, int win, int loss){
        
        playerInfo playerObj = (lookup.containsKey(playerNo)) ? lookup.get(playerNo) : new playerInfo(playerNo);
        
        playerObj.wins += win;
        playerObj.losses += loss;
        playerObj.totalMatches++;
        
        lookup.put(playerNo, playerObj);
    }
}