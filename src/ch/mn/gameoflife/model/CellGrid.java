/******************************************************************************
 *
 * [ CellGrid.java ]
 *
 * COPYRIGHT (c) 2002 - 2019 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.model;

import ch.mn.gameoflife.controller.GameGridController;

public class CellGrid {

    private Cell[][] cells = new Cell[GameGridController.GRIDCOLS][GameGridController.GRIDROWS];

    public CellGrid() {
        super();
        int idCounter = 1;
        for (int row = 0; row < this.cells.length; row++) {
            for (int col = 0; col < this.cells.length; col++) {
                this.cells[row][col] = new Cell(idCounter);
                idCounter++;
            }
        }
    }

    public void updateCells(Cell[][] newCells) {

        for (int i = 0; i < newCells.length; i++) {
            Cell[] cellCol = newCells[i];
            for (int j = 0; j < cellCol.length; j++) {
                cells[i][j].setAlive(newCells[i][j].getAlive());
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
