/******************************************************************************
 *
 * [ ISafeManager.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance;

public interface ISaveManager {

    void saveGame() throws Exception;

    void loadGame() throws Exception;

    void testAvailability() throws Exception;

}
