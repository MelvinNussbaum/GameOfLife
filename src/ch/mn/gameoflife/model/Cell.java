package ch.mn.gameoflife.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cell implements Serializable {

    private static final long serialVersionUID = 3880031941181526796L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean alive = false;

    private int aliveNeighbours;

    public Cell() {
        super();
    }

    public Cell(int id) {
        super();
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

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
