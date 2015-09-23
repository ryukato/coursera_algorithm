package coursera.algorithm.stack;

public class FixedCapacityStack<T> implements Stack<T> {
	private final Object[] array;
	private int size;
	
	
	public FixedCapacityStack(int capacity) {
		super();
		this.array = new Object[capacity];
	}

	@Override
	public void push(T item) {
		this.array[size++] = item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		final T t = (T)this.array[--size];
		this.array[size] = null; //for GC
		return t; //length -1
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

}
