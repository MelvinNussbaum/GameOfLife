/******************************************************************************
 *
 * [ JsonWR.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.model.Cell;

public class FileWR {

    public FileWR() {
        // TODO Auto-generated constructor stub
    }

    public void saveToFile(Cell[][] cells) throws IOException {

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

    public int[][] loadFromFile() throws FileNotFoundException {

        int[][] intA = new int[GameGridController.GRIDCOLS][GameGridController.GRIDROWS];
        File file = new File("save.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        for (int col = 0; col < GameGridController.GRIDCOLS; col++) {
            for (int row = 0; row < GameGridController.GRIDROWS; row++) {
                try {
                    int asciiValue = bufferedReader.read();
                    intA[col][row] = Integer.parseInt(Character.toString((char) asciiValue));
                } catch (IOException e) {
                    intA[col][row] = 0;
                    e.printStackTrace();
                }
            }
        }
        return intA;
    }
}
