package field;

import desktop_resources.GUI;
import entity.Player;

public class MoveCar extends ChanceCard {

	private int changeFieldPosition ;
//	private String msg;

	public MoveCar(String string, int changeinfieldposition) {
		this.changeFieldPosition = changeinfieldposition;
		// TODO Auto-generated constructor stub

	}

	@Override
	public void docard(Player p) {

		p.movePlayer(changeFieldPosition);
		// TODO Auto-generated method stub
//		GUI.showMessage(msg);
	}

}
