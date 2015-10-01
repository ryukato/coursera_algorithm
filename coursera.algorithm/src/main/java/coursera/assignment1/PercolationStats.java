package coursera.assignment1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private static final double COEFFICIENT = 1.96;
	private final double[] thresholds;
	private final int experiments;
	private final int gridRowSize;

	public PercolationStats(int N, int T) {
		validateRange(N);	
		validateRange(T);
			
		thresholds = new double[T];
		this.experiments = T;
		this.gridRowSize = N;

		for (int i = 0; i < T; i++) {
			thresholds[i] = getPercolationThreshold();
		}
	}

	private void validateRange(int input) {
		if (input <= 0){
			throw new java.lang.IllegalArgumentException(String.format("input is out of bounds, given input: %d", input));
		}
	}

	private double getPercolationThreshold() {
		Percolation percolation = new Percolation(gridRowSize);
		int i = 1, j = 1;
		int count = 0;
		
		for(;!percolation.percolates();){
			while(percolation.isOpen(i, j)){
				i = StdRandom.uniform(gridRowSize) + 1;
				j = StdRandom.uniform(gridRowSize) + 1;	
			}
			count++;
			percolation.open(i, j);
		}
		final double result = count/(Math.pow(gridRowSize,2));
		return result;
	}

	public double mean() {
		return StdStats.mean(thresholds);
	}

	public double stddev() {
		return StdStats.stddev(thresholds);
	}

	public double confidenceLo() {
		return mean() - COEFFICIENT * stddev() / Math.sqrt(experiments);
	}

	public double confidenceHi() {
		return mean() + COEFFICIENT * stddev() / Math.sqrt(experiments);
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(N, T);
		StdOut.printf("mean = %f\n", stats.mean());
		StdOut.printf("stddev = %f\n", stats.stddev());
		StdOut.printf("95%% confidence interval = %f, %f\n",
				stats.confidenceLo(), stats.confidenceHi());
	}
}
