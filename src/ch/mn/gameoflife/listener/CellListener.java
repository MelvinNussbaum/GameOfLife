package ch.mn.gameoflife.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ch.mn.gaemoflife.view.swing.SwingCell;

public class CellListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent me) {
		SwingCell source = (SwingCell) me.getSource();
		source.setAlive(!source.isAlive());
		source.repaintCell();
		super.mousePressed(me);
	}
}
