import java.util.Iterator;
import java.lang.Iterable;
public class LinkedListIterator<E> implements Iterator<E>
{
	private ListNode<E> curr;
	public LinkedListIterator(ListNode<E> head)
	{
		curr = head;
	} 
	public boolean hasNext()
	{	
		
		return (curr != null);
		
		//return (curr<vector.size());
	}
	
	public E next()
	{
		//if (hasNext() == false)
		//{
			//throw no such element exception*/
		//}
		//E temp = curr.getItem();
		//curr = curr.getNext();
		//return temp;
		
		E temp = curr.getItem();
		curr = curr.getNext();
		return (temp);
		
		//return (vector.get(curr++));
		
	}
}
