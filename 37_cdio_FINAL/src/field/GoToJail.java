package field;

public class GoToJail extends Field {


	private String name;

	public GoToJail(String name){
		this.name = name;
	}
	
	public void landOnField(Player player){
		
		System.out.println("Du skal i f�nsel");
		
		player.getPosition();
		
		System.out.println(player.getName() + " lander p� " + player.getPosition() + "og rykkes i f�ngsel.");
		
		player.setPosition(10);
		
	}
}
