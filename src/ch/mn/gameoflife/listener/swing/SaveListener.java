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

import javax.swing.JOptionPane;

import ch.mn.gameoflife.controller.SaveController;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.utils.Language;

public class SaveListener implements ActionListener {

    private SaveController saveController;

    private Cell[][] cells;

    public SaveListener(Cell[][] cells) throws Throwable {
        this.cells = cells;
        saveController = new SaveController(cells);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        switch (ae.getActionCommand()) {
            case "save":
                saveController.saveGame();
                String saveMessage = Language.getResourceBundle().getString("saveSuccessful");
                JOptionPane.showMessageDialog(null, saveMessage, null, JOptionPane.INFORMATION_MESSAGE);
                break;

            case "load":
                saveController.loadGame();
                String loadMessage = Language.getResourceBundle().getString("loadSuccessful");
                JOptionPane.showMessageDialog(null, loadMessage, null, JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                break;
        }
    }

}
