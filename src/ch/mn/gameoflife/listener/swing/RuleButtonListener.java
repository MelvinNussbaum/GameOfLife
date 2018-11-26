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
import javax.swing.JFrame;

import ch.mn.gameoflife.utils.Rule;
import ch.mn.gameoflife.view.swing.SwingNewRule;

public class RuleButtonListener implements ActionListener {

    Container parent;

    public RuleButtonListener(Container parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        JButton source = (JButton) ae.getSource();

        switch (source.getActionCommand()) {
            case "newRules":
                try {
                    SwingNewRule swingNewRule = new SwingNewRule((JFrame) parent, true);
                    swingNewRule.setVisible(true);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
                break;
            case "apply":
                String aliveGreaterObject = ((SwingNewRule) parent).getAliveRuleGreaterField().getText();
                String aliveLessObject = ((SwingNewRule) parent).getAliveRuleLessField().getText();
                String deadGreaterObject = ((SwingNewRule) parent).getDeadRuleGreaterField().getText();
                String deadLessObject = ((SwingNewRule) parent).getDeadRuleLessField().getText();

                Integer aliveGreater = Integer.parseInt(aliveGreaterObject);
                Integer aliveLess = Integer.parseInt(aliveLessObject);
                Integer deadGreater = Integer.parseInt(deadGreaterObject);
                Integer deadLess = Integer.parseInt(deadLessObject);

                Rule.setAliveCellsNeighboursGreaterThan(aliveGreater);
                Rule.setAliveCellsNeighboursLessThan(aliveLess);
                Rule.setDeadCellsNeighboursGreaterThan(deadGreater);
                Rule.setDeadCellsNeighboursLessThan(deadLess);

                ((SwingNewRule) parent).dispose();
                break;
            default:
                break;
        }
    }

}
