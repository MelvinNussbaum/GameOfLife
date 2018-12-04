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

import ch.mn.gameoflife.controller.SaveController;
import ch.mn.gameoflife.model.Cell;

public class SaveListener implements ActionListener {

    private SaveController saveController;

    private Cell[][] cells;

    public SaveListener(Cell[][] cells) {
        this.cells = cells;
        saveController = new SaveController(cells);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "save":
                saveController.saveGame();
                break;

            case "load":
                saveController.loadGame();
                break;

            default:
                break;
        }
    }

}
