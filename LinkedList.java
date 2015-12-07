/**
@author Madeline Temares
@version 11/19/15
*/

import java.util.Iterator;
import java.lang.Iterable;

public class LinkedList<E> implements Iterable<E>, Stack<E>, Queue<E>
{
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;
	
	/**
     Default constructor.
     
     */
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}	
	
	
	/**
     Constructor that adds a list node as the first (and last) item in the list.
     
     @param h ListNode to be added
     //@pre h.getNext() == null
     */
	public LinkedList(ListNode<E> h)
	{
		head = h; //head and tail are pointed to the one node that is added in this constructor
		tail = h;
		size = 1;
	}
	
	
	/**
	Copy constructor
     
     @param other LinkedList that it is copying
     */
	public LinkedList(LinkedList<E> other)
	{
		
		head = new ListNode<E>(other.get(0));
		head.setNext(other.getNode(1));
		
		ListNode<E> x = head;
		
		for (ListNode<E> curr = other.getNode(1); curr != null; curr = curr.getNext())
		{
			x.setNext(new ListNode<E>(curr.getItem()));
			x=x.getNext();
		}
		tail = new ListNode<E>(other.get(other.size()-1));
		size = other.size();
	}
	
	
	/**
     Size of the list.
     
     @return int Size of the list
     */
	public int size()
	{
		return size;
	}
	
	/**
     Adds a generic to the end of the list.
     
     @param item Generic item to be added to the list
     @return boolean Whether or not the generic is added (this will always be true).
     */
	public boolean add(E item)
	{
		add (size, item); //calls on the add that does it at a spot - the spot to add to the end is size
		return true;
	}	
	
	/**
     Adds a generic to the vector at a given location.
     
     @param toAdd Generic to be added
     @return void
     */
	public boolean add(int index, E item)
	{
		
		ListNode<E> toAdd = new ListNode<E>(item); //new node created with given item

		if (index>size)
        {
            throw new IndexOutOfBoundsException("This space does not exist");
        }
		
		if (size == 0) //if the list is empty, it sets the head and tail to the new node
		{
			head = toAdd;
			tail = toAdd;
			size++;
			return true;
		}
		
		else if (index == size) //if the index is the size, it is added to the end of the list - the tail needs to be set to this node
		{
			tail.setNext(toAdd);
			tail = toAdd;
			size++;
			return true;
		}
		
		else if (index == 0) //if the index is the size, it is added to the end of the list - the tail needs to be set to this node
		{
			ListNode<E> hold = head;
			head = toAdd;
			toAdd.setNext(hold);
			size++;
			return true;
		}
		
		else //for all other indexes, it traverses until it hits that spot
		{
			int count = 0;
			for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
			{
				
				if (count == index-1) //once it hits the spot, it does the adding and then breaks the for loop
				{
					toAdd.setNext(curr.getNext());
					curr.setNext(toAdd);
					size++;
					break;
				}
				count++;
			}		
		}
		return true;
	}
	
	
	/**
     Gets the item at a given location
     
     @param i Location for item to be returned
     @return E Item at given location
     */
	public E get(int i)
	{
		if (i<size)
		{
			int count = 0;
			for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
			{
				if (count == i)
				{
					return curr.getItem();
				}
				count++;
			}
		}
		else
		{
			throw new IndexOutOfBoundsException("This space does not exist.");
		}
		return null;
	}
	
	
	
	/**
     Gets the node at a given location
     
     @param i Location for node to be returned
     @return E node at given location
     */
	public ListNode<E> getNode(int i)
	{
		
		if (i<size)
		{
			int count = 0;
			for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
			{
				if (count == i)
				{
					return curr;
				}
				count++;
			}
			}
		else
		{
			throw new IndexOutOfBoundsException("This space does not exist.");
    	}
        return null;
	}
	
	
	/**
    Removes the item at a given location
     
     @param index Location for item to be removed
     @return E Item at given location that is removed
     */
	public E remove(int index)
	{
		E toReturn = getNode(index).getItem(); //has to store the item that it is removing
		
		if (index ==0 && index == size-1)
		{
			head = null;
			tail = null;
			size = 0;
			return toReturn;
		}
		
		if (index == size-1) //if you are removing the last item, you have to set the tail to the previous node
		{
			tail = getNode(index-1);
			getNode(index-1).setNext(null);
			size--;
			return toReturn;
		}
		if (index ==0) //if you are removing the first item, you have to set the head to the next node
		{
			head = getNode(index+1);
			size--;
			return toReturn;
		}		
		
		getNode(index-1).setNext(getNode(index+1));
		size--;
		
		return toReturn;	
	}
	/**
    Removes a given item
     
     @param o Item to be removed
     @return boolean Whether or not item was removed
     */
	public boolean remove (E o)
	{
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getItem() == null)
			{
				if (o== null)
				{
					remove(count);
					return true;
				}
			}
			else if (curr.getItem().equals(o))
			{
				remove(count);
				return true;
			}
			count++;
		}
		return false;
	}
	
	
	/**
    Finds index of the first instance of specified object
     
     @param o Item to be located
     @return int Index of first instance of specified object
     */
	
	public int indexOf(E o)
	{
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getItem().equals(o))
			{
				return count;
			}
			count++;
		}
		return -1;
	}
	
	/**
    Checks whether or not given item is in the linked list
     
     @param o Item that is being looked for
     @return boolean Whether or not given item is in the linked list
     */
	
	public boolean contains(E o)
	{
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getItem().equals(o))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
     Removes the first item
     
     @return E Item removed
     */
	public E removeFirst()
	{
		return remove(0); //calls the other remove method
	}
	
	
	/**
     Removes the last item
     
     @return E Last item - removed
     */
	public E removeLast()
	{
		return remove(size-1); //calls the other remove method
	}
	
	/**
     toString method is the string representation of the linked list
     
     @return String String representation of the linked list
     */
	public String toString()
	{
		String output = "";
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			output += curr.toString() + " , ";
		}
		return output;
	}


	/**
    Add object to stack
     
     @param item Item to be added to the stack
     @return void
     */
	public void push(E item)
	{
		add(0, item); //calls add method - adds to beginning because stack is FILO
	}
	
	
	/**
    Add object to queue
     
     @param item Item to be added to the queue
     @return void
     */
	public void offer(E item)
	{
		add(item); //calls add method - adds to beginning because queue is FILO
	}
	
	
	/**
    Add item to beginning of linked list
     
     @param item Item to be added to the beginning of the linked list
     @return void
     */
	public void addFirst(E item)
	{
		add(0, item);
	}
	
	
	/**
    Add item to end of linked list
     
     @param item Item to be added to the end of the linked list
     @return void
     */
	public void addLast(E item)
	{
		add(item);
	}
	
	
	/**
    Returns head item
     
     @return E head item
     */
	public E peek() //overrides both queue and stack method
	{
		return head.getItem();
	}
	
	
	/**
    Whether or not the list is empty
     
     @return boolean Whether or not list is empty
     */
	public boolean isEmpty()
	{
		if (size == 0)
		{
			return true;
		}
		return false;
	}
	
	
	/**
    Removes object from queue
     
     @return E item removed from queue
     */
	public E poll()
	{
		return remove(0); //removes first item - most recent put in
	}
	
	
	/**
    Removes object from stack
     
     @return E item removed from stack
     */
	public E pop()
	{
		return remove(0);//removes first item - first item put in
	}


	/**
    Removes all items from the list
     
     @return void
     */
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	
	/**
    Places object at specified location, overriding what was there before.
     
     @param o Item to be set at specified location
     @param i Place for item to be set
     @return E Item that was overridden
     */
	public E set(int i, E o)
	{
		if (i>size)
		{
			throw new IndexOutOfBoundsException("This space does not exist.");
		}
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (i == 0)
			{
				head.setItem(o);
			}
			else if (i == size)
			{
				tail.setItem(o);
			}
			if (count == i)
			{
				E temp = curr.getItem();
				curr.setItem(o);
				return temp;
			}
			count++;
		}
		return o;
	}

	/**
     Makes an iterator for this class.
     @return Iterator<E> Iterator made.
     */
	public Iterator iterator()
	{
		return new LinkedListIterator(head);
	}
}
