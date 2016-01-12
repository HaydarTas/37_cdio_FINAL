package field;

import entity.Player;

public class MoveCar extends ChanceCard {

	private int i;
	
	public MoveCar(String string, int i) {
		this.i = i;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void docard(Player p) {
		
		p.movePlayer(i);
		// TODO Auto-generated method stub

	}

}
