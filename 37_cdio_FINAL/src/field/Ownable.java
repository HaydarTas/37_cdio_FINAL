package field;


import field.Field;

import javax.swing.text.Position;

import controller.GameController;
import desktop_resources.GUI;
import entity.Player;

public abstract class Ownable extends Field {

	protected int price;
	protected Player owner = null;
	protected GameController gc;

	public Ownable(String name, int price, GameController gc) {
		super(name);
		this.price = price;
		this.gc = gc;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public abstract int getRent();

	// checks whether the territory is owned by another player or is for sale
	// if field is owned by another player, checks the rent.
	public void landOnField(Player p) {
		for(Field f : p.getFields()){
			System.out.println(f);
		}
		if (owner !=null && owner != p){ 
			//TODO tell user that he landed on an owned field 
			GUI.showMessage("Du er landet på" + getOwner() + "'s felt - Betal venligst!");
			p.addToBalance(-getRent());
			getOwner().addToBalance(getRent());
		} else if (owner !=null) {
			//Tell user that it is his own field
			GUI.showMessage("Du er landet på dit eget felt");
			boolean canBuy = false;
			for(Field owned : p.getFields()){
				if(owned instanceof Property){
					Property prop = (Property)owned;
					System.out.println("aaaaaaaaaa"+prop);
					if(gc.canBuild(p, prop.getGroup())){
						canBuy = true;
						System.out.println("canbuy");
						break;
					}
				}
			}
			if(canBuy){
				boolean jaTak = GUI.getUserLeftButtonPressed("Vil du købe et hus?", "Ja køb et hus tak", "Nej tak");
				if(jaTak){
					String[] properties = new String[25];
					for(int i =0; i<8;i++)
						
					{ 
						if (gc.canBuild(p,i )){
							for (int j=0; j<gc.getPropertiesFromGroup(i).length;j++)
								properties[i*3+j] = gc.getPropertiesFromGroup(i)[j].getName();
						}
						
					}
					String selection = GUI.getUserSelection("Køb på", properties);
					Field[] fields = gc.getProperties();
					for (Field field : fields) {
						if (field.getName().equals(selection)){
							//Buy house
							((Property)field).addHouse();
							p.addToBalance(-((Property)field).getHousePrice());
							GUI.showMessage("Du har nu betalt "+ p.addToBalance(-((Property)field).getHousePrice()));
							
						}
					}
					
				} else {
					
				}
				
			}
		    
			//				p.setInformation(1);
		} else {
			boolean input = GUI.getUserLeftButtonPressed("Du er landet på et felt der ikke er ejet, vil du købe feltet?", "Ja tak", "Nej tak");
			if (input){
				
				p.buyField(this);
				
			} else {
				//Player didnt buy field
			}
			//				p.setInformation(0);
		}

	}
}


