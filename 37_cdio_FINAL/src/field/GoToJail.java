package field;
import field.*;
import controller.GameController;
import entity.*;

public class GoToJail extends Field {
	private static final int VESTERFÆNGSEL = 11;
	private static final int POLITIGÅRDEN = 31;
	
	public GoToJail(String name, int points) {
		super(name, points);
	}
	
	public static void jail(Player p) {
		p.GoToJail();
		p.movePlayer(p.getPosition());
	}
	
	public void landOnField(Player p) {
		if(p.getPosition() == POLITIGÅRDEN)
			jail(p);
		else if(p.getJailed()) {
			if(p.getJailTime() < 3) {
				p.JailTime();
				if(p.getJailCard())
					p.getOutOfJail();
			} else p.getOutOfJail();
		}
	}
}
