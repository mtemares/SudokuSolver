import java.util.Iterator;
import java.lang.Iterable;
public class VectorIterator<E> implements Iterator<E>
{
	private Vector<E> vector;
	private int curr;
	
	/**
     Constructor to make new VectorIterator.
     @param Vector<E> Vector taken in.
     */
	
	public VectorIterator(Vector<E> v)
	{
		vector = v;
		curr =0;
	}
	
	/**
     Checks if the next spot in the vector has a value in it.
     @return boolean Whether or not the vector has a next value.
     */
	public boolean hasNext()
	{
		return (curr<vector.size());
	}
	
	/**
     Returns next object in vector.
     @return E Next object in vector.
     */
	public E next()
	{
		return (vector.get(curr++));
	}
	public void remove()
	{
	
	}
}
