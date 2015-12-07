public class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	
	public ListNode (E item)
	{
		this.item = item;
	}
	
	public ListNode(E item, ListNode<E> next)
	{
		this.item = item;
		this.next = next;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setItem(E i)
	{
		item = i;
	}
	
	public void setNext(ListNode<E> n)
	{
		next = n;
	}
	
	public String toString()
	{
		return item.toString();
	}
	
	
	
}
