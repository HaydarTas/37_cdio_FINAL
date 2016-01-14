
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
		int r = (int) (Math.random()*cards.length );
		System.out.println(cards.length + " card - drawn:" + r);
		cards[r].docard(p);
	}
	
	
	
	
	
}