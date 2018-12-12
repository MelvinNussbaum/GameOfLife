/******************************************************************************
 *
 * [ AbstractSafeManager.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance;

import ch.mn.gameoflife.model.Cell;

public abstract class AbstractSaveManager implements ISaveManager {

    protected Cell[][] cells;

    public AbstractSaveManager() {

    }

    public Cell[][] getCells() {

        return cells;
    }

    public void setCells(Cell[][] cells) {

        this.cells = cells;
    }

}
