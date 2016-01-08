package entity;

public class Dice {
	private int value;

	public int roll() {		
      this.value =  (int) (Math.random() * 6 + 1);
      return this.value;	
	}
	// getvaerdi skal oprettes så klasser udfra kan hente værdien for vaerdi.
	public int getValue() {
		return value;
	}


}

