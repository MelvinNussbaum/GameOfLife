package ch.mn.gameoflife.view.swing;

import java.awt.Color;

import javax.swing.JPanel;

import ch.mn.gaemoflife.view.interfaces.ICellView;
import ch.mn.gameoflife.model.Cell;

public class SwingCell extends JPanel implements ICellView {
	private static final long serialVersionUID = 1L;
	
	private Cell cellModel;
	
	public SwingCell(Cell cell) {
		super();
		this.cellModel = cell;
	}
	
	@Override
	public void repaintCell() {
		setBackground(cellModel.isAlive() ? Color.WHITE : Color.BLACK);
	}

	public Cell getCellModel() {
		return cellModel;
	}

	public void setCellModel(Cell cellModel) {
		this.cellModel = cellModel;
	}
}