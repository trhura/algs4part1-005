public class PercolationStats {
    private double[] percolationThresholds;
    private int N;
    private int T;

    public PercolationStats(int N, int T) {
	// perform T independent computational experiments on an N-by-N grid
	if (T <= 0 || N <= 0) {
	    throw new IllegalArgumentException();
	}

	this.T = T;
	this.N = N;

	percolationThresholds = new double[T];
	for (int i = 0; i < T; i++) {
	    Percolation percolation = new Percolation(N);
	    int opensites = 0;
	    while (!percolation.percolates()) {
		/// Get a random closed site
		int row, col;
		do {
		    row = StdRandom.uniform(1, N+1);
		    col = StdRandom.uniform(1, N+1);
		} while (percolation.isOpen(row, col));

		percolation.open(row, col);
		opensites++;
	    }

	    percolationThresholds[i] = opensites/((double) N*N);
	}
    }

    public double mean() {
	// sample mean of percolation threshold
	return StdStats.mean(percolationThresholds);
    }

    public double stddev() {
	// sample standard deviation of percolation threshold
	return StdStats.stddev(percolationThresholds);
    }

    public double confidenceLo() {
	// returns lower bound of the 95% confidence interval
	return mean() - (1.96*stddev()/Math.sqrt(T));
    }

    public double confidenceHi() {
	// returns upper bound of the 95% confidence interval
	return mean() + (1.96*stddev()/Math.sqrt(T));
    }

    public static void main(String[] args) {
	// test client, described below
	int N = Integer.parseInt(args[0]);
	int T = Integer.parseInt(args[1]);

	PercolationStats stats = new PercolationStats(N, T);
	StdOut.printf("mean                    = %f\n", stats.mean());
	StdOut.printf("stddev                  = %f\n", stats.stddev());
	StdOut.printf("95%% confidence interval = %f, %f\n", stats.confidenceLo(), stats.confidenceHi());
    }
}
