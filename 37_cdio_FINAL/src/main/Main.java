package main;

import controller.GameController;
import entity.Player;

public class Main {

	public static void main(String[] args) {
		GameController game = new GameController();
		game.setupGame();
		
		game.setupGUI();
		game.initPlayers();
		game.run();

	}

}
