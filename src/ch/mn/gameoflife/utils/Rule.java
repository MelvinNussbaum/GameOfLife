package ch.mn.gameoflife.utils;

import ch.mn.gameoflife.model.Cell;

public class Rule {

    // Implementierung der tatsächlichen Spielregeln
    static int deadCellsNeighboursGreaterThan = 2;

    static int deadCellsNeighboursLessThan = 4;

    static int aliveCellsNeighboursGreaterThan = 1;

    static int aliveCellsNeighboursLessThan = 4;

    public static boolean rule(Cell cell) {

        int aliveNeighbours = cell.getAliveNeighbours();
        if (!(cell.isAlive())) {
            if (aliveNeighbours > deadCellsNeighboursGreaterThan && aliveNeighbours < deadCellsNeighboursLessThan) {
                return true;
            }
        } else {
            if (aliveNeighbours > aliveCellsNeighboursGreaterThan && aliveNeighbours < aliveCellsNeighboursLessThan) {
                return true;
            }
        }
        return false;
    }

    public static void applyRules(Integer aliveGreater, Integer aliveLess, Integer deadGreater, Integer deadLess) {

        setAliveCellsNeighboursGreaterThan(aliveGreater);
        setAliveCellsNeighboursLessThan(aliveLess);
        setDeadCellsNeighboursGreaterThan(deadGreater);
        setDeadCellsNeighboursLessThan(deadLess);
    }

    public static int getDeadCellsNeighboursGreaterThan() {

        return deadCellsNeighboursGreaterThan;
    }

    public static void setDeadCellsNeighboursGreaterThan(int deadCellsNeighboursGreaterThan) {

        Rule.deadCellsNeighboursGreaterThan = deadCellsNeighboursGreaterThan;
    }

    public static int getDeadCellsNeighboursLessThan() {

        return deadCellsNeighboursLessThan;
    }

    public static void setDeadCellsNeighboursLessThan(int deadCellsNeighboursLessThan) {

        Rule.deadCellsNeighboursLessThan = deadCellsNeighboursLessThan;
    }

    public static int getAliveCellsNeighboursGreaterThan() {

        return aliveCellsNeighboursGreaterThan;
    }

    public static void setAliveCellsNeighboursGreaterThan(int aliveCellsNeighboursGreaterThan) {

        Rule.aliveCellsNeighboursGreaterThan = aliveCellsNeighboursGreaterThan;
    }

    public static int getAliveCellsNeighboursLessThan() {

        return aliveCellsNeighboursLessThan;
    }

    public static void setAliveCellsNeighboursLessThan(int aliveCellsNeighboursLessThan) {

        Rule.aliveCellsNeighboursLessThan = aliveCellsNeighboursLessThan;
    }

}
