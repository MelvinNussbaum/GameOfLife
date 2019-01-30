/******************************************************************************
 *
 * [ LoadListener.java ]
 *
 * COPYRIGHT (c) 2002 - 2019 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.listener.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.model.CellGrid;
import ch.mn.gameoflife.persistence.service.DatabaseManager;

public class LoadListener implements ActionListener {

    private DatabaseManager dbManager;

    private CellGrid cellGrid;

    public LoadListener(CellGrid cellGrid) {
        this.cellGrid = cellGrid;
        this.dbManager = new DatabaseManager("Hibernate_JPA");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Cell[][] newCells = dbManager.readCells();
        cellGrid.updateCells(newCells);
    }

}
