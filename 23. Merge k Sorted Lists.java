/*
Brute Force - collect all elements, sort them and create result list
Time O(nlogn) space O(1)/O(n) - depending on merge [or] quick sort, where n is the total nodes across the list

Improvement - 1. iterate through the entire list, get the minimum and repeat the process untill all the nodes are evaluated
Time O(kn) space O(1), where k is the number of lists in the input list and n is the total nodes of all lists combined

2. We can create a priority queue to retrieve the smallest element of n nodes
Time O(nlogk) Space O(k), where k is the number of lists in the input list and n is the total nodes of all lists combined

3. Divide and conquer - Merge 2 lists at a time -> the problem statement (merge) is same for both the main and sub problem, can be applied repeatedly to get result 
Time O(nlogk) O(1), for the iterative solution
*/

//Brute Force
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        List<Integer> result = new ArrayList<Integer>();
        
        for(ListNode list: lists){
            while(list != null){
               result.add(list.val);
                list = list.next;
            }
        }
        
        Collections.sort(result);
        
        ListNode dummy = new ListNode(-1);
        ListNode iterator = dummy;
        
        for(Integer val: result){
            iterator.next = new ListNode(val);
            iterator = iterator.next;
        }
        
        return dummy.next;
    }
}


//PriorityQueue 

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //This condition is required - https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html#PriorityQueue(int,%20java.util.Comparator)
        //initial capacity of a priority queue should be greater than 0, else throws an illegalArgumentException
        if(lists.length == 0) return null;
        
        PriorityQueue<ListNode> pq = 
            new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){ 
                @Override
                public int compare(ListNode one, ListNode two){
                    return one.val - two.val;
                }
            });
        
        for(ListNode l: lists){
            if(l != null) pq.offer(l);
        }
        
        ListNode dummy = new ListNode(-1, null);
        ListNode iterator = dummy;
        
        while(!pq.isEmpty()){
            ListNode leastAmongHeap = pq.poll();
            iterator.next = leastAmongHeap;

            if(leastAmongHeap.next != null) pq.offer(leastAmongHeap.next);
            iterator = iterator.next;
            iterator.next = null;
        }
        
        return dummy.next;
    }
}

//Divide and Conquer - Iterative

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        if(lists.length == 0) return null;
        
        int interval = 1;
        while(interval < lists.length){
            for(int index=0; index+interval<lists.length; index = index +(interval*2)){
                lists[index] = mergeTwoLists(lists, index, index+interval);
            }
            interval *= 2;
        }
        
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode[] lists, int one, int two){
        ListNode dummy = new ListNode(-1, null);
        
        ListNode list1 = lists[one], list2 = lists[two];
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

//Other way of writing the same solution

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        
        for(int interval = 1; interval < lists.length; interval *= 2){
            for(int index = 0; index + interval < lists.length; index = index + interval*2){
                lists[index] = mergeTwoLists(lists[index], lists[index + interval]);
            }
        }
        
        return (lists.length > 0) ? lists[0] : null;
    }
    
    private  ListNode mergeTwoLists(ListNode one, ListNode two){
        ListNode dummy = new ListNode(-1);
        ListNode iterator = dummy;
            
        while(one != null && two != null){
            if(one.val < two.val){
                iterator.next = one;
                one = one.next;
            }
            else{
                iterator.next = two;
                two = two.next;
            }
            
            iterator = iterator.next;
        }
        
        iterator.next = (one != null) ? one : two;
        
        return dummy.next;
    }
    
}