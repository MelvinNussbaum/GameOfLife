package ch.mn.gameoflife.controller;

import java.io.Serializable;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.utils.Rule;

public class CellController implements Serializable {

    private static final long serialVersionUID = 2047207213066121831L;

    Cell[][] cells = new Cell[GameGridController.GRIDROWS][GameGridController.GRIDCOLS];

    public CellController() {
        super();
        int idCounter = 0;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells.length; col++) {
                cells[row][col] = new Cell(idCounter);
                idCounter++;
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
                    if (cells[rowM][col].getAlive())
                        aliveNeighbours++;
                    if (colM != -1 && cells[rowM][colM].getAlive())
                        aliveNeighbours++;
                    if (colP != GameGridController.GRIDCOLS && cells[rowM][colP].getAlive())
                        aliveNeighbours++;
                }
                if (rowP != GameGridController.GRIDROWS) {
                    if (cells[rowP][col].getAlive())
                        aliveNeighbours++;
                    if (colM != -1 && cells[rowP][colM].getAlive())
                        aliveNeighbours++;
                    if (colP != GameGridController.GRIDCOLS && cells[rowP][colP].getAlive())
                        aliveNeighbours++;
                }
                if (colP != GameGridController.GRIDCOLS && cells[row][colP].getAlive())
                    aliveNeighbours++;
                if (colM != -1 && cells[row][colM].getAlive())
                    aliveNeighbours++;

                cells[row][col].setAliveNeighbours(aliveNeighbours);
            }
        }
    }

    public void judgeCells() {

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                cell.setAlive(Rule.rule(cell));
            }
        }
    }

    public void killAllCells() {

        for (Cell[] cellCol : cells) {
            for (Cell cell : cellCol) {
                cell.setAlive(false);
            }
        }
    }

    public Cell[][] getCells() {

        return cells;
    }

    public void setCells(Cell[][] cells) {

        this.cells = cells;
    }
}
