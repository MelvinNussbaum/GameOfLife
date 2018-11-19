package ch.mn.gameoflife.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ch.mn.gaemoflife.view.swing.SwingCell;
import ch.mn.gameoflife.model.Cell;

public class CellListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent me) {
		Cell source = ((SwingCell) me.getSource()).getCellModel();
		source.setAlive(!source.isAlive());
		super.mousePressed(me);
	}
}
