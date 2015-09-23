package coursera.algorithm.union;

import java.util.Arrays;

public class WeightedQuickUion extends QuickUion {
	final int[] sizeOfTree;
	private int count;
	public WeightedQuickUion(int[] source) {
		super(source);
		sizeOfTree = new int[source.length];
		
		Arrays.setAll(sizeOfTree, x -> 1);
	}
	
	@Override
	public void union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		int sizeP = this.sizeOfTree[rootP];
		int sizeQ = this.sizeOfTree[rootQ];
		if(sizeP < sizeQ){
			getArray()[p] = rootQ;
			this.sizeOfTree[rootQ] += sizeP;
		}else{
			getArray()[q] = rootP;
			this.sizeOfTree[rootP] += sizeQ;
		}
		count--;
	}

	@Override
	public int count() {
		return this.count;
	}
}
