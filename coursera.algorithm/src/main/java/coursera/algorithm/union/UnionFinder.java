package coursera.algorithm.union;

public interface UnionFinder {

	void union(int p, int q);

	int find(int p);

	boolean isConnected(int p, int q);

	int count();
	
	String getFinalResult(String delimeter);

}
