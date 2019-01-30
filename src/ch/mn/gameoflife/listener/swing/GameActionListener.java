package ch.mn.gameoflife.listener.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import ch.mn.gameoflife.thread.GameThread;

public class GameActionListener implements ActionListener, Serializable {

    private static final long serialVersionUID = 1802162015334332822L;

    private GameThread thread;

    public GameActionListener(GameThread thread) {
        super();
        this.thread = thread;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "reset":
                thread.resetGame();
                break;
            case "start":
                thread.startGame();
                break;
            case "pause":
                thread.pauseGame();
                break;
            default:
                break;
        }
    }

}
