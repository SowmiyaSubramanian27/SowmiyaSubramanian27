import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private final int n;
	private final int[] board;

	// create a board from an n-by-n array of tiles,
	// where tiles[row][col] = tile at (row, col)
	public Board(int[][] tiles) {
		if (tiles == null || tiles[0].length != tiles.length) {
			throw new IllegalArgumentException();
		}
		this.n = tiles[0].length;
		this.board = new int[n * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[k] = tiles[i][j];
				k++;
			}
		}
	}

	// string representation of this board
	public String toString() {
		StringBuilder outString = new StringBuilder();
		outString.append(n);
		outString.append(System.lineSeparator());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				outString.append(" ");
				outString.append(board[i * n + j]);
			}
			outString.append(System.lineSeparator());
		}

		return outString.toString();
	}

	// board dimension n
	public int dimension() {
		return n;

	}

	// number of tiles out of place
	public int hamming() {
		int count = 0;
		for (int i = 0; i < (n * n) - 1; i++) {
			if (board[i] != (i + 1))
				count++;
		}
		return count;

	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
		int manhattanCount = 0;
		for (int i = 0; i < (n * n); i++) {
			if (board[i] != i + 1 && board[i] != 0) {
				int hor = Math.abs((i / n) - (board[i] - 1) / n);
				int ver = Math.abs((i + hor * (n)) - (board[i] - 1));
				if (board[i] <= i)
					ver = Math.abs((i - hor * (n)) - (board[i] - 1));
				manhattanCount += hor + ver;
			}
		}
		return manhattanCount;

	}

	// is this board the goal board?
	public boolean isGoal() {
		for (int i = 0; i < (n * n) - 2; i++) {
			if (board[i] > board[i + 1])
				return false;
		}
		return true;
	}

	// does this board equal y?
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != this.getClass())
			return false;
		Board that = (Board) obj;
		return Arrays.equals(this.board, that.board);

	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<Board>();
		int[] blocksCopy;
		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int i = 0; i < n * n; i++) {
			if (board[i] == 0) {
				int j = 3;
				while (j >= 0) {
					blocksCopy = board.clone();
					int x = (i / n) + dir[j][0];
					int y = (i % n) + dir[j][1];
					if (x < n && y < n && x >= 0 && y >= 0) {
						int temp = (x * n) + y;
						if (temp >= 0 && temp < (n * n)) {
							blocksCopy[i] = blocksCopy[temp];
							blocksCopy[temp] = 0;
							neighbors.push(new Board(monoToBidi(blocksCopy)));
						}
					}
					j--;

				}
				break;

			}
		}
		return neighbors;
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		int[] blocksCopy = board.clone();
		int randomIndexFrom = 0;
		int randomIndexTo = 0;
		while (blocksCopy[randomIndexFrom] == 0)
			randomIndexFrom++;
		randomIndexTo = randomIndexFrom + 1;
		while (blocksCopy[randomIndexTo] == 0)
			randomIndexTo++;
		int tmpVal = blocksCopy[randomIndexFrom];
		blocksCopy[randomIndexFrom] = blocksCopy[randomIndexTo];
		blocksCopy[randomIndexTo] = tmpVal;

		return new Board(monoToBidi(blocksCopy));
	}

	private int[][] monoToBidi(final int[] array) {
		int[][] bidi = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(array, (i * n), bidi[i], 0, n);
		}
		return bidi;
	}

	// unit testing (not graded)
	public static void main(String[] args) {

		/*
		 * int[][] tiles = { { 1, 3, 0 }, { 4, 2, 5 }, { 7, 8, 6 } }; int[][] tiles = {
		 * { 1, 0 }, { 2, 3 } }; int[][] tiles = { { 5, 1, 8 }, { 2, 7, 3 }, { 4, 0, 6 }
		 * }; int[][] tiles = {{1, 4, 10, 14}, {2, 9, 0, 11}, {8, 3, 6, 7}, {5, 15, 12,
		 * 13}}; //int[][] tiles = { { 3, 7, 4 }, { 8, 1, 0 }, { 6, 5, 2 } }; Board
		 * other = board.twin(); System.out.println(board.toString());
		 * System.out.println(board.manhattan());
		 *
		 */
		int[][] tiles = { { 2, 3 }, { 0, 1 } };
		Board board = new Board(tiles);
		System.out.println(board.toString());
		board.neighbors();

	}

}