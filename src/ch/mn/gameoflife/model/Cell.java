package ch.mn.gameoflife.model;

import java.io.Serializable;

import ch.mn.gaemoflife.view.interfaces.ICell;

public class Cell implements ICell, Serializable {
	private static final long serialVersionUID = 3880031941181526796L;
	
	boolean isAlive = false;
	int aliveNeighbours;
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}
	
	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;		
	}

	@Override
	public int getAliveNeighbours() {
		return aliveNeighbours;
	}

	@Override
	public void setAliveNeighbours(int aliveNeighbours) {
		this.aliveNeighbours = aliveNeighbours;		
	}

}
