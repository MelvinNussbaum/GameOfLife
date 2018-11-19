package ch.mn.gameoflife.listener.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.view.swing.SwingCell;

public class CellListener extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = -6120715169654453325L;

	@Override
	public void mousePressed(MouseEvent me) {
		Cell source = ((SwingCell) me.getSource()).getCellModel();
		source.setAlive(!source.isAlive());
		super.mousePressed(me);
	}
}
