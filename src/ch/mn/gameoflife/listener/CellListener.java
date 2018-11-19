package ch.mn.gameoflife.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ch.mn.gameoflife.model.SwingCell;

public class CellListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent me) {
		SwingCell source = (SwingCell) me.getSource();
		if (source.isAlive()) {
			source.setBackground(Color.BLACK);
			source.setAlive(false);
		} else {
			source.setBackground(Color.WHITE);
			source.setAlive(true);
		}
		super.mousePressed(me);
	}
}
