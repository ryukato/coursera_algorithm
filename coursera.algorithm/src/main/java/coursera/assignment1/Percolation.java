package coursera.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	private final boolean[] sites;
	private final int N;
	private final WeightedQuickUnionUF unionFinder;
	private final int topIndex;
	private final int bottomIndex;
	public Percolation(int N){
		validatePositive(N);
		this.N = N;
		int totalSize = N*N;
		unionFinder = new WeightedQuickUnionUF(totalSize + 2);
		sites = new boolean[totalSize];
		topIndex = totalSize;
		bottomIndex = totalSize +1;
		initGrid(totalSize);
	}

	private void initGrid(int totalSize) {
		for(int i = 0; i < totalSize; i++){
			sites[i] = Boolean.FALSE;
		}
	}
	
	public void open(int i, int j){
		validateInvalidIndex(i, j);
		int index = getIndex(i, j);
		if(!isOpen(i, j)){
			this.sites[index] = Boolean.TRUE;
			
			if(N == 1){
				this.unionFinder.union(index, bottomIndex);
				return;
			}
			connectToLeftIfOpen(i, j);
			connectToRightIfOpen(i, j);
			connectToUpIfOpen(i, j);
			connectToBelowIfOpen(i, j);
			
			if (1 == i) {
                this.unionFinder.union(index, topIndex);
            }
			
			int gridSize = N *N;
			for (int b = gridSize-1; b >= gridSize-N; b--) {
                if (this.sites[b] && this.unionFinder.connected(topIndex, b)) {
                	this.unionFinder.union(b, bottomIndex);
                    break;
                }
            }
		}
	}

	private void connectToBelowIfOpen(int i, int j) {
		if(i - 1 > 0 && isOpen(i - 1, j)){
			union(getIndex(i - 1, j), getIndex(i, j));
		}
	}

	private void connectToUpIfOpen(int i, int j) {
		if(i + 1 <= N && isOpen(i + 1, j)){
			union(getIndex(i, j), getIndex(i + 1, j));
		}
	}

	private void connectToRightIfOpen(int i, int j) {
		if(j + 1 <= N && isOpen(i, j + 1)){
			union(getIndex(i, j), getIndex(i, (j + 1)));
		}
	}

	private void connectToLeftIfOpen(int i, int j) {
		if(j - 1 > 0 && isOpen(i, j -1)){
			union(getIndex(i, j), getIndex(i, (j - 1)));
		}
	}
	
	public void union(int p, int q) {
		unionFinder.union(p, q);
	}
	
	public boolean isOpen(int i, int j) {
		validateInvalidIndex(i, j);
		int index = getIndex(i, j);
		return (this.sites[index]);
	}

	private int getIndex(int i, int j) {
		int index = ((i -1) * N) + j - 1;
		return index;
	}
	
	public boolean isFull(int i, int j) {
		validateInvalidIndex(i, j);
		return this.unionFinder.connected(topIndex, getIndex(i, j));
	}
	
	private void validatePositive(int N){
		if(N < 1){
			throw new java.lang.IllegalArgumentException("N has to be greater than 0");
		}
	}
	private void validateInvalidIndex(int i, int j){
		if((i < 1 || j < 1)){
			throw new IndexOutOfBoundsException("Index i, j have to be greater than 0, i="+i + "j="+j);
		}
		if((i -1 >= N  || j -1 >= N)){
			throw new IndexOutOfBoundsException("Index i, j have to be less than "+N +",i="+i + "j="+j);
		}
	}

	public boolean percolates() {
		if(N ==1){
			return isOpen(1, 1);
		}
		System.out.println("parent of topIndex: "+ this.unionFinder.find(topIndex));
		System.out.println("parent of bottomIndex: "+ this.unionFinder.find(bottomIndex));
		return this.unionFinder.connected(topIndex, bottomIndex);
	}

	

	
}
