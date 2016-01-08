package field;

import field.Field;
import field.Shipping;
import field.Ownable;
import entity.Player;

public class Shipping extends Ownable {

	private final int RENT_1 = 500;
	private final int RENT_2 = 1000;
	private final int RENT_3 = 2000;
	private final int RENT_4 = 4000;

	public Shipping(String name, int price) {
		super(name, price);
	}

	// landOnField checks if the player who lands on fleet owns it. If not
	// checks if someone else owns the fleet; if yes the player has to pay
	// the rent
	@Override
	public void landOnField(Player p) {
		if (super.getOwner() != null) {
			if (!super.getOwner().equals(p)) {
				p.addToBalance(-getRent());
				super.getOwner().addToBalance(getRent());
				p.setInformation(-1); // When information is =-1 the fleet is
										// bought by another player
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1); // He owns the fleet
			} else {
				p.setInformation(0); // No player owns the fleet. Is for sale
			}
		} else {
			p.setInformation(0);
		}
	}

	public int countFleet() {
		Field[] fields = super.getOwner().getFields();
		int fleets = 0;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] instanceof Shipping) {
				fleets++;
			}
		}
		return fleets;
	}

	public int getRent() {
		int amount = this.countFleet();

		switch (amount) {
		case 1:
			return RENT_1;
		case 2:
			return RENT_2;
		case 3:
			return RENT_3;
		case 4:
			return RENT_4;
		default:
			return 0;
		}
	}
}

