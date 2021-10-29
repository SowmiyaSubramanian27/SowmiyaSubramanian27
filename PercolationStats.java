import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	// perform independent trials on an n-by-n grid
	private final int t;
	private final double[] results;
	private static double CONF = 1.96;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		this.t = trials;

		results = runMonteCarlo(n);
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(results);

	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(results);

	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - ((CONF * stddev()) / Math.sqrt(t));

	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + ((CONF * stddev()) / Math.sqrt(t));

	}

	private double[] runMonteCarlo(int n) {
		double[] results = new double[t];
		for (int i = 0; i < t; i++) {
			results[i] = runMonteCarloInstance(n);
		}
		return results;
	}

	private double runMonteCarloInstance(int n) {
		Percolation percolation = new Percolation(n);
		double openSites = 0.0;
		while (!percolation.percolates()) {
			int i = StdRandom.uniform(1, n + 1);
			int j = StdRandom.uniform(1, n + 1);
			if (!percolation.isOpen(i, j)) {
				percolation.open(i, j);
				openSites++;
			}
		}
		return openSites / (n * n);
	}

	public static void main(String[] arg) {
		int n = 0;
		int t = 0;
		if (arg.length == 2) {
			if (!StdIn.isEmpty())
				n = StdIn.readInt();
			if (!StdIn.isEmpty())
				t = StdIn.readInt();
			PercolationStats stats = new PercolationStats(n, t);
			StdOut.println("mean = " + stats.mean());
			StdOut.println("stddev = " + stats.stddev());
			StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
		}

	}

}
