package field;

import field.Field;
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
		p.setInformation(2);

	}

	public void pay() {
		p.addToBalance(-this.taxAmount);
	}

	public void payPercent() {
		int tax = (taxRate * p.getBalance() / 100) * 10;
		p.addToBalance(-tax);
	}
}
