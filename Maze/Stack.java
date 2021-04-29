public class Stack
{
	SLL list = new SLL();
	public Stack() {}
	
	public void clear()
	{
		list.clear();
	}
	
	public boolean isNull()
	{
		return list.isNull();
	}
	
	public void push(Block element)
	{
		list.addToHead(element);
	}
	
	public Block peek()
	{
		if(isNull())
		{ 
			System.out.println("Stack is empty");
			return null;
		}
		else
		{
			return list.getHead().getInfo();
		}
	}
	
	public Block pop()
	{
		if(isNull())
		{ 
			System.out.println("Stack is empty");
			return null;
		}
		else
		{
			SLLNode head = list.getHead();
			list.deleteFromHead();
			return head.getInfo();
		}
	}
	
	public void listAll()
	{
		list.listAll();
	}
}