package controller;



import java.awt.Color;
import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Start;
//import desktop_fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.*;
import field.*;


public class GameController {
	private static final Color red = null;
	private Dicebox box = new Dicebox();
	private Player[] players = new Player[6];
	private int playerCount;
	private Field[] fields;
	private int playerTurn;
	private desktop_fields.Field[] start;
	private String buttons;

	public void run() {
		while (true){
			//Kast med terninger
			roll();
			//Find ud af hvad der sker p� feltet!
			landOnField();
			this.switchTurn();
		}

	}
	private void playerTurn(Player player) {
		int roll = box.roll();
		int newPosition = (player.getPosition() + roll) % fields.length;

		int points = fields[newPosition].getPoints();
		String fieldname = fields[newPosition].getName();
		boolean check_account = player.addToBalance(points);

		if (check_account == true) {
			GUI.setBalance(player.getName(), player.getBalance());
		} else {
			player.setBankrupt();
		}

		System.out.println("spiller" + player.getName() + "  har slaaet: " + roll + " han fik: " + points
				+ " og han har landet paa felt: " + fields + ", saldo:" + player.getBalance());
		GUI.removeAllCars(player.getName());
		GUI.setCar(newPosition - 1, player.getName());
		// Suspend excecution for 200 ms
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// creates fields in array
	public void setupGame() {
		fields = new Field[] { 
				new Refuge("Start",4000),
				new Property("R�dovrevej", new int[]{4900, 2500}, 200, 1000, 5), 
				new Property("Hvidovrevej", new int[]{4900, 2500}, 300, 1400, 5),
				new Property("Jernbane alle", new int[]{4900, 2500}, 500, 1600), 
				new Property("Roskilevej", new int[]{4900, 2500}, 700, 2000, 7),
				new Property("valby langgade", new int[]{4900, 2500}, 1000, 2500, 7), 
				new Property("Allegade", new int[]{4900, 2500}, 1300, 4000, 7),
				new Property("Frederiksberg Alle", new int[]{4900, 2500}, 2000, 4800, 6), 
				new Property("Bulowsvej", new int[]{4900, 2500}, 2500, 5300, 6),
				new Property("Gl.kongevej", new int[]{4900, 2500}, 2600, 6000, 6), 
				new Property("Bernstorffsvej", new int[]{4900, 2500}, 3200, 6200, 3),
				new Property("Hellerupsvej", new int[]{4900, 2500}, 4000, 7000, 3),
				new Property("Strandvej", new int[]{4900, 2500}, 4500, 7500, 3),
				new Property("Triaglen", new int[]{4900, 2500}, 4600, 8000, 4),
				new Property("Østerbrogade", new int[]{4900, 2500}, 4700, 9000, 4),	
				new Property("Grønningen", new int[]{4900, 2500}, 4900, 10000, 4),
				new Property("Bredgade", new int[]{4900, 2500}, 5000, 12000, 2),
				new Property("Kgs.nytorv",new int[]{4900, 2500}, 5000,13000, 2),
				new Property("Østergade",new int[]{4900, 2500}, 5000,13000, 2),
				new Property("Amagertorv",new int[]{4900, 2500}, 5000,13000, 1),
				new Property("Vimmelskaftet", new int[]{4900, 2500}, 5000, 14000, 1),
				new Property("Nygade", new int[]{4900, 2500}, 5000, 14500, 1),
				new Property("Rådhuspladsen", new int[]{4900, 2500}, 7000, 15000, 0),
				new Property("Frederikberggade", new int[]{4900, 2500}, 7000, 14000, 0),
				new Property("GL.Kongevej", new int[]{4900, 2500}, 7000, 13000),
				new Property("h.c. andersen boulevard", new int[]{4900, 2500}, 7000, 15000),






				new Refuge("Parking", 4000),
				new Refuge("parking", 4000),
				new Brewer("Coca", 2500, 5000), 
				new Brewer("TUBORG", 2500, 5000),
				new Tax("Tax", 0),
				new Tax("Tax", 0), 
				new Shipping("DFDS seaways", 4000),
				new Shipping("CM port", 4000), 
				new Shipping("Rødby Havn", 4000),
				new Shipping("Helsingør Havn", 4000),



		};
	}

	// adds fields to GUI
	public void setupGUI() {

		desktop_fields.Field[] fields = new desktop_fields.Field[40];
		Street st = null;
		Start s= null;

		s = new Start.Builder().setBgColor(Color.RED).setTitle("Start").build();
		s.setDescription("modtag: 4000");
		s.setSubText("");
		fields[0]=s;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Rødovrevej").build();
		st.setDescription("Rødovrevej");
		st.setSubText("pris: 1000");
		fields[1] = st;

		st = new Street.Builder().setBgColor(Color.LIGHT_GRAY).setTitle("Hvidovrevej").build();
		st.setDescription("pris: 1400");
		st.setSubText("pris: 1400");
		fields[2] = st;

		st = new Street.Builder().setBgColor(Color.white).setTitle("Jernbane alle").build();
		st.setDescription("Jernbane alle");
		st.setSubText("Pris: 1600");
		fields[3] = st;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Roskildevej").build();
		st.setDescription("Roskildevej");
		st.setSubText("Pris: 2000");
		fields[4] = st;

		st = new Street.Builder().setBgColor(Color.green).setTitle("Valby langgade").build();
		st.setDescription("Valby langgade");
		st.setSubText("Pris: 2500");
		fields[5] = st;

		st = new Street.Builder().setBgColor(Color.orange).setTitle("Allegade").build();
		st.setDescription("Allegade");
		st.setSubText("Pris: 4000");
		fields[6] = st;

		st = new Street.Builder().setBgColor(Color.green).setTitle("Frederiksberg Alle").build();
		st.setDescription("Frederiksberg Alle");
		st.setSubText("Pris:4800");
		fields[7] = st;

		st = new Street.Builder().setBgColor(Color.magenta).setTitle("Bulowsvej").build();
		st.setDescription("Bulowsvej");
		st.setSubText("Pris: 5300");
		fields[8] = st;

		st= new Street.Builder().setBgColor(Color.CYAN).setTitle("Gl.kongevej").build();
		st.setDescription("Gl.kongevej");
		st.setSubText("Pris: 6000");
		fields[9] = st;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Bernstorffsvej").build();
		st.setDescription("Bernstorffsvej");
		st.setSubText("Pris: 6200");
		fields[10] = st;

		st = new Street.Builder().setBgColor(Color.blue).setTitle("Hellerupsvej").build();
		st.setDescription("Hellerupsvej");
		st.setSubText("Pris: 7000");
		fields[11] = st;

		st = new Street.Builder().setBgColor(Color.white).setTitle("Strandvej").build();
		st.setDescription("Strandvej");
		st.setSubText("Pris: 7500");
		fields[12] = st;

		st = new Street.Builder().setBgColor(Color.green).setTitle("Trianglen").build();
		st.setDescription("Trianglen");
		st.setSubText("Pris: 8000");
		fields[13] = st;

		st= new Street.Builder().setBgColor(Color.red).setTitle("Østerbrogade").build();
		st.setDescription("Østerbrogade");
		st.setSubText("Pris: 9000");
		fields[14] = st;

		st= new Street.Builder().setBgColor(Color.red).setTitle("Grønning").build();
		st.setDescription("Grønningen");
		st.setSubText("Pris: 10000");
		fields[15] =st;

		st= new Street.Builder().setBgColor(Color.red).setTitle("Bredgade").build();
		st.setDescription("Bredgade");
		st.setSubText("Pris: 12000");
		fields[16]= st;

		desktop_fields.Chance c=null;
		c =new desktop_fields.Chance.Builder().setBgColor(Color.RED).build();
		c.setDescription("prøv lykken");
		c.setSubText("better luck nextime");
		fields[17] = c;

		desktop_fields.Brewery b=null;
		b=new desktop_fields.Brewery.Builder().setBgColor(Color.magenta).setTitle("TUBORG").build();
		b.setDescription("Tuborg");
		b.setSubText("Pris: 5000");
		fields[18]= b;


		desktop_fields.Refuge r = null;
		r = new desktop_fields.Refuge.Builder().setFgColor(Color.DARK_GRAY).setTitle("Parking").build();
		r.setDescription("parking");
		r.setSubText("Modtag: 4000");
		fields[19] = r;

		r = new desktop_fields.Refuge.Builder().setBgColor(Color.DARK_GRAY).setTitle("Parking").build();
		r.setDescription("parking");
		r.setSubText("modtag: 4000");
		fields[20] = r;

		desktop_fields.Tax t = null;
		t = new desktop_fields.Tax.Builder().setBgColor(Color.magenta).setDescription("Tax").build();
		t.setDescription("tax");
		t.setSubText("du skal betale min ven");
		fields[21] = t;

		t = new desktop_fields.Tax.Builder().setBgColor(Color.CYAN).setDescription("Tax").build();
		t.setDescription("tax");
		t.setSubText("betale nu din skat");
		fields[22] = t;

		desktop_fields.Shipping f = null;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.gray).setTitle("DFDS seaways").build();
		f.setDescription("DFDS seaways");
		f.setSubText("Pris:4000");
		fields[23] = f;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.blue).setTitle("CM port").build();
		f.setDescription("CM port");
		f.setSubText("Pris:4000");
		fields[24] = f;
		f = new desktop_fields.Shipping.Builder().setBgColor(Color.CYAN).setTitle("Rødby havn").build();
		f.setDescription("Rødby Havn");
		f.setSubText("Pris: 4000");
		fields[25] = f;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.green).setTitle("Helsingør Havn").build();
		f.setDescription("Helsingør Havn");
		f.setSubText("Pris: 4000");
		fields[26] = f;

		b=new desktop_fields.Brewery.Builder().setBgColor(Color.magenta).setTitle("COCA COLA").build();
		b.setDescription("Coca Cola");
		b.setSubText("Pris: 5000");
		fields[27]= b;

		st= new Street.Builder().setBgColor(Color.gray).setTitle("Kgs.Nytorv").build();
		st.setDescription("Kgs.Nytorv");
		st.setSubText("Pris: 13000");
		fields[28]=st;

		st= new Street.Builder().setBgColor(Color.gray).setTitle("Østergade").build();
		st.setDescription("Østergade");
		st.setSubText("Pris: 13000");
		fields[29]=st;

		st= new Street.Builder().setBgColor(Color.gray).setTitle("Amagertorv").build();
		st.setDescription("Amagertorv");
		st.setSubText("Pris: 13000");
		fields[30]=st;

		st= new Street.Builder().setBgColor(Color.cyan).setTitle("Vimmelskaftet").build();
		st.setDescription("Vimmelskaftet");
		st.setSubText("Pris: 14000");
		fields[31]=st;

		st= new Street.Builder().setBgColor(Color.LIGHT_GRAY).setTitle("Nygade").build();
		st.setDescription("Nygade");
		st.setSubText("Pris: 14500");
		fields[32]=st;

		st= new Street.Builder().setBgColor(Color.RED).setTitle("Rådhuspladsen").build();
		st.setDescription("Rådhuspladsen");
		st.setSubText("Pris: 15000");
		fields[33]=st;

		st= new Street.Builder().setBgColor(Color.RED).setTitle("Vesterbrogade").build();
		st.setDescription("Vesterbrogade");
		st.setSubText("Pris: 12000");
		fields[34]=st;

		desktop_fields.Jail j=null;			
		j = new desktop_fields.Jail.Builder().setBgColor(Color.WHITE).setTitle("jail").build();
		j.displayOnCenter();
		j.setDescription("De fængsles");
		j.setSubText("dont drop the soap");
		fields[35] = j ;

		j =new desktop_fields.Jail.Builder().setBgColor(Color.white).setTitle("jail").build();
		j.displayOnCenter();
		j.setDescription("fængsel");
		j.setSubText("du er på besøg");
		fields[36] =j;

		c=new desktop_fields.Chance.Builder().setBgColor(Color.red).build();
		c.displayOnCenter();
		c.setDescription("prøv lykken");
		c.setSubText("tag chancen");
		fields[37]=c;


		st= new Street.Builder().setBgColor(Color.GREEN).setTitle("GL.kongevej").build();
		st.setDescription("GL.Kongevej");
		st.setSubText("Pris: 13000");
		fields[38]=st;

		st= new desktop_fields.Street.Builder().setBgColor(Color.lightGray).setTitle("h.c. andersen boulevard").build();
		st.setDescription("h.c. andersen boulevard");
		st.setSubText("Pris: 15000");
		fields[39]=st;



		GUI.create(fields);
	}
	//
	//	private Object valueOf(String string, String[] attributes) {
	//			// TODO Auto-generated method stub
	//			return null;
	//		}

	private Car getCar(int i) {
		Car car = null;
		switch (i) {
		case 0:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.RED).build();
			break;
		case 1:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.BLACK).build();
			break;
		case 2:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.BLUE).build();
			break;
		case 3:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.GREEN).build();
			break;
		case 4:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.GRAY).build();
			break;
		case 5:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.CYAN).build();
			break;
		}
		return car;
	}

	//
	public void addPlayer() {
		String playerName = GUI.getUserString("Write user name, minum 2-6 names, after x - names/player, leave empty space press OK");

		if (playerName.length() != 0) {
			GUI.addPlayer(playerName, 30000, getCar(playerCount));
			playerCount++;
			for (int i = 0; i < players.length; i++) {
				if (players[i] == null) {
					players[i] = new Player(playerName, i);
					break;
				}
			}
		}
	}

	public void roll() {
		GUI.getUserButtonPressed("Kast terning", "Dice");
		int r = box.roll();
		GUI.setDice(box.getDice()[0].getValue(), box.getDice()[1].getValue());
		players[playerTurn].setLastRoll(r);
		if(players[playerTurn].getPosition() >= 0)
			GUI.removeCar(players[playerTurn].getPosition()+1, players[playerTurn].getName());
		players[playerTurn].movePlayer(r);
		GUI.setCar(players[playerTurn].getPosition()+1, players[playerTurn].getName());
	}
	//TODO rewrite to something 3 lines long
	public void switchTurn() {
		int noOfPlayersInGame = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				noOfPlayersInGame++;
			}
		}
		playerTurn++;
		if (playerTurn>= noOfPlayersInGame)
			playerTurn = 0;
		}
	

	public void landOnField(){

		Player playa = players[playerTurn];
		int position = playa.getPosition() % fields.length;
		System.out.println("-------------------------------------");
		fields[position].landOnField(playa);

		System.out.println("playa:" + playa.getName());
		System.out.println("slag:" + playa.getLastRoll());

		System.out.println("position:" + position);
		System.out.println("playa.getPosition():" + playa.getPosition());
		System.out.println("playa.geetMoney():" + playa.getBalance());

		//		System.out.println("felt:" + fields[position-1].getName());
		//		for (Field field : fields) {
		//			System.out.println(field.getName());
		//		}
	}

}

