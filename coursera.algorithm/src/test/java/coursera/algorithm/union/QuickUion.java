package coursera.algorithm.union;

import coursera.algorithm.util.Util;

public class QuickUion extends QuickFinder {
	private int count;
	public QuickUion(int[] source) {
		super(source);
	}

	@Override
	protected boolean isSame(int p, int q) {
		return super.isSame(p, q);
	}

	@Override
	public void union(int p, int q) {
		if(isSame(p, q)) return;
		int rootP = root(p);
		int rootQ = root(q);
		getArray()[rootP] = rootQ;
		count--;
	}

	@Override
	public int find(int p) {
		return root(p);
	}
	
	protected int root(int p){
		int id = p;
		while(id != getArray()[id]){
			id = getArray()[id];
		}
		
		return id;
	}

	@Override
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public String getFinalResult(String delimeter) {
		return Util.collected(getArray(), delimeter);
	}

}
