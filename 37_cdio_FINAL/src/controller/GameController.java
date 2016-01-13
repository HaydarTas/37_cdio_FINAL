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
	private Field[] fields;
	private int playerCount;
	private int playerTurn =0;
	private desktop_fields.Field[] start;
	private String buttons;



	public void run() {
		while (true){
			//Kast med terninger
			Player activePlayer = players[playerTurn];
			if (activePlayer.getJailed()){
				jailTurn(activePlayer);
			} else {
				playerTurn(activePlayer);
			}

			this.switchTurn();
		}

	}
	private void jailTurn(Player activePlayer) {
		System.out.println(activePlayer.getJailTime());
		
		if(activePlayer.getJailed()){
			
			if(activePlayer.getJailTime()==3){
				activePlayer.addToBalance(-1000);
				GUI.showMessage("du har nu betalt 1000 dk.kr");
					activePlayer.setJailTime(0);
					activePlayer.setJail(false);
				
			}
			
		
			String res = GUI.getUserButtonPressed("vil du betale eller slå med terning t ", "Slå", "Betal");
			switch (res) {
			case "Slå":
				Dicebox db = new Dicebox();
				db.roll();
				//GUI.setDice(2, 4);
				System.out.println(db.getDice()[0].getValue()+", "+ db.getDice()[1].getValue());
				GUI.setDice(db.getDice()[0].getValue(), db.getDice()[1].getValue());
				if(db.isEqual()){
					activePlayer.setJailTime(0);
					break;
				}
			case "betal":
			
				activePlayer.addToBalance(-1000);
		GUI.showMessage("du har nu betalt 1000 dk.kr");
			activePlayer.setJailTime(0);
			
			break;
			
			}
			activePlayer.addjailtimecounter();
		}

	}
	private void playerTurn(Player player) {
		roll();
		landOnField();



	}


	// creates fields in array
	public void setupGame() {

		ChanceCard[] cards = {
			
//				new MoneyCard(300, "Du vinder i lotto, modtag kr. 300,-"),
//				new MoneyCard(-500, "Du har glemt at betale told, betal kr. 500,-"),
				new MoveCar("Du rykker 2 felter frem", 2),
		};

		fields = new Field[] { 
				new Refuge("Start",4000),
				new Property("Rødovrevej", new int[]{200, 600, 1000, 1400, 1800, 2200}, 2000, 4000, 5, this), 
				new Chance("lykke1", 0, cards),
				new Property("Hvidovrevej", new int[]{200, 600, 1000, 1400, 1800, 2200}, 2000, 4000, 5, this),
				new Tax("Tax", 1000, 10),
				new Shipping("DFDS seaways", 4000, this),
				new Property("Roskilevej", new int[]{300, 800, 1200, 1600, 2000, 2400}, 2200, 4500, 7, this),
				new Chance("lykke2", 1, cards),
				new Property("valby langgade", new int[]{300, 800, 1200, 1600, 2000, 2400}, 2200, 5000, 7, this), 
				new Property("Allegade", new int[]{300, 800, 1200, 1600, 2000, 2400}, 2200, 5000, 7, this),
				new Refuge("VESTERFÆNGSEL",0),
				new Property("Frederiksberg Alle", new int[]{400, 900, 1300, 1700, 2100, 2500}, 2300, 5500, 6, this), 
				new Brewer("Squash", 2500, 5000, this),
				new Property("Bulowsvej", new int[]{400, 900, 1300, 1700, 2100, 2500 }, 2500, 5300, 6, this),
				new Property("Gl.kongevej", new int[]{400, 1000, 1400, 1800, 2200, 2600 }, 2600, 6000, 6, this), 
				new Shipping("CM port", 4000, this),
				new Property("Bernstorffsvej", new int[]{400, 1100, 1500, 1900, 2300, 2700 }, 2700, 6200, 3, this),
				new Chance("lykke3", 3, cards),
				new Property("Hellerupsvej", new int[]{400, 1100, 1500, 1900, 2300, 2700 }, 2700, 7000, 3, this),
				new Property("Strandvej", new int[]{500, 1200, 1600, 2000, 2400, 2800 }, 2800, 7500, 3, this),
				new Refuge("Parking", 0),
				new Property("Triaglen", new int[]{500, 1300, 1700, 2100, 2500, 2900 }, 2900, 8000, 4, this),
				new Chance("lykke4", 4, cards),
				new Property("Østerbrogade", new int[]{500, 1300, 1700, 2100, 2500, 2900 }, 2900, 9000, 4, this),	
				new Property("Grønningen", new int[]{600, 1500, 1900, 2300, 2700, 3100 }, 3000, 10000, 4, this),
				new Shipping("Rødby Havn", 4000, this),
				new Property("Bredgade", new int[]{600, 1600, 2000, 2400, 2800, 3200 }, 3100, 12000, 2, this),
				new Property("Kgs.nytorv",new int[]{600, 1700, 2100, 2500, 2900, 3300 }, 3200, 13000, 2, this),
				new Brewer("Coca", 2500, 5000, this), 
				new Property("Østergade",new int[]{700, 1800, 2200, 2600, 3000, 3400 }, 3300, 13000, 2, this),
				new GoToJail("jail", playerCount),
				new Property("Amagertorv",new int[]{700, 1800, 2200, 2600, 3000, 3400 }, 3300, 13000, 1, this),
				new Property("Vimmelskaftet", new int[]{700, 1900, 2300, 2700, 3100, 3500 }, 3400, 14000, 1, this),
				new Chance("lykke5", 5, cards),
				new Property("Nygade", new int[]{800, 2000, 2400, 2800, 3200, 3600 }, 3500, 13500, 1, this),
				new Shipping("Helsingør Havn", 4000, this),
				new Chance("lykke6", 6, cards),
				new Property("Frederikberggade", new int[]{1000, 2200, 2600, 3000, 3400, 3800 }, 3700, 14500, 0, this),
				new Tax("Tax", 500), 
				new Property("Rådhuspladsen", new int[]{1100, 2300, 2700, 3100, 3500, 3900 }, 3800, 15000, 0, this),		


		};
	}



	// adds fields to GUI
	public void setupGUI() {

		desktop_fields.Field[] fields = new desktop_fields.Field[40];
		Street st = null;
		Start s= null;
		desktop_fields.Shipping f = null;
		desktop_fields.Chance c=null;
		desktop_fields.Tax t = null;
		desktop_fields.Jail j=null;
		desktop_fields.Refuge r = null;



		s = new Start.Builder().setBgColor(Color.RED).setTitle("Start").build();
		s.setDescription("Modtag: 4000");
		s.setSubText("");
		fields[0]=s;

		st = new Street.Builder().setBgColor(Color.blue).setTitle("Rødovrevej").build();
		st.setDescription("Rødovrevej");
		st.setSubText("pris: 1000");
		fields[1] = st;


		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("Prøv lykken");
		c.setSubText("Better luck nextime");
		fields[2] = c;

		st = new Street.Builder().setBgColor(Color.blue).setTitle("Hvidovrevej").build();
		st.setDescription("pris: 1400");
		st.setSubText("pris: 1400");
		fields[3] = st;


		t = new desktop_fields.Tax.Builder().setBgColor(Color.white).setDescription("Tax").build();
		t.setDescription("tax");
		t.setSubText("Du skal betale min ven");
		fields[4] = t;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.LIGHT_GRAY).setTitle("DFDS seaways").build();
		f.setDescription("DFDS seaways");
		f.setSubText("Pris:4000");
		fields[5] = f;


		st = new Street.Builder().setBgColor(Color.orange).setTitle("Roskildevej").build();
		st.setDescription("Roskildevej");
		st.setSubText("Pris: 2000");
		fields[6] = st;

		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("Prøv lykken");
		c.setSubText("Better luck nextime");
		fields[7] = c;

		st = new Street.Builder().setBgColor(Color.orange).setTitle("Valby langgade").build();
		st.setDescription("Valby langgade");
		st.setSubText("Pris: 2500");
		fields[8] = st;

		st = new Street.Builder().setBgColor(Color.orange).setTitle("Allegade").build();
		st.setDescription("Allegade");
		st.setSubText("Pris: 4000");
		fields[9] = st;

		fields[10] = new desktop_fields.Refuge.Builder().setPicture(null).build();

		//		j =new desktop_fields.Jail.Builder().setBgColor(Color.white).setTitle("VESTERFÆNGSEL").build();
		//		j.displayOnCenter();
		//		j.setDescription("VESTERFÆNGSEL");
		//		j.setSubText("Du er på besøg");
		//		fields[10] =j;

		st = new Street.Builder().setBgColor(Color.green).setTitle("Frederiksberg Alle").build();
		st.setDescription("Frederiksberg Alle");
		st.setSubText("Pris:4800");
		fields[11] = st;

		desktop_fields.Brewery b=null;
		b=new desktop_fields.Brewery.Builder().setBgColor(Color.magenta).setTitle("Squash").build();
		b.setDescription("Squash");
		b.setSubText("Pris: 5000");
		fields[12]= b;

		st = new Street.Builder().setBgColor(Color.green).setTitle("Bulowsvej").build();
		st.setDescription("Bulowsvej");
		st.setSubText("Pris: 5300");
		fields[13] = st;

		st= new Street.Builder().setBgColor(Color.green).setTitle("Gl. Kongevej").build();
		st.setDescription("Gl. Kongevej");
		st.setSubText("Pris: 6000");
		fields[14] = st;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.LIGHT_GRAY).setTitle("CM port").build();
		f.setDescription("CM port");
		f.setSubText("Pris:4000");
		fields[15] = f;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Bernstorffsvej").build();
		st.setDescription("Bernstorffsvej");
		st.setSubText("Pris: 6200");
		fields[16] = st;

		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("Prøv lykken");
		c.setSubText("Better luck nextime");
		fields[17] = c;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Hellerupsvej").build();
		st.setDescription("Hellerupsvej");
		st.setSubText("Pris: 7000");
		fields[18] = st;

		st = new Street.Builder().setBgColor(Color.gray).setTitle("Strandvej").build();
		st.setDescription("Strandvej");
		st.setSubText("Pris: 7500");
		fields[19] = st;


		r = new desktop_fields.Refuge.Builder().setFgColor(Color.white).setTitle("Parking").build();
		r.setDescription("Parking");
		r.setSubText("Modtag: 4000");
		fields[20] = r;

		st = new Street.Builder().setBgColor(Color.red).setTitle("Trianglen").build();
		st.setDescription("Trianglen");
		st.setSubText("Pris: 8000");
		fields[21] = st;

		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("Prøv lykken");
		c.setSubText("Better luck nextime");
		fields[22] = c;

		st= new Street.Builder().setBgColor(Color.red).setTitle("Østerbrogade").build();
		st.setDescription("Østerbrogade");
		st.setSubText("Pris: 9000");
		fields[23] = st;

		st= new Street.Builder().setBgColor(Color.red).setTitle("Grønning").build();
		st.setDescription("Grønningen");
		st.setSubText("Pris: 10000");
		fields[24] =st;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.CYAN).setTitle("Rødby havn").build();
		f.setDescription("Rødby Havn");
		f.setSubText("Pris: 4000");
		fields[25] = f;

		st= new Street.Builder().setBgColor(Color.white).setTitle("Bredgade").build();
		st.setDescription("Bredgade");
		st.setSubText("Pris: 12000");
		fields[26]= st;


		st= new Street.Builder().setBgColor(Color.white).setTitle("Kgs.Nytorv").build();
		st.setDescription("Kgs.Nytorv");
		st.setSubText("Pris: 13000");
		fields[27]=st;

		b=new desktop_fields.Brewery.Builder().setBgColor(Color.red).setTitle("COCA COLA").build();
		b.setDescription("Coca Cola");
		b.setSubText("Pris: 5000");
		fields[28]= b;

		st= new Street.Builder().setBgColor(Color.white).setTitle("Østergade").build();
		st.setDescription("Østergade");
		st.setSubText("Pris: 13000");
		fields[29]=st;


		j = new desktop_fields.Jail.Builder().setBgColor(Color.white).setTitle("jail").build();
		j.displayOnCenter();
		j.setDescription("Don't drop the soap");
		j.setSubText("De fængsles");
		fields[30] = j ;

		st= new Street.Builder().setBgColor(Color.yellow).setTitle("Amagertorv").build();
		st.setDescription("Amagertorv");
		st.setSubText("Pris: 13000");
		fields[31]=st;

		st= new Street.Builder().setBgColor(Color.yellow).setTitle("Vimmelskaftet").build();
		st.setDescription("Vimmelskaftet");
		st.setSubText("Pris: 14000");
		fields[32]=st;

		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("prøv lykken");
		c.setSubText("better luck nextime");
		fields[33] = c;

		st= new Street.Builder().setBgColor(Color.yellow).setTitle("Nygade").build();
		st.setDescription("Nygade");
		st.setSubText("Pris: 14500");
		fields[34]=st;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.blue).setTitle("Helsingør Havn").build();
		f.setDescription("Helsingør Havn");
		f.setSubText("Pris: 4000");
		fields[35] = f;

		c =new desktop_fields.Chance.Builder().setBgColor(Color.white).build();
		c.setDescription("prøv lykken");
		c.setSubText("better luck nextime");
		fields[36] = c;


		st= new Street.Builder().setBgColor(Color.magenta).setTitle("Frederiksberggade").build();
		st.setDescription("Frederiksberggade");
		st.setSubText("Pris: 13000");
		fields[37]=st;

		t = new desktop_fields.Tax.Builder().setBgColor(Color.white).setDescription("tax").build();
		t.setDescription("tax");
		t.setSubText("betal din skat");
		fields[38] = t;

		st= new Street.Builder().setBgColor(Color.magenta).setTitle("Rådhuspladsen").build();
		st.setDescription("Rådhuspladsen");
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
		String playerName = GUI.getUserString("Indtast spillernes navne og derefter lad feltet stå tomt og tryk 'OK' (2 - 6 spillere).");

		if (playerName.length() != 0) {
			GUI.addPlayer(playerName, 30000, getCar(playerCount));
			GUI.setCar(1, playerName);
			playerCount++;
			for (int i = 0; i < players.length; i++) {
				if (players[i] == null) {
					players[i] = new Player(playerName, i);
					break;
				}
			}
		}
	}

	public boolean canBuild(Player player, int group){
		int groupsOwned = 0;
		int groupsExist = 0;
		//Tæller hvor mange ledige grupper der er
		for(Field field : fields){
			if(field instanceof Property){
				Property p = (Property)field;
				if(p.getGroup() == group) groupsExist++;
			}
		}
		//Tæller om en spiller ejer en gruppe eller flere
		for(Field owned : player.getFields()){
			if(owned instanceof Property){
				Property p = (Property)owned;
				if(p.getGroup() == group) groupsOwned++;
			}
		}

		return groupsOwned == groupsExist;
	}

	public Property[] getPropertiesFromGroup(int group){
		int pCount = 0;
		for(Field f : fields){
			if(f instanceof Property){
				Property p = (Property) f;
				if(p.getGroup() == group) {
					pCount++;
				}
			}
		}
		Property[] properties = new Property[pCount];
		int i = 0;
		for(Field f : fields){
			if(f instanceof Property){
				Property p = (Property) f;
				if(p.getGroup() == group) {
					properties[i++] = p;
				}
			}
		}
		return properties;
	}





	//	//Undersøger om et felt tilhører en gruppe og smider det ind i et array
	//	public void buyPropertyGroup(Player player, int group, int Field[]){
	//		Field[] buy = new fields;
	//		int count = 0;
	//		for (int i = 0; i < fields.length; i++){
	//			if ((fields instanceof Property) && ((Property) fields[i]).getGroup() == group))   {
	//				buy[count] = fields[i];
	//				count++;
	//			}
	//		}
	//	}




	public void roll() {
		GUI.getUserButtonPressed("Kast terning", "Dice");
		int r = box.roll();
		GUI.setDice(box.getDice()[0].getValue(), box.getDice()[1].getValue());
		players[playerTurn].setLastRoll(r);
//		if(players[playerTurn].getPosition() >= 0)
//			GUI.removeCar(players[playerTurn].getPosition()+1, players[playerTurn].getName());
		players[playerTurn].movePlayer(r);
//		GUI.setCar(players[playerTurn].getPosition()+1, players[playerTurn].getName());
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
	public Field[] getProperties() {
		return fields;
		
	}
	

}


