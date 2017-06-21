package connectfour;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import minmax.Board;
import minmax.Move;

public class ConnectFourBoard implements Board {

	private int board[][];
	private int player;

	private int checkMovesAhead;

	private static int[][] evaluationTable = {{3,4,5,5,4,3},{4,6,8,8,6,4},{5,8,11,11,8,5},{7,10,13,13,10,7},{5,8,11,11,8,5},{4,6,8,8,6,4},{3,4,5,5,4,3}};

	private static int iterationCounter = 0;
	private int selfIterationCounter;

	private final static int BOARD_SIZE_X = 7;
	private final static int BOARD_SIZE_Y = 6;

	public ConnectFourBoard() {
		board = new int[BOARD_SIZE_X][BOARD_SIZE_Y];
		player = 1;
	}

	public int getPlayer() {
		return player;
	}

	public int[][] getBoard() {
		return board;
	}

	public ConnectFourBoard(int[][] board, int player) {
		this.board = board;
		this.player = player;
		selfIterationCounter = iterationCounter++;
	}

	public ConnectFourBoard(int[][] board, int player, int checkMovesAhead) {
		this(board, player);
		this.checkMovesAhead = checkMovesAhead;
	}

	public int evaluateBoard2() {
		int elem1 = 0, elem2 = 0, elem3 = 0, elem4 = 0;
		int score = 0;
		int result = 0;
		int[] Countertab = new int[9];

		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 4; i++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j];
				elem3 = board[i + 2][j];
				elem4 = board[i + 3][j];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int j = 3; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				elem1 = board[i][j];
				elem2 = board[i][j - 1];
				elem3 = board[i][j - 2];
				elem4 = board[i][j - 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 3; j < 6; j++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j - 1];
				elem3 = board[i + 2][j - 2];
				elem4 = board[i + 3][j - 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j + 1];
				elem3 = board[i + 2][j + 2];
				elem4 = board[i + 3][j + 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		if (Countertab[0] != 0) {
			return -1000000;
		} else if (Countertab[8] != 0) {
			return 1000000;
		} else {
			result = +(Countertab[5] + 2 * Countertab[6] + 5 * Countertab[7] * 7 - Countertab[3] - 2 * Countertab[2]
					- 5 * Countertab[1] * 7);
			return player * result;
		}

	}

	@Override
	public int evaluateBoard() {
		int score = scoreTwos(player) + scoreThrees(player);// -
															// scoreTwos(switchPlayer())
															// -
															// scoreThrees(switchPlayer());
		if (scoreFours(player) > 0) {
			score = 10_000_000;
		} else if (scoreFours(switchPlayer()) > 0) {
			printBoard();
			score = -10_000_000;
		}
		return score;
	}

	
	public int scoreOnes(int player){
		int score = 0;
		 for (int i = 0; i < BOARD_SIZE_X; i++)
	            for (int j = 0; j <BOARD_SIZE_Y; j++)
	                if (board[i][j] == player)
	                	score += evaluationTable[i][j];
	                else if (board[i][j] == switchPlayer())
	                	score -= evaluationTable[i][j];
		return score;
	}
	
	public int evaluateBoard(int player) {
		int score = scoreTwos(player) + scoreThrees(player) - scoreTwos(switchPlayer()) - scoreThrees(switchPlayer()) + scoreOnes(player);
		if (scoreFours(player) > 0) {
			score = 10_000_000;
		} else if (scoreFours(switchPlayer()) > 0) {
			printBoard();
			score = -10_000_000;
		}
		if (player == this.player) {
			score = -score;
		}
		return score;
	}

