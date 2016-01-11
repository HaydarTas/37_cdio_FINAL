package field;

import entity.Player;
import field.Ownable;

public class Street extends Ownable {

	private int[] rents;
	private int housePrice;
	private int houseCount;
	


	public Street(String name, int[] rents, int price, int housePrice) {
		super(name, price);
		this.rents = rents;
		this.housePrice = housePrice;
		
	}

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {
		if (super.getOwner() != null) {
			if (!super.getOwner().equals(p)) 
				
				p.addToBalance(-getRent());
				super.getOwner().addToBalance(getRent());
				p.setInformation(-1);
			} else if (super.getOwner().equals(p)) {
				p.setInformation(1);
			} else {
				p.setInformation(0);
			}
		}
	

	public void addHouse(){
		houseCount++;
	}

	public int getRent() {
		return rents[houseCount];
	}


}
