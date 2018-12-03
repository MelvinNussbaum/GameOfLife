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

import ch.mn.gameoflife.view.swing.SwingNewRuleDialog;

public class RuleButtonListener implements ActionListener {

    SwingNewRuleDialog parent;

    public RuleButtonListener(SwingNewRuleDialog parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        switch (ae.getActionCommand()) {

            case "applyRules":
                parent.validateAndSendRuleInput();
                break;

            case "defaultRules":
                parent.setDefaultRules();
                break;

            default:
                break;
        }
    }

}
