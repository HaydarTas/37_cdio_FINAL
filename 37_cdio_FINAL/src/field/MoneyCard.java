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
//Her bliver efter der er trukket et kort lagt / trukket fra player´s konto og der
	//bliver udskrevet text på GUI,// 
	@Override
	public void docard(Player p) {
		p.addToBalance(amount);
		GUI.showMessage(msg);
	}

}