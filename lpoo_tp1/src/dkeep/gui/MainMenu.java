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

public class MainMenu {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblStatus;
	private JButton btnUp, btnDown, btnRight, btnLeft;
	private GamePanel gamePanel;
	private Game g;

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

		JLabel lblGuardPersonality = new JLabel("Guard personality");

		JLabel lblOgreAmount = new JLabel("Ogre amount");

		String[] guardTypes = new String[] { "Rookie", "Drunken", "Suspicious" };
		JComboBox<String> comboBox = new JComboBox(guardTypes);

		textField = new JTextField();
		textField.setText("2");
		textField.setColumns(10);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('d');
			}
		});
		btnRight.setEnabled(false);
		btnRight.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRight.setPreferredSize(new Dimension(60, 23));

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('a');
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLeft.setPreferredSize(new Dimension(60, 23));

		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('w');
			}
		});
		btnUp.setEnabled(false);
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnUp.setPreferredSize(new Dimension(60, 23));

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update('s');
			}
		});
		btnDown.setEnabled(false);
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDown.setPreferredSize(new Dimension(60, 23));

		lblStatus = new JLabel("You can start a new game");

		gamePanel = new GamePanel();

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

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGuardPersonality)
								.addComponent(lblOgreAmount))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(433, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewGame)
									.addGap(30))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(34)))
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnExit)
										.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(44))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStatus)
							.addContainerGap(489, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOgreAmount)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardPersonality)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnNewGame)
							.addGap(46)
							.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExit))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addComponent(lblStatus)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void update(char dir) {
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
			//if (ogreDir != 'n') {
				gamePanel.setOgreDir(ogreDir, i);
				gamePanel.setOgrePos(g.getOgrePos(i), i);
			//}
			char clubDir = gamePanel.getOgrePos(i).directionMoved(g.getOgreClub(i));
			//if (ogreDir != 'n') {
				gamePanel.setClubDir(clubDir, i);
				gamePanel.setClubPos(g.getOgreClub(i), i);
			//}
		}
		gamePanel.setGameMap(g.getGameMapVoid());
		gamePanel.setHeroDir(dir);
		// textArea.setText(g.getGameMapAsString());
	}
}
