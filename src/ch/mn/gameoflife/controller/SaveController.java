/******************************************************************************
 *
 * [ SaveController.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.controller;

import ch.mn.gameoflife.hibernate.ManageCell;
import ch.mn.gameoflife.model.Cell;

public class SaveController {

    private ManageCell mCell = new ManageCell();

    private Cell[][] cells;

    public SaveController(Cell[][] cells) {
        this.cells = cells;
    }

    public void saveGame() {

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                mCell.updateCell(cell.getId(), cell.getAlive());
            }
        }
    }

    public void loadGame() {

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                cell.setAlive(mCell.readCell(cell.getId()));
            }
        }
    }
}
