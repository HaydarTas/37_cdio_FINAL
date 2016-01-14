package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.GameController;
import entity.Player;
import entity.Territory;
import field.Property;

public class PropertyTest {

	Player p1;
	Player p2;
	Property property1;
	Property property2;
	GameController GameController;
	
	@Before
	public void setUp() throws Exception {
		
		property1 = new Property("Frederikberggade", new int[]{1000, 2200, 2600, 3000, 3400, 3800 }, 3700, 9500, 0, GameController);
		
		property2 = new Property("Rådhuspladsen", new int[]{1100, 2300, 2700, 3100, 3500, 3900 }, 3800, 10000, 0, GameController);
		
		p1 = new Player("Morten", 0);
		
		p1.addToBalance(10000);
		
		p2 = new Player("Casper", 1);
		
		p2.addToBalance(15000);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testNewOwner() {
		int saldo = p1.getBalance();
		Assert.assertEquals(40000, saldo);
	}

}
