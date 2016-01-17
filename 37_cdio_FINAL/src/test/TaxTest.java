package test;

import org.junit.*;

import field.Field;
import entity.Player;
import field.Tax;

// Vi definerer en klasse TaxTest, hvor vi tester felt typen TAX

public class TaxTest {
	private Tax tax_1;
	private Tax tax_2;
	private Tax tax_3;

	private int startBeloeb;
	
	private Player spiller;

	@Before
	// setUp metoden bliver kaldt FÃ˜R hver test metode
	public void setUp(){
		
		//opret 3 typer skat (beløb og procent )
		tax_1 = new Tax("Betal 2000", 2000);
		tax_2 = new Tax("Betal 1000 eller 10%", 1000, 10);
		tax_3 = new Tax("Betal 4000", 4000);
	
		//opret en ny spiller med 30.000kr i konto
		spiller = new Player("Mouse", 1);
		startBeloeb = spiller.getBalance();
	}
	@After
	// setUp metoden bliver kaldt FÃ˜R hver test metode
	public void tearDown(){
		spiller = new Player("Mouse", 1);
	}
	//I denne metode testes om beløbet trÃ¦kkes fra spillerens konto
	
	@Test
	public void testBelob2000(){
		int saldo = spiller.getBalance();
		Assert.assertEquals(saldo, startBeloeb);
		
		tax_1.landOnField(spiller);
		tax_1.pay();
		
		saldo = spiller.getBalance();
		Assert.assertEquals(saldo, startBeloeb - 2000);	
		
	}
	
	@Test
	//I denne metode testes om der trÃ¦kkes 10% fra konto
	public void test10Procent(){
		
		int tiProcent = (10 * spiller.getBalance()) / 100;

		tax_2.landOnField(spiller);
		tax_2.payPercent();
		
		//udregn 10% af pengebeholdning
		int saldo = startBeloeb - tiProcent;

		// test om saldoen vi har regnet os frem til er det samme som spiller har i kontoen
		Assert.assertTrue(saldo == spiller.getBalance());
	}
	
	public void testBelob4000(){
		tax_3.landOnField(spiller);
		tax_3.pay();
		int expected = startBeloeb - 4000;
		
		Assert.assertEquals(expected, spiller.getBalance());
	}
	
}
