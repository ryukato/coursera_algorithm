package coursera.assignment1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PercolationTest {
	@Test(expected=IllegalArgumentException.class)
	public void test_N_is_less_than_1(){
		new Percolation(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_open_x_index_is_less_than_1(){
		Percolation percolation = new Percolation(1);
		percolation.open(0, 1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_open_y_index_is_less_than_1(){
		Percolation percolation = new Percolation(1);
		percolation.open(1, 0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_open_x_index_is_greater_than_N(){
		int N = 1;
		Percolation percolation = new Percolation(N);
		percolation.open(2, 1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_open_y_index_is_greater_than_N(){
		int N = 1;
		Percolation percolation = new Percolation(N);
		percolation.open(1, 2);
	}
	
	@Test
	public void test_open(){
		int N = 1;
		Percolation percolation = new Percolation(N);
		percolation.open(1, 1);
		
		percolation = new Percolation(2);
		percolation.open(1, 1);
		percolation.open(1, 2);
		percolation.open(2, 1);
		percolation.open(2, 2);
	}
	
	
	@Test
	public void test_isOpen(){
		Percolation percolation = new Percolation(2);
		percolation.open(1, 1);
		boolean isOpen = percolation.isOpen(1, 1);
		assertTrue(isOpen);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_isFull_with_out_of_range(){
		Percolation percolation = new Percolation(1);
		boolean isOpen = percolation.isFull(1, 2); //1,2 is not open
		assertFalse(isOpen);
		
	}
	
	@Test
	public void test_isFull_with_N_is_2(){
		Percolation percolation = new Percolation(2);
		percolation.open(1, 1);
		boolean isOpen = percolation.isFull(1, 1);
		assertTrue(isOpen);
		
		isOpen = percolation.isFull(1, 2); //1,2 is not open
		assertFalse(isOpen);
		
		percolation.open(1, 2);
		isOpen = percolation.isFull(1, 2); //1,2 is not open
		assertTrue(isOpen);
	}
	
	@Test
	public void test_percolates_with_N_is_1(){
		Percolation percolation = new Percolation(1);
		assertFalse(percolation.percolates());
		percolation.open(1, 1);
		assertTrue(percolation.percolates());
	}
	
	@Test
	public void test_percolates_with_N_is_2(){
		Percolation percolation = new Percolation(2);
		assertFalse(percolation.percolates());
		percolation.open(1, 1);
		assertFalse(percolation.percolates());
		percolation.open(2, 1);
		assertTrue(percolation.percolates());
	}
}
