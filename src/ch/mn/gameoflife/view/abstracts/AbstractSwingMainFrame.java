package ch.mn.gameoflife.view.abstracts;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.listener.swing.CellListener;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.view.interfaces.IMainFrame;
import ch.mn.gameoflife.view.swing.SwingCell;

public abstract class AbstractSwingMainFrame extends JFrame implements IMainFrame {
	private static final long serialVersionUID = 1280637062592072627L;


	public void drawGridLines(int row, int col, SwingCell swingCell) {
		if (col > GameGridController.GRIDCOLS - 2 && row > GameGridController.GRIDROWS - 2) {
			swingCell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		} else if (row > GameGridController.GRIDROWS - 2) {
			swingCell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		} else if (col > GameGridController.GRIDCOLS - 2) {
			swingCell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		} else {
			swingCell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
		}
	}
	
	public void drawGrid(Cell[][] cells, SwingCell[][] swingCells, JPanel gameGrid, CellListener cellListener) {
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells.length; col++) {
				swingCells[row][col] = new SwingCell(cells[row][col]);
				SwingCell swingCell = swingCells[row][col];
				swingCell.setBackground(Color.BLACK);
				swingCell.addMouseListener(cellListener);
			//  drawGridLines(row, col, swingCell); // Optionales Design
				gameGrid.add(swingCell);
			}
		}
	}
}
