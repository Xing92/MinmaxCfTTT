package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import connectfour.ConnectFourBoard;
import connectfour.ConnectFourMainWindow;
import minmax.Board;
import minmax.MinMax;
import minmax.Node;
import tictactoe.TicTacToeMainWindow;

public class MainWindow {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 180, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConnectFour = new JButton("Connect Four");
		btnConnectFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectFourMainWindow cfmw = new ConnectFourMainWindow();
				cfmw.frame.setVisible(true);
			}
		});
		btnConnectFour.setBounds(10, 11, 144, 60);
		frame.getContentPane().add(btnConnectFour);
		
		JButton btnTicTacToe = new JButton("Tic Tac Toe");
		btnTicTacToe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicTacToeMainWindow tttmw = new TicTacToeMainWindow();
				tttmw.frame.setVisible(true);
			}
		});
		btnTicTacToe.setBounds(10, 82, 144, 60);
		frame.getContentPane().add(btnTicTacToe);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(10, 153, 144, 60);
		frame.getContentPane().add(btnExit);
	}

	private static void startMe() {

		int[][] b = new int[7][6];

		MinMax mm = new MinMax();
		Board board = new ConnectFourBoard(b, 1, 7);
		System.out.println("Generating...");
		Node node = mm.start(board);
		// Node node = new Node(board);

		System.out.println("Done");

		displayingResults(node);

		System.out.println("Finished");
	}

	private static void displayingResults(Node node) {
		int option;
		Node newNode = node;
		Scanner scanIn = new Scanner(System.in);
		whileloop: while (true) {
			option = Integer.parseInt(scanIn.nextLine());
			switch (option) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				newNode = newNode.getChildren().get(option - 1);
				newNode.printBoard();
				break;
			default:
				break whileloop;
			}

		}
		scanIn.close();
	}
}
