package hr.fer.zemris.java.custom.collections;

/**
 * This class acts like an interface to subclasses
 * 
 * @author Ante Spajic
 *
 */
public class Collection {

	/**
	 * Default empty constructor
	 */
	protected Collection() {

	}

	/**
	 * Returns <tt>true</tt> if this collection contains no elements.
	 *
	 * @return <tt>true</tt> if this collection contains no elements
	 */
	public boolean isEmpty() {
		return true;
	}

	/**
	 * Returns the number of elements in this collection. If this collection
	 * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this collection
	 */
	public int size() {
		return 0;
	}

	/**
	 * Ensures that this collection contains the specified element. Allows
	 * adding duplicate elements.
	 *
	 * @param value
	 *            element whose presence in this collection is to be ensured
	 * @throws NullPointerException
	 *             if the specified element is null and this collection does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of the element prevents it from being added
	 *             to this collection
	 */
	public void add(Object value) {

	}

	/**
	 * Returns <tt>true</tt> if this collection contains the specified element.
	 * More formally, returns <tt>true</tt> if and only if this collection
	 * contains at least one element such that it meets that objects equality
	 * conditions.
	 *
	 * @param value
	 *            element whose presence in this collection is to be tested
	 * @return <tt>true</tt> if this collection contains the specified element
	 * @throws NullPointerException
	 *             if the specified element is null and this collection does not
	 *             permit null elements
	 */
	public boolean contains(Object value) {
		return false;
	}

	/**
	 * Removes a single instance of the specified element from this collection,
	 * if it is present (optional operation). More formally, removes an element
	 * value such that it meets that objects equality conditions, if this
	 * collection contains one or more such elements only the first one is
	 * removed. Returns <tt>true</tt> if this collection contained the specified
	 * element (or equivalently, if this collection changed as a result of the
	 * call).
	 *
	 * @param value
	 *            element to be removed from this collection, if present
	 * @return <tt>true</tt> if an element was removed as a result of this call
	 * @throws IllegalArgument
	 *             if the specified element is null and this collection does not
	 *             permit null elements
	 */
	public boolean remove(Object value) {
		return false;
	}

	/**
	 * Returns an array containing all of the elements in this collection.
	 *
	 * This method acts as bridge between array-based and collection-based APIs.
	 *
	 * @return an array containing all of the elements in this collection
	 * @throws UnsupportedOperationException
	 *             If Method hasn't been overridden and implemented
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method calls processor.process(.) for each element of this collection.
	 * 
	 * @param processor
	 *            Processor to process all the elements from collection.
	 */
	public void forEach(Processor processor) {

	}

	/**
	 * Adds all of the elements in the specified collection to this collection.
	 * behavior of this call is undefined if the specified collection is this
	 * collection, and this collection is nonempty.)
	 *
	 * @param other
	 *            collection containing elements to be added to this collection
	 * @throws NullPointerException
	 *             if the specified collection contains a null element and this
	 *             collection does not permit null elements, or if the specified
	 *             collection is null
	 * @throws IllegalArgumentException
	 *             if some property of an element of the specified collection
	 *             prevents it from being added to this collection
	 * @see #add(Object)
	 */
	public void addAll(Collection other) {
		Processor procesor = new Processor() {
			@Override
			public void process(Object value) {
				Collection.this.add(value);
			}
		};
		other.forEach(procesor);
	}

	/**
	 * Removes all of the elements from this collection. The collection will be
	 * empty after this method returns.
	 *
	 */
	public void clear() {

	}
}
