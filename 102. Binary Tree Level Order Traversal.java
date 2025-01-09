/**
 There are two ways to solve this problem:
 1. Depth first search
 Approach - Do a preorder traversal (root, left right) while storing an extra data depth 
 indicating the depth at which the node was found
 Though left nodes processed first, we still need to sort the entire traversal with depth
 Moreover, we need to ensure we are using a stable sorting algorithm to make sure 
 the order per level is preserved 
 Time - O(nlogn), where n is the total number of nodes
 Space - O(H), where H is max height of the tree which results
 in maximum recursive stack space consumed at any point
 
 2. Breath First Search
 Approach - process current level of nodes in a queue until we have no more levels to process
 Observe how this approach eliminates the need for sorting part of earlier solution, since
 we preserve the order of current level with a queue
 Time - O(n), where n is the total number of nodes where each node is visited once
 Space - O(m), where m is the maximum nodes processed per level among all levels  
 */


// Breath First Search - Iterative solution
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Deque<TreeNode> nodesToBeVisited = new LinkedList<>();
        if(root != null) nodesToBeVisited.addLast(root);
        
        while(!nodesToBeVisited.isEmpty()){
            int currentLevelSize = nodesToBeVisited.size();
                List<Integer> currentLevelNodes = new ArrayList<>();
            
            while(currentLevelSize > 0){
                TreeNode currentNode = nodesToBeVisited.removeFirst();
                
                if(currentNode.left != null)  nodesToBeVisited.addLast(currentNode.left);
                if(currentNode.right != null)  nodesToBeVisited.addLast(currentNode.right);
                
                currentLevelNodes.add(currentNode.val);
                currentLevelSize--;
            }
            
            result.add(currentLevelNodes);
        }
        
        return result;
    }
}

// Breath First Search - Recursive solution
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Deque<TreeNode> nodesToBeVisited = new LinkedList<>();
        if(root != null) nodesToBeVisited.addLast(root);
        
        processLevel(nodesToBeVisited, result);
        
        return result;
    }
    
    private void processLevel(Deque<TreeNode> nodesToBeVisited, List<List<Integer>> result){
        
        if(nodesToBeVisited.isEmpty()) return;
        
        int currentLevelSize = nodesToBeVisited.size();
        List<Integer> currentLevelNodes = new ArrayList<>();

        while(currentLevelSize > 0){
            TreeNode currentNode = nodesToBeVisited.removeFirst();

            if(currentNode.left != null)  nodesToBeVisited.addLast(currentNode.left);
            if(currentNode.right != null)  nodesToBeVisited.addLast(currentNode.right);

            currentLevelNodes.add(currentNode.val);
            currentLevelSize--;
        }

        result.add(currentLevelNodes);
    
        
        processLevel(nodesToBeVisited, result);
    }
}