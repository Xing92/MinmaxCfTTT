package minmax;

import java.util.ArrayList;
import java.util.List;

public class MinMax {

	private Node rootNode;

	public MinMax() {

	}

	public Node start(Board board) {
		board.evaluateBoard();
		rootNode = new Node(board);
		populateNodes(rootNode);
		return rootNode;
	}

	private List<Node> populateNodes(Node rootNode) {

		Board rootBoard = rootNode.getBoard();
		List<Board> listOfBoards = new ArrayList<Board>();
		List<Node> listOfNodes = new ArrayList<Node>();

		List<Move> listOfMoves = rootBoard.generateMoves();
		for (Move move : listOfMoves) {
			Board board = rootBoard.makeMove(move);
			listOfBoards.add(board);
			Node node = new Node(board, move, rootNode);
			rootNode.addChild(node);
			listOfNodes.add(node);

			populateNodes(node);
		}

		// for(Board board : listOfBoards){
		// board.printBoard();
		// }

		return listOfNodes;
	}

	// public ValueMove minMax(Board board, int depth) {
	// return maxMove(board, depth);
	// }
	//
	// private ValueMove maxMove(Board board, int depth) {
	// if (depth == 0 || board.isGameFinished()) {
	// ValueMove vm = new ValueMove(board.evaluateBoard(), null);
	// return vm;
	// }
	// ValueMove tempVM = null;
	// ValueMove bestVM = new ValueMove();
	// bestVM.setValue(-10_000);
	// List<Move> moveList = board.generateMoves();
	//
	// for (Move move : moveList) {
	// Board tempBoard = board.makeMove(move);
	// tempVM = minMove(tempBoard, depth - 1);
	// tempVM.setMove(move);
	// if (tempVM.getValue() > bestVM.getValue()) {
	// bestVM = tempVM;
	// }
	// }
	// return bestVM;
	// }
	//
	// private ValueMove minMove(Board board, int depth) {
	// if (depth == 0 || board.isGameFinished()) {
	// ValueMove vm = new ValueMove(board.evaluateBoard(), null);
	// return vm;
	// }
	// ValueMove tempVM = null;
	// ValueMove bestVM = new ValueMove();
	// bestVM.setValue(10_000);
	// List<Move> moveList = board.generateMoves();
	//
	// for (Move move : moveList) {
	// Board tempBoard = board.makeMove(move);
	// tempVM = minMove(tempBoard, depth - 1);
	// tempVM.setMove(move);
	// if (tempVM.getValue() < bestVM.getValue()) {
	// bestVM = tempVM;
	// }
	// }
	// return bestVM;
	// }

	public Node generateTree(Board board, int depth, int player) {
		Node rootNode = new Node(board);
		generateSubtree(rootNode, depth, player);
		minMax(rootNode, depth);
		return rootNode;
	}

	private void generateSubtree(Node subRootNode, int depth, int player) {
		Board board = subRootNode.getBoard();

		if (depth == 0) {
			subRootNode.setValue(board.evaluateBoard(player));
//			System.out.println("Reached bottom. Value: " + subRootNode.getValue());
			return;
		}
		List<Move> moveList = board.generateMoves();
		for (Move move : moveList) {
			Board tempBoard = board.makeMove(move);
			Node tempNode = new Node(tempBoard, move, subRootNode);
			subRootNode.addChild(tempNode);
			generateSubtree(tempNode, depth - 1, player);
		}
	}

	public void minMax(Node rootNode, int depth) {
		maxMove(rootNode, depth);
	}

	public int maxMove(Node node, int depth) {
		if (depth == 0) {
			return node.getValue();
		}
		int bestValue = Integer.MIN_VALUE;
		for (Node childNode : node.getChildren()) {
			int tempValue = minMove(childNode, depth - 1);
			childNode.setValue(tempValue);
			if (tempValue > bestValue) {
				bestValue = tempValue;
			}
		}
		return bestValue;
	}

