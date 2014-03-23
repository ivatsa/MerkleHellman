import java.math.BigInteger;


public class BigIntegerDoublyLinkedList {
	BigIntegerDoubleNode head;

	public void addCharAtEnd(BigInteger c) {
		if (head == null) {
			head = new BigIntegerDoubleNode();
			head.data = c;
			return;
		}
		
		BigIntegerDoubleNode cur  = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		
		if(cur.next == null) {
			BigIntegerDoubleNode node = new BigIntegerDoubleNode();
			node.data = c;
			cur.next = node;
			node.prev = cur;
		}
	}

	public void deleteChar(BigInteger c) {
		if (head == null) return;
		if (head.data == c && head.next == null) {
			head = null;
			return;
		}
		if (head.data == c) {
			head = head.next;
			head.prev = null;
		}
		
		BigIntegerDoubleNode temp = head;
		while (temp != null) {
			if (temp.data == c) {
				if (temp.next != null) {
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				} else {
					temp.prev.next = null;
				}
				break;
			}
			temp = temp.next;
		}
	}

	public void addCharAtFront(BigInteger c) {
		if (head == null) {
			head = new BigIntegerDoubleNode();
			head.data = c;
			return;
		}
		BigIntegerDoubleNode node = new BigIntegerDoubleNode();
		node.data = c;
		node.next = head;
		head.prev = node;
		head = node;
	}

	public int countNodes() {
		char[] count = new char[1];
		BigIntegerDoubleNode node = new BigIntegerDoubleNode();
		node = head;
		int counter = 0;
		while (node != null) {
			node = node.next;
			counter++;
		}
		//count[0] = Character.forDigit(counter, 10);
		return counter;
 	}

	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	public BigInteger[] removeCharFromFront() {
		BigInteger[] c = new BigInteger[1];
		if (head.next == null) {
			head = null;
			return c;       
		}
		c[0] = head.data;
		head = head.next;
		head.prev = null;
		return c;
	}

	public BigInteger[] removeCharAtEnd() {
		BigInteger[] c = new BigInteger[1];
		if (head.next == null) {
			head = null;
			return c;
		}
		
		BigIntegerDoubleNode temp = head;
		while(temp.next != null) 
			temp = temp.next;
		
		if (temp.next == null) {
			c[0] = temp.data;
			temp.prev.next = null;
		}		
		return c;
	}

	public void reverse() {
		if (head == null) 
	          return;

		BigIntegerDoubleNode previous = head;
		BigIntegerDoubleNode current = head.next;
	    head.next = null;
		while (current != null)
		{
			BigIntegerDoubleNode next = current.next;
		current.next = previous;
		current.prev = next;
		previous = current;
		current = next;
		}
		 head = previous;
	}
	
	@Override
	public String toString() {
		BigIntegerDoubleNode temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
		return " ";
	}
	
	public BigIntegerDoubleNode get(int index) {
		  if (head == null) {
	            return null;
	        }

	        BigIntegerDoubleNode node = head;
	        for (int i = 0; i < index; i++) {
	            node = node.next;
	            if (node == null) {
	                return null;
	            }
	        }
	        return node;
	}
}
