/*

Intuition
create stack to store all the nodes to be processed later along the path -> doing this because we only are exploring one side of the current subtree 
When no more left subtrees[or]when iteration reaches null -> top of the stack is the leftmost subtree unexplored!
Since right subtree hasn't been explored, we manually update our iterator to right subtree

Time - O(n) Space - O(n)

----------------------------------------------------------------------------------------------------------------------------------------------------

Important: 

During recursion, the recursive call stack stores the local variable where iteration points to (current, left and right addresses)
and has a way to steer the direction of iteration by storing the last line of code executed (recursive call upon current.left/current.right)
All of these are automatically managed over a stack

The stack in iterative solution only stores the nodes which are not fully explored and 
we have an iterating pointer to traverse all nodes of the tree which needs to be manually updated

The stack in iterative solution shouldn't be compared to recursive call stack 
and implementing the exact call stack will be difficult (need to manage current, left and right addresses along with a way to steer direction)

Think of iterative solution as a regular for loop iterating over the nodes and using stack for its fundamental advantage of reverse iteration

*/
 
//Iterative solution
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> nodesSkipped = new LinkedList<TreeNode>();

        TreeNode iterator = root;
        
        while(iterator != null || !nodesSkipped.isEmpty()){
            
            while(iterator != null){
                nodesSkipped.addFirst(iterator);
                iterator = iterator.left;
            }
            
            TreeNode inorderNode = nodesSkipped.removeFirst();
            result.add(inorderNode.val);
            iterator = inorderNode.right;
        }
        
        return result;
    }
}



//Recursive solution
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        
        inorderIteration(root, result);
        
        return result;
    }
    
    //Recursive Function Expectations:
    //adds current node to result ie the correct position of current element in inorder traversal 
    //dont add if it is null -> termination condition
    
    private void inorderIteration(TreeNode iterator, List<Integer> result){
     
        if(iterator == null) return;
        
        inorderIteration(iterator.left, result); //before adding current node, Assume this function adds all the nodes in inorder for the left subtree 
        result.add(iterator.val); //functions job for the node
        inorderIteration(iterator.right, result); //after adding current node, Assume this function prints all the nodes in inorder for the right subtree
        
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
 *         this.val = val;66
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */