package mnpl.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
	BufferedImage image;

	public Panel(){
		try{
		image = ImageIO.read(new File("C:/Users/Miguel Pires/Documents/GitHub/LPOO1617_T1G3/Monopoly/resources/houses.jpg"));
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
}
