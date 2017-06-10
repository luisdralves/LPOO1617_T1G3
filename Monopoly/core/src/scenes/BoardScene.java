package scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lpoo1617t1g3.Monopoly;

import logic.Board;
import logic.GameData;
import logic.Player;
import logic.Property;

public class BoardScene {
    private Texture house, hotel;

    public BoardScene() {
        house = new Texture("house.png");
        hotel = new Texture("hotel.png");
        int size = logic.Board.getSquares().size();
    }

    public static Vector2 posToCoords(int pos) {
        int width = 59 * Monopoly.HEIGHT / 720;
        int height = 2 * Monopoly.HEIGHT / 15;

        if (pos < 10)
            return new Vector2(96 + (9 - pos) * 59, 0);
        else if (pos < 20)
            return new Vector2(0, 96 + (pos - 11) * 59);
        else if (pos < 30)
            return new Vector2(96 + (pos - 21) * 59, 624);
        else if (pos < 40)
            return new Vector2(624, 96 + (39 - pos) * 59);
        return new Vector2(0, 0);
    }

    public void render(SpriteBatch spb) {
        spb.begin();

        for(Player p : GameData.getPlayers()) {
            if (p.getPosition() < 10)
                spb.draw(p.getToken(), posToCoords(p.getPosition()).x - 3, 0);
            else if (p.getPosition() < 20)
                spb.draw(p.getToken(), 0, posToCoords(p.getPosition()).y - 3);
            else if (p.getPosition() < 30)
                spb.draw(p.getToken(), posToCoords(p.getPosition()).x - 3, Monopoly.HEIGHT - p.getToken().getHeight());
            else
                spb.draw(p.getToken(), Monopoly.HEIGHT - p.getToken().getWidth(), posToCoords(p.getPosition()).y - 3);
        }

        for(int j = 0; j < Board.getSquares().size(); j++) {
            if(Board.getSquare(j) instanceof Property) {
                Property p = ((Property) Board.getSquare(j));
                if (p.getHouses() == 5) {
                    if(j < 10)
                        spb.draw(hotel, posToCoords(j).x + 20, posToCoords(j).y + 75, 20, 20);
                    else if (j < 20)
                        spb.draw(hotel, posToCoords(j).x + 75, posToCoords(j).y + 20, 20, 20);
                    else if (j < 30)
                        spb.draw(hotel, posToCoords(j).x + 20, posToCoords(j).y + 3, 20, 20);
                    else
                        spb.draw(hotel, posToCoords(j).x + 3, posToCoords(j).y + 20, 20, 20);
                }
                else {
                    if (j < 10)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + i * 14, posToCoords(j).y + 75, 20, 20);
                    else if (j < 20)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + 75, posToCoords(j).y + 42 - i * 14, 20, 20);
                    else if (j < 30)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + i * 14, posToCoords(j).y + 3, 20, 20);
                    else
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + 3, posToCoords(j).y + 42 - i * 14, 20, 20);
                }
            }
        }
        spb.end();
    }
}
