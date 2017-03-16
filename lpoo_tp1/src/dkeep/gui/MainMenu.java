package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JTextArea textArea;
	private JLabel lblStatus;
	private JButton btnUp, btnDown, btnRight, btnLeft;
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
		frame.setBounds(100, 100, 601, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblGuardPersonality = new JLabel("Guard personality");

		JLabel lblOgreAmount = new JLabel("Ogre amount");

		String[] guardTypes = new String[] { "Rookie", "Drunken", "Suspicious" };
		JComboBox<String> comboBox = new JComboBox(guardTypes);

		textField = new JTextField();
		textField.setText("2");
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

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

		JButton btnNewGame = new JButton("New game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ogreAmount = Integer.parseInt(textField.getText());
				String guardType = (String) comboBox.getSelectedItem();
				g = new Game(2, ogreAmount, guardType);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				textArea.setText(g.getGameMapAsString());
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblGuardPersonality)
								.addComponent(lblOgreAmount))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(394, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(36)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewGame).addGap(30))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnRight, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addComponent(btnUp, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(34)))
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnExit).addComponent(btnDown, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(44))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblStatus).addContainerGap(529, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblOgreAmount).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblGuardPersonality)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addGroup(groupLayout
						.createSequentialGroup().addComponent(btnNewGame).addGap(46)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(7).addComponent(btnDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(btnExit)
								.addContainerGap(99, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblStatus).addContainerGap()))));
		frame.getContentPane().setLayout(groupLayout);
	}

	private void update(char dir) {
		boolean gameState[] = g.moveHero(dir);
		if (gameState[0]) {
			lblStatus.setText(g.getVictoryMessage());
			g.nextLevel();
		}
		if (gameState[1]) {
			lblStatus.setText(g.getLossMessage());
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnRight.setEnabled(false);
			btnLeft.setEnabled(false);
		}
		if (gameState[2])
			lblStatus.setText("Invalid movement");
		if (gameState[3])
			lblStatus.setText("You have beaten an ogre to sleep");
		textArea.setText(g.getGameMapAsString());
	}
}
