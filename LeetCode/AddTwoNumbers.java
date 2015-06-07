// Definition for singly-linked list.
/*public static class ListNode {
  int val;
      ListNode next;
          ListNode(int x) { val = x; }
	   }
*/
 
public class AddTwoNumbers{

    ListNode l3;
    ListNode h3;
    boolean  remainder;
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

	if(l1 == null && l2 == null)
	    return null;
	
	while(l1 != null || l2 != null || remainder){
	    addNode(l1, l2);
	    if(l1 != null)
		l1 = l1.next;
	    if(l2 != null)
		l2 = l2.next;
	}

	return h3;
    }
    
    protected void addNode(ListNode l1, ListNode l2){
	byte addResult = (byte) ((l1 != null)? l1.val : 0);
	addResult += (byte) ((l2 != null)? l2.val : 0);
	if(remainder)
	    ++addResult;
	
	remainder = addResult > 9;
	
	if(remainder)
	    addNewNodeToList(addResult % 10);
	else
	    addNewNodeToList(addResult);
    }
    
    // Adds result to final result list
    protected void addNewNodeToList(int addResult){
	if(l3 == null){
	    l3 = new ListNode(addResult);
	    h3 = l3;
	}

        else{
	    l3.next = new ListNode(addResult);
	    l3 = l3.next;
	}
    }
}
