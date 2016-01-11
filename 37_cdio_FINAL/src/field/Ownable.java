package field;


import field.Field;
import desktop_resources.GUI;
import entity.Player;

public abstract class Ownable extends Field {

	protected int price;
	protected Player owner = null;

	public Ownable(String name, int price) {
		super(name);
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public abstract int getRent();

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {

		if (owner !=null && owner != p){ 
			//TODO tell user that he landed on an owned field 
			GUI.showMessage("You landed on" + getOwner() + "'s field - pay up!");
			p.addToBalance(-getRent());
			getOwner().addToBalance(getRent());
		} else if (owner !=null) {
			//Tell user that it is his own field
			GUI.showMessage("You landed on your own field");
			//				p.setInformation(1);
		} else {
			boolean input = GUI.getUserLeftButtonPressed("You landed on an unowned fi - Buy?", "Ja tak", "Nej tak");
			if (input){
				//Todo buy field
				p.addToBalance(-getPrice());
				GUI.setOwner(p.getPosition()+1, p.getName());
				setOwner(p);
				GUI.setBalance(p.getName(), p.getBalance());
			} else {
				//Player didnt buy field
			}
			//				p.setInformation(0);
		}

	}
}

