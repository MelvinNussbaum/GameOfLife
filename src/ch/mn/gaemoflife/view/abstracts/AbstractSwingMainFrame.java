package ch.mn.gaemoflife.view.abstracts;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import ch.mn.gaemoflife.view.interfaces.IMainFrame;
import ch.mn.gaemoflife.view.swing.SwingCell;
import ch.mn.gameoflife.controller.GameGridController;

public abstract class AbstractSwingMainFrame extends JFrame implements IMainFrame {
	private static final long serialVersionUID = 1280637062592072627L;


	public void drawGridLines(int row, int col, SwingCell cell) {
		if (col > GameGridController.GRIDCOLS - 2 && row > GameGridController.GRIDROWS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		} else if (row > GameGridController.GRIDROWS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		} else if (col > GameGridController.GRIDCOLS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		} else {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
		}
	}

}
