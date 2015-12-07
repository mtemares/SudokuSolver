public interface Stack<E>
{
	/**
    Add object to stack
     
     @param item Item to be added to the stack
     @return void
     */
	
	void push(E item); //adds to the beginning
	
	/**
    Removes object from stack
     
     @return E item removed from stack
     */
	E pop();
	
	/**
    Returns head item
     
     @return E head item
     */
	E peek();
	
	/**
    Whether or not the list is empty
     
     @return boolean Whether or not list is empty
     */
	boolean isEmpty();
}
