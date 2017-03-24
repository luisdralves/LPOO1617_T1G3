package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Coords;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -1271074511834697397L;
	private static TexturePaint paint;
	// private Game g;
	private static BufferedImage herou_i = null;
	private static BufferedImage herod_i = null;
	private static BufferedImage herol_i = null;
	private static BufferedImage heror_i = null;
	private static BufferedImage guardu_i = null;
	private static BufferedImage guardd_i = null;
	private static BufferedImage guardl_i = null;
	private static BufferedImage guardr_i = null;
	private static BufferedImage guardz_i = null;
	private static BufferedImage ogreu_i = null;
	private static BufferedImage ogred_i = null;
	private static BufferedImage ogrel_i = null;
	private static BufferedImage ogrer_i = null;
	private static BufferedImage clubu_i = null;
	private static BufferedImage clubl_i = null;
	private static BufferedImage clubr_i = null;
	private static BufferedImage clubd_i = null;
	private static BufferedImage floor_i = null;
	private static BufferedImage wall_i = null;
	private static BufferedImage lever_i = null;
	private static BufferedImage dooro_i = null;
	private static BufferedImage doorc_i = null;
	private static BufferedImage key_i = null;
	private static Image herou_t = null;
	private static Image herod_t = null;
	private static Image herol_t = null;
	private static Image heror_t = null;
	private static Image guardu_t = null;
	private static Image guardd_t = null;
	private static Image guardl_t = null;
	private static Image guardr_t = null;
	private static Image guardz_t = null;
	private static Image ogreu_t = null;
	private static Image ogred_t = null;
	private static Image ogrel_t = null;
	private static Image ogrer_t = null;
	private static Image clubu_t = null;
	private static Image clubl_t = null;
	private static Image clubr_t = null;
	private static Image clubd_t = null;
	private static Image lever_t = null;
	private static Image dooro_t = null;
	private static Image doorc_t = null;
	private static Image key_t = null;
	private static Rectangle2D floor_dim = new Rectangle2D.Double(0, 0, 50, 40);

	private char[][] gameMap;
	private Coords keyCoords;
	private boolean drawKey;
	private Coords heroCoords;
	private char heroDir;
	private Coords[] guardCoords;
	private char[] guardDir;
	private Coords[] ogreCoords;
	private char[] ogreDir;
	private Coords[] clubCoords;
	private char[] clubDir;

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
			guardz_i = ImageIO.read(new File("rsc\\guardzzz.png"));
			ogreu_i = ImageIO.read(new File("rsc\\ogreup.png"));
			ogred_i = ImageIO.read(new File("rsc\\ogredown.png"));
			ogrel_i = ImageIO.read(new File("rsc\\ogreleft.png"));
			ogrer_i = ImageIO.read(new File("rsc\\ogreright.png"));
			clubu_i = ImageIO.read(new File("rsc\\clubup.png"));
			clubd_i = ImageIO.read(new File("rsc\\clubdown.png"));
			clubl_i = ImageIO.read(new File("rsc\\clubleft.png"));
			clubr_i = ImageIO.read(new File("rsc\\clubright.png"));
			floor_i = ImageIO.read(new File("rsc\\floor.jpg"));
			wall_i = ImageIO.read(new File("rsc\\wall.png"));
			lever_i = ImageIO.read(new File("rsc\\lever.png"));
			dooro_i = ImageIO.read(new File("rsc\\door_o.png"));
			doorc_i = ImageIO.read(new File("rsc\\door_c.png"));
			key_i = ImageIO.read(new File("rsc\\key.png"));
			
			int color = herou_i.getRGB(0, 0);
			herou_t = removeColor(herou_i, new Color(color));
			herod_t = removeColor(herod_i, new Color(color));
			herol_t = removeColor(herol_i, new Color(color));
			heror_t = removeColor(heror_i, new Color(color));
			guardu_t = removeColor(guardu_i, new Color(color));
			guardd_t = removeColor(guardd_i, new Color(color));
			guardl_t = removeColor(guardl_i, new Color(color));
			guardr_t = removeColor(guardr_i, new Color(color));
			guardz_t = removeColor(guardz_i, new Color(color));
			ogreu_t = removeColor(ogreu_i, new Color(color));
			ogred_t = removeColor(ogred_i, new Color(color));
			ogrel_t = removeColor(ogrel_i, new Color(color));
			ogrer_t = removeColor(ogrer_i, new Color(color));
			clubu_t = removeColor(clubu_i, new Color(color));
			clubd_t = removeColor(clubd_i, new Color(color));
			clubl_t = removeColor(clubl_i, new Color(color));
			clubr_t = removeColor(clubr_i, new Color(color));
			lever_t = removeColor(lever_i, new Color(color));
			dooro_t = removeColor(dooro_i, new Color(color));
			doorc_t = removeColor(doorc_i, new Color(color));
			key_t = removeColor(key_i, new Color(color));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameMap = new char[][] { { ' ' } };
		drawKey = false;
		heroCoords = new Coords(0, 0);
		heroDir = 'i';
		guardCoords = new Coords[1];
		guardCoords[0] = new Coords(0, 0);
		guardDir = new char[1];
		guardDir[0] = 'i';
		ogreCoords = new Coords[1];
		ogreCoords[0] = new Coords(0, 0);
		ogreDir = new char[1];
		ogreDir[0] = 'i';
		clubCoords = new Coords[1];
		clubCoords[0] = new Coords(0, 0);
		clubDir = new char[1];
		clubDir[0] = 'i';
	}

	public void initialize(int guardAmount, int ogreAmount) {
		guardCoords = new Coords[guardAmount];
		guardDir = new char[guardAmount];
		ogreCoords = new Coords[ogreAmount];
		ogreDir = new char[ogreAmount];
		clubCoords = new Coords[ogreAmount];
		clubDir = new char[ogreAmount];
		for (int i = 0; i < ogreAmount; i++) {
			ogreDir[i] = 'u';
			clubDir[i] = 'u';
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paint = new TexturePaint(floor_i, floor_dim);
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		if(drawKey)
			g2d.drawImage(key_t, 30 * keyCoords.getX(), 30 * keyCoords.getY(), null);
		
		switch (heroDir) {
		case 'w':
			g2d.drawImage(herou_t, 30 * heroCoords.getX(), 30 * heroCoords.getY(), null);
			break;
		case 'd':
			g2d.drawImage(heror_t, 30 * heroCoords.getX(), 30 * heroCoords.getY(), null);
			break;
		case 'a':
			g2d.drawImage(herol_t, 30 * heroCoords.getX(), 30 * heroCoords.getY(), null);
			break;
		case 's':
			g2d.drawImage(herod_t, 30 * heroCoords.getX(), 30 * heroCoords.getY(), null);
			break;
		}

		switch (guardDir[0]) {
		case 'w':
			g2d.drawImage(guardu_t, 30 * guardCoords[0].getX(), 30 * guardCoords[0].getY(), null);
			break;
		case 'd':
			g2d.drawImage(guardr_t, 30 * guardCoords[0].getX(), 30 * guardCoords[0].getY(), null);
			break;
		case 'a':
			g2d.drawImage(guardl_t, 30 * guardCoords[0].getX(), 30 * guardCoords[0].getY(), null);
			break;
		case 's':
			g2d.drawImage(guardd_t, 30 * guardCoords[0].getX(), 30 * guardCoords[0].getY(), null);
			break;
		case 'n':
			g2d.drawImage(guardz_t, 30 * guardCoords[0].getX(), 30 * guardCoords[0].getY(), null);
			break;
		}

		for (int i = 0; i < ogreCoords.length; i++) {
			switch (clubDir[i]) {
			case 'w':
				g2d.drawImage(clubu_t, 30 * clubCoords[i].getX(), 30 * clubCoords[i].getY(), null);
				break;
			case 'd':
				g2d.drawImage(clubr_t, 30 * clubCoords[i].getX(), 30 * clubCoords[i].getY(), null);
				break;
			case 'a':
				g2d.drawImage(clubl_t, 30 * clubCoords[i].getX(), 30 * clubCoords[i].getY(), null);
				break;
			case 's':
				g2d.drawImage(clubd_t, 30 * clubCoords[i].getX(), 30 * clubCoords[i].getY(), null);
				break;
			}
			
			switch (ogreDir[i]) {
			case 'w':
				g2d.drawImage(ogreu_t, 30 * ogreCoords[i].getX(), 30 * ogreCoords[i].getY(), null);
				break;
			case 'd':
				g2d.drawImage(ogrer_t, 30 * ogreCoords[i].getX(), 30 * ogreCoords[i].getY(), null);
				break;
			case 'a':
				g2d.drawImage(ogrel_t, 30 * ogreCoords[i].getX(), 30 * ogreCoords[i].getY(), null);
				break;
			case 's':
				g2d.drawImage(ogred_t, 30 * ogreCoords[i].getX(), 30 * ogreCoords[i].getY(), null);
				break;
			}
		}

		int i = 0, j = 0;
		char currentChar;
		for (i = 0; i < gameMap.length; i++) {
			for (j = 0; j < gameMap[i].length; j++) {
				currentChar = gameMap[j][i];
				if (currentChar == 'k')
					g2d.drawImage(lever_t, 30 * i, 30 * j, null);
				if (currentChar == 'S')
					g2d.drawImage(dooro_t, 30 * i, 30 * j, null);
				if (currentChar == 'I')
					g2d.drawImage(doorc_t, 30 * i, 30 * j, null);
				if (currentChar == 'X')
					g2d.drawImage(wall_i, 30 * i, 30 * j, null);
			}
		}
	}

	public static Image removeColor(BufferedImage im, Color color) {
        ImageFilter imf = new RGBImageFilter() {
            int markerRGB = color.getRGB() | 0xFF000000;
            public int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(im.getSource(), imf);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
	
	public void setGameMap(char[][] map) {
		gameMap = map;
		repaint();
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
	
	public void setClubPos(Coords ogreCoords, int i) {
		this.clubCoords[i] = ogreCoords;
	}

	public void setClubDir(char c, int i) {
		clubDir[i] = c;
	}

	public void drawKey(boolean b) {
		drawKey = b;
	}

	public void setKeyCoords(Coords c) {
		keyCoords = c;		
	}
}
