package field;

import desktop_resources.GUI;
import entity.Player;

public class MoneyCard extends ChanceCard {
	private int amount;
	private String msg;
	
	public MoneyCard(int amount, String msg){
		this.amount = amount;
		this.msg = msg;
	}

	@Override
	public void docard(Player p) {
		p.addToBalance(amount);
		GUI.showMessage(msg);
	}

}