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
import java.io.IOException;
import java.net.ConnectException;

import javax.swing.JOptionPane;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.persistance.AbstractSaveManager;
import ch.mn.gameoflife.persistance.database.hibernate.DatabaseManager;
import ch.mn.gameoflife.persistance.utils.SaveManagerFactory;
import ch.mn.gameoflife.utils.Language;

public class SaveListener implements ActionListener {

    private AbstractSaveManager safeManager;

    private Cell[][] cells;

    public SaveListener(Cell[][] cells) throws InstantiationException, IllegalAccessException, IOException {
        this.safeManager = SaveManagerFactory.getImplementation();
        this.cells = cells;

        this.safeManager.setCells(this.cells);

        if (!safeManager.getClass().equals(DatabaseManager.class)) {
            throw new ConnectException();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        switch (ae.getActionCommand()) {
            case "save":
                try {
                    safeManager.saveGame();
                    String saveMessage = Language.getResourceBundle().getString("saveSuccessful");
                    JOptionPane.showMessageDialog(null, saveMessage, null, JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    String saveMessage = Language.getResourceBundle().getString("saveUnsuccessful");
                    JOptionPane.showMessageDialog(null, saveMessage, null, JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                break;

            case "load":
                try {
                    safeManager.loadGame();
                    String loadMessage = Language.getResourceBundle().getString("loadSuccessful");
                    JOptionPane.showMessageDialog(null, loadMessage, null, JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    String loadMessage = Language.getResourceBundle().getString("loadUnsuccessful");
                    JOptionPane.showMessageDialog(null, loadMessage, null, JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

}
