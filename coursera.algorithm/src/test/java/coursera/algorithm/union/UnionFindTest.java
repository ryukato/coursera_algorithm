package coursera.algorithm.union;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import coursera.algorithm.util.Util;

public class UnionFindTest {
	private final String COMMA = ", ";
	
	@Test
	public void test_quick_find(){
		int [] source = new int[10];
		for(int i=0; i < source.length; i++){
			source[i] = i;
		}
		UnionFinder uf = new QuickFinder(source);
		uf.union(3, 4);
		uf.union(4, 9);
		uf.union(8, 0);
		uf.union(2, 3);
		uf.union(5, 6);
		uf.union(5, 9);
		uf.union(7, 3);
		uf.union(4, 8);
		uf.union(6, 1);

		assertEquals(buildExpectedResult(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}), uf.getFinalResult(COMMA));
	}
	
	@Test
	public void test_quick_union(){
		int [] source = new int[10];
		for(int i=0; i < source.length; i++){
			source[i] = i;
		}
		UnionFinder uf = new QuickUion(source);
		uf.union(3, 4);
		uf.union(4, 9);
		uf.union(8, 0);
		uf.union(2, 3);
		uf.union(5, 6);
		uf.union(5, 9);
		uf.union(7, 3);
		uf.union(4, 8);
		uf.union(6, 1);

		assertEquals(buildExpectedResult(new int[]{1, 1, 9, 4, 9, 6, 9, 9, 0, 0}), uf.getFinalResult(COMMA));
	}
	
	@Test
	public void test_weighted_quick_union(){
		int [] source = new int[10];
		for(int i=0; i < source.length; i++){
			source[i] = i;
		}
		UnionFinder uf = new WeightedQuickUion(source);
		uf.union(3, 4);
		uf.union(4, 9);
		uf.union(8, 0);
		uf.union(2, 3);
		uf.union(5, 6);
		uf.union(5, 9);
		uf.union(7, 3);
		uf.union(4, 8);
		uf.union(6, 1);

		assertEquals(buildExpectedResult(new int[]{8, 3, 3, 3, 3, 3, 5, 3, 3, 3}), uf.getFinalResult(COMMA));
	}
	
	@Test
	public void test_weighted_n_path_compression_quick_union(){
		int [] source = new int[10];
		for(int i=0; i < source.length; i++){
			source[i] = i;
		}
		UnionFinder uf = new WeightedPathCompressionQuickUion(source);
		uf.union(3, 4);
		uf.union(4, 9);
		uf.union(8, 0);
		uf.union(2, 3);
		uf.union(5, 6);
		uf.union(5, 9);
		uf.union(7, 3);
		uf.union(4, 8);
		uf.union(6, 1);

		assertEquals(buildExpectedResult(new int[]{8, 3, 3, 3, 3, 3, 3, 3, 3, 3}), uf.getFinalResult(COMMA));
	}
	
	private String buildExpectedResult(int[] array){
		return Util.collected(array, COMMA);
	}
	
}
