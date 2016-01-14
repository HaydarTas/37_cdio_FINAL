package test;

import org.junit.Before;
import org.junit.Test;

import entity.Player;
import field.Chance;
import field.ChanceCard;
import field.MoneyCard;
import field.MoveCar;
import junit.framework.TestCase;;

public class ChanceTest extends TestCase{
	hhhhhh
	private Player spiller1;
	private Chance chancecontroller;

	@Before
	// setUp metoden bliver kaldt F�R hver test metode
	public void setUp(){
		MoneyCard Chancefield1 = new MoneyCard(300, "Du vinder i lotto, modtag kr. 300,-");
		MoveCar Chancefield2 = new MoveCar("Du rykker 2 felter frem", 2);
		chancecontroller = new Chance("test", 1000, new ChanceCard[]{Chancefield1, Chancefield2});
		spiller1 = new Player("Obama", 0);
		
		
	}
	@Test
	public void testLandonField(){
	
	chancecontroller.landOnField(spiller1);
	
	
	}
	
}

