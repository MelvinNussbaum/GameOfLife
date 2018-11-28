package ch.mn.gameoflife.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import ch.mn.gameoflife.listener.swing.RuleButtonListener;
import ch.mn.gameoflife.utils.Rule;

public class SwingNewRuleDialog extends JDialog {

    private static final long serialVersionUID = -649758692822963496L;

    private final String mask = "#";

    private ResourceBundle rBundle;

    private MaskFormatter maskFormatter = new MaskFormatter(mask);

    private JPanel aliveRuleGreaterPanel = new JPanel(new BorderLayout(10, 0));

    private JPanel aliveRuleLessPanel = new JPanel(new BorderLayout(10, 0));

    private JPanel deadRuleGreaterPanel = new JPanel(new BorderLayout(10, 0));

    private JPanel deadRuleLessPanel = new JPanel(new BorderLayout(10, 0));

    private JPanel buttonPanel = new JPanel(new BorderLayout(10, 0));

    private JLabel aliveRuleGreaterLabel = new JLabel();

    private JLabel aliveRuleLessLabel = new JLabel();

    private JLabel deadRuleGreaterLabel = new JLabel();

    private JLabel deadRuleLessLabel = new JLabel();

    private JFormattedTextField aliveRuleGreaterField = new JFormattedTextField(maskFormatter);

    private JFormattedTextField aliveRuleLessField = new JFormattedTextField(maskFormatter);

    private JFormattedTextField deadRuleGreaterField = new JFormattedTextField(maskFormatter);

    private JFormattedTextField deadRuleLessField = new JFormattedTextField(maskFormatter);

    private RuleButtonListener ruleButtonListener = new RuleButtonListener(this);

    private JButton sendRulesButton = new JButton();

    private JButton defaultRulesButton = new JButton();

    public SwingNewRuleDialog(SwingMainFrame parent, boolean modal) throws ParseException {
        super(parent, modal);
        this.setTitle("Neue Regel");
        this.setResizable(true);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.rBundle = parent.getResourceBundle();

        buildGUI();
        pack();

        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }

    private void buildGUI() {

        sendRulesButton.setText(rBundle.getString("applyRules"));
        sendRulesButton.setActionCommand("applyRules");
        sendRulesButton.addActionListener(ruleButtonListener);

        defaultRulesButton.setText(rBundle.getString("defaultRules"));
        defaultRulesButton.setActionCommand("defaultRules");
        defaultRulesButton.addActionListener(ruleButtonListener);

        aliveRuleGreaterLabel.setText(rBundle.getString("aliveRuleGreater"));
        aliveRuleLessLabel.setText(rBundle.getString("aliveRuleLess"));
        deadRuleGreaterLabel.setText(rBundle.getString("deadRuleGreater"));
        deadRuleLessLabel.setText(rBundle.getString("deadRuleLess"));

        aliveRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        aliveRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        deadRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        deadRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        sendRulesButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        defaultRulesButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        aliveRuleGreaterField.setHorizontalAlignment(SwingConstants.CENTER);
        aliveRuleLessField.setHorizontalAlignment(SwingConstants.CENTER);
        deadRuleGreaterField.setHorizontalAlignment(SwingConstants.CENTER);
        deadRuleLessField.setHorizontalAlignment(SwingConstants.CENTER);

        aliveRuleGreaterField.setPreferredSize(new Dimension(25, 25));
        aliveRuleLessField.setPreferredSize(new Dimension(25, 25));
        deadRuleGreaterField.setPreferredSize(new Dimension(25, 25));
        deadRuleLessField.setPreferredSize(new Dimension(25, 25));

        aliveRuleGreaterField.setText("" + Rule.getAliveCellsNeighboursGreaterThan());
        aliveRuleLessField.setText("" + Rule.getAliveCellsNeighboursLessThan());
        deadRuleGreaterField.setText("" + Rule.getDeadCellsNeighboursGreaterThan());
        deadRuleLessField.setText("" + Rule.getDeadCellsNeighboursLessThan());

        aliveRuleGreaterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        aliveRuleLessPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        deadRuleGreaterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        deadRuleLessPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        aliveRuleGreaterPanel.add(aliveRuleGreaterLabel, BorderLayout.WEST);
        aliveRuleGreaterPanel.add(aliveRuleGreaterField, BorderLayout.EAST);
        aliveRuleLessPanel.add(aliveRuleLessLabel, BorderLayout.WEST);
        aliveRuleLessPanel.add(aliveRuleLessField, BorderLayout.EAST);
        buttonPanel.add(sendRulesButton, BorderLayout.CENTER);
        buttonPanel.add(defaultRulesButton, BorderLayout.EAST);

        deadRuleGreaterPanel.add(deadRuleGreaterLabel, BorderLayout.WEST);
        deadRuleGreaterPanel.add(deadRuleGreaterField, BorderLayout.EAST);
        deadRuleLessPanel.add(deadRuleLessLabel, BorderLayout.WEST);
        deadRuleLessPanel.add(deadRuleLessField, BorderLayout.EAST);

        add(aliveRuleGreaterPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(aliveRuleLessPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(deadRuleGreaterPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(deadRuleLessPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    public JFormattedTextField getAliveRuleGreaterField() {

        return aliveRuleGreaterField;
    }

    public void setAliveRuleGreaterField(JFormattedTextField aliveRuleGreaterField) {

        this.aliveRuleGreaterField = aliveRuleGreaterField;
    }

    public JFormattedTextField getAliveRuleLessField() {

        return aliveRuleLessField;
    }

    public void setAliveRuleLessField(JFormattedTextField aliveRuleLessField) {

        this.aliveRuleLessField = aliveRuleLessField;
    }

    public JFormattedTextField getDeadRuleGreaterField() {

        return deadRuleGreaterField;
    }

    public void setDeadRuleGreaterField(JFormattedTextField deadRuleGreaterField) {

        this.deadRuleGreaterField = deadRuleGreaterField;
    }

    public JFormattedTextField getDeadRuleLessField() {

        return deadRuleLessField;
    }

    public void setDeadRuleLessField(JFormattedTextField deadRuleLessField) {

        this.deadRuleLessField = deadRuleLessField;
    }

}