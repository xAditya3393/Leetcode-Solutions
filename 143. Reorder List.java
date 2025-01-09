/*
Brute Force - read all entries -> create new list/ modfy the input with required order
Time O(n) Space O(n)/O(1), if the input can be modified

Improvement - Find the second half -> reverse the second half -> rearrange the input to required order
Time O(n) Space O(1) 
Advantage - done in place, without modifying the input
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

//With functions
class Solution {
    public void reorderList(ListNode head) {
        
        //identify second half of the list
        //reverse the list
        //merge them in even/odd places
        
        if(head == null) return;
        
        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        if(slow == head) return;
        
        ListNode secondHead = reversedHalf(slow);
        mergeEvenOdd(head, secondHead);
    }
    
    private ListNode reversedHalf(ListNode list){
        ListNode prev = null;
        
        while(list != null){
            ListNode nextNode = list.next;
            list.next = prev;
            prev = list;
            list = nextNode;
        }
        
        return prev;
    }
    
    //1->2->3->null    null<-3<-4
    
    private ListNode mergeEvenOdd(ListNode evenHead, ListNode oddHead){
        
        ListNode dummy = new ListNode(-1);
        ListNode iterator = dummy;
        int currentNumber = 0;
        
        while(evenHead != null &&  oddHead != null){
            if(currentNumber%2 == 0){
                iterator.next = evenHead;
                evenHead = evenHead.next;
            }
            else{
                iterator.next = oddHead;
                oddHead = oddHead.next;
            }
            
            currentNumber++;
            iterator = iterator.next;
        }
        
        //This statement will cause cycle 
        //since one list will be larger than the other
        //the last element in larger list is to be intentionally left out
        //iterator.next = (evenHead != null) ? evenHead: oddHead;
        
        return dummy.next;
    }
}


//Without functions
class Solution {
    public void reorderList(ListNode head) {
        
        //identify second half of the list
        //reverse the list
        //merge them in even/odd places

        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode prev = null, current = slow;
        
        while(current != null){
            ListNode nextNode = current.next;
            
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode evenHead = head, oddHead = prev;
        ListNode iterator = dummy;
        int currentNumber = 0;
        
        while(evenHead != null &&  oddHead != null){
            if(currentNumber%2 == 0){
                iterator.next = evenHead;
                evenHead = evenHead.next;
            }
            else{
                iterator.next = oddHead;
                oddHead = oddHead.next;
            }
            
            currentNumber++;
            iterator = iterator.next;
        }
        
        dummy = dummy.next;
    }
}