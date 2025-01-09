
//Delete the next node

class Solution {
    public void deleteNode(ListNode node) {
        
        ListNode next = node.next;
        
        //swap values
        node.val = next.val;
        
        //remove the next node
        node.next = next.next;
        next.next = null;
    }
}