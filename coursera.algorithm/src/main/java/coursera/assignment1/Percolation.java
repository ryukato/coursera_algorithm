package coursera.assignment1;


public class Percolation {
	private final int OPEN = 1;
	private final int BLOCKED = 0;
	private final int[] grid;
	private final int N;
	private final int[] sizeOfTree;
	
	public Percolation(int N){
		checkInitN(N);
		this.N = N;
		int totalSize = N*N;
		grid = new int[totalSize];
		sizeOfTree = new int[totalSize];
		
		initGrid(totalSize);
	}

	private void initGrid(int totalSize) {
		for(int i = 0; i < totalSize; i++){
			grid[i] = BLOCKED;
			sizeOfTree[i] = 1;
		}
	}
	
	public void open(int i, int j){
		checkRange(i, j);
		int index = getIndex(i, j);
		if(this.grid[index] != OPEN){
			this.grid[index] = OPEN;
		}
	}
	
	public void union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		int sizeP = this.sizeOfTree[rootP];
		int sizeQ = this.sizeOfTree[rootQ];
		if(sizeP < sizeQ){
			this.grid[p] = rootQ;
			this.sizeOfTree[rootQ] += sizeP;
		}else{
			this.grid[q] = rootP;
			this.sizeOfTree[rootP] += sizeQ;
		}
	}
	
	public boolean isOpen(int i, int j) {
		checkRange(i, j);
		int index = getIndex(i, j);
		return (this.grid[index]  == OPEN);
	}

	private int getIndex(int i, int j) {
		int index = ((j -1) * N) + i - 1;
		return index;
	}
	
	public boolean isFull(int i, int j) {
		checkRange(i, j);
		final int index = getIndex(i, j);
		int root = root(index);
		return (this.grid[root] == OPEN && root >= 0  && root <= N -1);
	}
	
	private int root(int p) {
		if(p == 0)return 0;
		int root = p;
		while((root != this.grid[root]) && (this.grid[root] == OPEN)){
			this.grid[root] = this.grid[root(this.grid[root])];
			root = this.grid[root];
		}
		return root;
	}
	
	private void checkInitN(int N){
		if(N < 1){
			throw new java.lang.IllegalArgumentException("N has to be greater than 0");
		}
	}
	private void checkRange(int i, int j){
		if((i < 1 || j < 1)){
			throw new IndexOutOfBoundsException("Index i, j have to be greater than 0, i="+i + "j="+j);
		}
		if((i -1 >= N  || j -1 >= N)){
			throw new IndexOutOfBoundsException("Index i, j have to be less than "+N +",i="+i + "j="+j);
		}
	}

	public boolean percolates() {
		if(N ==1){
			for(int i = 0; i < N; i++){
				if(this.grid[i] == OPEN){
					return true;
				}
			}
			return false;
		}
		
		for(int j = 1; j <= N; j++){
			if(isFull(N, j)){
				return true;
			}
		}
		return false;
	}

	

	
}
