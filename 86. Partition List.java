//Brute force - have three lists and note values as we iterate, then on second iteration rewrite the nodes
//Re-arrange the input based on the condition with a single iteration 
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lessThan = new ListNode(-1, null);
        ListNode greaterThanOrEqual = new ListNode(-1, null);
        
        ListNode lessThanTail = lessThan;
        ListNode greaterThanOrEqualTail = greaterThanOrEqual;
        
        
        while(head != null){
            
            if(head.val < x){
                lessThanTail.next = head;
                lessThanTail = lessThanTail.next; 
            }
            else{
                greaterThanOrEqualTail.next = head;
                greaterThanOrEqualTail = greaterThanOrEqualTail.next;
            }
            
            ListNode nextNode = head.next;
            head.next = null;
            head = nextNode;
        }
        
        lessThanTail.next = greaterThanOrEqual.next;
        greaterThanOrEqual.next = null;
        
        return lessThan.next;
    }
}