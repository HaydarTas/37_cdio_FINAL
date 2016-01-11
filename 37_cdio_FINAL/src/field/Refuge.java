package field;

import field.Field;
import entity.Player;

public class Refuge extends Field {

	int bonus;
	String name;
	
	public Refuge(String name, int bonus){
		super(name);
		this.bonus = bonus;
		this.name = name;
	}
	public void landOnField(Player p){
		p.addToBalance(bonus);
//		p.setInformation(2);
	}

	int getBonus() {
		return bonus;
	}

	void setBonus(int bonus) {
		this.bonus = bonus;
	}
}