	private int scoreTwos(int player) {
		int count = 0;
		final int multiplicator = 10;
		for (int row = 0; row < BOARD_SIZE_Y - 1; row++) {
			for (int column = 0; column < BOARD_SIZE_X - 1; column++) {
				if (player == board[column][row] && player == board[column][row + 1]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row + 1]) {
					count++;
				} else if (player == board[column][row + 1] && player == board[column + 1][row]) {
					count++;
				}
			}
		}
		// System.out.println("Twos: " + count);
		return count * multiplicator;
	}

	private int scoreThrees(int player) {
		int count = 0;
		final int multiplicator = 100;
		for (int row = 0; row < BOARD_SIZE_Y - 2; row++) {
			for (int column = 0; column < BOARD_SIZE_X - 2; column++) {
				if (player == board[column][row] && player == board[column][row + 1]
						&& player == board[column][row + 2]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row]
						&& player == board[column + 2][row]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row + 1]
						&& player == board[column + 2][row + 2]) {
					count++;
				} else if (player == board[column][row + 2] && player == board[column + 1][row + 1]
						&& player == board[column + 2][row]) {
					count++;
				}
			}
		}
		// System.out.println("Threes: " + count);
		return count * multiplicator;
	}

	private int scoreFours(int player) {
		int count = 0;
		final int multiplicator = 1;
		for (int row = 0; row < BOARD_SIZE_Y - 3; row++) {
			for (int column = 0; column < BOARD_SIZE_X - 3; column++) {
				if (player == board[column][row] && player == board[column][row + 1] && player == board[column][row + 2]
						&& player == board[column][row + 3]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row]
						&& player == board[column + 2][row] && player == board[column + 3][row]) {
					count++;
				} else if (player == board[column][row] && player == board[column + 1][row + 1]
						&& player == board[column + 2][row + 2] && player == board[column + 3][row + 3]) {
					count++;
				} else if (player == board[column][row + 3] && player == board[column + 1][row + 2]
						&& player == board[column + 2][row + 1] && player == board[column + 3][row]) {
					count++;
				}
			}
		}
		// System.out.println("Fours: " + count);
		return count * multiplicator;
	}

	@Override
	public boolean isGameFinished() {
		// CheckWinnerCol(board);
		// CheckWinnerRow(board);
		// CheckWinnerDiagonallyRight(board);
		// CheckWinnerDiagonallyLeft(board);
		// return CheckWinnerCol(board) || CheckWinnerRow(board) ||
		// CheckWinnerDiagonallyRight(board) ||
		// CheckWinnerDiagonallyLeft(board);
		int x = evaluateBoard();
		if (x == 10000 || x == -10000) {
			return true;
		}
		return false;
	}

	@Override
	public List<Move> generateMoves() {
		if (isGameFinished()) { // TODO consider moving
								// it into different
								// place - somewhere
								// earlier
			return new ArrayList<Move>();
		}
		List<Move> moveList = new ArrayList<Move>();
		for (int column = 0; column < BOARD_SIZE_X; column++) {
			if (isMoveDoable(column)) {
				moveList.add(new ConnectFourMove(switchPlayer(), column));
			}
		}
		return moveList;
	}

	@Override
	public Board makeMove(Move move) {
		return makeMove((ConnectFourMove) move);
	}

	@Override
	public void printBoard() {
		System.out.println("Printing board: " + selfIterationCounter);
		// System.out.println("Board value: " + evaluateBoard());
		for (int y = 0; y < BOARD_SIZE_Y; y++) {
			for (int x = 0; x < BOARD_SIZE_X; x++) {
				System.out.print(board[x][y] + "\t");
			}
			System.out.println();
		}
	}

	private Board makeMove(ConnectFourMove move) {
		int newBoard[][] = copyBoardValues(board);
		int column = move.getMove();
		int newPlayer = player;
		// System.out.println(selfIterationCounter + ": " + column);
		if (isMoveDoable(column)) {
			newBoard[column][getRowFromColumn(column)] = player;
			newPlayer = switchPlayer();
		}

		return new ConnectFourBoard(newBoard, newPlayer, move.getDepth());
	}

	private boolean isMoveDoable(int column) {
		if (board[column][BOARD_SIZE_Y - 1] != 0)
			return false;
		else
			return true;

	}

	private int switchPlayer() {
		return (player == 1) ? -1 : 1;
	}

	private int getRowFromColumn(int column) {

		for (int row = 0; row < BOARD_SIZE_Y; row++) {
			if (board[column][row] == 0) {
				return row;
			}
		}
		return -1;
	}

	private int[][] copyBoardValues(int[][] board) {
		int[][] newBoard = new int[BOARD_SIZE_X][BOARD_SIZE_Y];
		for (int column = 0; column < BOARD_SIZE_X; column++) {
			for (int row = 0; row < BOARD_SIZE_Y; row++) {
				newBoard[column][row] = board[column][row];
			}
		}
		return newBoard;
	}

	private boolean CheckWinnerCol(int[][] b) {
		for (int i = 0; i < 7; i++) {
			for (int j = 5; j >= 3; j--) {
				if (board[i][j] == player && board[i][j] == board[i][j - 1] && board[i][j] == board[i][j - 2]
						&& board[i][j] == board[i][j - 3]) {
					System.out.println("Computer Wins!");
					JOptionPane.showMessageDialog(null, (player == 1 ? "Player" : "Computer") + " Wins!");
					return true;
				}
			}
		}
		return false;
	}

	private boolean CheckWinnerRow(int[][] b) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] == player && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j]
						&& board[i][j] == board[i + 3][j]) {
					System.out.println("Computer Wins!");
					JOptionPane.showMessageDialog(null, (player == 1 ? "Player" : "Computer") + " Wins!");
					return true;
				}
			}
		}
		return false;
	}

	private boolean CheckWinnerDiagonallyRight(int[][] b) {
		int column;
		int row;

		for (column = 0; column < 4; column++) {
			for (row = 3; row < 6; row++) {
				if (b[column][row] == player && b[column][row] == b[column + 1][row - 1]
						&& b[column][row] == b[column + 2][row - 2] && b[column][row] == b[column + 3][row - 3]) {
					System.out.println("Computer Wins!");
					JOptionPane.showMessageDialog(null, (player == 1 ? "Player" : "Computer") + " Wins!");
					return true;
				}
			}
		}
		return false;
	}

	private boolean CheckWinnerDiagonallyLeft(int[][] b) {
		int column;
		int row;

		for (column = 0; column < 4; column++) {
			for (row = 0; row < 3; row++) {
				if (b[column][row] == player && b[column][row] == b[column + 1][row + 1]
						&& b[column][row] == b[column + 2][row + 2] && b[column][row] == b[column + 3][row + 3]) {
					System.out.println("Computer Wins!");
					JOptionPane.showMessageDialog(null, (player == 1 ? "Player" : "Computer") + " Wins!");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Board getBoardCopy() {
		return new ConnectFourBoard(copyBoardValues(board), player);
	}
}
