import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] map;
	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF ufr;
	private final int n;
	private int openCount = 0;
	private final int sqn;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n < 1)
			throw new IllegalArgumentException();
		this.n = n;
		this.sqn = n * n;
		this.uf = new WeightedQuickUnionUF((sqn) + 2);
		this.ufr = new WeightedQuickUnionUF((sqn) + 2);
		map = new boolean[n + 2][n + 2];
	}

	
	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException();
		if (map[row][col])
			return;
		else {
			map[row][col] = true;
			openCount++;
			int val = getVal(row, col);
			if (row != 1)
				if (isOpen(row - 1, col)) {
					uf.union(val, val - n);
					ufr.union(val, val - n);
				}
			if (col != 1)
				if (isOpen(row, col - 1)) {
					uf.union(val, val - 1);
					ufr.union(val, val - 1);
				}
			if (col != n)
				if (isOpen(row, col + 1)) {
					uf.union(val, val + 1);
					ufr.union(val, val + 1);
				}
			if (row != n)
				if (isOpen(row + 1, col)) {
					uf.union(val, val + n);
					ufr.union(val, val + n);
				}
			if (row == 1) {
				uf.union(val, sqn);
				if (isFullr(row, col)) {
					ufr.union(val, sqn + 1);
					if (isFull(row, col))
						uf.union(sqn, sqn + 1);
				}

			}
			if (row == n) {
				ufr.union(val, sqn);
				if (isFull(row, col)) {
					uf.union(val, sqn + 1);
				}
			}
		}

	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException();
		return map[row][col];

	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException();
		if (n == 1 || row == 1)
			return isOpen(row, col);
		if (isOpen(row, col)) {
			if (uf.find(getVal(row, col)) == uf.find(sqn))
				return true;
		}
		return false;
	}

	private boolean isFullr(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException();
		if (n == 1 || row == n)
			return isOpen(row, col);
		if (isOpen(row, col)) {
			if (ufr.find(getVal(row, col)) == ufr.find(sqn))
				return true;
		}
		return false;
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return openCount;
	}

	// does the system percolate?
	public boolean percolates() {
		if (n == 1)
			return isOpen(1, 1);
		if (uf.find(sqn) == uf.find(sqn + 1))
			return true;
		return false;

	}

	private int getVal(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException();
		return (row * n) - (n - col) - 1;
	}

}