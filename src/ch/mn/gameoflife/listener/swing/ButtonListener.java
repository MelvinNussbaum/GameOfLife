package ch.mn.gameoflife.listener.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import ch.mn.gameoflife.thread.GameThread;

public class ButtonListener implements ActionListener, Serializable	{
	private static final long serialVersionUID = 1802162015334332822L;
	
	private GameThread gameThread;

	public ButtonListener(GameThread gameThread) {
		super();
		this.gameThread = gameThread;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = e.getSource();

		switch (source.getText()) {
		case "Reset":
			gameThread.getCellController().killAllCells();
			gameThread.setGenerationCounter(0);
			if (gameThread.isPaused()) break;
			//$FALL-THROUGH$
		case "Start":
			if (gameThread.isGameOver()) {
				gameThread.setGameOver(false);
				gameThread.setGenerationCounter(0);
			}
			//$FALL-THROUGH$
		case "Pause":
			gameThread.setPaused(!gameThread.isPaused());
			break;
		default:
			break;
		}
	}

}
