import java.util.*;

public class MyStack<T> implements StackInterface<T> {

	private List<T> arr;
	private int maxSize = -1;

	/**
	 * Creates an empty stack with size size.
	 * 
	 * @param size the size of the stack.
	 */
	public MyStack(int size) {
		arr = new ArrayList<>(size);
		maxSize = size;
	}

	/**
	 * Creates an empty stack.
	 */
	public MyStack() {
		arr = new ArrayList<>();
	}

	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	 * is the first bottom element of the Stack
	 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	 * list reference within your Stack, you will be allowing direct access to the data of
	 * your Stack causing a possible security breach.
	 * 
	 * @param <T>
	 * 
	 * @param list elements to be added to the Stack from bottom to top
	 * @throws StackOverflowException if stack gets full
	 */
	public void fill(ArrayList<T> list) throws StackOverflowException {
		ArrayList<T> copy = new ArrayList<T>();
		copy.addAll(list);

		//Check if arr has space for the new arraylist.
		if (maxSize == -1 || maxSize > (arr.size() + copy.size()) ) {
			for(int i = 0; i < copy.size(); i++) {
				arr.add(copy.get(i));
			}
		} else {
			throw new StackOverflowException("Not enough space in the Stack.");
		}
	}

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return arr.isEmpty();
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		if (arr.size() == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the item at the top of this stack.
	 *
	 * @return the item at the top of this stack
	 */
	public T pop() throws StackUnderflowException {
		if (arr.isEmpty()) {
			throw new StackUnderflowException("There's nothing to remove!");
		}
		return arr.remove(arr.size()-1);
	}

	/**
	 * Adds an element to the top of the Stack
	 * 
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */

	public boolean push(T e) throws StackOverflowException {
		if (maxSize == -1 || maxSize > arr.size()) {
			arr.add(e);
			return true;
		} else {
			throw new StackOverflowException("The Stack is full!");
		}
	}

	/**
	 * Returns the number of elements on this stack.
	 *
	 * @return the size of this stack
	 */
	public int size() {
		return arr.size();
	}

	/**
	 * Returns the item at the top of this stack.  That item is not
	 * removed.
	 *
	 * @return the item at the top of this stack
	 */
	public T top() throws StackUnderflowException {
		if (arr.isEmpty()) {
			throw new StackUnderflowException("There's nothing in the Stack!");
		}

		return arr.get(arr.size() - 1);
	}

	/**
	 * Returns the string representation of the elements in the Stack, 
	 * the beginning of the string is the front of the Stack
	 * Place the delimiter between all elements of the Stack
	 * 
	 * @param delimiter the delimiter between all elements.
	 * @return string representation of the Stack with elements separated with the delimiter
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
	/**
	 * Returns the string representation of the elements in the Stack, 
	 * the beginning of the string is the front of the queue
	 * 
	 * @return string representation of the Stack with elements
	 */
	@Override
	public String toString() {
		String s =  "";
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i);
		}
		return s;
	}


}
