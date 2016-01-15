package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.GameController;
import entity.Player;
import field.Property;

public class PropertyTest {

	private Player p1;
	private Player p2;
	private Property property1;
	private Property property2;
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
		property1 = null;
		property2 = null;
		p1 = null;
		p2 = null;
	}

	@Test
	public void testNewOwner() {
		
		int saldo1 = p1.getBalance();
		Assert.assertEquals(40000, saldo1);

		property1.landOnField(p1);
		
		p1.buyField(property1);
	

		int expected = 40000-3700;
		int actual = p1.getBalance();

		Assert.assertEquals(40000-3700, actual);


	}   
	@Test
	public void testBuildHouse() {
		int saldo1 = p1.getBalance();
		Assert.assertEquals(40000, saldo1);

		int saldo2 = p2.getBalance();
		Assert.assertEquals(45000, saldo2);

		property1.landOnField(p1);
		p1.buyField(property1);
		

		int expected = 40000-3700;
		int actual = p1.getBalance();

		Assert.assertEquals(40000-3700, actual);
//
//		if (((Property)property1).addHouse() != null){
//
//			Assert.assertTrue(false);
//		}
//		property2.landOnField(p1);
//		p1.buyField(property2);
//		
//		boolean bought2 = false;
//		if(bought2){
//			Assert.assertTrue(true);
//		}
//
//		int expected2 = 40000-3700-3800;
//		int actual2 = p1.getBalance();
//
//		Assert.assertEquals(40000-3700-3800, actual2);
//
//		if (((Property)property1).addHouse() != null){
//
//			Assert.assertTrue(true);
//		}
//		
//		property1.landOnField(p2);
//		int expected3 = 40000-2200;
//		int actual3 = p2.getBalance();
//		Assert.assertEquals(4000-2200, actual);


	}
}
