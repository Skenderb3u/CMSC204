import java.util.*;

public class MyQueue<T> implements QueueInterface<T> {

	private List<T> arr;
	private int maxSize = -1;

	/**
	 * Creates an empty queue of size size.
	 * 
	 * @param size the size of the queue.
	 */
	public MyQueue(int size) {
		arr = new ArrayList<>(size);
		maxSize = size;
	}

	/**
	 * Creates an empty queue.
	 */
	public MyQueue() {
		arr = new ArrayList<>();
	}

	/**
	 * Removes the item at the front of this queue.
	 *
	 * @return the item at the front of this queue
	 */
	public T dequeue() throws QueueUnderflowException {
		if (arr.isEmpty()) {
			throw new QueueUnderflowException("There's nothing to remove!");
		}
		return arr.remove(0);
	}

	/**
	 * Adds an item to the end of this queue.
	 *
	 * @param e the item to add
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if (maxSize == -1 || maxSize > arr.size()) {
			arr.add(e);
			return true;
		} else {
			throw new QueueOverflowException("The Queue is full!");
		}
	}

	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	 * is the first element in the Queue
	 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	 * list reference within your Queue, you will be allowing direct access to the data of
	 * your Queue causing a possible security breech.
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full

	 */
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		ArrayList<T> copy = new ArrayList<T>();
		copy.addAll(list);

		//Check if arr has space for the new arraylist.
		if (maxSize == -1 || maxSize > (arr.size() + copy.size()) ) {
			for(int i = 0; i < copy.size(); i++) {
				arr.add(copy.get(i));
			}
		} else {
			throw new QueueOverflowException("Not enough space in the Queue.");
		}
	}

	/**
	 * Determines if the Queue is Empty
	 * 
	 * @return true if Queue is Empty, false if not
	 */
	public boolean isEmpty() {
		return arr.isEmpty();
	}

	/**
	 * Determines if the Queue is Full
	 * 
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		if (arr.size() == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of elements on this queue.
	 *
	 * @return the size of this queue
	 */
	public int size() {
		return arr.size();
	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String s =  "";
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i);
		}
		return s;
	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * 
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String s =  "";
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i);
			if (i < arr.size() - 1) {
				s += delimiter;
			}
		}
		return s;
	}

}
