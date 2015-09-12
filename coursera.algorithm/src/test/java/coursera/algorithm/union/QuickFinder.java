package coursera.algorithm.union;

import coursera.algorithm.util.Util;

public class QuickFinder implements UnionFinder {
	private final int[] array;
	private int count;
	
	protected int[] getArray(){
		return this.array;
	}
	public QuickFinder(int[] source) {
		array = source;
		count = this.array.length;
	}

	@Override
	public void union(int p, int q) {
		if(isSame(p, q)){
			return;
		}
		final int pId = this.array[p];
		final int qId = this.array[q];
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] == pId){
				this.array[i] = qId;
			}
		}
		count--;
	}
	
	protected boolean isSame(int p, int q){
		final int pId = this.array[p];
		final int qId = this.array[q];
		if((p == q) || (pId == qId)){
			return true;
		}
		return false;
	}

	@Override
	public int find(int p) {
		return this.array[p];
	}

	@Override
	public boolean isConnected(int p, int q) {
		return (this.array[p] == this.array[q]);
	}

	@Override
	public int count() {
		return this.count;
	}

	@Override
	public String getFinalResult(String delimeter) {
		return Util.collected(array, delimeter);
	}

}
