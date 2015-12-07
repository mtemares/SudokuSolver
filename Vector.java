import java.util.Iterator;
import java.lang.Iterable;
/**
@author Madeline Temares
@version 10/22/15
*/
public class Vector<E> implements Iterable<E>
{
	private Object[] data;
    private int capacity; //how many items the vector can currently hold
	private int size;  //how many items are in the vector
	
	/**
     This is the default constructor, which initializes data to be of capacity 10.
     */
    public Vector()
	{
        this(10);
	}
    
    /**
     This constructor takes in a given capacity.
     
     @param initCapacity The given capacity for the data vector
     */
	
	public Vector(int initCapacity)
	{
		if (initCapacity <= 0)
		{
			throw new IllegalArgumentException("Capacity must be greater than 0");
		}
		size = 0;
        capacity = initCapacity;
		data = new Object[initCapacity];
	}
    
    /**
     This Constructor copies a vector.
     
     @param other Vector to be copied and initialized to
     */
    
    public Vector(Vector<E> other)
    {
        data = new Object[other.capacity];
        size = 0;
        for (int x = 0; x<other.size; x++)
        {
        	add(other.get(x));
        }
        capacity = data.length;
        
    }
	
    /**
     Adds a generic to the vector.
     
     @param toAdd Generic to be added
     @return void
     */
    
	public void add(E toAdd)
	{
        add(size, toAdd);
	}
	
    /**
     Adds a generic to the vector at a given location.
     
     @param index Location for generic to be added at
     @param toAdd Generic to be added
     @return void
     
     */
    
	public void add(int index, E toAdd) //IF SIZE == DATA LENGTH
	{
		if (index>size)
        {
            throw new IndexOutOfBoundsException("This space does not exist");
        }
        if (size>= data.length)
        {
        	increaseCapacity();
        }
        for (int x = size-1; x>=index; x--)
        {
            data[x+1] = data[x];
        }
        //System.out.println(toAdd);
        //System.out.println(index);
        data[index] = toAdd;
        size++;
        //if vector is full
	}
	
    /**
     Increases the capacity of the vector to twice what it is.
     
     @return void
     */
    
    private void increaseCapacity()
    {
        Object[] doubled = new Object[capacity * 2];

        for (int x = 0; x < size; x++)
        {
            doubled[x] = data[x];
        }
        data = doubled;
        capacity = data.length;
        //copy the vector into one double the size
    }
    
    /**
     Gets the generic at a given location.
     
     @param index Location of generic to be returned
     @return E Generic found at given location
     */
    
    @SuppressWarnings("unchecked")
    public E get(int index)
    {
        if (index<size)
            {
                if (data[index]!=null)
                    return (E)(data[index]);
            }
        else
        {
            throw new IndexOutOfBoundsException("This space does not exist.");
        }

        return null;
    }
    
    /**
     Removes a generic of a given location.
     
     @param index Location of generic to be removed and returned
     @return E Generic removed at given location
     
     */
     @SuppressWarnings("unchecked")
    public E remove (int index)
    {
        E toReturn = (E)data[index];
        for (int x = index; x<size; x++)
        {
            data[x] = data[x+1];
        }
        size--;
        return toReturn;
        
        //size--;
        //if index is out of the bounds of the vector
        //if the space in the vector is null
    }
	
    
    /**
     Removes generic if generic is in the data vector.
     
     @param obj Generic to be removed
     @return boolean Whether or not the generic to be removed was in the vector
     
     */
    
    public boolean remove(E obj)
    {
        if (contains(obj) != true)
        { return false;}
        remove(indexOf(obj));
        return true;
            
    }
    
    /**
     Sets a specific location to have a specific generic
     
     @param index Location to be set
     @param obj Generic to be set
     
     @return E ?? //is this supposed to be what was there before or what we just put in
     
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E obj)
    {
        if (data[index] == null)
        {
            size++;
        }
        data[index] = obj;
        return (E)(obj);
        //if the index is out of the bounds of the vector
        
    }
    
    /**
     Returns size (number of generics in data vector)
     @return int Number of generics in data vector (size)
     */
	
	public int size()
	{
		return size;
	}
    
    /**
     Clears the vector.
     
     @return void
     */
    
    public void clear()
    {
        size = 0;
        //remove all items
    }
    
    /**
     Sees whether the vector is empty or not.
     @return boolean Whether or not the vector is empty 
     */
    
    public boolean isEmpty()
    {
        if (size == 0)
            return true;
        return false;
        
    }
    
    /**
     Sees whether the vector contains a specific generic or not.
     @return boolean Whether or not the given generic exists in the vector  
     */
    public boolean contains(E obj)
    {
        if (indexOf(obj) != -1)
        {
        	return true;
        }
        return false;
        //for (int x = 0; x<size; x++)
        //{
            //if (data[x] == obj)
            //{
                //return true;
            //}
        //}
        //return false;
    }
    
    /**
     Returns index of given generic.
     @return int Index of given generic
     */
    
    public int indexOf(E obj)
    {
        //if object does not exist --> cannot return int
        for (int x = 0; x<= size; x++)
        {
            if (data[x] == null || obj == null)
            {
            	if (data[x] == obj)
            	{return x;}
            }
            else if (data[x].equals(obj))
            {
                return x;
            }
        }
        return -1;
    }
    
    /**
     How the vector will be printed.
     
     @return String Vector in print format
     */
    
	public String toString()
	{
		String s = "[";
		for (int x = 0; x<size; x++)
		{
			s += data[x] + " ";
		}
		s += "]";
		return s;
	}
    
    /**
     Makes an iterator for this class.
     @return Iterator<E> Iterator made.
     */
    
    public Iterator<E> iterator()
    {
    	return new VectorIterator<E>(this);    
    }
    
    public static void main(String[] args)
    {
        Vector<String> v = new Vector<String>();
        v.add("first");
        v.add("second");
        v.add("third");
        System.out.println(v);
        System.out.println(v.get(0));
        v.set(2, "newthird");
        System.out.println(v);
        v.remove(2);
        System.out.println(v);
        v.remove("second");
        System.out.println(v);
        v.clear();
        System.out.println(v);
        //v.add(7, "seventh");
        //System.out.println(v);
        System.out.println(v.indexOf("first"));
        System.out.println(v.contains("first"));
    }

	

}



//errors thrown: grow, index out of bounds, class exception
