/*

Brute Force
Calculate the height of left and right subtree for each node and validate
Time - O(n^2) -> O(n) for traversal, O(n) for calculating height
Space - O(h), where h is the maximum height from leaf to a node

Observation

height of the node is calculated multiple times over the course of traversing all nodes
height calculation and node traversal do their operation via same approach of visiting all nodes
Improvement

The height of a given node can be stored in extra space - hashtable
Time O(n) -> lookup table gives O(1) height if exists, therefore calculation happens exactly once
Space O(n) -> the hashtable space dictates space complexity over height

Include the validating condition along with height calculation

directly -> not readable, involves placing validating conditions while calculating height
create a class storing data -> height and flag for balanced
Time O(n) -> the nodes are visited on conditional basis
Space O(h), where h is the maximum height from leaf to a node


All solutions are bottom-up approach as child nodes will be processed before the root

*/

//Brute Force
class Solution {
    
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        return isBalanced(root.left) && isBalanced(root.right) &&
            (int)Math.abs(calculateHeight(root.left) - calculateHeight(root.right)) <= 1;
    }
    
    
    //Recursive function expectations
    //calculates left and right subtree heights and returns difference
    private int calculateHeight(TreeNode root){
        
        if(root == null) return 0;
        
        //Trusting the function to return us the height of left and right subtrees
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        
        return (int)Math.max(leftHeight, rightHeight) + 1;
        
    }
}


//Optimization - lookup table
class Solution {
    
    //lookup table
    private Map<TreeNode, Integer> heightLookup;
    
    public boolean isBalanced(TreeNode root) {

        if(root == null) return true;
        
        heightLookup = new HashMap<TreeNode, Integer>();
        
        return (int)Math.abs(calculateHeight(root.left) - calculateHeight(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    
    //Recursive function expectations
    //calculates height of current node and store if not calculated
    private int calculateHeight(TreeNode current){
        
        if(current == null) return 0;
        
        if(heightLookup.containsKey(current)) return heightLookup.get(current);
        
        
        int leftHeight, rightHeight;
        
        if(heightLookup.containsKey(current.left)){
            leftHeight = heightLookup.get(current.left);   
        }else{
            leftHeight = calculateHeight(current.left);
            heightLookup.put(current.left, leftHeight);
        } 
        
        if(heightLookup.containsKey(current.right)){
            rightHeight = heightLookup.get(current.right);
        }else{
            rightHeight = calculateHeight(current.right);
            heightLookup.put(current.right, rightHeight);
        }
        
        
        return (int)Math.max(leftHeight, rightHeight) + 1;
        
    }
}


//Optimization - directly placing the conditions
class Solution {
    
    public boolean isBalanced(TreeNode root) {
        return calculateHeight(root) != -1;
    }
    
    
    //Recursive function expectations
    //calculates height of current node
    private int calculateHeight(TreeNode current){
        
        if(current == null) return 0;
        
        int leftHeight = calculateHeight(current.left);
        int rightHeight = calculateHeight(current.right);
        
        //conditions for validation 
        //-1 indicates the subtree is not balanced
        if(leftHeight == -1 || rightHeight == -1) return -1;
        if((int)Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return (int)Math.max(leftHeight, rightHeight) + 1;
        
    }
}


//Optimization - placing conditions with a class object
class Solution {
    
    //private class holding the validating variables
    private class balanceStatusWithHeight{
        private boolean balanced;
        private int height;
        
        balanceStatusWithHeight(boolean b, int h){
            balanced = b;
            height = h;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        return validate(root).balanced;
    }
    
    
    //Recursive function expectations
    //calculates height and checks for balancing condition of current node
    private balanceStatusWithHeight validate(TreeNode current){
        
        if(current == null) return new balanceStatusWithHeight(true, 0);
        
        balanceStatusWithHeight currentLeft = validate(current.left);
        if(!currentLeft.balanced) return new balanceStatusWithHeight(false, -1); //condition to avoid traversals if left subtree is unbalanced
        
        balanceStatusWithHeight currentReight = validate(current.right);
        if(!currentReight.balanced) return new balanceStatusWithHeight(false, -1); //condition to avoid traversals if right subtree is unbalanced
        
        
        boolean balancedCurrent = Math.abs(currentLeft.height - currentReight.height) > 1 ? false : true;
        int heightCurrent = Math.max(currentLeft.height, currentReight.height) + 1;
        
        return new balanceStatusWithHeight(balancedCurrent, heightCurrent);
        
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