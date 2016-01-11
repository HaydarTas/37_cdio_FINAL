package field;

import desktop_resources.GUI;
import entity.Player;
import field.Ownable;

public class Property extends Ownable {
	
	public static final int LILLA = 0;
	public static final int GUL = 1;
	public static final int HVID = 2;
	public static final int GRÅ = 3;
	public static final int RØD = 4;
	public static final int BLÅ = 5;
	public static final int GRØN = 6;
	public static final int ORANGE = 7;
	

	private int[] rents;
	private int housePrice;
	private int houseCount;
	private int houseRent;
	private int group;


	public Property(String name, int[] rents, int price, int housePrice, int group) {
		super(name, price);
		this.rents = rents;
		this.housePrice = housePrice;
		this.group = group;
	}

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {

		if (owner !=null && owner != p){ 
			//TODO tell user that he landed on an owned field 
			GUI.showMessage("You landed on player" + getOwner() + "'s field - pay up!");
			p.addToBalance(-getRent());
			super.getOwner().addToBalance(getRent());
		} else if (owner !=null && super.getOwner().equals(p)) {
			//Tell user that it is his own field
			GUI.showMessage("You landed on your own field");
			//				p.setInformation(1);
		} else {
			boolean input = GUI.getUserLeftButtonPressed("You landed on an unowned filed - Buy?", "Ja tak", "Nej tak");
			if (input){
				//Todo buy field
				p.addToBalance(-getPrice());
				GUI.setOwner(p.getPosition()+1, p.getName());
				setOwner(p);
			} else {
				//Player didnt buy field
			}
			//				p.setInformation(0);
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
