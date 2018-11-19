package ch.mn.gameoflife;

import ch.mn.gameoflife.view.swing.SwingMainFrame;

public class Main {

	private static void initializeGame() {
		SwingMainFrame mainFrame = new SwingMainFrame("Game of Life");
		mainFrame.setVisible(true);
		mainFrame.getGameThread().start();
	}
	
	public static void main(String[] args) {
		initializeGame();
	}

}
