//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//                               V.W.F Mattana 21128707
//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777

//							SLL.java	
//					a generic linked list class
//======================================================================================================
//                             					SLL
//======================================================================================================
public class SLL 
{
	//******************************************************************************************************
	//												DECLARATIONS
	//******************************************************************************************************
	private SLLNode head, tail;
	public SLLNode current;
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             DEFAULT CONSTRUCTOR
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public SLL()
	{
		head = tail = null;
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             ADDING METHODS
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void addToHead(Block element)
	{
		head = new SLLNode(element, head);
		if (tail == null)
			tail = head;
	}
	
	public void addNodeToHead(SLLNode newNode)
	{
		head = newNode;
		if (tail == null)
			tail = head;
	}
	
	public void addToTail(Block element)
	{
		if (!isNull())
		{
			tail.next= new SLLNode(element);
			tail = tail.next;
		}
		else head = tail = new SLLNode(element);
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                            DELETION METHODS
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clear()
	{
		head = tail = null;
	}
	
	public SLLNode deleteFromHead()
	{
		if (isEmpty())
			return null;
		SLLNode deleted = head;
		if (head == tail)
			head = tail = null;
		else head = head.next;
		return deleted;
	}
	
	public Block deleteFromTail()
	{
		if (isEmpty())
			return null;
			Block element = tail.info;
		if (head == tail)
			head = tail = null;
		else 
		{
			SLLNode temp;
			for (temp = head; temp.next != tail; temp = temp.next);
			tail = temp;
			tail.next = null;
		}
		return element;
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             LISTING METHOD
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void listAll()
	{
		if(isNull())
			System.out.println("\tEmpty");
		else
		{
			for ( SLLNode temp = head; temp!= tail.next; temp = temp.next)
				System.out.println(temp.info);
		}
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             SEARCH METHOD
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public SLLNode isInList(Block element)
	{
		SLLNode temp;
		for ( temp = head; temp != null && !((temp.info.toString()).equals(element.toString())); temp = temp.next);
		return temp ;
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             ACCESSORS
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public SLLNode getHead()
	{
		return head;
	}
	
	public SLLNode getTail()
	{
		return tail;
	}
	
	public SLLNode getCurrent()
	{
		return current;
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//                                             CONVENIECE METHODS
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void incrementCurrent(int n)
	{
		current = head;
		for(int i=0;i<n;i++)
		{
			current = current.next;
		}
	}
	
	public int length()
	{
		int i =0;
		for ( SLLNode temp = head; temp != null ; temp = temp.next)
			i++;
		return i;
	}
	
	public Block element(int n)
	{
		SLLNode temp;
		int i =0;
		for ( temp = head; temp != null && i != n ; temp = temp.next)
		{
			i++;
		}
		return temp.getInfo();
	}
	
	public boolean isEmpty()
	{
		return head == tail;
	}
	
	public void setToEmpty()
	{
		head = tail = null;
	}
	
	public boolean isNull()
	{
		if(head == tail)
			if(head == null || tail == null)
				return true;
			else 
				return false;
		else 
			return false;
	}

}// END OF SLL<T> CLASS
	
	
	
	
	
	