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



	public Field addHouse(){
		houseCount++;
		return null;
	}

	public int getRent() {
		return rents[houseCount];
	}



	public int getGroup() {
		return group;
	}

	
}

