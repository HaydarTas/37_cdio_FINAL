
package field;
import entity.Player;


public class Chance extends Field {


	
	
	
	private ChanceCard[] cards;

	public Chance(String name, int points, ChanceCard[] cards) {
		super(name, points);
		// TODO Auto-generated constructor stub
		this.cards = cards;
	}
// her udføres i hvilken rækkefølge kortene skal tages/vælges
	public void landOnField(Player p){
		cards[0].docard(p);
	}
	
	
	
	
	
}