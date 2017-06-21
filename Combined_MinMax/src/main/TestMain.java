package main;

import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;

import connectfour.ConnectFourBoard;
import connectfour.ConnectFourMove;
import minmax.Board;
import minmax.MinMax;
import minmax.Node;

public class TestMain extends JFrame{
	
	public static void main(String[] args){
		
		Board board = new ConnectFourBoard();
		MinMax minmax = new MinMax();

		System.out.println("Started");
		
		int depth = 7;
		Node rootNode = minmax.generateTree(board, depth, 1);
//		minmax.minMax(rootNode, depth);
		
		System.out.println("Finished");
		rootNode.printValues();
		
		int option;
		Node newNode = rootNode;
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
//				board = newNode.getBoard();
//				board = board.makeMove(new ConnectFourMove(board.getPlayer(), option - 1));
//				newNode = minmax.generateTree(board, depth);
				newNode.printBoard();
				newNode.printValues();
				break;
			default:
				break whileloop;
			}

		}
		scanIn.close();
		
	}
	
}
