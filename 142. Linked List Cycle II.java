There are three ways to solve this problem depending on what the interviewer expects:

1. When is extra space allowed -> Time - O(N), Space - O(N) 
	* 	use hashset to track all the nodes visited - insert each node before moving ahead
	* 	if cycle exists, the node should be present in the hashset (we look for this condition while iterating the list)
	* 	if there is no cycle, the iteration ends pointing to null
	

			public ListNode detectCycle(ListNode head) {
				Set<ListNode> lookup = new HashSet<>(); 
				ListNode current = head;

				while(current != null){
					if(lookup.contains(current))  return current;

					lookup.add(current);    
					current = current.next;
				}

				return null;
			}

2. If you are not allowed for extra space, but modifying the input is allowed  -> Time - O(N), Space - O(1) 
	* 	set the current node to Integer.MAX_VALUE, and continue
	* 	if cycle exists, the value should be Integer.MAX_VALUE (as it was updated earlier)
	* 	if there is no cycle, the iteration ends pointing to null

			public ListNode detectCycle(ListNode head) {
				ListNode current = head;

				while(current != null){
					if(current.val == Integer.MAX_VALUE)  return current;

					current.val = Integer.MAX_VALUE;    
					current = current.next;
				}

				return null;
			}
			
3. If you are not allowed to modify input [or] use extra space  -> Time - O(N), Space - O(1) 

	Algorithm:
	* Step 1 - use two pointers - fast (moving 2 steps each iteration) and slow (moving 1 step each iteration) iterating pointers for cycle detection
	* Step 2 - if cycle exists - detect the length of the cycle / If no cycle - return null
	* Step 3 - use two pointers placed cycle length apart,  and move both one step at time -> when both are equal the start of the cycle is found! 

			//Code Reference - Elements of Programming Interviews in Java
			public ListNode detectCycle(ListNode head) {
					//fast and slow points for cycle detection
					ListNode slow = head, fast = head;


					while(fast != null && fast.next != null){
						slow = slow.next;
						fast = fast.next.next;
						
						if(fast == slow){

							int cycleLength = 0;
							
							//If cycle exist - find cycle length
							do{
								cycleLength++;
								fast = fast.next;
							}while(fast != slow);
							
							//two points distanced by cycle length to find cycle's starting node
							ListNode one = head, two = head;

							while(cycleLength > 0){
								two = two.next;
								cycleLength--;
							}

							while(one != two){
								one = one.next;
								two = two.next;
							}

							return one;
						}
					}

					return null;
				}



The above solution can be further optimized - based on optimization (solution 3) suggested for https://leetcode.com/problems/intersection-of-two-linked-lists/solution/

	public ListNode detectCycle(ListNode head) {

			ListNode slow = head, fast = head;


			while(fast != null && fast.next != null){
				slow = slow.next;
				fast = fast.next.next;

				if(fast == slow){
					//Reset slow pointer to head 
					//since fast pointer moves twice as fast, by the time fast reaches end, slow is mid way
					//if there is a cycle - resetting slow to start and incrementing both one step at a time, 
					//they will meet at the start of the cycle - (draw out examples to see this)
					slow = head;

					while(slow != fast){
						slow = slow.next;
						fast = fast.next;
					}

					return slow;
				}
			}

			return null;
		}