/******************************************************************************
 *
 * [ JsonWR.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance.localfilesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.persistance.AbstractSaveManager;

public class LocalFileSystemManager extends AbstractSaveManager {

    @Override
    public void saveGame() throws IOException {

        FileOutputStream outputStream = new FileOutputStream("save.txt");

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                String boolToIntString = "" + (cell.getAlive() ? 1 : 0);
                byte[] bytes = boolToIntString.getBytes();
                outputStream.write(bytes);
            }
        }
        outputStream.close();
    }

    @Override
    public void loadGame() throws IOException {

        File file = new File("savegames/save.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        for (Cell[] celCol : cells) {
            for (Cell cell : celCol) {
                int asciiValue = bufferedReader.read();
                String aliveString = Character.toString((char) asciiValue);
                cell.setAlive(aliveString == "1" ? true : false);
            }
        }
    }

    @Override
    public boolean testAvailability() {

        boolean available;
        FileOutputStream outputStream = null;
        BufferedReader bufferedReader = null;

        try {
            available = true;
            outputStream = new FileOutputStream("save.txt");
            outputStream.write(null);

            File file = new File("savegames/save.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.read();

        } catch (Exception e) {
            available = false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return available;
    }
}
