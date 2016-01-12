package entity;

public class Dicebox {

	private Dice d1 = new Dice();
	private Dice d2 = new Dice();

	public int roll() {
		return d1.roll() + d2.roll();
	}

	public boolean isEqual() {
		if (d1.getValue() == d2.getValue()) {
			return true;
		}
		return false;
	}

	public int getDiceSum() {
		return d1.getValue() + d2.getValue();

	}

	public Dice[] getDice() {
		return new Dice[] { d1, d2 };
	}

}