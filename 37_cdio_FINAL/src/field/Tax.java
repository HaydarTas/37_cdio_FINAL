package field;

import field.Field;
import desktop_resources.GUI;
import entity.Player;

public class Tax extends Field {

	private int taxAmount;
	private int taxRate = -1;
	private Player p;

	public Tax(String name, int taxtAmount, int taxRate) {
		super(name);
		this.taxAmount = taxtAmount;
		this.taxRate = taxRate;
	}

	public Tax(String name, int taxtAmount) {
		super(name);
		this.taxAmount = taxtAmount;
	}

	public void landOnField(Player p) {
		this.p = p;
		if(taxRate == -1){
			pay();
		}else{
			final String btn1 = "kr. "+taxAmount+",-";
			final String btn2 = taxRate+"%";
			String res = GUI.getUserButtonPressed("Hvad vil du betale", btn1, btn2);
			if(btn1.equals(res)) pay();
			else payPercent();
		}
//		p.setInformation(2);

	}

	public void pay() {
		p.addToBalance(-this.taxAmount);
	}

	public void payPercent() {
		int tax = (taxRate * p.getBalance() / 100) * 10;
		p.addToBalance(-tax);
	}
}

