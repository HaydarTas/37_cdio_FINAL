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

	private Player spiller1;
	private Player spiller2;
	private Property property1;
	private Property property2;
	GameController GameController;

	@Before
	public void setUp() throws Exception {

		property1 = new Property("Frederikberggade", new int[]{1000, 2200, 2600, 3000, 3400, 3800 }, 3700, 9500, 0, GameController);

		property2 = new Property("Rådhuspladsen", new int[]{1100, 2300, 2700, 3100, 3500, 3900 }, 3800, 10000, 0, GameController);

		spiller1 = new Player("Morten", 0);

		spiller1.addToBalance(10000);

		spiller2 = new Player("Casper", 1);

		spiller2.addToBalance(15000);
		
		
	}

	@After
	public void tearDown() throws Exception {
		property1 = null;
		property2 = null;
		spiller1 = null;
		spiller2 = null;
		
	}

	@Test
	public void testNewOwner() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander på Property1, den koster 6.000
		property1.landOnField(spiller1);
		
		
		
		// udregn det forventede resultat
		int expected = 34000;
		
		//f� saldo ud af spillerens konto
		int actual = spiller1.getBalance();
		
		//test om de tal er ens
		Assert.assertEquals(expected, actual);
	}
	
	//To spillere lander på samme felter efter hinanden
	// den første betaler for at eje, den næste betaler husleje
	@Test
	public void testAlreadyOwned() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
//		int saldo = spiller1.getBalance();
//		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander p� Property1, den koster 6.000
		property1.landOnField(spiller1);
		
		
			boolean bought = spiller1.buyField(property1);
		
		
		int saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		property1.landOnField(spiller2);

		saldo = 45000 - 1000;
		
		if(bought){
			Assert.assertEquals(saldo, spiller2.getBalance());
		}


		
//		// udregn det forventede resultat
//		int expected = 40000 - 6000;
//		
//		//f� saldo ud af spillerens konto
//		int actual = spiller1.getBalance();
//		
//		//test om de tal er ens
//		Assert.assertEquals(expected, actual);
//		
//		// Nu lander spiller 2 p� samme felt.. dvs han skal betale husleje p� 1.000
//		Property1.landOnField(spiller2);
//		// udregn det forventede resultat
//		expected = 15000 - 1000;
//		
//		//f� saldo ud af spillerens konto
//		actual = spiller2.getBalance();
//		//test om de tal er ens
//		Assert.assertEquals(expected, actual);
	}

	// Vi tester om huslejlen passer med det forventede tal, som vi har
	// defineret h�jere oppe i setUp metoden
	public void testGetRent(){
		int expected = 5000;
		int actual = property2.getRent();
		
		Assert.assertTrue(expected == actual);
	}

	//På samme måde tester vi prisen for feltet
	public void testGetPrice(){
		int expected = 11000;
		int actual = property2.getPrice();
		
		Assert.assertTrue(expected == actual);
	}
}
