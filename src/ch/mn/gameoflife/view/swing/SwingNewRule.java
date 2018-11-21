package ch.mn.gameoflife.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class SwingNewRule extends JDialog {
	private static final long serialVersionUID = -649758692822963496L;
	
	private final String mask = "#";
	private MaskFormatter maskFormatter = new MaskFormatter(mask);
	
	private JPanel aliveRuleGreaterPanel = new JPanel(new BorderLayout(10,0));
	private JPanel aliveRuleLessPanel = new JPanel(new BorderLayout(10,0));
	private JPanel deadRuleGreaterPanel = new JPanel(new BorderLayout(10,0));
	private JPanel deadRuleLessPanel = new JPanel(new BorderLayout(10,0));
	
	private JLabel aliveRuleGreaterLabel = new JLabel("<HTML><b>Lebende Zellen</b> bleiben am leben wenn Anzahl lebende Nachbarn <b>grösser als</b></HTML>: ");
	private JLabel aliveRuleLessLabel = new JLabel("<HTML><b>Lebende Zellen</b> bleiben am leben wenn Anzahl lebende Nachbarn <b>kleiner als</b></HTML>: ");
	private JLabel deadRuleGreaterLabel = new JLabel("<HTML><b>Tote Zellen</b> auferstehen wenn Anzahl lebende Nachbarn <b>grösser als</b></HTML>: ");
	private JLabel deadRuleLessLabel = new JLabel("<HTML><b>Tote Zellen</b> auferstehen wenn Anzahl lebende Nachbarn <b>kleiner als</b></HTML>: ");
	
	private JFormattedTextField aliveRuleGreaterField = new JFormattedTextField(maskFormatter);
	private JFormattedTextField aliveRuleLessField = new JFormattedTextField(maskFormatter);
	private JFormattedTextField deadRuleGreaterField = new JFormattedTextField(maskFormatter);
	private JFormattedTextField deadRuleLessField = new JFormattedTextField(maskFormatter);
	
	
	public SwingNewRule() throws ParseException {
		super();
		this.setTitle("Neue Regel");
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		buildGUI();
		pack();
	}
	
	private void buildGUI() {
	
		aliveRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		aliveRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		deadRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		deadRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		
		aliveRuleGreaterField.setPreferredSize(new Dimension(25,25));
		aliveRuleLessField.setPreferredSize(new Dimension(25,25));
		deadRuleGreaterField.setPreferredSize(new Dimension(25,25));
		deadRuleLessField.setPreferredSize(new Dimension(25,25));

		aliveRuleGreaterPanel.add(aliveRuleGreaterLabel, BorderLayout.WEST);
		aliveRuleGreaterPanel.add(aliveRuleGreaterField, BorderLayout.EAST);
		aliveRuleLessPanel.add(aliveRuleLessLabel, BorderLayout.WEST);
		aliveRuleLessPanel.add(aliveRuleLessField, BorderLayout.EAST);
		
		deadRuleGreaterPanel.add(deadRuleGreaterLabel, BorderLayout.WEST);
		deadRuleGreaterPanel.add(deadRuleGreaterField, BorderLayout.EAST);
		deadRuleLessPanel.add(deadRuleLessLabel, BorderLayout.WEST);
		deadRuleLessPanel.add(deadRuleLessField, BorderLayout.EAST);
		
		add(aliveRuleGreaterPanel);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(aliveRuleLessPanel);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(deadRuleGreaterPanel);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(deadRuleLessPanel);
	}
}