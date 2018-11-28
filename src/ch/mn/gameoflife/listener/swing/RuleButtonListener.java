/******************************************************************************
 *
 * [ ruleButtonListener.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.listener.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ch.mn.gameoflife.utils.Rule;
import ch.mn.gameoflife.view.swing.SwingMainFrame;
import ch.mn.gameoflife.view.swing.SwingNewRuleDialog;

public class RuleButtonListener implements ActionListener {

    Container parent;

    SwingNewRuleDialog parentDialog;

    SwingMainFrame parentFrame;

    public RuleButtonListener(Container parent) {
        super();
        this.parent = parent;

        if (parent instanceof SwingMainFrame) {
            parentFrame = SwingMainFrame.class.cast(parent);
        } else if (parent instanceof SwingNewRuleDialog) {
            parentDialog = SwingNewRuleDialog.class.cast(parent);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        JButton source = (JButton) ae.getSource();

        switch (source.getActionCommand()) {
            case "newRules":
                try {
                    SwingNewRuleDialog swingNewRule = new SwingNewRuleDialog(parentFrame, true);
                    swingNewRule.setVisible(true);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
                break;

            case "applyRules":
                try {
                    String aliveGreaterObject = parentDialog.getAliveRuleGreaterField().getText();
                    String aliveLessObject = parentDialog.getAliveRuleLessField().getText();
                    String deadGreaterObject = parentDialog.getDeadRuleGreaterField().getText();
                    String deadLessObject = parentDialog.getDeadRuleLessField().getText();

                    Integer aliveGreater = Integer.parseInt(aliveGreaterObject);
                    Integer aliveLess = Integer.parseInt(aliveLessObject);
                    Integer deadGreater = Integer.parseInt(deadGreaterObject);
                    Integer deadLess = Integer.parseInt(deadLessObject);

                    Rule.setAliveCellsNeighboursGreaterThan(aliveGreater);
                    Rule.setAliveCellsNeighboursLessThan(aliveLess);
                    Rule.setDeadCellsNeighboursGreaterThan(deadGreater);
                    Rule.setDeadCellsNeighboursLessThan(deadLess);

                    parentDialog.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    String errorMessage = ((SwingMainFrame) parentDialog.getParent()).getResourceBundle()
                        .getString("ruleErrorMessage");
                    JOptionPane.showMessageDialog(parentDialog, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "defaultRules":
                parentDialog.getAliveRuleGreaterField().setText("1");
                parentDialog.getAliveRuleLessField().setText("4");
                parentDialog.getDeadRuleGreaterField().setText("2");
                parentDialog.getDeadRuleLessField().setText("4");
                break;

            default:
                break;
        }
    }

}
