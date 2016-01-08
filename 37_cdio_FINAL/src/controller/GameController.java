package controller;

package controller;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Start;
//import desktop_fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.*;

public class GameController {
	private static final Color red = null;
	private Dicebox box = new Dicebox();
	private Player[] players = new Player[6];
	private int playerCount;
	private Field[] fields;
	private int playerTurn;
	private desktop_fields.Field[] start;

	public void run() {
		Player tmp = players[playerTurn];
		switch(tmp.getInformation()){
		case -1: 
			GUI.showMessage(String.format("Din balance er nu: %d  styre lige din økonomi", tmp.getBalance()));  
			break;
		case 0:
			String answer = GUI.getUserSelection("Vil du koebe feltet?", "Ja","Nej");
			switch(answer){
			case "Ja": 
				if(tmp.buyField((Ownable)fields[tmp.getPosition()])) {
					GUI.setBalance(tmp.getName(), tmp.getBalance());
					GUI.setOwner(tmp.getPosition()+1, tmp.getName());
				} else {
					GUI.showMessage("Du har ikke nok penge!");
				}
				break;
			}
			break;
		case 1: 
			GUI.showMessage("Du ejer feltet");
			break;
		case 2:
			if(fields[tmp.getPosition()] instanceof Tax) {
				GUI.showMessage("du har nu betalt!!!");
			} else {
				GUI.showMessage("SHOW ME DA MONEYYYYY!!!");
			}
		}
		this.switchTurn();
		
	}

	private void playerTurn(Player player) {
		int roll = box.roll();
		int points = fields[roll].getPoints();
		String fieldname = fields[roll].getName();
		boolean check_account = player.addToBalance(points);

		if (check_account == true) {
			GUI.setBalance(player.getName(), player.getBalance());
		} else {
			player.setBankrupt();
		}

		System.out.println("spiller" + player.getName() + "  har slaaet: " + roll + " han fik: " + points
				+ " og han har landet paa felt: " + fields + ", saldo:" + player.getBalance());
		GUI.removeAllCars(player.getName());
		GUI.setCar(roll - 1, player.getName());
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
				new Territory("Rødovrevej", 200, 1000), 
				new Territory("Hvidovrevej", 300, 1400),
				new Territory("Jernbane alle", 500, 1600), 
				new Territory("Roskilevej", 700, 2000),
				new Territory("valby langgade", 1000, 2500), 
				new Territory("Allegade", 1300, 4000),
				new Territory("Frederiksberg Alle", 2000, 4800), 
				new Territory("Bulowsvej", 2500, 5300),
				new Territory("Gl.kongevej", 2600, 6000), 
				new Territory("Bernstorffsvej", 3200, 6200),
				new Territory("Hellerupsvej", 4000, 7000),
				new Territory("Strandvej", 4500, 7500),
				new Territory("Triaglen", 4600, 8000),
				new Territory("Østerbrogade", 4700, 9000),	
				new Territory("Grønningen", 4900, 10000),
				new Territory("Bredgade", 5000, 12000),
				
				new Refuge("Parking", 4000),
				new Refuge("parking", 4000),
				new entity.Brewery("Coca", 2500, 5000), 
				new entity.Brewery("TUBORG", 2500, 5000),
				new Tax("Tax", 0), new Tax("Tax", 0), 
				new Fleet("DFDS seaways", 4000),
				new Fleet("CM port", 4000), 
				new Fleet("Rødby Havn", 4000),
				new Fleet("Helsingør Havn", 4000) };
				new Territory("Kgs.nytorv",5000,13000);
	}

	// adds fields to GUI
	public void setupGUI() {

		desktop_fields.Field[] fields = new desktop_fields.Field[29];
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
		
		st= new Street.Builder().setBgColor(Color.red).setSubText("Østerbrogade").build();
		st.setDescription("Østerbrogade");
		st.setSubText("Pris: 9000");
		fields[14] = st;
		
		st= new Street.Builder().setBgColor(Color.red).setSubText("Grønningen").build();
		st.setDescription("Grønningen");
		st.setSubText("Pris: 10000");
		fields[15] =st;
		
		st= new Street.Builder().setBgColor(Color.red).setSubText("Bredgade").build();
		st.setDescription("Bredgade");
		st.setSubText("Pris: 12000");
		fields[16]= st;
		desktop_fields.Brewery b=null;
		b=new desktop_fields.Brewery.Builder().setBgColor(Color.magenta).setTitle("COCA COLA").build();
		b.setDescription("Coca Cola");
		b.setSubText("Pris: 5000");
		fields[17]= b;
		
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
		t = new desktop_fields.Tax.Builder().setBgColor(Color.magenta).setTitle("Tax").build();
		t.setDescription("Skat");
		t.setSubText("du skal betale min ven");
		fields[21] = t;

		t = new desktop_fields.Tax.Builder().setBgColor(Color.CYAN).setTitle("Tax").build();
		t.setDescription("Skat");
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
		
		desktop_fields.Chance c=null;
        c =new desktop_fields.Chance.Builder().setBgColor(Color.RED).build();
        c.setDescription("prøv lykken");
        c.setSubText("better luck nextime");
        fields[27] = c;
        
        st= new Street.Builder().setBgColor(Color.red).setSubText("Kgs.Nytorv").build();
        st.setDescription("Kgs.Nytorv");
        st.setSubText("Pris: 13000");
		fields[28]=st;
		           

		GUI.create(fields);
	}

	private Object valueOf(String string, String[] attributes) {
			// TODO Auto-generated method stub
			return null;
		}

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

	public void switchTurn() {
		int leif = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				leif++;
			}
		}
		switch (playerTurn) {
		case 0:
			playerTurn = 1;
			break;
		case 1:
			if(leif == 2) {
				playerTurn = 0;
			} else {
				playerTurn = 2;
			}
			break;
		case 2:
			if (leif == 3){
				playerTurn = 0;
			} else {
				playerTurn = 3;
			}
			break;
		case 3:
			if (leif == 4) {
				playerTurn = 0;
			} else {
				playerTurn = 4;
			}
			break;
		case 4:
			if (leif == 5) {
				playerTurn = 0;
			} else {
				playerTurn = 5;
			}
			break;
		case 5:
			playerTurn = 0;
			break;
		}
	}
	
	public int landOnField(){
		Player unicorn = players[playerTurn];
		int position = unicorn.getPosition() % 29;
		fields[position].landOnField(unicorn);
		return unicorn.getInformation();
	}
	
}

