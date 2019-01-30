package ch.mn.gameoflife.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Cell implements Serializable {

    private static final long serialVersionUID = 3880031941181526796L;

    @Id
    private long id;

    private boolean alive = false;

    @Transient
    private int aliveNeighbours;

    public Cell() {
        super();
    }

    public Cell(long id) {
        super();
        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public boolean getAlive() {

        return alive;
    }

    public void setAlive(boolean alive) {

        this.alive = alive;
    }

    public int getAliveNeighbours() {

        return aliveNeighbours;
    }

    public void setAliveNeighbours(int aliveNeighbours) {

        this.aliveNeighbours = aliveNeighbours;
    }

    public static long getSerialversionuid() {

        return serialVersionUID;
    }

}
