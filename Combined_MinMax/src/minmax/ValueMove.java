package minmax;

public class ValueMove {
	private int value;
	private Move move;

	public ValueMove(int value, Move move) {
		this.value = value;
		this.move = move;
	}

	public ValueMove() {
	}

	public int getValue() {
		return value;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
