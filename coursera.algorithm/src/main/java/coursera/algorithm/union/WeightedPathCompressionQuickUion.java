package coursera.algorithm.union;

public class WeightedPathCompressionQuickUion extends WeightedQuickUion {

	public WeightedPathCompressionQuickUion(int[] source) {
		super(source);
	}

	@Override
	protected int root(int p) {
		int root = p;
		while(root != getArray()[root]){
			//getArray()[root] = getArray()[getArray()[root]];
			getArray()[root] = getArray()[root(getArray()[root])];
			root = getArray()[root];
		}
		return root;
	}

	

}
