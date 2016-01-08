package field;

import field.Field;
import field.Ownable;
import entity.Player;

public class Brewery extends Ownable {

	int baseRent;

	public Brewery(String name, int rent, int price) {
		super(name, price);
		this.baseRent = rent;
	}

	@Override
	public void landOnField(Player p) {
		if (super.getOwner() != null) {
			if (!super.getOwner().equals(p)) {
				p.addToBalance(-calculateTax(p.getLastRoll()));
				super.getOwner().addToBalance(calculateTax(p.getLastRoll()));
				p.setInformation(-1);
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1);
			} else {
				p.setInformation(0);
			}
		}else {
			p.setInformation(0);
		}

	}

	public int countLaborCamps() {
		Field[] field = super.getOwner().getFields();
		int laborCamps = 0;

		for (int i = 0; i < field.length; i++) {
			if (field[i] instanceof Brewery) {
				laborCamps++;
			}
		}
		return laborCamps;
	}

	public int calculateTax(int roll) {
		return roll * 100 * this.countLaborCamps();
	}

	public int getRent() {
		return baseRent;
	}

	void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}
}
