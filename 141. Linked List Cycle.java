/*
Brute force -> 1. Store all the nodes visited -> if cycle exists we encounter it in the visited space [or] no cycle, reaches end of list
Time O(n) Space O(n), for storing visited nodes in hashset
2. If modifying the input is allowed, we set it to a max value -> if cycle exists we encounter max value [or] no cycle, reaches end of list
Time O(n) Space O(1) -> but requires modifying the input

Improvement - slow and fast pointer -> one iterates 2 times faster than the other, if cycle exists they will be equal at some point
Time O(n) Space O(1) -> Doesn't require modifying input [or] extra space 

 */


public class Solution {
    public boolean hasCycle(ListNode head) {
        
        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow) return true;
        }
        
        return false;
    }
}