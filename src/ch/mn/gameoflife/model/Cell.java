package ch.mn.gameoflife.model;

import javax.swing.JPanel;

public class Cell extends JPanel {
	private static final long serialVersionUID = 1L;

	boolean isAlive;
	int aliveNeighbours;

	public Cell() {
		super();
		this.isAlive = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAliveNeighbours() {
		return aliveNeighbours;
	}

	public void setAliveNeighbours(int aliveNeighbours) {
		this.aliveNeighbours = aliveNeighbours;
	}
}