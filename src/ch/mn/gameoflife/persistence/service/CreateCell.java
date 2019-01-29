/******************************************************************************
 *
 * [ CreateCell.java ]
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

public class CreateCell {

    public static void main(String[] args) {

        EntityManagerFactory cellFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        EntityManager entitymanager = cellFactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Cell cell = new Cell();
        cell.setId(1);
        cell.setAlive(false);

        entitymanager.persist(cell);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        cellFactory.close();
    }
}