package tictactoe;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import minmax.Board;
import minmax.MinMax;
import minmax.Move;
import minmax.Node;
import resources.ImageContainer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import connectfour.ConnectFourBoard;
import connectfour.ConnectFourMove;

public class TicTacToeMainWindow {

	public JFrame frame;
	private Board board;
	private ImageContainer IC = new ImageContainer();
	JSpinner spinner;
	JButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
	private boolean useAi = true;

	/**
	 * Create the application.
	 */
	public TicTacToeMainWindow() {
		board = new TicTacToeBoard();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame() {
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (board.getBoard()[i][j] == 0) {
							g2d.drawImage(IC.getBlank(), 190 + 80 * i, 100 + 80 * j, this);
						} else if (board.getBoard()[i][j] == 1) {
							g2d.drawImage(IC.getCross(), 190 + 80 * i, 100 + 80 * j, this);
						} else if (board.getBoard()[i][j] == -1) {
							g2d.drawImage(IC.getCircle(), 190 + 80 * i, 100 + 80 * j, this);
						}
					}
				}
			}
		};
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic-Tac-Toe - PC");
		frame.setBounds(100, 100, 500, 700);
		frame.getContentPane().setLayout(null);

		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(0);
			}
		});
		button_1.setBounds(169, 356, 80, 80);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(1);
			}
		});
		button_2.setBounds(259, 356, 80, 80);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(2);
			}
		});
		button_3.setBounds(349, 356, 80, 80);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(3);
			}
		});
		button_4.setBounds(169, 447, 80, 80);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(4);
			}
		});
		button_5.setBounds(259, 447, 80, 80);
		frame.getContentPane().add(button_5);

		button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(5);
			}
		});
		button_6.setBounds(349, 447, 80, 80);
		frame.getContentPane().add(button_6);

		button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(6);
			}
		});
		button_7.setBounds(169, 538, 80, 80);
		frame.getContentPane().add(button_7);

		button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(7);
			}
		});
		button_8.setBounds(259, 538, 80, 80);
		frame.getContentPane().add(button_8);

		button_9 = new JButton("");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(8);
			}
		});
		button_9.setBounds(349, 538, 80, 80);
		frame.getContentPane().add(button_9);

		spinner = new JSpinner();
		spinner.setBounds(10, 356, 45, 22);
		spinner.setModel(new SpinnerNumberModel(5, 1, 11, 2));
		frame.getContentPane().add(spinner);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board = new TicTacToeBoard();
				frame.repaint();
				enableButtons();
			}
		});
		btnNewGame.setBounds(20, 389, 89, 23);
		frame.getContentPane().add(btnNewGame);

		JButton btnUseAi = new JButton("Use AI");
		btnUseAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useAi = !useAi;
				System.out.println("Computer switched to: " + useAi);
				JOptionPane.showMessageDialog(null, "Computer switched to: " + useAi);
			}
		});
		btnUseAi.setBounds(20, 423, 89, 23);
		frame.getContentPane().add(btnUseAi);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				frame.dispose();
			}
		});
		btnExit.setBounds(20, 458, 89, 23);
		frame.getContentPane().add(btnExit);
	}

	private void makeMove(int intMove) {
		Move move = new TicTacToeMove(board.getPlayer(), intMove, (Integer) spinner.getValue());
		board = board.makeMove(move);
		frame.repaint();
		checkFinish();
		if(useAi){
			MinMax minmax = new MinMax();
			Node newNode = minmax.generateTree(board, (Integer) spinner.getValue(), board.getPlayer());
//			newNode.printValues();
			if(newNode.getBestMove() != null){
				System.out.println("Best: " + newNode.getBestMove().getMove());
				Move newMove = new TicTacToeMove(board.getPlayer(), newNode.getBestMove().getMove(), (Integer) spinner.getValue());
//				board = board.makeMove(newMove);
				frame.repaint();
				checkFinish();
			}
			
//			Move newMove = newNode.getBestMove();
//			System.out.println("Best move: " + newMove.getMove());
//			board.generateMoves();
		}

	}
	
	private void checkFinish(){
		if (((TicTacToeBoard) board).checkWin(1)) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Player 1 wins");
		}
		else if (((TicTacToeBoard) board).checkWin(-1)) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Player 2 (AI) wins");
		}
		else if (board.isGameFinished()) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Nobody wins");
			System.out.println("FINISHED");
		}
	}
	
	private void disableButtons(){
		button_1.setEnabled(false);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		button_6.setEnabled(false);
		button_7.setEnabled(false);
		button_8.setEnabled(false);
		button_9.setEnabled(false);
	}
	
	private void enableButtons(){
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
		button_8.setEnabled(true);
		button_9.setEnabled(true);
	}
}
