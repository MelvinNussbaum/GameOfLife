package ch.mn.gaemoflife.view.interfaces;

public interface ICell {
	
	public void repaintCell();

	public boolean isAlive();

	public void setAlive(boolean isAlive);

	public int getAliveNeighbours();

	public void setAliveNeighbours(int aliveNeighbours);
}
