package field;

import entity.Player;
import field.Ownable;

public class Property extends Ownable {

	private int[] rents;
	private int housePrice;
	private int houseCount;
	private int houseRent;


	public Property(String name, int[] rents, int price, int housePrice) {
		super(name, price);
		this.rents = rents;
		this.housePrice = housePrice;
		this.houseRent = houseRent;
	}

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {
		if (super.getOwner() instanceof Player) {
			if (!super.getOwner().equals(p)){ 
				
				p.addToBalance(-getRent());
				super.getOwner().addToBalance(getRent());
				p.setInformation(-1);
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1);
			} else {
				p.setInformation(0);
			}
		}
	}

	public Field addHouse(){
		houseCount++;
		return null;
	}

	public int getRent() {
		return rents[houseCount];
	}


}
