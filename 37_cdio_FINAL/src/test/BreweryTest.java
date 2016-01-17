package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Player;
import field.Brewer;

public class BreweryTest {
	private Brewer Brewer1;
	private Brewer Brewer2;
	
	private Player spiller1;
	private Player spiller2;

	@Before
	// setUp metoden bliver kaldt FøR hver test metode
	public void setUp(){
		Brewer1 = new Brewer("Brewer1, leje: 1000, pris: 6000", 1000, 6000, null);
	
		Brewer2 = new Brewer("Brewer2, leje: 5000, pris: 11000", 5000, 11000, null);

		spiller1 = new Player("Obama", 1);
		spiller1.addToBalance(10000);
		
		spiller2 = new Player("Clinton", 2);
		spiller2.addToBalance(15000);
	}
	// En spiller lander p� et frit felt
	@Test
	public void testNewOwner() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander på Territory1, den koster 6.000
		Brewer1.landOnField(spiller1);
		
		boolean bought = false;
		
		
		
		//hvis han fik lov til at købe feltet, så test saldo - pris
		if(bought){
			// udregn det forventede resultat
			int expected = 40000 - 6000;
			
			//få saldo ud af spillerens konto
			int actual = spiller1.getBalance();
			//test om de tal er ens
			Assert.assertEquals(expected, actual);
		}else{
			// hvis ikke han kunne købe feltet, så skal saldoen være startbeløbet
			saldo = spiller1.getBalance();
			Assert.assertEquals(40000, saldo);
		}
	}

	@Test
	public void testAlreadyOwned() {
		int expected, actual;
		
		// en simpel test for at sikre at startbel�bet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);

		// en simpel test for at sikre at startbel�bet er rigtigt
		saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		//spiller 1 lander p� Fleet, den koster 16.000
		Brewer1.landOnField(spiller1);
		
		boolean bought = false;
		
		
		
		// fik han købt feltet? hvis ja, test om prisen er fratrukket saldoen
		if(bought){
			expected = 40000 - 6000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
			//ellers burde beløbet ikke være fratrukket
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		
		// test om spiller 2 har det rigtige startbeløb
		saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		// hvis spiller 1 havde købt feltet, så må spiller 2 betale husleje
		if(bought){
			
			//lad spiller 2 lande på feltet
			Brewer1.landOnField(spiller2);
			boolean rented = false;
			
			
			
			//hvis det lykkedes at leje feltet, test om lejepris er fratrukket saldo
			if(rented){
				expected = 45000 - 1000;
				actual = spiller2.getBalance();
				Assert.assertEquals(expected, actual);
			}
		}
	}

}