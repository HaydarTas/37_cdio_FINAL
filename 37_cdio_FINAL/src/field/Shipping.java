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

	// landOnField checks if the player who lands on Shipping owns it. If not
	// checks if someone else owns the Shipping; if yes the player has to pay
	// the rent
	@Override
	public void landOnField(Player p) {
		if (super.getOwner() != null) {
			if (!super.getOwner().equals(p)) {
				p.addToBalance(-getRent());
				super.getOwner().addToBalance(getRent());
				p.setInformation(-1); // When information is =-1 the Shipping is
										// bought by another player
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1); // He owns the Shipping
			} else {
				p.setInformation(0); // No player owns the Shipping. Is for sale
			}
		} else {
			p.setInformation(0);
		}
	}

	public int countShipping() {
		Field[] fields = super.getOwner().getFields();
		int Shippings = 0;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] instanceof Shipping) {
				Shippings++;
			}
		}
		return Shippings;
	}

	public int getRent() {
		int amount = this.countShipping();

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

