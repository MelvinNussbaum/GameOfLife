package ch.mn.gameoflife.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.mn.gameoflife.controller.GameGridController;
import ch.mn.gameoflife.listener.ButtonListener;
import ch.mn.gameoflife.listener.CellListener;
import ch.mn.gameoflife.model.Cell;
import ch.mn.gameoflife.model.GameGrid;
import ch.mn.gameoflife.thread.GameThread;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 2978608857717274514L;

	private GameThread gameThread = new GameThread(this);

	private Cell[][] cells = gameThread.getCellController().getCells();
	private GameGrid gameGrid = gameThread.getGridController().getGameGrid();

	private JPanel controlPanel = new JPanel();

	private JButton pauseStartButton = new JButton("Start");
	private JButton resetButton = new JButton("Reset");

	private JLabel gameOfLifeLabel = new JLabel("Game of Life");
	private JLabel generationCounterLabel = new JLabel("Generation: 0", SwingConstants.CENTER);

	private ButtonListener buttonListener = new ButtonListener(gameThread);

	public MainFrame(String title) {
		super();
		this.setTitle(title);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setSize(0, 800);
		this.setBackground(Color.GRAY);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.buildGui();
		setVisible(true);
		runGame();
	}

	public void runGame() {
		gameThread.start();
	}

	private void buildGui() {
		gameGrid.setLayout(new GridLayout(GameGridController.GRIDROWS, GameGridController.GRIDCOLS));
		gameGrid.setPreferredSize(new Dimension(getHeight(), getHeight()));

		drawGrid();

		pauseStartButton.addActionListener(buttonListener);
		resetButton.addActionListener(buttonListener);

		gameOfLifeLabel.setAlignmentX(CENTER_ALIGNMENT);
		pauseStartButton.setAlignmentX(CENTER_ALIGNMENT);
		resetButton.setAlignmentX(CENTER_ALIGNMENT);
		generationCounterLabel.setAlignmentX(CENTER_ALIGNMENT);

		gameOfLifeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		pauseStartButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		generationCounterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setPreferredSize(new Dimension(300, this.getHeight()));
		controlPanel.setBackground(Color.GRAY);

		controlPanel.add(Box.createVerticalStrut(20));
		controlPanel.add(gameOfLifeLabel);
		controlPanel.add(Box.createVerticalGlue());
		controlPanel.add(pauseStartButton);
		controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		controlPanel.add(resetButton);
		controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		controlPanel.add(generationCounterLabel);
		controlPanel.add(Box.createVerticalGlue());

		this.add(controlPanel);
		this.add(gameGrid);

		pack();
	}

	public void updateUI() {
		generationCounterLabel.setText("Generation: " + gameThread.getGenerationCounter());
		pauseStartButton.setText(gameThread.isPaused() ? "Start" : "Pause");
		pack();
	}

	private void drawGrid() {
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells.length; col++) {
				Cell cell = cells[row][col];
				cell.setBackground(Color.BLACK);
				cell.addMouseListener(new CellListener());

				// drawGridLindes(row, col, cell);

				gameGrid.add(cell);
			}
		}
	}

	@SuppressWarnings("unused")
	private static void drawGridLindes(int row, int col, Cell cell) {
		if (col > GameGridController.GRIDCOLS - 2 && row > GameGridController.GRIDROWS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		} else if (row > GameGridController.GRIDROWS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		} else if (col > GameGridController.GRIDCOLS - 2) {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		} else {
			cell.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
		}
	}

	public static void repaintCell(Cell cell, Color color) {
		cell.setBackground(color);
	}

	public JButton getPausePlayButton() {
		return pauseStartButton;
	}

	public void setPausePlayButton(JButton pausePlayButton) {
		this.pauseStartButton = pausePlayButton;
	}

	public JLabel getGenerationCounterLabel() {
		return generationCounterLabel;
	}

	public void setGenerationCounterLabel(JLabel generationCounterLabel) {
		this.generationCounterLabel = generationCounterLabel;
	}
}
