package field;

import entity.Player;

public class Street extends Ownable {

	private int rent;
	private int housePrice;
	private int houseCount;
	private int houseRent;
	

	public Territory(String name, int rent, int price, int houseRent, int housePrice) {
		super(name, price);
		this.rent = rent;
		this.housePrice = housePrice;
		this.houseRent = houseRent;
	}

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {
		if (super.getOwner() != null) {
			if (!super.getOwner().equals(p)) {
				p.addToBalance(-getRent());
				super.getOwner().addToBalance(getRent());
				p.setInformation(-1);
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1);
			} else {
				p.setInformation(0);
			}
		} else {
			p.setInformation(0);
		}
	}
	
	public void addHouse(){
		houseCount++;
	}

	public int getRent() {
		return rent + houseCount*houseRent;
	}
}
