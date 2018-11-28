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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ch.mn.gameoflife.utils.Rule;
import ch.mn.gameoflife.view.swing.SwingMainFrame;
import ch.mn.gameoflife.view.swing.SwingNewRuleDialog;

public class RuleButtonListener implements ActionListener {

    SwingNewRuleDialog parent;

    SwingMainFrame grandParent;

    public RuleButtonListener(SwingNewRuleDialog parent) {
        super();
        this.parent = parent;
        this.grandParent = (SwingMainFrame) parent.getParent();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        switch (ae.getActionCommand()) {

            case "applyRules":
                try {
                    String aliveGreaterObject = parent.getAliveRuleGreaterField().getText();
                    String aliveLessObject = parent.getAliveRuleLessField().getText();
                    String deadGreaterObject = parent.getDeadRuleGreaterField().getText();
                    String deadLessObject = parent.getDeadRuleLessField().getText();

                    Integer aliveGreater = Integer.parseInt(aliveGreaterObject);
                    Integer aliveLess = Integer.parseInt(aliveLessObject);
                    Integer deadGreater = Integer.parseInt(deadGreaterObject);
                    Integer deadLess = Integer.parseInt(deadLessObject);

                    Rule.setAliveCellsNeighboursGreaterThan(aliveGreater);
                    Rule.setAliveCellsNeighboursLessThan(aliveLess);
                    Rule.setDeadCellsNeighboursGreaterThan(deadGreater);
                    Rule.setDeadCellsNeighboursLessThan(deadLess);

                    parent.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    String errorMessage = grandParent.getResourceBundle().getString("ruleErrorMessage");
                    JOptionPane.showMessageDialog(parent, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "defaultRules":
                parent.getAliveRuleGreaterField().setText("1");
                parent.getAliveRuleLessField().setText("4");
                parent.getDeadRuleGreaterField().setText("2");
                parent.getDeadRuleLessField().setText("4");
                break;

            default:
                break;
        }
    }

}
