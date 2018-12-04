package ch.mn.gameoflife.model;

import java.io.Serializable;

public class Cell implements Serializable {

    private static final long serialVersionUID = 3880031941181526796L;

    private int id;

    private boolean isAlive = false;

    private int aliveNeighbours;

    public Cell() {
        super();
    }

    public Cell(int id) {
        super();
        this.id = id;
    }

    public boolean isAlive() {

        return isAlive;
    }

    public void setAlive(boolean isAlive) {

        this.isAlive = isAlive;
    }

    public int getAliveNeighbours() {

        return aliveNeighbours;
    }

    public void setAliveNeighbours(int aliveNeighbours) {

        this.aliveNeighbours = aliveNeighbours;
    }

}
