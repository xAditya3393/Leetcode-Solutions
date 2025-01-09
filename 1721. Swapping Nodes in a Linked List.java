/*

Intuition
Calculate the length of the list -> position the first pointer -> position second pointer using list length

Brute Force 
Use extra space to store all the elements -> swap values -> copy all the elements back in the list
Time - O(n) for storing all elements in extra space + O(n) for copying back into list
Space - O(n) for storing all elements


Improvement - Doing the operations without using extra space, Space O(1)

1. Time - O(3n) -> O(n) for length calculation + O(n) for positioning first pointer + O(n) for positioning second pointer

Observation - we can calculate the length and position first pointer in one pass
2. Time - O(2n) -> O(n) for length calculation & positioning first pointer + O(n) for positioning second pointer  

Observation - using an iterator we can set the first pointer when distance is k 
-> when iterator reaches end while maintaining k distance, end pointer can be placed in one pass
3. Time - O(n)   

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


//3
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode first = null, second = null;
        int length = 0;

        ListNode iterator = head;
        
        while(iterator != null){
            
            length++;
            
            if(second != null) second = second.next;
            
            if(length == k) {
                first = iterator;
                second = head;
            }
            
            iterator = iterator.next;
        }
        
        swapValues(first, second);
        
        return head;
    }
    
    private void swapValues (ListNode first, ListNode second){
        if(first == null || (first == second)) return; 
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}


//2
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode first = null, second = null;
        int length = 0;
        
        //First node
        ListNode iterator = head; 
        while(iterator != null){
            length++;
            if(length == k) first = iterator;
            iterator = iterator.next;
        }
        
        
        //Second Node
        //set iterator position
        iterator = head;
        for(int pos = 1; pos < k; pos++){
            iterator = iterator.next;
        }
        
        second = head;
        while(iterator.next != null){
            second = second.next;
            iterator = iterator.next;
        }
        
        swapValues(first, second);
        
        return head;
    }
    
    private void swapValues (ListNode first, ListNode second){
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}


//1
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        int length = calculateLength(head); 
        
        
        ListNode first = head, second = head;
        
        //First node 
        for(int pos = 1; pos < k; pos++){
            first = first.next;
        }
        
        //Second Node
        //set iterator position
        ListNode iterator = head;
        for(int pos = 1; pos < k; pos++){
            iterator = iterator.next;
        }
        
        while(iterator.next != null){
            second = second.next;
            iterator = iterator.next;
        }
        
        swapValues(first, second);
        
        return head;
    }
    
    private void swapValues (ListNode first, ListNode second){
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    
    private int calculateLength(ListNode current){
        int length = 1;
        
        while(current.next != null){
            current = current.next;
            length++;
        }
        
        return length;
    }
}