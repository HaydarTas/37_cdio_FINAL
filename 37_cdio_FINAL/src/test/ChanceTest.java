package test;

import org.junit.Before;
import org.junit.Test;

import entity.Player;
import field.Chance;
import field.ChanceCard;
import field.MoneyCard;
import field.MoveCar;
import junit.framework.TestCase;;

public class ChanceTest extends TestCase {
	
	private Player spiller1;
	private Chance chancecontroller;

	@Before
	// setUp metoden bliver kaldt FoR  test metode
	public void setUp(){
		MoneyCard Chancefield1 = new MoneyCard(300, "Du vinder i lotto, modtag kr. 300,-");
		MoveCar Chancefield2 = new MoveCar("Du rykker 2 felter frem", 2);
		chancecontroller = new Chance("test", 1000, new ChanceCard[]{Chancefield1, Chancefield2});
		spiller1 = new Player("Obama", 0);
		
		
	}
	// tester i hvilken rækkefølge kortene bliver trukket når de lander 
	// chance felterne. // de bliver trukket fra 0-1. 
	@Test
	public void testChanceLandonfield(){
	
	chancecontroller.landOnField(spiller1);
	
	
	}
	
}

