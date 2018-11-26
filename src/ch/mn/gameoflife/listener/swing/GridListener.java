/******************************************************************************
 *
 * [ GridListener.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.listener.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.view.swing.SwingCell;

public class GridListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {

        JPanel grid = (JPanel) e.getSource();
        JPanel swingCell = (JPanel) grid.getComponentAt(e.getPoint());
        if (!(swingCell.getClass() == SwingCell.class)) {
            return;
        }
        Cell cell = ((SwingCell) swingCell).getCellModel();
        cell.setAlive(!cell.isAlive());
        super.mousePressed(e);
    }

}
