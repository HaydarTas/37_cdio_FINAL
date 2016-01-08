package field;


import field.Field;
import entity.Player;

public abstract class Ownable extends Field {

	private int price;
	private Player owner;

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


}

