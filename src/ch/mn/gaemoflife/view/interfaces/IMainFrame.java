package ch.mn.gaemoflife.view.interfaces;

import ch.mn.gameoflife.model.SwingCell;

public interface IMainFrame {
	
	
	void repaintCell(SwingCell cell);
	
	void drawGridLines(int row, int col, SwingCell cell);
}
