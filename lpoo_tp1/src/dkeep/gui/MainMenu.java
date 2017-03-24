package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.logic.Coords;
import dkeep.logic.DungeonMap;
import dkeep.logic.Game;

import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainMenu {

	private JFrame frame;
	private JTextField textField;
	private static JLabel lblStatus;
	private static JButton btnUp;
	private static JButton btnDown;
	private static JButton btnRight;
	private static JButton btnLeft;
	private static GamePanel gamePanel;
	private static Game g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] guardTypes = new String[] { "Rookie", "Drunken", "Suspicious" };
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 85, 200, 60, 60, 60, 60, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 28, 28, 28, 28, 23, 23, 0, 0, 135, 17, 14, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblOgreAmount = new JLabel("Ogre amount");
		GridBagConstraints gbc_lblOgreAmount = new GridBagConstraints();
		gbc_lblOgreAmount.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblOgreAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblOgreAmount.gridx = 0;
		gbc_lblOgreAmount.gridy = 0;
		frame.getContentPane().add(lblOgreAmount, gbc_lblOgreAmount);

		textField = new JTextField();
		textField.setText("2");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(textField, gbc_textField);

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 1;
		frame.getContentPane().add(lblGuardPersonality, gbc_lblGuardPersonality);
		JComboBox<String> comboBox = new JComboBox(guardTypes);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridheight = 3;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		frame.getContentPane().add(comboBox, gbc_comboBox);

		gamePanel = new GamePanel();
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.gridheight = 7;
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		gbc_gamePanel.insets = new Insets(0, 0, 5, 5);
		gbc_gamePanel.gridwidth = 2;
		gbc_gamePanel.gridx = 0;
		gbc_gamePanel.gridy = 3;
		frame.getContentPane().add(gamePanel, gbc_gamePanel);

		JButton btnNewGame = new JButton("New game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ogreAmount = Integer.parseInt(textField.getText());
				String guardType = (String) comboBox.getSelectedItem();
				g = new Game(2, ogreAmount, guardType);
				gamePanel.initialize(1, ogreAmount);
				gamePanel.addKeyListener(g);
				gamePanel.setFocusable(true);
				gamePanel.requestFocusInWindow();
				gamePanel.setHeroDir('d');
				gamePanel.setHeroPos(g.getHeroPos());
				gamePanel.setGuardDir('a', 0);
				gamePanel.setGuardPos(g.getGuardPos(0), 0);
				gamePanel.drawKey(false);
				for (int i = 0; i < g.getOgreAmount(); i++) {
					gamePanel.setOgreDir('u', i);
					gamePanel.setOgrePos(g.getOgrePos(i), i);
					gamePanel.setClubDir('u', i);
					gamePanel.setClubPos(g.getOgreClub(i), i);
				}
				// gamePanel.repaint();
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				// textArea.setText(g.getGameMapAsString());
				// gamePanel.setGameMap(g.getGameMapVoid());
				update('i');
				lblStatus.setText("Escape the " + guardType + " guard");
			}
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.gridwidth = 3;
		gbc_btnNewGame.anchor = GridBagConstraints.NORTH;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 3;
		gbc_btnNewGame.gridy = 3;
		frame.getContentPane().add(btnNewGame, gbc_btnNewGame);

		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('w');
			}
		});
		btnUp.setEnabled(false);
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnUp.setPreferredSize(new Dimension(60, 23));
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUp.anchor = GridBagConstraints.SOUTH;
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 4;
		gbc_btnUp.gridy = 5;
		frame.getContentPane().add(btnUp, gbc_btnUp);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('s');
			}
		});

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('a');
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLeft.setPreferredSize(new Dimension(60, 23));
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.anchor = GridBagConstraints.WEST;
		gbc_btnLeft.fill = GridBagConstraints.VERTICAL;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 3;
		gbc_btnLeft.gridy = 6;
		frame.getContentPane().add(btnLeft, gbc_btnLeft);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('d');
			}
		});
		btnRight.setEnabled(false);
		btnRight.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRight.setPreferredSize(new Dimension(60, 23));
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.gridx = 5;
		gbc_btnRight.gridy = 6;
		frame.getContentPane().add(btnRight, gbc_btnRight);
		btnDown.setEnabled(false);
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDown.setPreferredSize(new Dimension(60, 23));
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDown.anchor = GridBagConstraints.NORTH;
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 4;
		gbc_btnDown.gridy = 7;
		frame.getContentPane().add(btnDown, gbc_btnDown);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.anchor = GridBagConstraints.NORTH;
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 9;
		frame.getContentPane().add(btnExit, gbc_btnExit);

		lblStatus = new JLabel("You can start a new game");
		lblStatus.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 11;
		frame.getContentPane().add(lblStatus, gbc_lblStatus);
	}

	public static void update(char dir) {
		boolean gameState[] = g.moveHero(dir);
		if (g.heroHasKey())
			gamePanel.setKeyCoords(g.getHeroPos());
		if (gameState[2])
			lblStatus.setText("Invalid movement");
		else
			lblStatus.setText("You're doing great!");
		if (gameState[0]) {
			lblStatus.setText(g.getVictoryMessage());
			g.nextLevel();
			if (g.getCurrentLevel() == 1) {
				gamePanel.drawKey(true);
				gamePanel.setKeyCoords(g.getKeyCoords());
			}
		}
		if (gameState[1]) {
			lblStatus.setText(g.getLossMessage());
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnRight.setEnabled(false);
			btnLeft.setEnabled(false);
		}
		if (gameState[3])
			lblStatus.setText("You have beaten an ogre to sleep");
		gamePanel.setHeroPos(g.getHeroPos());
		gamePanel.setGuardDir(gamePanel.getGuardPos(0).directionMoved(g.getGuardPos(0)), 0);
		gamePanel.setGuardPos(g.getGuardPos(0), 0);
		for (int i = 0; i < g.getOgreAmount(); i++) {
			char ogreDir = gamePanel.getOgrePos(i).directionMoved(g.getOgrePos(i));
			// if (ogreDir != 'n') {
			gamePanel.setOgreDir(ogreDir, i);
			gamePanel.setOgrePos(g.getOgrePos(i), i);
			// }
			char clubDir = gamePanel.getOgrePos(i).directionMoved(g.getOgreClub(i));
			// if (ogreDir != 'n') {
			gamePanel.setClubDir(clubDir, i);
			gamePanel.setClubPos(g.getOgreClub(i), i);
			// }
		}
		gamePanel.setGameMap(g.getGameMapVoid());
		gamePanel.setHeroDir(dir);
		// textArea.setText(g.getGameMapAsString());
	}
}
