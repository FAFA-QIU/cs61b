package hw4.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements WorldState {
    private int[][] board;
    private int size;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        size = tiles.length;
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    // Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j > size) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    // Returns the board size N
    public int size() {
        return size;
    }

    // Returns the neighbors of the current board
    public Iterable<WorldState> neighbors() {
        List<WorldState> list = new ArrayList<>();
        int row = -1;
        int col = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (row - 1 >= 0) {
            Board b = new Board(this.board);
            swap(b, row, col, row - 1, col);
            list.add(b);
        }

        if (row + 1 < size) {
            Board b = new Board(this.board);
            swap(b, row, col, row + 1, col);
            list.add(b);
        }

        if (col - 1 >= 0) {
            Board b = new Board(this.board);
            swap(b, row, col, row, col - 1);
            list.add(b);
        }

        if (col + 1 < size) {
            Board b = new Board(this.board);
            swap(b, row, col, row, col + 1);
            list.add(b);
        }
        return list;
    }

    private void swap(Board b, int row, int col, int sRow, int sCol) {
        int temp = tileAt(row, col);
        b.board[row][col] = b.board[sRow][sCol];
        b.board[sRow][sCol] = temp;

    }

    // return hamming distance
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    break;
                }
                if (tileAt(i, j) != size * i + j + 1) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // return manhattan distance
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != 0 && tileAt(i, j) != size * i + j + 1) {
                    int goalRow = (tileAt(i, j) - 1) / size;
                    int goalCol = (tileAt(i, j) - 1) % size;
                    manhattan = manhattan + Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return manhattan;
    }

    // Estimated distance to goal. This method should
    // simply return the results of manhattan() when submitted to
    // Gradescope.
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    // Returns true if this board's tile values are the same
    // position as y's
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board b = (Board) y;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + Arrays.hashCode(board);
        hash = hash * 31 + size;
        return hash;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
