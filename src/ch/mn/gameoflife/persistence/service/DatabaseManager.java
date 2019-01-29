/******************************************************************************
 *
 * [ DatabaseManager.java ]
 *
 * COPYRIGHT (c) 2002 - 2019 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistence.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;

public class DatabaseManager {

    public DatabaseManager() {
        super();
    }

    public void createCellGrid() {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entitymanager = cellFactory.createEntityManager();
        entitymanager.getTransaction().begin();

        for (int i = 0; i < GameGridController.GRIDCOLS * GameGridController.GRIDROWS; i++) {
            Cell cell = new Cell();
            cell.setAlive(false);
            entitymanager.persist(cell);
        }

        entitymanager.getTransaction().commit();
        entitymanager.close();
        cellFactory.close();
    }

    public Cell[][] readCells() {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entityManager = cellFactory.createEntityManager();

        Cell[][] cellA = new Cell[GameGridController.GRIDCOLS][GameGridController.GRIDROWS];

        for (int i = 0; i < cellA.length; i++) {
            Cell[] cellCol = cellA[i];
            for (int j = 0; j < cellCol.length; j++) {
                Cell cell = cellCol[j];
                cellA[i][j] = entityManager.find(Cell.class, cell.getId());
            }
        }

        entityManager.close();
        cellFactory.close();

        return cellA;
    }

    private void updateCell(Cell cell) {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entitymanager = cellFactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Cell updatedCell = entitymanager.find(Cell.class, cell.getId());
        updatedCell.setAlive(!cell.getAlive());

        entitymanager.getTransaction().commit();
        entitymanager.close();
        cellFactory.close();
    }

    public void deleteCellGrid(int cellID) {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entityManager = cellFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Cell cell = entityManager.find(Cell.class, cellID);
        entityManager.remove(cell);

        entityManager.getTransaction().commit();
        entityManager.close();
        cellFactory.close();
    }

}
