package entity;

public class Account {
	
	private int balance = 0;

	public int getBalance() {
		return balance;
	}

	public boolean addToBalance(int balance) {
		if(this.balance + balance < 0){
			return false;
		}else{
			this.balance = balance + this.balance ;	
			return true;
		}
	}
	
}

