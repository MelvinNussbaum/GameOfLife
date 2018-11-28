/******************************************************************************
 *
 * [ SwingSettingsDialog.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.view.swing;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

public class SwingSettingsDialog extends JDialog {

    private ResourceBundle rBundle;

    public SwingSettingsDialog(SwingMainFrame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("Settings");
        this.setResizable(true);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.rBundle = parent.getResourceBundle();

        buildGUI();
        pack();

        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }

    private void buildGUI() {

        // TODO Auto-generated method stub

    }

}
