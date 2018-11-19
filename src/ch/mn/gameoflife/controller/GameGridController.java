package ch.mn.gameoflife.controller;

import java.io.Serializable;

import ch.mn.gaemoflife.view.swing.GameGrid;

public class GameGridController implements Serializable {
	private static final long serialVersionUID = -4225020371706191879L;
	
	public static final int GRIDCOLS = 50;
	public static final int GRIDROWS = 50;


	private GameGrid gameGrid = new GameGrid();

	public GameGridController() {
		super();
	}

	public GameGrid getGameGrid() {
		return gameGrid;
	}

	public void setGameGrid(GameGrid gameGrid) {
		this.gameGrid = gameGrid;
	}
}
