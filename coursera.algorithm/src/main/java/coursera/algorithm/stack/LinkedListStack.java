package coursera.algorithm.stack;

public class LinkedListStack<T> implements Stack<T> {
	private Node first;
	@Override
	public void push(T t) {
		Node oldNode = first;
		first = new Node();
		first.t = t;
		first.next = oldNode;
	}

	@Override
	public T pop() {
		if(isEmpty()){
			return null;
		}
		T item = first.t;
		first = first.next;
		return item;
		
	}

	@Override
	public boolean isEmpty() {
		return (first == null);
	}
	
	private class Node{
		private T t;
		private Node next;
	}

}
