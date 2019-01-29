/******************************************************************************
 *
 * [ DeleteCell.java ]
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

import ch.mn.gameoflife.model.Cell;

public class DeleteCell {

    public static void main(String[] args) {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entityManager = cellFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Cell cell = entityManager.find(Cell.class, 1);
        entityManager.remove(cell);

        entityManager.getTransaction().commit();
        entityManager.close();
        cellFactory.close();
    }

}
