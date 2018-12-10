/******************************************************************************
 *
 * [ DatabaseConnection.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance.database.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {

    public static SessionFactory getDatabaseConnection() throws Throwable {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory;
    }
}
