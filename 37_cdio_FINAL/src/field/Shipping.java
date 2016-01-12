package field;

import field.Field;
import field.Shipping;
import field.Ownable;
import controller.GameController;
import entity.Player;

public class Shipping extends Ownable {

	private final int RENT_1 = 500;
	private final int RENT_2 = 1000;
	private final int RENT_3 = 2000;
	private final int RENT_4 = 4000;

	public Shipping(String name, int price, GameController gc) {
		super(name, price, gc );
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


