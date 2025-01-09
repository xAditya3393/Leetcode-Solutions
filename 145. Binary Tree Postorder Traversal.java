/*

PostOrder Traversal - Left->Right->Root
Key Intuition - Visit all left and right subtree nodes before adding root


We keep traversing the tree using an iterator pointing at different subtrees, going towards left subtrees, until we reach one of the following cases:
1. leaf node, subtrees are null -> In this case, both right and left subtree nodes are null, therefore we get root and add to our result
2. non leaf subtree root node, after left subtree nodes have been explored -> we shift iterator's direction to right side 
3. non leaf subtree root node, after right subtree nodes have been explored -> In this case both left and right have been explored, get root and add to our result

Stack stores all subtree root nodes unexplored, which can be found on the top of the stack!

Notice how on cases 1 & 3 have the same conditions -> in both cases we remove root since all the subtrees have been explored 

*/

//Iterative solution
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        //stores all the subtress which are not fully explored
        Deque<TreeNode> subTreesUnexplored = new LinkedList<>();
        TreeNode iterator = root; //pointer for exploring all subtrees 
        
        while(iterator != null || !subTreesUnexplored.isEmpty()){
            while(iterator != null){
                subTreesUnexplored.addFirst(iterator);
                iterator = iterator.left;
            }
            
            //condition for leaf node && iterator is right node of last subtree
            while(!subTreesUnexplored.isEmpty() && iterator == subTreesUnexplored.peekFirst().right) {
                iterator = subTreesUnexplored.removeFirst();
                result.add(iterator.val);
            }
            //condition for non-leaf node 
            if(!subTreesUnexplored.isEmpty() && iterator != subTreesUnexplored.peekFirst().right){
                iterator = subTreesUnexplored.peekFirst().right;
            }
            
            //Termination condition - no more subtrees to explore
            if(subTreesUnexplored.isEmpty()) break;
        }
        
        return result;
        
    }
}


//Recursive solution
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        
        return result; 
    }
    
    //Recursive Function Expectations
    //adds the current node in its right PostOrder position
    private void postOrder(TreeNode current, List<Integer> result){
        
        //Termination condition
        if(current == null) return;
        
        //Current node can be added to our result only when 
        //all the left and right subtree nodes are explored
        postOrder(current.left, result);
        postOrder(current.right, result);
        result.add(current.val);
        
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */