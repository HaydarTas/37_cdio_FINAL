package test;

import org.junit.*;

import field.Field;
import entity.Player;
import field.Refuge;

public class RefugeTest {

	private Player player;

	private Field refuge2;

	private Field refuge0;

	private Field refugeNeg200;

	private int startBeloeb;
	
	@Before

	public void setUp() throws Exception {

		this.player = new Player("Mufasa", 1);
		startBeloeb = this.player.getBalance();
		
		this.refuge2 = new Refuge( "simba +200", 200);

		this.refuge0 = new Refuge("simba 0", 0);

		this.refugeNeg200 = new Refuge("simba ­200", -200);

	}

	@After

	public void tearDown() throws Exception {

	}

	@Test

	public void testEntities() {

		//The fields are unaltered

		Assert.assertNotNull(this.player);

		Assert.assertNotNull(this.refuge2);

		Assert.assertNotNull(this.refuge0);

		Assert.assertNotNull(this.refugeNeg200);

		Assert.assertTrue(this.refuge2 instanceof Refuge);

		Assert.assertTrue(this.refuge0 instanceof Refuge);

		Assert.assertTrue(this.refugeNeg200 instanceof Refuge);

	}

	@Test

	public void testLandOnField200() {

		int expected = startBeloeb;

		int actual = this.player.getBalance();

		Assert.assertEquals(expected, actual);

		//Perform the action to be tested

		this.refuge2.landOnField(this.player);

		expected = startBeloeb + 200;

		actual = this.player.getBalance();

		Assert.assertEquals(expected, actual);

	}

	@Test

	public void testLandOnField200Twice() {

		int expected = startBeloeb;

		//Perform the action to be tested

		this.refuge2.landOnField(this.player);

		this.refuge2.landOnField(this.player);

		expected = startBeloeb + 200 + 200;

		int actual = this.player.getBalance();

		Assert.assertEquals(expected, actual);
	}
}
