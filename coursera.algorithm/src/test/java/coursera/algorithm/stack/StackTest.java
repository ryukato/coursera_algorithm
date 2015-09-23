package coursera.algorithm.stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {
	@Test
	public void test_linked_list_stack(){
		Stack<String> stack = new LinkedListStack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		
		assertFalse(stack.isEmpty());
		assertEquals("C", stack.pop());
		assertEquals("B", stack.pop());
		assertEquals("A", stack.pop());
	}
	
	@Test
	public void test_fixed_capacity_list_stack(){
		Stack<String> stack = new FixedCapacityStack<String>(10);
		stack.push("A");
		stack.push("B");
		stack.push("C");
		
		assertFalse(stack.isEmpty());
		assertEquals("C", stack.pop());
		assertEquals("B", stack.pop());
		assertEquals("A", stack.pop());
	}
}
