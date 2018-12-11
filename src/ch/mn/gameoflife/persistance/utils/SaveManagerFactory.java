/******************************************************************************
 *
 * [ SaveManagerFactory.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance.utils;

import java.io.IOException;
import java.net.ConnectException;

import ch.mn.gameoflife.persistance.AbstractSaveManager;
import ch.mn.gameoflife.persistance.database.hibernate.DatabaseManager;
import ch.mn.gameoflife.persistance.localfilesystem.LocalFileSystemManager;

public class SaveManagerFactory {

    public static <T extends AbstractSaveManager> T getImplementation()
        throws InstantiationException, IllegalAccessException, IOException {

        Class<T> type = null;
        T instance;

        try {
            checkDB();
            type = (Class<T>) DatabaseManager.class;
            instance = type.newInstance();
        } catch (Exception e) {
            checkLFS();
            type = (Class<T>) LocalFileSystemManager.class;
            instance = type.newInstance();
        }

        return instance;
    }

    private static void checkDB() throws ConnectException {

        DatabaseManager databaseManager = null;
        databaseManager = new DatabaseManager();
        databaseManager.testAvailability();
    }

    // Local File System
    private static void checkLFS() throws IOException {

        LocalFileSystemManager fileSystemManager = new LocalFileSystemManager();
        fileSystemManager.testAvailability();
    }
}
