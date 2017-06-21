package minmax;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Board board;
	private Move move;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();;
	private boolean isRootNode = false;

	private int value;

	public Node(Board board, Move move, Node parent) {
		this.board = board;
		this.move = move;
		this.parent = parent;
		parent.addChild(this);
	}

	public Node(Board board) {
		this.board = board;
		this.isRootNode = true;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void printBoard() {
		board.printBoard();
	}

	public Board getBoard() {
		return board;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void printAllNodes() {
		for (Node chlid : children) {
			System.out.println("Node value: " + value);
			chlid.printAllNodes();
		}
	}

	public void printValues() {
		for (Node child : children) {
			System.out.println("Node value: " + child.getValue());
		}
	}

	public Move getBestMove() {
		if(children.isEmpty()) return null;
		Node bestNode = children.get(0);
		Move bestMove = bestNode.getMove();

		for (Node node : children) {
			if (node.getValue() > bestNode.getValue()) {
				bestNode = node;
				bestMove = bestNode.getMove();
			}
		}
		return bestMove;
	}

	public Move getMove() {
		return move;
	}
}
