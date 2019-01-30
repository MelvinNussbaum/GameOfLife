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

import ch.mn.gameoflife.controller.CellController;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.persistence.service.DatabaseManager;

public class LoadListener implements ActionListener {

    private DatabaseManager dbManager;

    private Cell[][] cells;

    public LoadListener(CellController cellController) {
        this.cells = cells;
        this.dbManager = new DatabaseManager("Hibernate_JPA");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        dbManager.readCells();

    }

}
