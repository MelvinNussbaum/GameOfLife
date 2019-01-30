package ch.mn.gameoflife;

import ch.mn.gameoflife.controller.CellController;
import ch.mn.gameoflife.model.CellGrid;
import ch.mn.gameoflife.model.GameModel;
import ch.mn.gameoflife.thread.GameThread;
import ch.mn.gameoflife.view.swing.SwingMainFrame;

public class Main {

    private static void initializeGame() {

        SwingMainFrame mainFrame = new SwingMainFrame("Game of Life");
        GameThread gameThread = new GameThread(mainFrame);
        GameModel frameModel = new GameModel();
        CellGrid cellGrid = new CellGrid();
        CellController cellController = new CellController();

        cellController.setCells(cellGrid.getCells());

        gameThread.setModel(frameModel);
        gameThread.setCellController(cellController);

        mainFrame.setModel(frameModel);
        mainFrame.setCellGrid(cellGrid);
        mainFrame.setGameThread(gameThread);
        mainFrame.buildGUI();

        gameThread.start();

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {

        initializeGame();
    }

}
