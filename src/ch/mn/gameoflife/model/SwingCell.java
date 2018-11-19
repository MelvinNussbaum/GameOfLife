package ch.mn.gameoflife.model;

import java.awt.Color;

import javax.swing.JPanel;

import ch.mn.gaemoflife.view.interfaces.ICell;

public class SwingCell extends JPanel implements ICell{
	private static final long serialVersionUID = 1L;

	boolean isAlive;
	int aliveNeighbours;

	public SwingCell() {
		super();
		this.isAlive = false;
	}
	
	@Override
	public void repaint() {
		if (isAlive) {
			setBackground(Color.WHITE);
		} else {
			setBackground(Color.BLACK);
		}
	}

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