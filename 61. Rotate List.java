class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null) return head;
    
        int length = 1;
        ListNode tail = head;
        
        while(tail.next != null){
            tail = tail.next;
            length++;
        }
         
        int rotateValue = k%length;
        if(rotateValue == 0) return head;
        
        tail.next = head;
        
        int newTailPosition = length - rotateValue;
        ListNode newTail = head;
        
        while(newTailPosition > 1){
            newTail = newTail.next;
            newTailPosition--;
        }
    
        
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}