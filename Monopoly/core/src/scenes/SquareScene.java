package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoo1617t1g3.Monopoly;

import java.util.List;

import logic.Board;
import logic.GameData;
import logic.Property;
import logic.Purchasable;
import logic.Square;
import logic.Station;
import logic.Utility;
import screens.PlayScreen;

public class SquareScene {
    private static TextField propNo;
    private static Label lblTitle, lblCosts, lblRents, lblOwner;
    private static TextButton btnExit, btnBuy, btnAuction, btnMortgage;
    private static ImageButton positionMore, positionLess;
    private static Color set;
    private static Table tableInfo;
    private static Table tableHeader;
    private static int sqPos;
    private static boolean auctioning;
    private static boolean flags[];
    private static boolean viewingSelection;
    private static List<Integer> viewable;
    private AuctionScene auctionScene;
    private Stage stage;
    private Table tableButtons;
    private Texture bg;

    public SquareScene() {
        auctionScene = new AuctionScene();
        auctioning = false;
        stage = new Stage();
        bg = new Texture("prop_bg.png");
        set = Color.BLACK;

        flags = new boolean[8];

        propNo = new TextField("", Monopoly.tflStyle);
        propNo.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        propNo.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    view(Integer.parseInt(propNo.getText()));
                }
                return false;
            }
        });

        btnExit = new TextButton("Exit", Monopoly.btnStyle);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen.exitPropertyScene();
            }
        });
        btnExit.getLabel().setFontScale(0.6f);

        btnBuy = new TextButton("Acquire", Monopoly.btnStyle);
        btnBuy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!flags[0] && !flags[1] && flags[2] && (flags[3] || flags[4])) {
                    GameData.getPlayer().purchase();
                } else if (flags[4] && flags[1]) {
                    ((Property) Board.getSquare(sqPos)).addToHouses(1);
                } else if (flags[0] && (flags[3] || flags[4])) {
                    auctionScene.negotiate(sqPos);
                    auctioning = true;
                }
                view(sqPos);
            }
        });
        btnBuy.getLabel().setFontScale(0.6f);

        btnAuction = new TextButton("Auction", Monopoly.btnStyle);
        btnAuction.getLabel().setFontScale(0.6f);
        btnAuction.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                auctionScene.auction();
                auctioning = true;
            }
        });

        btnMortgage = new TextButton("Mortgage", Monopoly.btnStyle);
        btnMortgage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Purchasable) Board.getSquare(sqPos)).toggleMortgage();
                view(sqPos);
            }
        });
        btnMortgage.getLabel().setFontScale(0.6f);

        positionMore = new ImageButton(Monopoly.ibtnStyleRight);
        positionLess = new ImageButton(Monopoly.ibtnStyleLeft);
        positionMore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (viewingSelection) {
                    sqPos++;
                    sqPos %= viewable.size();
                    propNo.setText(Integer.toString(viewable.get(sqPos)));
                    view(sqPos, viewable);
                } else if (Integer.parseInt(propNo.getText()) >= 0) {
                    propNo.setText(Integer.toString((Integer.parseInt(propNo.getText()) + 1) % 40));
                    view(Integer.parseInt(propNo.getText()));
                }
            }
        });
        positionLess.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (viewingSelection) {
                    sqPos--;
                    if (sqPos < 0)
                        sqPos += viewable.size();
                    propNo.setText(Integer.toString(viewable.get(sqPos)));
                    view(sqPos, viewable);
                } else if (Integer.parseInt(propNo.getText()) > 0) {
                    propNo.setText(Integer.toString(Integer.parseInt(propNo.getText()) - 1));
                    view(Integer.parseInt(propNo.getText()));
                } else if (Integer.parseInt(propNo.getText()) == 0) {
                    propNo.setText(Integer.toString(39));
                    view(Integer.parseInt(propNo.getText()));
                }
            }
        });

        int padding = 1 * bg.getHeight() / 16;


        tableHeader = new Table();
        tableHeader.right();
        tableHeader.setBounds(Monopoly.WIDTH / 2 + 32, Monopoly.HEIGHT / 2 + bg.getHeight() / 2 - 3, bg.getWidth() / 2, propNo.getHeight());
        tableHeader.add(positionLess);
        tableHeader.add(propNo).width(30);
        tableHeader.add(positionMore);

        tableInfo = new Table();
        tableInfo.top();
        tableInfo.setBounds(0, (Monopoly.HEIGHT - bg.getHeight()) / 2, Monopoly.WIDTH, bg.getHeight());

        tableInfo.add(new Label("", Monopoly.lblStyle)).width(8 * bg.getWidth() / 10).height(padding).colspan(2).row();

        lblTitle = new Label("", Monopoly.lblStyle);
        lblTitle.setFontScale(0.7f);
        tableInfo.add(lblTitle).colspan(2).row();

        Label lblCost = new Label("Cost: ", Monopoly.lblStyle);
        lblCost.setFontScale(0.6f);
        tableInfo.add(lblCost).top().left().padTop(padding);
        lblCosts = new Label("", Monopoly.lblStyle);
        lblCosts.setFontScale(0.6f);
        tableInfo.add(lblCosts).right().padTop(padding).row();

        Label lblRent = new Label("Rent: ", Monopoly.lblStyle);
        lblRent.setFontScale(0.6f);
        tableInfo.add(lblRent).top().left().padTop(padding);
        lblRents = new Label("", Monopoly.lblStyle);
        lblRents.setFontScale(0.6f);
        tableInfo.add(lblRents).right().padTop(padding).row();


        tableButtons = new Table();
        tableButtons.setBounds(0, (Monopoly.HEIGHT - bg.getHeight()) / 2, Monopoly.WIDTH, bg.getHeight());
        tableButtons.bottom();
        lblOwner = new Label("", Monopoly.lblStyle);
        lblOwner.setFontScale(0.6f);
        tableButtons.add(lblOwner).colspan(2).padTop(2 * padding / 3).row();

        tableButtons.add(btnBuy).width(8 * bg.getWidth() / 20 - padding / 2).padTop(padding / 2).padRight(padding / 2);
        tableButtons.add(btnAuction).width(8 * bg.getWidth() / 20 - padding / 2).padTop(padding / 2).padLeft(padding / 2).row();
        tableButtons.add(btnMortgage).width(8 * bg.getWidth() / 20 - padding / 2).padTop(padding / 2).padBottom(padding).padRight(padding / 2);
        tableButtons.add(btnExit).width(8 * bg.getWidth() / 20 - padding / 2).padTop(padding / 2).padBottom(padding).padLeft(padding / 2);

        stage.addActor(tableHeader);
        stage.addActor(tableInfo);
        stage.addActor(tableButtons);
    }


    public static void view(int pos) {
        viewingSelection = false;
        sqPos = pos;
        propNo.setText(Integer.toString(sqPos));
        lblTitle.setText(Board.getSquare(pos).getTitle());

        updateFlags(pos);

        manageButtons(pos);
    }

    public static void view(int i, List<Integer> pos) {
        viewingSelection = true;
        viewable = pos;
        sqPos = i;
        propNo.setText(Integer.toString(viewable.get(sqPos)));
        lblTitle.setText(Board.getSquare(viewable.get(sqPos)).getTitle());

        updateFlags(viewable.get(sqPos));

        manageButtons(viewable.get(sqPos));
    }

    private static void updateFlags(int pos) {
        Square sq = Board.getSquare(pos);
        flags[0] = false;
        flags[1] = false;
        flags[2] = GameData.getPlayer().getPosition() == pos;
        flags[4] = sq instanceof Property;
        flags[3] = (sq instanceof Purchasable && !flags[4]);
        flags[5] = false;
        flags[6] = false;
        flags[7] = false;
        if ((flags[3] || flags[4]) && ((Purchasable) sq).isOwned()) {
            flags[0] = ((Purchasable) sq).getOwnerID() != GameData.getPlayer().getID();
            flags[1] = !flags[0];
        }
        if (flags[0] || flags[1])
            flags[5] = ((Purchasable) sq).isActive();
        if (flags[4] && ((Property) sq).getHouses() >= 5)
            flags[6] = true;
        if (flags[4] && flags[1])
            flags[7] = ((Property) sq).isSetComplete();
    }

    private static void manageButtons(int pos) {
        if (flags[3] || flags[4]) {
            updateLabels(pos);
            if (((!flags[0] || !flags[1]) && (!flags[0] || flags[3]) && (!flags[1] || flags[3])) || (flags[0] && (flags[3] || flags[4])))
                btnBuy.setText("Acquire");
            else
                btnBuy.setText("Improve");
            btnBuy.setVisible(true);
            if (flags[5] || (!flags[0] && !flags[1]))
                btnMortgage.setText("Mortgage");
            else
                btnMortgage.setText("Unmortgage");
            btnMortgage.setVisible(true);
            btnAuction.setVisible(true);
            if ((flags[1] || flags[5] || !flags[2]) && (flags[5] || !flags[1] || !flags[4] || !flags[7]) && !(flags[0] && (flags[3] || flags[4]))) {
                btnBuy.setTouchable(Touchable.disabled);
                btnBuy.setDisabled(true);
            } else {
                btnBuy.setTouchable(Touchable.enabled);
                btnBuy.setDisabled(false);
            }
            if (flags[1]) {
                btnMortgage.setTouchable(Touchable.enabled);
                btnMortgage.setDisabled(false);
            } else {
                btnMortgage.setTouchable(Touchable.disabled);
                btnMortgage.setDisabled(true);
            }
            if (!flags[0] && !flags[1] && flags[2]) {
                btnAuction.setTouchable(Touchable.enabled);
                btnAuction.setDisabled(false);
            } else {
                btnAuction.setTouchable(Touchable.disabled);
                btnAuction.setDisabled(true);
            }
        } else {
            set = Color.GRAY;
            tableInfo.getCells().get(2).getActor().setVisible(false);
            tableInfo.getCells().get(4).getActor().setVisible(false);
            lblCosts.setVisible(false);
            lblRents.setVisible(false);
            lblOwner.setVisible(false);

            btnBuy.setVisible(false);
            btnMortgage.setVisible(false);
            btnAuction.setVisible(false);
            btnBuy.setTouchable(Touchable.disabled);
            btnMortgage.setTouchable(Touchable.disabled);
            btnAuction.setTouchable(Touchable.disabled);
        }
    }

    private static void updateLabels(int pos) {
        Square sq = Board.getSquare(pos);
        set = Color.GRAY;
        tableInfo.getCells().get(2).getActor().setVisible(true);
        tableInfo.getCells().get(4).getActor().setVisible(true);
        lblCosts.setVisible(true);
        lblRents.setVisible(true);
        lblOwner.setVisible(true);
        btnBuy.setVisible(true);
        btnAuction.setVisible(true);
        btnMortgage.setVisible(true);
        lblTitle.setText(sq.getTitle());
        lblCosts.setText(String.format("$%d/Land\n$%d/Mort.", ((Purchasable) sq).getLandCost(), ((Purchasable) sq).getMortgage()));
        lblOwner.setText(((Purchasable) sq).getOwnerName());
        if (sq instanceof Property) {
            lblCosts.setText(String.format("$%d/Land\n$%d/Mort.\n$%d/House", ((Purchasable) sq).getLandCost(), ((Purchasable) sq).getMortgage(), ((Property) sq).getHouseCost()));
            lblRents.setText(String.format("$%d\n$%d\n$%d\n$%d\n$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1), ((Purchasable) sq).getRent(2), ((Purchasable) sq).getRent(3), ((Purchasable) sq).getRent(4), ((Purchasable) sq).getRent(5)));
            set = ((Property) sq).getColourSet();
        } else if (sq instanceof Station) {
            lblRents.setText(String.format("$%d\n$%d\n$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1), ((Purchasable) sq).getRent(2), ((Purchasable) sq).getRent(3)));
        } else if (sq instanceof Utility) {
            lblRents.setText(String.format("$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1)));
        }
    }

    public static void exitAuction() {
        auctioning = false;
    }

    public void render(SpriteBatch spb) {
        Gdx.input.setInputProcessor(this.stage);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Monopoly.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Monopoly.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.6f);
        Monopoly.shapeRenderer.rect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        Monopoly.shapeRenderer.setColor(set);
        Monopoly.shapeRenderer.rect((Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2, bg.getWidth(), bg.getHeight());
        Monopoly.shapeRenderer.end();
        spb.begin();
        spb.draw(bg, (Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2);
        spb.end();
        stage.act();
        stage.draw();
        if (auctioning)
            auctionScene.render(spb);
    }
}
