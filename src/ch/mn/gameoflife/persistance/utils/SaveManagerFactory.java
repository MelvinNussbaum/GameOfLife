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

import ch.mn.gameoflife.persistance.AbstractSaveManager;
import ch.mn.gameoflife.persistance.database.hibernate.DatabaseManager;
import ch.mn.gameoflife.persistance.localfilesystem.LocalFileSystemManager;

public class SaveManagerFactory {

    public static <T extends AbstractSaveManager> T getImplementation()
        throws InstantiationException, IllegalAccessException {

        Class<T> type = null;
        T instance;

        try {
            type = (Class<T>) DatabaseManager.class;
            instance = type.newInstance();
        } catch (Exception e) {
            type = (Class<T>) LocalFileSystemManager.class;
            instance = type.newInstance();
        }

        return instance;
    }

    private static boolean checkDB() {

        DatabaseManager databaseManager = null;
        try {
            databaseManager = new DatabaseManager();
        } catch (Throwable e) {
            return false;
        }
        return databaseManager.testAvailability();
    }

    // Local File System
    private static boolean checkLFS() {

        LocalFileSystemManager fileSystemManager = new LocalFileSystemManager();
        return fileSystemManager.testAvailability();
    }
}
