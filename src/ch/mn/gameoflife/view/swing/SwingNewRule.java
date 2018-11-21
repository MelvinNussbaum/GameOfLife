package ch.mn.gameoflife.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

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
	
	private JPanel aliveRuleGreaterPanel = new JPanel(new BorderLayout());
	private JPanel aliveRuleLessPanel = new JPanel(new BorderLayout());
	private JPanel deadRuleGreaterPanel = new JPanel(new BorderLayout());
	private JPanel deadRuleLessPanel = new JPanel(new BorderLayout());
	
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
		this.setSize(500, 200);
		this.setResizable(true);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		buildGUI(); 
	}
	
	private void buildGUI() {
		
		aliveRuleGreaterPanel.setSize(500, 25);
		
		aliveRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		aliveRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		deadRuleGreaterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		deadRuleLessLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		
		aliveRuleGreaterField.setMaximumSize(new Dimension(50,50));
		aliveRuleLessField.setPreferredSize(new Dimension(50,50));
		deadRuleGreaterField.setPreferredSize(new Dimension(50,50));
		deadRuleLessField.setPreferredSize(new Dimension(50,50));
		
		aliveRuleGreaterPanel.add(aliveRuleGreaterLabel, BorderLayout.WEST);
		aliveRuleGreaterPanel.add(aliveRuleGreaterField, BorderLayout.EAST);
		aliveRuleLessPanel.add(aliveRuleLessLabel, BorderLayout.WEST);
		aliveRuleLessPanel.add(aliveRuleLessField, BorderLayout.EAST);
		
		deadRuleGreaterPanel.add(deadRuleGreaterLabel, BorderLayout.WEST);
		deadRuleGreaterPanel.add(deadRuleGreaterField, BorderLayout.EAST);
		deadRuleLessPanel.add(deadRuleLessLabel, BorderLayout.WEST);
		deadRuleLessPanel.add(deadRuleLessField, BorderLayout.EAST);
		
		add(aliveRuleGreaterPanel);
		add(aliveRuleLessPanel);
		add(deadRuleGreaterPanel);
		add(deadRuleLessPanel);
	}
}