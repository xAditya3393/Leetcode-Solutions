/*
Brute Force - determine length in one pass -> calculate the required position -> delete the node in second pass
Time O(n) Space O(1) 
Disadvantage - scanning data on filesystem, when too large to get in main memory, we need multiple passes to delete the required entry

Improvement - set two pointers given distance appart, and iterate the list -> when end is reached, the second pointer is at required position
Time O(n) Space O(1)
Advantage - completes in one pass, and disk reads are halfed 
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(-1, head), current = dummy, nAhead = dummy;
        
        while(n-- > 0){
            nAhead = nAhead.next;
        }
        
        while(nAhead.next != null){
            nAhead = nAhead.next;
            current = current.next;
        }
        
        ListNode deleteNode = current.next;
        
        current.next = deleteNode.next;
        deleteNode.next = null;
        
        return dummy.next;
    }
}