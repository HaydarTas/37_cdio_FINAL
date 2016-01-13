package field;
import desktop_resources.GUI;
import entity.Player;

public class GoToJail extends Field {
	private static final int VESTERFÆNGSEL = 11;
	private static final int GoToJail = 31;

	public GoToJail(String name, int points) {
		super(name, points);
	}

	public void landOnField(Player p) {
		p.setJailTime(3);
		p.movePlayer(-(GoToJail-VESTERFÆNGSEL));

		GUI.setCar(p.getPosition()+1, p.getName());
		GUI.removeCar(GoToJail, p.getName());
		GUI.showMessage("Du rykkes til fængsel feltet");

	}
}