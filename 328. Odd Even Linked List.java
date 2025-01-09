class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode evenHead = new ListNode(-1, null);
        ListNode oddHead = new ListNode(-1, null);
        
        ListNode evenTail = evenHead;
        ListNode oddTail = oddHead;
        
        int nodeNumber = 1;
        
        while(head != null){
            ListNode inputNextNode = head.next;
            
            if(nodeNumber%2 == 1){
                oddTail.next = head;
                oddTail = oddTail.next;
            }
            else{
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            
            head.next = null;
            head = inputNextNode;
            nodeNumber++;
        }
        
    
        oddTail.next = evenHead.next;
        evenHead.next = null;
        
        return oddHead.next;
    }
}