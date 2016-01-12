package field;

import entity.Player;

public class MoveCar extends ChanceCard {

	private int changeFieldPosition ;
	
	public MoveCar(String string, int changeinfieldposition) {
		this.changeFieldPosition = changeinfieldposition;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void docard(Player p) {
		
		p.movePlayer(changeFieldPosition);
		// TODO Auto-generated method stub

	}

}
