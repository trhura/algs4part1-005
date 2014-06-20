public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[] gridStatus; // false => blocked, true => open
    private boolean[] connectedToBottom; // true => site connected to bottom site else false
    private int N;

    public Percolation(int N) {
	// create N-by-N grid, with all sites blocked
	if (N <= 0) {
	    throw new IllegalArgumentException();
	}

	this.N = N;
	this.grid = new WeightedQuickUnionUF(this.gridSize());
	this.gridStatus = new boolean[this.gridSize()];
	this.connectedToBottom = new boolean[this.gridSize()];

	//Connect first element to first row
	for (int q = gridPosition(1, 1); q <= gridPosition(1, N); q++) {
	    this.grid.union(0, q);
	}
    }

    public void open(int i, int j) {
	// open site (row i, column j) if it is not already
	if (isOpen(i, j)) {
	    return;
	}

	int p = gridPosition(i, j);
	this.gridStatus[p] = true;

	// set bottom sites to connected
	if (p >= gridPosition(N, 1)) {
	    this.connectedToBottom[this.grid.find(p)] = true;
	}

	if (i > 1 && isOpen(i-1, j)) {
	    // if the row above is open
	    int q = gridPosition(i-1, j);
	    if(this.connectedToBottom[this.grid.find(p)]) {
		this.connectedToBottom[this.grid.find(q)] = true;
	    }
	    this.grid.union(p, q);
	}

	if(i < N && isOpen(i+1, j)) {
	    // if the bottom row is open
	    int q = gridPosition(i+1, j);
	    if(this.connectedToBottom[this.grid.find(q)]) {
		this.connectedToBottom[this.grid.find(p)] = true;
	    }
	    this.grid.union(p, q);
	}

	if(j > 1 && isOpen(i, j-1)) {
	    // if the left row is open
	    int q = gridPosition(i, j-1);
	    if(this.connectedToBottom[this.grid.find(q)] || this.connectedToBottom[this.grid.find(p)]) {
		this.connectedToBottom[this.grid.find(p)] = true;
		this.connectedToBottom[this.grid.find(q)] = true;
	    }
	    this.grid.union(p, q);
	}

	if(j < N && isOpen(i, j+1)) {
	    // if the right row is open
	    int q = gridPosition(i, j+1);
	    if(this.connectedToBottom[this.grid.find(q)] || this.connectedToBottom[this.grid.find(p)]) {
		this.connectedToBottom[this.grid.find(p)] = true;
		this.connectedToBottom[this.grid.find(q)] = true;
	    }
	    this.grid.union(p, q);
	}
    }

    public boolean isOpen(int i, int j) {
	// is site (row i, column j) open?
	return this.gridStatus[gridPosition(i, j)];
    }

    public boolean isFull(int i, int j) {
	// is site (row i, column j) full?
	return this.isOpen(i, j) && this.grid.connected(0, gridPosition(i, j));
    }

    public boolean percolates() {
	// does the system percolate?
	return this.connectedToBottom[this.grid.find(0)];
    }

    private int gridSize() {
	return (this.N * this.N) + 1;
    }

    private int gridPosition(int i, int j) {
	if (i < 1 || i > this.N) {
	    throw new IndexOutOfBoundsException();
	}

	if (j < 1 || j > this.N) {
	    throw new IndexOutOfBoundsException();
	}

	int reali = i - 1;
	int realj = j - 1;
	return 1 + (reali * this.N) + realj;
    }
}
