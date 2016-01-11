package field;

import field.Field;
import field.Ownable;
import entity.Player;

public class Brewer extends Ownable {

	int baseRent;

	public Brewer(String name, int rent, int price) {
		super(name, price);
		this.baseRent = rent;
	}

	public int countBrewer() {
		Field[] field = super.getOwner().getFields();
		int Brewer = 0;

		for (int i = 0; i < field.length; i++) {
			if (field[i] instanceof Brewer) {
				Brewer++;
			}
		}
		return Brewer;
	}

	public int calculateTax(int roll) {
		return roll * 100 * this.countBrewer();
	}

	public int getRent() {
		return baseRent;
	}

	void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}
}