	public int minMove(Node node, int depth) {
		if (depth == 0) {
			return node.getValue();
		}
		int bestValue = Integer.MAX_VALUE;
		for (Node childNode : node.getChildren()) {
			int tempValue = maxMove(childNode, depth - 1);
			childNode.setValue(tempValue);
			if (tempValue < bestValue) {
				bestValue = tempValue;
			}
		}
		return bestValue;
	}

	// public Node generateTree(Node node, int depth, boolean maximizingPlayer){
	// if(depth == 0){
	// node.setValue(node.getBoard().evaluateBoard());
	// return node;
	//
	// }
	// Board board = node.getBoard().getBoardCopy();
	//// List<Move> moveList = new ArrayList<Move>();
	// for(Move move : board.generateMoves()){
	//// moveList.add(move);
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// Node value = generateTree(newNode, depth-1, !maximizingPlayer);
	// newNode.setValue(value.getValue());
	// }
	//// node.printAllNodes();
	// return node;
	// }

	// public int minMax(Node node, int depth, int player, boolean
	// maximizingPlayer) {
	// if (depth == 0) {
	// node.setValue(node.getBoard().evaluateBoard());
	// return player * node.getBoard().evaluateBoard();
	// }
	// int value;
	// int bestValue;
	// Board board = node.getBoard();
	// if (maximizingPlayer) {
	// bestValue = -10_000;
	// for (Move move : node.getBoard().generateMoves()) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// int newPlayer = player == 1 ? -1 : 1;
	// value = minMax(newNode, depth - 1, player, !maximizingPlayer);
	// bestValue = Math.max(bestValue, value);
	// newNode.setValue(bestValue);
	// }
	// } else {
	// bestValue = 10_000;
	// for (Move move : node.getBoard().generateMoves()) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// int newPlayer = player == 1 ? -1 : 1;
	// value = minMax(newNode, depth - 1, player, !maximizingPlayer);
	// bestValue = Math.min(bestValue, value);
	// newNode.setValue(bestValue);
	// }
	// }
	//
	// // node.printAllNodes();
	// return bestValue;
	// }

	public ValueMove alphaBeta(Node node, int depth, int player, boolean maximizingPlayer, int maxPlayerBestValue,
			int minPlayerBestValue) {

		if (depth == 0) {
			return new ValueMove(node.getBoard().evaluateBoard(), null);
			// return /*player * */node.getBoard().evaluateBoard();
		}

		int bestValue;
		Move bestMove;
		Board board = node.getBoard();
		if (maximizingPlayer) {
			List<Move> moveList = board.generateMoves();
			bestMove = moveList.get(0);
			bestValue = -10_000;
			for (Move move : moveList) {
				Board newBoard = board.makeMove(move);
				Node newNode = new Node(newBoard, move, node);
				ValueMove newValueMove;
				newValueMove = alphaBeta(newNode, depth - 1, player, !maximizingPlayer, minPlayerBestValue,
						maxPlayerBestValue);
				newNode.setValue(newValueMove.getValue());
				if (newValueMove.getValue() >= bestValue) {
					bestMove = move;
					bestValue = newValueMove.getValue();
				}
				if (bestValue >= minPlayerBestValue) {
					return new ValueMove(bestValue, bestMove);
				}
			}
			return new ValueMove(bestValue, bestMove);
		} else {
			bestValue = 10_000;
			List<Move> moveList = board.generateMoves();
			bestMove = moveList.get(0);
			for (Move move : moveList) {
				Board newBoard = board.makeMove(move);
				Node newNode = new Node(newBoard, move, node);
				ValueMove newValueMove;
				newValueMove = alphaBeta(newNode, depth - 1, player, !maximizingPlayer, minPlayerBestValue,
						maxPlayerBestValue);
				if (newValueMove.getValue() <= bestValue) {
					bestMove = move;
					bestValue = newValueMove.getValue();
				}
				if (bestValue <= maxPlayerBestValue) {
					return new ValueMove(bestValue, bestMove);
				}
			}
			return new ValueMove(bestValue, bestMove);
		}
	}

}
