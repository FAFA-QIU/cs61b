package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    // Record if a block is open
    private int[] openChecker;
    // n represents virtual top, n + 1 represents virtual bottom
    private int n;
    private int openSite;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 1);
        openChecker = new int[N * N];
        n = N;
        openSite = 0;
    }

    // transfer given indices of row and column to 1d array index
    private int to1DIndex(int row, int col) {
        if (row > n || col > n) {
            throw new IndexOutOfBoundsException();
        }
        return row * n + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        int index = to1DIndex(row, col);
        openChecker[index] = 1;
        connectToNearby(row, col);
        openSite++;
    }

    // connect nearby blocks if they are opened
    private void connectToNearby(int row, int col) {
        if (row == 0) {
            uf.union(to1DIndex(row, col), n * n);
        }
//        if (row == n - 1) {
//            uf.union(to1DIndex(row, col), n * n + 1);
//        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            uf.union(to1DIndex(row, col), to1DIndex(row - 1, col));
        }
        if (row + 1 < n && isOpen(row + 1, col)) {
            uf.union(to1DIndex(row, col), to1DIndex(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            uf.union(to1DIndex(row, col), to1DIndex(row, col - 1));
        }
        if (col + 1 < n && isOpen(row, col + 1)) {
            uf.union(to1DIndex(row, col), to1DIndex(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = to1DIndex(row, col);
        return openChecker[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && uf.connected(to1DIndex(row, col), n * n);
    }

//    private boolean isConnectedToNearby(int row, int col) {
//        if (row == 0) {
//            return true;
//        }
//        boolean b1 = false;
//        boolean b2 = false;
//        boolean b3 = false;
//        if (row - 1 >= 0) {
//            b1 = uf.connected(to1DIndex(row - 1, col), n * n);
//        }
//        if (col - 1 >= 0) {
//            b2 = uf.connected(to1DIndex(row, col - 1), n * n);
//        }
//        if (col + 1 < n) {
//            b3 = uf.connected(to1DIndex(row, col + 1), n * n);
//        }
//        return b1 || b2 || b3;
//    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (uf.connected(n * n, to1DIndex(n - 1, i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }

}
