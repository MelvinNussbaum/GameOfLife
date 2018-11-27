package ch.mn.gameoflife.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.listener.swing.ButtonListener;
import ch.mn.gameoflife.listener.swing.GridListener;
import ch.mn.gameoflife.listener.swing.RuleButtonListener;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.thread.GameThread;
import ch.mn.gameoflife.view.abstracts.AbstractSwingMainFrame;

public class SwingMainFrame extends AbstractSwingMainFrame {

    private static final long serialVersionUID = 2978608857717274514L;

    private GameThread gameThread = new GameThread(this);

    private Cell[][] cells = gameThread.getCellController().getCells();

    private SwingCell[][] swingCells = new SwingCell[GameGridController.GRIDROWS][GameGridController.GRIDCOLS];

    private JPanel controlPanel = new JPanel();

    private JPanel gameGrid = new JPanel(new GridLayout(GameGridController.GRIDROWS, GameGridController.GRIDCOLS));

    private JButton pauseStartButton = new JButton();

    private JButton resetButton = new JButton();

    private JButton newRuleButton = new JButton();

    private JLabel gameOfLifeLabel = new JLabel();

    private JLabel generationCounterLabel = new JLabel("", SwingConstants.CENTER);

    private ButtonListener buttonListener = new ButtonListener(gameThread);

    public SwingMainFrame(String title) {
        super();
        this.setTitle(title);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setSize(0, 800);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        rBundle = switchLanguage(currentLocale);
        currentLocale = rBundle.getLocale();
        buildGUI();
    }

    @Override
    public void buildGUI() {

        gameOfLifeLabel.setText(rBundle.getString("gameOfLife"));
        newRuleButton.setText(rBundle.getString("newRules"));
        resetButton.setText(rBundle.getString("reset"));
        pauseStartButton.setText(rBundle.getString("start"));
        generationCounterLabel.setText(rBundle.getString("generation") + ": 0");

        gameGrid.setLayout(new GridLayout(GameGridController.GRIDROWS, GameGridController.GRIDCOLS));
        gameGrid.setPreferredSize(new Dimension(getHeight(), getHeight()));
        gameGrid.addMouseListener(new GridListener());

        drawGrid(cells, swingCells, gameGrid);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(300, this.getHeight()));
        controlPanel.setBackground(Color.GRAY);
        controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        pauseStartButton.setActionCommand("start");
        resetButton.setActionCommand("reset");
        newRuleButton.setActionCommand("newRules");
        pauseStartButton.addActionListener(buttonListener);
        resetButton.addActionListener(buttonListener);
        newRuleButton.addActionListener(new RuleButtonListener(this));

        gameOfLifeLabel.setAlignmentX(CENTER_ALIGNMENT);
        pauseStartButton.setAlignmentX(CENTER_ALIGNMENT);
        resetButton.setAlignmentX(CENTER_ALIGNMENT);
        newRuleButton.setAlignmentX(CENTER_ALIGNMENT);
        generationCounterLabel.setAlignmentX(CENTER_ALIGNMENT);

        gameOfLifeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        pauseStartButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        newRuleButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        generationCounterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        controlPanel.add(Box.createVerticalStrut(20));
        controlPanel.add(gameOfLifeLabel);
        controlPanel.add(Box.createVerticalGlue());
        controlPanel.add(pauseStartButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(resetButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(newRuleButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(generationCounterLabel);
        controlPanel.add(Box.createVerticalGlue());

        this.add(controlPanel);
        this.add(gameGrid);

        pack();
    }

    @Override
    public void updateGUI() {

        for (SwingCell[] swiCelCol : swingCells) {
            for (SwingCell swingCell : swiCelCol) {
                swingCell.repaintCell();
            }
        }
        generationCounterLabel.setText(rBundle.getString("generation") + ": " + gameThread.getGenerationCounter());
        pauseStartButton.setText(gameThread.isPaused() ? rBundle.getString("start") : rBundle.getString("pause"));
        pauseStartButton.setActionCommand(gameThread.isPaused() ? "start" : "pause");
        pack();
    }

    public JButton getPausePlayButton() {

        return pauseStartButton;
    }

    public JLabel getGenerationCounterLabel() {

        return generationCounterLabel;
    }

    public GameThread getGameThread() {

        return gameThread;
    }

}
