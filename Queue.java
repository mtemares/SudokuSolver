public interface Queue<E>
{
	
	/**
    Add object to queue
     
     @param item Item to be added to the queue
     @return void
     */
	void offer(E item); //adds to the end
	
	/**
    Removes object from queue
     
     @return E item removed from queue
     */
	E poll();
	
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
