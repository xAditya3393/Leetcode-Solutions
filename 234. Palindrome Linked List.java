//If modifying the list is allowed
class Solution {
    public boolean isPalindrome(ListNode head) {
       
        //Find the second half of the list
        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode firstHalfIterator = head;
        ListNode secondHalfIterator = reverseList(slow);
        
        while(firstHalfIterator != null && secondHalfIterator != null){
            
            if(firstHalfIterator.val != secondHalfIterator.val) return false;
            
            firstHalfIterator = firstHalfIterator.next;
            secondHalfIterator = secondHalfIterator.next;
        }
        
        return true;
    }
    
    private ListNode reverseList(ListNode current){
        ListNode prev = null;
        
        while(current != null){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        
        return prev;
    }
}


//If not, then for a string and compare reverse [or] take the input in array and use two pointer approach
class Solution {
    public boolean isPalindrome(ListNode head) {
        
        StringBuilder input = new StringBuilder();
        ListNode iterator = head;
        
        while(iterator != null){
            input.append(Integer.toString(iterator.val));
            iterator = iterator.next;
        }
        
        return input.toString().equals(input.reverse().toString());
    }
}