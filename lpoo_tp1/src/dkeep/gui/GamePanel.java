package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GamePanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = -1271074511834697397L;
	private static TexturePaint paint;
	private static Game g;
	private static BufferedImage herou_i = null;
	private static BufferedImage herod_i = null;
	private static BufferedImage herol_i = null;
	private static BufferedImage heror_i = null;
	private static BufferedImage guard_i = null;
	private static BufferedImage ogre_i = null;
	private static BufferedImage club_i = null;
	private static BufferedImage floor_i = null;
	private static BufferedImage wall_i = null;
	private static BufferedImage lever_i = null;
	private static BufferedImage dooro_i = null;
	private static BufferedImage doorc_i = null;
	private static Rectangle2D floor_dim = new Rectangle2D.Double(0, 0, 50, 40);

	private static char[][] gameMap = { {' '} };
	private static char heroChar = 'H';
	private static char heroDir = 'd';

	public GamePanel(Game g) {
		try {
			herou_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\heroup.png"));
			herod_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\herodown.png"));
			herol_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\heroleft.png"));
			heror_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\heroright.png"));
			guard_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\guard.png"));
			ogre_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\ogre.jpg"));
			club_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\club.png"));
			floor_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\floor.jpg"));
			wall_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\wall.png"));
			lever_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\lever.png"));
			dooro_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\door_o.png"));
			doorc_i = ImageIO.read(new File("D:\\Temp\\LPOO1617_T1G3\\rsc\\door_c.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.g = g;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paint = new TexturePaint(floor_i, floor_dim);
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		int i = 0, j = 0;
		char currentChar;
		for (i = 0; i < gameMap.length; i++) {
			for (j = 0; j < gameMap[i].length; j++) {
				currentChar = gameMap[j][i];
				if (currentChar == '\n')
					j++;
				if (currentChar == heroChar || currentChar == 'K') {
					switch(heroDir) {
					case 'w':
						g2d.drawImage(herou_i, 30*i, 30*(j+1), null);
						break;
					case 'd':
						g2d.drawImage(heror_i, 30*i, 30*(j+1), null);
						break;
					case 'a':
						g2d.drawImage(herol_i, 30*i, 30*(j+1), null);
						break;
					case 's':
						g2d.drawImage(herod_i, 30*i, 30*(j+1), null);
						break;
					}
				}
				if (currentChar == 'G') {
					g2d.drawImage(guard_i, 30*i, 30*(j+1), null);
				}
				if (currentChar == 'O' || currentChar == '$')
					g2d.drawImage(ogre_i, 30*i, 30*(j+1), null);
				if (currentChar == '*')
					g2d.drawImage(club_i, 30*i, 30*(j+1), null);
				if (currentChar == 'k')
					g2d.drawImage(lever_i, 30*i, 30*(j+1), null);
				if (currentChar == 'S')
					g2d.drawImage(dooro_i, 30*i, 30*(j+1), null);
				if (currentChar == 'I')
					g2d.drawImage(doorc_i, 30*i, 30*(j+1), null);
				if (currentChar == 'X')
					g2d.drawImage(wall_i, 30*i, 30*(j+1), null);
			}
		}

	}

	public void setGameMap(char[][] map) {
		gameMap = map;
		repaint();
	}

	public void setHeroChar(char c) {
		heroChar = c;
	}
	
	public void setHeroDir(char c) {
		heroDir = c;
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		g.moveHero(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
