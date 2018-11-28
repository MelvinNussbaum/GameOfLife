package ch.mn.gameoflife.listener.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import ch.mn.gameoflife.thread.GameThread;

public class GameActionListener implements ActionListener, Serializable {

    private static final long serialVersionUID = 1802162015334332822L;

    private GameThread gameThread;

    public GameActionListener(GameThread gameThread) {
        super();
        this.gameThread = gameThread;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();

        switch (source.getActionCommand()) {
            case "reset":
                gameThread.getCellController().killAllCells();
                gameThread.setGenerationCounter(0);
                gameThread.setPaused(true);
                gameThread.setGameOver(false);
                break;
            case "start":
                if (gameThread.isGameOver()) {
                    gameThread.setGameOver(false);
                    gameThread.setGenerationCounter(0);
                }
                gameThread.setPaused(false);
                break;
            case "pause":
                gameThread.setPaused(true);
                break;
            default:
                break;
        }
    }

}
