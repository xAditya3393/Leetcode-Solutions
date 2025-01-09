/*
Preorder -> Root, Left, Right
We print as we explore each node (each node is the root of its subtree)
when we reach leftmost node, we need node address to iterate in reverse direction -> use stack while iteration
all the roots in the stack are kept since they are not fully explored -> fetch top and go right

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
    public List<Integer> preorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> revertNodes = new LinkedList<>();
        
        TreeNode iterator = root;
        
        while(iterator != null || !revertNodes.isEmpty()){
            
            while(iterator != null){
                result.add(iterator.val);
                revertNodes.addFirst(iterator);
                iterator = iterator.left;
            }
            
            TreeNode unExploredNode = revertNodes.removeFirst();
            iterator = unExploredNode.right;
        }
        
        return result;
    }
}


//Recursive solution
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();       
        postOrder(root, result);
         
        return result;
    }
    
    //Recursive Function expectation
    //Puts the current node in its right position in postOrder traversal
    //dont add if it is null -> termination condition
    private void postOrder(TreeNode iterator, List<Integer> result){
        
         if(iterator == null) return;
        
        result.add(iterator.val);
        //Trust the function to place all the left/right nodes in its right postOrder traversal
        postOrder(iterator.left, result); 
        postOrder(iterator.right, result);
    }
}