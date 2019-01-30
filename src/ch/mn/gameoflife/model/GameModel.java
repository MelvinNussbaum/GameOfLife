/******************************************************************************
 *
 * [ MainFrameModel.java ]
 *
 * COPYRIGHT (c) 2002 - 2019 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.model;

public class GameModel {

    public static final int GENERATION_TIME_MILLIS = 1000 / 18;

    private int generationCounter = 0;

    private boolean paused = true;

    private boolean gameOver = false;

    public GameModel() {
        super();
    }

    public int getGenerationCounter() {

        return generationCounter;
    }

    public void setGenerationCounter(int generationCounter) {

        this.generationCounter = generationCounter;
    }

    public boolean isPaused() {

        return paused;
    }

    public void setPaused(boolean paused) {

        this.paused = paused;
    }

    public boolean isGameOver() {

        return gameOver;
    }

    public void setGameOver(boolean gameOver) {

        this.gameOver = gameOver;
    }

}
