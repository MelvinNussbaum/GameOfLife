/******************************************************************************
 *
 * [ SaveListener.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.listener.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.persistence.service.DatabaseManager;

public class SaveListener implements ActionListener {

    private DatabaseManager dbManager;

    private Cell[][] cells;

    public SaveListener(Cell[][] cells) {
        this.cells = cells;
        this.dbManager = new DatabaseManager("Hibernate_JPA");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        dbManager.createCellGrid();
        dbManager.updateCells(this.cells);
    }
}
