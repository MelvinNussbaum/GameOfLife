package ch.mn.gameoflife.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.listener.swing.GameActionListener;
import ch.mn.gameoflife.listener.swing.GridListener;
import ch.mn.gameoflife.listener.swing.SaveListener;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.thread.GameThread;
import ch.mn.gameoflife.view.abstracts.AbstractSwingMainFrame;

public class SwingMainFrame extends AbstractSwingMainFrame {

    private static final long serialVersionUID = 2978608857717274514L;

    private boolean databaseConnected = true;

    private GameThread gameThread = new GameThread(this);

    private Cell[][] cells = gameThread.getCellController().getCells();

    private SwingCell[][] swingCells = new SwingCell[GameGridController.GRIDROWS][GameGridController.GRIDCOLS];

    private JPanel controlPanel = new JPanel();

    private JPanel gameGrid = new JPanel(new GridLayout(GameGridController.GRIDROWS, GameGridController.GRIDCOLS));

    private JButton pauseStartButton = new JButton();

    private JButton resetButton = new JButton();

    private JButton settingsButton = new JButton();

    private JButton saveButton = new JButton();

    private JButton loadButton = new JButton();

    private JLabel gameOfLifeLabel = new JLabel();

    private JLabel generationCounterLabel = new JLabel("", SwingConstants.CENTER);

    private GameActionListener gameActionListener = new GameActionListener(gameThread);

    private GridListener gridListener = new GridListener();

    private SaveListener saveListener;

    public SwingMainFrame(String title) {
        super();
        this.setTitle(title);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setSize(0, 800);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildGUI();
    }

    @Override
    public void buildGUI() {

        try {
            saveListener = new SaveListener(cells);
        } catch (Throwable e) {
            databaseConnected = false;
            String errorMessage = rBundle.getString("dataBaseConnectionException");
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        gameOfLifeLabel.setText(rBundle.getString("gameOfLife"));
        settingsButton.setText(rBundle.getString("settings"));
        resetButton.setText(rBundle.getString("reset"));
        pauseStartButton.setText(rBundle.getString("start"));
        saveButton.setText(rBundle.getString("saveGame"));
        loadButton.setText(rBundle.getString("loadGame"));
        generationCounterLabel.setText(rBundle.getString("generation") + ": 0");

        gameGrid.setLayout(new GridLayout(GameGridController.GRIDROWS, GameGridController.GRIDCOLS));
        gameGrid.setPreferredSize(new Dimension(getHeight(), getHeight()));
        gameGrid.addMouseListener(gridListener);
        gameGrid.addMouseMotionListener(gridListener);

        drawGrid(cells, swingCells, gameGrid);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(300, this.getHeight()));
        controlPanel.setBackground(Color.GRAY);
        controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        pauseStartButton.setActionCommand("start");
        resetButton.setActionCommand("reset");
        settingsButton.setActionCommand("newSettings");
        saveButton.setActionCommand("save");
        loadButton.setActionCommand("load");
        pauseStartButton.addActionListener(gameActionListener);
        resetButton.addActionListener(gameActionListener);
        saveButton.addActionListener(saveListener);
        loadButton.addActionListener(saveListener);
        settingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                new SwingSettingsDialog(SwingMainFrame.this, true);
            }
        });

        gameOfLifeLabel.setAlignmentX(CENTER_ALIGNMENT);
        pauseStartButton.setAlignmentX(CENTER_ALIGNMENT);
        resetButton.setAlignmentX(CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        generationCounterLabel.setAlignmentX(CENTER_ALIGNMENT);

        gameOfLifeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        pauseStartButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        settingsButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        saveButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loadButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        generationCounterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        controlPanel.add(Box.createVerticalStrut(20));
        controlPanel.add(gameOfLifeLabel);
        controlPanel.add(Box.createVerticalGlue());
        controlPanel.add(pauseStartButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(resetButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(settingsButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (databaseConnected) {
            controlPanel.add(saveButton);
            controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            controlPanel.add(loadButton);
            controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
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

    public ResourceBundle getResourceBundle() {

        return rBundle;
    }
}
