package coursera.assignment1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	private final boolean[] sites;
	private final int gridRowSize;
	private final WeightedQuickUnionUF unionFinder;
	private final int imaginaryTopIndex;
	private final int imaginaryBottomIndex;
	
	public Percolation(int N){
		validatePositive(N);
		this.gridRowSize = N;
		int totalSize = N*N;
		unionFinder = new WeightedQuickUnionUF(totalSize + 2);
		sites = new boolean[totalSize];
		imaginaryTopIndex = totalSize;
		imaginaryBottomIndex = totalSize +1;
		initSiteWithDefault(totalSize);
	}
	
	public void open(int i, int j){
		validateInvalidIndex(i, j);
		int index = getIndex(i, j);
		if(!isOpen(i, j)){
			openSite(index);
			
			if(gridRowSize == 1){
				this.unionFinder.union(index, imaginaryBottomIndex);
				return;
			}
			connectToLeftIfOpen(i, j);
			connectToRightIfOpen(i, j);
			connectToUpIfOpen(i, j);
			connectToBelowIfOpen(i, j);
			connectToTopIfTopRow(i, index);
			connectToOpenNeighbors();
		}
	}

	public void union(int p, int q) {
		unionFinder.union(p, q);
	}
	
	public boolean isOpen(int i, int j) {
		validateInvalidIndex(i, j);
		int index = getIndex(i, j);
		return isOpenSite(index);
	}

	public boolean isFull(int i, int j) {
		validateInvalidIndex(i, j);
		final int index = getIndex(i, j);
		if(isTopRow(index) && isOpenSite(index)){
			return Boolean.TRUE;
		}
		return this.unionFinder.connected(imaginaryTopIndex, index);
	}

	public boolean percolates() {
		if(gridRowSize == 1){
			return isOpen(1, 1);
		}
		return this.unionFinder.connected(imaginaryTopIndex, imaginaryBottomIndex);
	}
	
	private void initSiteWithDefault(int totalSize) {
		for(int i = 0; i < totalSize; i++){
			sites[i] = Boolean.FALSE;
		}
	}
	
	private boolean isTopRow(final int index) {
		return index <= gridRowSize;
	}
	
	private void openSite(int index) {
		this.sites[index] = Boolean.TRUE;
	}
	
	private boolean isOpenSite(final int index) {
		return this.sites[index];
	}
	
	private int getIndex(int i, int j) {
		int index = ((i -1) * gridRowSize) + j - 1;
		return index;
	}
	
	private void connectToTopIfTopRow(int i, int index) {
		if (1 == i) {
		    this.unionFinder.union(index, imaginaryTopIndex);
		}
	}

	private void connectToOpenNeighbors() {
		int gridSize = gridRowSize *gridRowSize;
		for (int b = gridSize-1; b >= gridSize-gridRowSize; b--) {
		    if (isOpenSite(b) && this.unionFinder.connected(imaginaryTopIndex, b)) {
		    	this.unionFinder.union(b, imaginaryBottomIndex);
		        break;
		    }
		}
	}

	private void connectToBelowIfOpen(int i, int j) {
		if(i - 1 > 0 && isOpen(i - 1, j)){
			union(getIndex(i - 1, j), getIndex(i, j));
		}
	}

	private void connectToUpIfOpen(int i, int j) {
		if(i + 1 <= gridRowSize && isOpen(i + 1, j)){
			union(getIndex(i, j), getIndex(i + 1, j));
		}
	}

	private void connectToRightIfOpen(int i, int j) {
		if(j + 1 <= gridRowSize && isOpen(i, j + 1)){
			union(getIndex(i, j), getIndex(i, (j + 1)));
		}
	}

	private void connectToLeftIfOpen(int i, int j) {
		if(j - 1 > 0 && isOpen(i, j -1)){
			union(getIndex(i, j), getIndex(i, (j - 1)));
		}
	}
	
	private void validatePositive(int N){
		if(N < 1){
			throw new java.lang.IllegalArgumentException("N has to be greater than 0");
		}
	}
	
	private void validateInvalidIndex(int i, int j){
		if((i < 1 || j < 1)){
			throw new IndexOutOfBoundsException("Index i, j have to be greater than 0, i="+i + ", j="+j);
		}
		if((i -1 >= gridRowSize  || j -1 >= gridRowSize)){
			throw new IndexOutOfBoundsException("Index i, j have to be less than "+gridRowSize +",i="+i + ", j="+j);
		}
	}
	
	public static void main(String[] args){
		final int N = 25;
		Percolation percolation = new Percolation(N);
		assertFalse("assert percolates after create Percolation is false", percolation.percolates());
		percolation.open(1, 7);
		percolation.open(1, 8);
		percolation.open(1, 9);
		percolation.open(1, 17);
		percolation.open(1, 18);
		percolation.open(1, 19);

		percolation.open(2, 5);
		percolation.open(2, 6);
		percolation.open(2, 7);
		assertTrue("assert isFull for i:2, j:5 is true", percolation.isFull(2, 5));
		percolation.open(2, 8);
		percolation.open(2, 9);
		assertTrue("assert isFull for i:2, j:7 is true", percolation.isFull(2, 7));
		assertFalse("assert percolates is false", percolation.percolates());
	}

	private static void assertTrue(String message, boolean result) {
		if(!result){
			throw new AssertionError("Fail");
		}
		StdOut.println(message);
	}

	private static void assertFalse(String message, boolean result) {
		assertTrue(message, !result);
	}
}
