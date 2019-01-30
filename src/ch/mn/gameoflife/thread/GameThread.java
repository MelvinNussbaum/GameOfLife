package ch.mn.gameoflife.thread;

import java.io.Serializable;

import ch.mn.gameoflife.controller.CellController;
import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.model.GameModel;
import ch.mn.gameoflife.view.interfaces.IMainFrame;

public class GameThread extends Thread implements Runnable, Serializable {

    private static final long serialVersionUID = -3332944283162129482L;

    private GameModel model;

    private IMainFrame mainFrame;

    private GameGridController gridController = new GameGridController();

    private CellController cellController;

    public GameThread(IMainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    @Override
    public synchronized void run() {

        super.run();
        while (!interrupted()) {
            while (!model.isGameOver()) {
                while (!model.isPaused()) {
                    cellController.countAliveNeighbours();
                    cellController.judgeCells();
                    model.setGenerationCounter(model.getGenerationCounter() + 1);
                    checkGameOver();
                    mainFrame.updateGUI();
                    try {
                        wait(GameModel.GENERATION_TIME_MILLIS);
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
        model.setGameOver(true);
        model.setPaused(true);
    }

    public void resetGame() {

        getCellController().killAllCells();
        model.setGenerationCounter(0);
        model.setPaused(true);
        model.setGameOver(false);
    }

    public void startGame() {

        if (model.isGameOver()) {
            model.setGameOver(false);
            model.setGenerationCounter(0);
        }
        model.setPaused(false);
    }

    public void pauseGame() {

        model.setPaused(true);
    }

    public GameModel getModel() {

        return model;
    }

    public void setModel(GameModel model) {

        this.model = model;
    }

    public void setCellController(CellController cellController) {

        this.cellController = cellController;
    }

    public CellController getCellController() {

        return cellController;
    }

    public GameGridController getGridController() {

        return gridController;
    }
}
