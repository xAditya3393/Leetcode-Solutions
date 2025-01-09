//Brute force - convert into two integers -> add them -> form a list out of the result

//Doing the operations with one iteration of input - maintain carry with each digit addition
//Time - O(m+n) Space - O(1)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode(-1, null);
        ListNode iterator = dummy;
        int carryIn = 0, carryOut = 0;
        
        while(l1 != null || l2 != null){
            ListNode currentDigit = new ListNode(-1,null);
            int currentSum = 0;
            
            if(l1 != null){
                currentSum += l1.val;  
                l1 = l1.next;
            }
            if(l2 != null){
                currentSum += l2.val;
                l2 = l2.next;
            }
            
            currentDigit.val = (currentSum + carryIn)%10;
            carryOut = (currentSum + carryIn)/10;
            carryIn = carryOut;
            
            iterator.next = currentDigit;
            iterator = iterator.next;
            
        }
        
        if(carryOut > 0) iterator.next = new ListNode(carryOut, null);
        
        return dummy.next;
    }
}