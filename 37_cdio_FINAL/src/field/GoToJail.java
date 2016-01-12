package field;
import field.*;
import controller.GameController;
import desktop_resources.GUI;
import entity.*;

public class GoToJail extends Field {
	private static final int VESTERFÆNGSEL = 11;
	private static final int GoToJail = 31;
	
	public GoToJail(String name, int points) {
		super(name, points);
	}
	
	public void landOnField(Player p) {
//		if(p.getPosition() == GoToJail)
			p.setJailTime(3);
			p.movePlayer(11);
			
			GUI.setCar(p.getPosition()+1, p.getName());
			GUI.removeCar(31, p.getName());
			GUI.showMessage("en tur til fængsel");
//			p.setPosition(VESTERFÆNGSEL-1);
			
		
	   // else {
	   
		//	GUI.showMessage("vent 3 rundt");
		
//}

}
}