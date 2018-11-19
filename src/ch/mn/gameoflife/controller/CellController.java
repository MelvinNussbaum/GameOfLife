package ch.mn.gameoflife.controller;

import java.io.Serializable;

import ch.mn.gameoflife.model.SwingCell;

public class CellController implements Serializable {
	private static final long serialVersionUID = 2047207213066121831L;

	SwingCell[][] cells = new SwingCell[GameGridController.GRIDROWS][GameGridController.GRIDCOLS];

	public CellController() {
		super();
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells.length; col++) {
				cells[row][col] = new SwingCell();
			}
		}
	}


	public void countAliveNeighbours() {
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells.length; col++) {
				int aliveNeighbours = 0;

				// Variabeln um den Zustand der Nachbarn zu analysieren 
				int rowP = row + 1;
				int rowM = row - 1;
				int colP = col + 1;
				int colM = col - 1;

				// Falls rowM (also row-1) -1 wäre (somit ausserhalb vom Grid) werden diese Nachbarn nicht gezählt und als tot gewertet
				if (rowM != -1) {
					if (cells[rowM][col].isAlive()) aliveNeighbours++;
					if (colM != -1 && cells[rowM][colM].isAlive()) aliveNeighbours++;
					if (colP != GameGridController.GRIDCOLS && cells[rowM][colP].isAlive()) aliveNeighbours++;
				}
				if (rowP != GameGridController.GRIDROWS) {
					if (cells[rowP][col].isAlive()) aliveNeighbours++;
					if (colM != -1 && cells[rowP][colM].isAlive()) aliveNeighbours++;
					if (colP != GameGridController.GRIDCOLS && cells[rowP][colP].isAlive()) aliveNeighbours++;
				}
				if (colP != GameGridController.GRIDCOLS && cells[row][colP].isAlive()) aliveNeighbours++;
				if (colM != -1 && cells[row][colM].isAlive()) aliveNeighbours++;

				cells[row][col].setAliveNeighbours(aliveNeighbours);
			}
		}
	}

	// Implementierung der tatsächlichen Spielregeln
	public void judgeCells() {
		for (SwingCell[] celCol : cells) {
			for (SwingCell cell : celCol) {
				if (!(cell.isAlive())) {
					if (cell.getAliveNeighbours() == 3) reviveCell(cell);
				} else {
					if (cell.getAliveNeighbours() < 2 || cell.getAliveNeighbours() > 3) killCell(cell);
				}
			}
		}
	}

	private static void killCell(SwingCell cell) {
		cell.setAlive(false);
		cell.repaint();
	}

	public void killAllCells() {
		for (SwingCell[] cellCol : cells) {
			for (SwingCell cell : cellCol) {
				killCell(cell);
			}
		}
	}

	private static void reviveCell(SwingCell cell) {
		cell.setAlive(true);
		cell.repaint();
	}

	public SwingCell[][] getCells() {
		return cells;
	}

	public void setCells(SwingCell[][] cells) {
		this.cells = cells;
	}
}
