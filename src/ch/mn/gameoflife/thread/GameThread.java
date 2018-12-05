package ch.mn.gameoflife.thread;

import java.io.Serializable;

import ch.mn.gameoflife.controller.CellController;
import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.view.interfaces.IMainFrame;

public class GameThread extends Thread implements Runnable, Serializable {

    private static final long serialVersionUID = -3332944283162129482L;

    public static final int GENERATION_TIME_MILLIS = 1000 / 18;

    private int generationCounter = 0;

    private boolean paused = true;

    private boolean gameOver = false;

    private IMainFrame mainFrame;

    private GameGridController gridController = new GameGridController();

    private CellController cellController = new CellController();

    public GameThread(IMainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    @Override
    public synchronized void run() {

        super.run();
        while (!interrupted()) {
            while (!gameOver) {
                while (!paused) {
                    cellController.countAliveNeighbours();
                    cellController.judgeCells();
                    generationCounter++;
                    checkGameOver();
                    mainFrame.updateGUI();
                    try {
                        wait(GENERATION_TIME_MILLIS);
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                }
                mainFrame.updateGUI();
            }
            mainFrame.updateGUI();
        }
    }

    // Überprüft, ob keine Zellen mehr leben
    private void checkGameOver() {

        for (Cell[] celCol : cellController.getCells()) {
            for (Cell cell : celCol) {
                if (cell.getAlive()) {
                    return;
                }
            }
        }
        gameOver = true;
        paused = true;
    }

    public boolean isPaused() {

        return paused;
    }

    public void setPaused(boolean paused) {

        this.paused = paused;
    }

    public CellController getCellController() {

        return cellController;
    }

    public GameGridController getGridController() {

        return gridController;
    }

    public int getGenerationCounter() {

        return generationCounter;
    }

    public void setGenerationCounter(int generationCounter) {

        this.generationCounter = generationCounter;
    }

    public boolean isGameOver() {

        return gameOver;
    }

    public void setGameOver(boolean gameOver) {

        this.gameOver = gameOver;
    }

}
