package field;

public class GoToJail extends Field {


	private String name;

	public GoToJail(String name){
		this.name = name;
	}
	
	public void landOnField(Player player){
		
		System.out.println("Du skal i fænsel");
		
		player.getPosition();
		
		System.out.println(player.getName() + " lander på " + player.getPosition() + "og rykkes i fængsel.");
		
		player.setPosition(10);
		
	}
}
