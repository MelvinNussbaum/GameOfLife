package ch.mn.gameoflife.view;

import java.awt.Color;

import javax.swing.BorderFactory;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;

public interface IMainFrame {
	
	
	static void repaintCell(Cell cell) {
		if (cell.isAlive()) {
			cell.setBackground(Color.WHITE);
		} else {
			cell.setBackground(Color.BLACK);
		}
	}
	
	static void drawGridLines(int row, int col, Cell cell) {
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
