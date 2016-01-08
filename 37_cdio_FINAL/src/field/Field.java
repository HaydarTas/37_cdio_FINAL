package field;

import entity.Player;

public abstract class Field {
	private String name;
	private int points;


	public Field(String name){
		this.name = name;
	}

	public Field(String name, int points) {
		this.name = name;
		this.points = points;
	}

	@Override
	public String toString() {
		return "felt [name=" + name + ", points=" + points + "]";
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;

	}

	public abstract void landOnField(Player p);
}


