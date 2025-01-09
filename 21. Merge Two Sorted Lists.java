/**
Brute Force - Append the two lists and sort them - Time O(m+n)log(m+n)  Space O(1) - sort takes up time complexity
Disadvantage -> doesn't use sorted input to advantage

Improvement - traversering lists, choose smallest node and restructure the result 
Time O(n) Space O(1)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1, null); 
        ListNode iterator = dummy;
        
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                iterator.next = list1;
                list1 = list1.next;
            }
            else{ 
                iterator.next = list2;
                list2 = list2.next;
            }
            
            iterator = iterator.next;
        }
        
        iterator.next = (list1 != null) ? list1 : list2;
        
        return dummy.next;
    }
}