package mnpl.gui;

import java.awt.Font;

import javax.swing.*;

public class Label {

	public static void main(String[] args) {
		JFrame frame = new JFrame("JLabel demo");
		JLabel label = new JLabel();
		label.setText("Hello World!");
		label.setFont(new Font("Consolas", Font.PLAIN, 24));
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
