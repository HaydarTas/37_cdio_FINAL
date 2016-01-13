
package entity;

import desktop_resources.GUI;
import field.Field;
import field.Ownable;

public class Player {

	private String name;
	private Account account;
	private boolean bankrupt = false;
	private Field[] fields = new Field[40];
	private int number;
	private int position;
//	private int info; // Information about whether the field is bought or not
	private int lastRoll;
	private int jailTime = 0;

	public Player(String name, int number) {
		this.name = name;
		this.account = new Account();
		this.account.addToBalance(30000);
		this.number = number;

	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return this.account.getBalance();

	}

	public boolean addToBalance(int points) {
		return this.account.addToBalance(points);

	}

	public boolean isBankrupt() {
		return bankrupt;
	}

	/**
	 * setBankrupt sets the bankrupt status to true.
	 */
	public void setBankrupt() {
		bankrupt = true;
	}

	public Field[] getFields() {
		return fields;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public void movePlayer(int dice) {
	System.out.println("flytter spiller");
	GUI.removeCar(position+1, name);
	
		if((position + dice) > fields.length) {
			this.addToBalance(4000);
			System.out.println("får 4000");
			GUI.setBalance(this.getName(), this.getBalance());
		}
		position = (position + dice) % fields.length;

		GUI.setCar(position+1, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player tmp = (Player) obj;
			return this.name == tmp.getName() && this.number == tmp.getNumber();
		} else {
			return false;
		}

	}
	public boolean rentField(Ownable field) {
		if (this.getBalance() > field.getRent()) {
			this.addToBalance(-field.getRent());
			GUI.setBalance(this.name, this.getBalance());
			return true;
		}
		return false;
	}

	public boolean buyField(Ownable field) {
		boolean bought = false;
		if (this.getBalance() > field.getPrice()) {
			for (int i = 0; i < fields.length; i++) {
				if (fields[i] == null) {
					fields[i] = field;
					bought = true;
					break;
				}
			}
		}
		if(bought){
			field.setOwner(this);
			this.addToBalance(-field.getPrice());
			GUI.setBalance(this.name, this.getBalance());
			GUI.setOwner(this.position+1, this.getName());
//			this.setInformation(1);
		}
		return bought;
	}

//	public int getInformation() {
//		return info;
//	}

//	public void setInformation(int i) {
//		info = i;
//	}

	public int getLastRoll() {
		return lastRoll;
	}

	public void setLastRoll(int diceValues) {
		lastRoll = diceValues;
	}

	public boolean getJailed() {
		return jailTime > 0; 
	}

	public int getJailTime() {
		return jailTime;
	}
	
	public void setJailTime(int jailTime) {
		this.jailTime = jailTime;
	}
	

//	public boolean getJailCard() {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public void getOutOfJail() {
		this.jailTime=3;
		
	}

	public void GoToJail() {
		// TODO Auto-generated method stub
		
	}

	

}
