package ch.mn.gameoflife.utils;

import ch.mn.gameoflife.model.Cell;

public class Rule {

	// Implementierung der tatsächlichen Spielregeln
	static int deadCellsNeighboursGreaterThan = 2;
	static int deadCellsNeighboursLessThan = 4;
	
	static int aliveCellsNeighboursGreaterThan = 1;
	static int aliveCellsNeighboursLessThan = 4;
	
	
	public static boolean rule(Cell cell) {
		int aliveNeighbours = cell.getAliveNeighbours();
		if (!(cell.isAlive())) {
			if (aliveNeighbours > deadCellsNeighboursGreaterThan && aliveNeighbours < deadCellsNeighboursLessThan) {
				return true;
			}
		} else {
			if (aliveNeighbours > aliveCellsNeighboursGreaterThan && aliveNeighbours < aliveCellsNeighboursLessThan) {
				return true;
			}
		}
		return false;
	}
}
