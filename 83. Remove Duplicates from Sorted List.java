class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode iterator = head;
        
        while(iterator != null){
            
            ListNode nextDistinct = iterator;
            
            while(nextDistinct != null && nextDistinct.val == iterator.val){
                nextDistinct = nextDistinct.next;
            }
            
            iterator.next = nextDistinct;
            iterator = nextDistinct;
            
        }
        
        return head;
        
    }
}