package ch.mn.gameoflife;

import ch.mn.gameoflife.view.MainFrame;

public class Main {

	private static void initializeGame() {
		new MainFrame("Game of Life");
	}
	
	public static void main(String[] args) {
		initializeGame();
	}

}
