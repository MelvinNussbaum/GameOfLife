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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class SwingSettingsDialog extends JDialog {

    private ResourceBundle rBundle;

    private JButton newRuleButton = new JButton();

    public SwingSettingsDialog(SwingMainFrame parent, boolean modal) {
        super(parent, modal);
        this.rBundle = parent.getResourceBundle();
        this.setTitle(rBundle.getString("settings"));
        this.setResizable(true);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        buildGUI();
        pack();

        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }

    private void buildGUI() {

        newRuleButton.setText(rBundle.getString("newRules"));
        newRuleButton.setActionCommand("newRules");
        newRuleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    new SwingNewRuleDialog(SwingSettingsDialog.this, true);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
            }
        });

        newRuleButton.setAlignmentX(CENTER_ALIGNMENT);
        newRuleButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        add(newRuleButton);

    }

    public ResourceBundle getResourceBundle() {

        return rBundle;
    }

}
