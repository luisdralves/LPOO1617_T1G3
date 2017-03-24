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

import dkeep.logic.Coords;

public class GamePanel extends JPanel {
	private final long serialVersionUID = -1271074511834697397L;
	private TexturePaint paint;
	// private Game g;
	private static BufferedImage herou_i = null;
	private static BufferedImage herod_i = null;
	private static BufferedImage herol_i = null;
	private static BufferedImage heror_i = null;
	private static BufferedImage guardu_i = null;
	private static BufferedImage guardd_i = null;
	private static BufferedImage guardl_i = null;
	private static BufferedImage guardr_i = null;
	private static BufferedImage ogreu_i = null;
	private static BufferedImage ogred_i = null;
	private static BufferedImage ogrel_i = null;
	private static BufferedImage ogrer_i = null;
	private static BufferedImage club_i = null;
	private static BufferedImage floor_i = null;
	private static BufferedImage wall_i = null;
	private static BufferedImage lever_i = null;
	private static BufferedImage dooro_i = null;
	private static BufferedImage doorc_i = null;
	private static Rectangle2D floor_dim = new Rectangle2D.Double(0, 0, 50, 40);

	private char[][] gameMap = { { ' ' } };
	private char heroChar = 'H';
	private Coords heroCoords;
	private char heroDir = 'i';
	private Coords[] guardCoords;
	private char[] guardDir;
	private Coords[] ogreCoords;
	private char[] ogreDir;

	public GamePanel() {
		try {
			herou_i = ImageIO.read(new File("rsc\\heroup.png"));
			herod_i = ImageIO.read(new File("rsc\\herodown.png"));
			herol_i = ImageIO.read(new File("rsc\\heroleft.png"));
			heror_i = ImageIO.read(new File("rsc\\heroright.png"));
			guardu_i = ImageIO.read(new File("rsc\\guardup.png"));
			guardd_i = ImageIO.read(new File("rsc\\guarddown.png"));
			guardl_i = ImageIO.read(new File("rsc\\guardleft.png"));
			guardr_i = ImageIO.read(new File("rsc\\guardright.png"));
			ogreu_i = ImageIO.read(new File("rsc\\ogreup.png"));
			ogred_i = ImageIO.read(new File("rsc\\ogredown.png"));
			ogrel_i = ImageIO.read(new File("rsc\\ogreleft.png"));
			ogrer_i = ImageIO.read(new File("rsc\\ogreright.png"));
			club_i = ImageIO.read(new File("rsc\\club.png"));
			floor_i = ImageIO.read(new File("rsc\\floor.jpg"));
			wall_i = ImageIO.read(new File("rsc\\wall.png"));
			lever_i = ImageIO.read(new File("rsc\\lever.png"));
			dooro_i = ImageIO.read(new File("rsc\\door_o.png"));
			doorc_i = ImageIO.read(new File("rsc\\door_c.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize(int guardAmount, int ogreAmount) {
		guardCoords = new Coords[guardAmount];
		guardDir = new char[guardAmount];
		guardDir[0] = 'u';
		ogreCoords = new Coords[ogreAmount];
		ogreDir = new char[ogreAmount];
		for (char c : ogreDir)
			c = 'u';
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
				/*
				if (currentChar == heroChar || currentChar == 'K') {
					switch (heroDir) {
					case 'w':
						g2d.drawImage(herou_i, 30 * i, 30 * (j + 1), null);
						break;
					case 'd':
						g2d.drawImage(heror_i, 30 * i, 30 * (j + 1), null);
						break;
					case 'a':
						g2d.drawImage(herol_i, 30 * i, 30 * (j + 1), null);
						break;
					case 's':
						g2d.drawImage(herod_i, 30 * i, 30 * (j + 1), null);
						break;
					}
				}
				if (currentChar == 'G') {
					g2d.drawImage(guard_i, 30 * i, 30 * (j + 1), null);
				}
				if (currentChar == 'O' || currentChar == '$')
					g2d.drawImage(ogre_i, 30 * i, 30 * (j + 1), null);
				if (currentChar == '*')
					g2d.drawImage(club_i, 30 * i, 30 * (j + 1), null);
				*/
				if (currentChar == 'k')
					g2d.drawImage(lever_i, 30 * i, 30 * (j + 1), null);
				if (currentChar == 'S')
					g2d.drawImage(dooro_i, 30 * i, 30 * (j + 1), null);
				if (currentChar == 'I')
					g2d.drawImage(doorc_i, 30 * i, 30 * (j + 1), null);
				if (currentChar == 'X')
					g2d.drawImage(wall_i, 30 * i, 30 * (j + 1), null);
			}
		}
		
		switch (heroDir) {
		case 'w':
			g2d.drawImage(herou_i, 30 * heroCoords.getX(), 30 * (heroCoords.getY() + 1), null);
			break;
		case 'd':
			g2d.drawImage(heror_i, 30 * heroCoords.getX(), 30 * (heroCoords.getY() + 1), null);
			break;
		case 'a':
			g2d.drawImage(herol_i, 30 * heroCoords.getX(), 30 * (heroCoords.getY() + 1), null);
			break;
		case 's':
			g2d.drawImage(herod_i, 30 * heroCoords.getX(), 30 * (heroCoords.getY() + 1), null);
			break;
		}
		
		switch (guardDir[0]) {
		case 'w':
			g2d.drawImage(guardu_i, 30 * guardCoords[0].getX(), 30 * (guardCoords[0].getY() + 1), null);
			break;
		case 'd':
			g2d.drawImage(guardr_i, 30 * guardCoords[0].getX(), 30 * (guardCoords[0].getY() + 1), null);
			break;
		case 'a':
			g2d.drawImage(guardl_i, 30 * guardCoords[0].getX(), 30 * (guardCoords[0].getY() + 1), null);
			break;
		case 's':
			g2d.drawImage(guardd_i, 30 * guardCoords[0].getX(), 30 * (guardCoords[0].getY() + 1), null);
			break;
			
		}
		
		for (i = 0; i < ogreCoords.length; i++) {
			switch (ogreDir[i]) {
			case 'w':
				g2d.drawImage(ogreu_i, 30 * ogreCoords[i].getX(), 30 * (ogreCoords[i].getY() + 1), null);
				break;
			case 'd':
				g2d.drawImage(ogrer_i, 30 * ogreCoords[i].getX(), 30 * (ogreCoords[i].getY() + 1), null);
				break;
			case 'a':
				g2d.drawImage(ogrel_i, 30 * ogreCoords[i].getX(), 30 * (ogreCoords[i].getY() + 1), null);
				break;
			case 's':
				g2d.drawImage(ogred_i, 30 * ogreCoords[i].getX(), 30 * (ogreCoords[i].getY() + 1), null);
				break;
				
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

	public void setHeroPos(Coords heroPos) {
		heroCoords = heroPos;
	}
	
	public void setHeroDir(char c) {
		heroDir = c;
	}

	public Coords getGuardPos(int i) {
		return guardCoords[i];
	}

	public void setGuardPos(Coords guardCoords, int i) {
		this.guardCoords[i] = guardCoords;
	}

	public void setGuardDir(char c, int i) {
		guardDir[i] = c;
	}

	public Coords getOgrePos(int i) {
		return ogreCoords[i];
	}

	public void setOgrePos(Coords ogreCoords, int i) {
		this.ogreCoords[i] = ogreCoords;
	}

	public void setOgreDir(char c, int i) {
		ogreDir[i] = c;
	}
}
