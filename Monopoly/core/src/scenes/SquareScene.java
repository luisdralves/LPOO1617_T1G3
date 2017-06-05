package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoo1617t1g3.Monopoly;

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
    private static Color set;
    private static Table tableInfo;
    private Stage stage;
    private Table tableButtons;
    private Texture bg;

    public SquareScene() {
        stage = new Stage();
        bg = new Texture("prop_bg.png");
        set = Color.BLACK;

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
            public void clicked(InputEvent event, float x, float y)  {
                PlayScreen.exitPropertyScene();
            }
        });
        btnExit.getLabel().setFontScale(0.6f);

        btnBuy = new TextButton("Acquire", Monopoly.btnStyle);
        btnBuy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                GameData.getPlayer().purchase();
                view(GameData.getPlayer().getPosition());
            }
        });
        btnBuy.getLabel().setFontScale(0.6f);

        btnAuction = new TextButton("Auction", Monopoly.btnStyle);
        btnAuction.getLabel().setFontScale(0.6f);
        btnMortgage = new TextButton("Mortgage", Monopoly.btnStyle);
        btnMortgage.getLabel().setFontScale(0.6f);

        int padding = 1 * bg.getHeight() / 16;

        tableInfo = new Table();
        tableInfo.top();
        tableInfo.setBounds(0, (Monopoly.HEIGHT - bg.getHeight()) / 2, Monopoly.WIDTH, bg.getHeight() + propNo.getHeight() - 3);

        tableInfo.add(propNo).width(30).right().colspan(2).row();
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
        tableButtons.add(lblOwner).colspan(2).padTop(2*padding/3).row();

        tableButtons.add(btnBuy).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padRight(padding/2);
        tableButtons.add(btnAuction).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padLeft(padding/2).row();
        tableButtons.add(btnMortgage).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padBottom(padding).padRight(padding/2);
        tableButtons.add(btnExit).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padBottom(padding).padLeft(padding/2);

        stage.addActor(tableInfo);
        stage.addActor(tableButtons);
    }

    public static void view(int pos) {
        Square sq = Board.getSquare(pos);
        propNo.setText(String.valueOf(pos));
        if(sq instanceof Purchasable) {
            if(GameData.getPlayer().getID() == ((Purchasable) sq).getOwnerID()) {
                btnBuy.setText("Improve");
                //btnBuy.listener
            }
            tableInfo.getCells().get(3).getActor().setVisible(true);
            tableInfo.getCells().get(5).getActor().setVisible(true);
            lblCosts.setVisible(true);
            lblRents.setVisible(true);
            lblOwner.setVisible(true);
            btnBuy.setVisible(true);
            btnAuction.setVisible(true);
            btnMortgage.setVisible(true);
            lblTitle.setText(sq.getTitle());
            lblCosts.setText(String.format("$%d/Land", ((Purchasable) sq).getLandCost()));
            lblOwner.setText(((Purchasable) sq).getOwnerName());
            set = Color.GRAY;
            if(sq instanceof Property) {
                lblCosts.setText(String.format("$%d/Land\n$%d/House", ((Purchasable) sq).getLandCost(), ((Property) sq).getHouseCost()));
                lblRents.setText(String.format("$%d\n$%d\n$%d\n$%d\n$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1), ((Purchasable) sq).getRent(2), ((Purchasable) sq).getRent(3), ((Purchasable) sq).getRent(4), ((Purchasable) sq).getRent(5)));
                set = ((Property) sq).getColourSet();
            } else if(sq instanceof Station) {
                lblRents.setText(String.format("$%d\n$%d\n$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1), ((Purchasable) sq).getRent(2), ((Purchasable) sq).getRent(3)));
            } else if(sq instanceof Utility) {
                lblRents.setText(String.format("$%d\n$%d", ((Purchasable) sq).getRent(0), ((Purchasable) sq).getRent(1)));
            }
        } else {
            set = Color.GRAY;
            tableInfo.getCells().get(3).getActor().setVisible(false);
            tableInfo.getCells().get(5).getActor().setVisible(false);
            lblTitle.setText(sq.getTitle());
            lblTitle.setVisible(true);
            lblCosts.setVisible(false);
            lblRents.setVisible(false);
            lblOwner.setVisible(false);
            btnBuy.setVisible(false);
            btnAuction.setVisible(false);
            btnMortgage.setVisible(false);
        }
    }

    public void render(SpriteBatch spb) {
        Gdx.input.setInputProcessor(this.stage);
        Monopoly.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Monopoly.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.6f);
        Monopoly.shapeRenderer.rect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        Monopoly.shapeRenderer.setColor(set);
        Monopoly.shapeRenderer.rect((Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2, bg.getWidth(), bg.getHeight());
        Monopoly.shapeRenderer.end();
        Monopoly.shapeRenderer.end();
        spb.begin();
        spb.draw(bg, (Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2);
        spb.end();
        stage.act();
        stage.draw();
    }
}
