// Definition for singly-linked list.
/*public static class ListNode {
  int val;
      ListNode next;
          ListNode(int x) { val = x; }
	   }
*/
 
public class AddTwoNumbers{

    static ListNode l3;
    static ListNode h3;
    static boolean  remainder;
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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
    
    protected static void addNode(ListNode l1, ListNode l2){
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
    
    protected static void addNewNodeToList(int addResult){
	if(l3 == null){
	    l3 = new ListNode(addResult);
	    h3 = l3;
	}

        else{
	    l3.next = new ListNode(addResult);
	    l3 = l3.next;
	}
	
    }
    
    public static void main(String args[]){
	
	ListNode a = new ListNode(5);
	
	ListNode b = new ListNode(5);
	
	addTwoNumbers(a, b);
	
    }
}
