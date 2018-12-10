/******************************************************************************
 *
 * [ ManageCell.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance.database.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.persistance.AbstractSafeManager;

public class DatabaseManager extends AbstractSafeManager {

    private static SessionFactory factory;

    int cellAmount = GameGridController.GRIDCOLS * GameGridController.GRIDROWS;

    public DatabaseManager() throws Throwable {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        fillDatabase();
    }

    private void fillDatabase() {

        if (countCells() < cellAmount) {
            for (int i = countCells(); i < cellAmount; i++) {
                try {
                    addCell(i);
                } catch (Exception e) {
                    System.out.println("Cell with id=" + i + " already exists in the database, so can't be added.");
                }
            }
        } else if (countCells() > cellAmount) {
            int tempCountCells = countCells();
            for (int i = cellAmount; i < tempCountCells; i++) {
                try {
                    deleteCell(i);
                } catch (Exception e) {
                    System.out.println("Cell with id=" + i + " doesn't exist in the database, so can't be deleted.");
                }
            }
        }
    }

    @Override
    public void saveGame() throws Exception {

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                updateCell(cell.getId(), cell.getAlive());
            }
        }
    }

    @Override
    public void loadGame() throws Exception {

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                cell.setAlive(readCell(cell.getId()));
            }
        }

    }

    /* Method to CREATE a Cell in the database */
    public void addCell(int id) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Cell cell = new Cell(id);
            session.save(cell);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean readCell(int id) {

        Session session = factory.openSession();

        Cell cell = session.get(Cell.class, id);
        boolean alive = cell.getAlive();

        session.close();
        return alive;
    }

    /* Method to UPDATE a Cell */
    public void updateCell(int id, boolean alive) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Cell cell = session.get(Cell.class, id);
            cell.setAlive(alive);
            session.update(cell);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an Cell from the records */
    public void deleteCell(int id) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Cell cell = session.get(Cell.class, id);
            session.delete(cell);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int countCells() {

        Session session = factory.openSession();

        int rowCount = ((Number) session.createQuery("select count(*) from Cell").uniqueResult()).intValue();

        return rowCount;
    }

}
