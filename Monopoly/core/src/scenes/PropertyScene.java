package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

public class PropertyScene {
    private Stage stage;
    private Table tableInfo;
    private Table tableButtons;
    private static Label lblTitle, lblCosts, lblRents, lblOwner;
    private Texture bg;
    private ShapeRenderer shapeRenderer;
    private static Color set;
    private TextButton btnExit;
    private TextButton btnBuy;
    private TextButton btnAuction;
    private TextButton btnMortgage;

    public PropertyScene() {
        stage = new Stage();
        bg = new Texture("prop_bg.png");
        shapeRenderer = new ShapeRenderer();
        set = Color.BLACK;

        btnExit = new TextButton("Exit", Monopoly.btnStyle);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                PlayScreen.exitPropertyScene();
            }
        });
        btnExit.getLabel().setFontScale(0.6f);

        btnBuy = new TextButton("Acquire", Monopoly.btnStyle);
        btnBuy.getLabel().setFontScale(0.6f);
        btnAuction = new TextButton("Auction", Monopoly.btnStyle);
        btnAuction.getLabel().setFontScale(0.6f);
        btnMortgage = new TextButton("Mortgage", Monopoly.btnStyle);
        btnMortgage.getLabel().setFontScale(0.6f);

        int padding = 1 * bg.getHeight() / 16;

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
        tableButtons.add(lblOwner).colspan(2).padTop(2*padding/3).row();

        tableButtons.add(btnBuy).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padRight(padding/2);
        tableButtons.add(btnAuction).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padLeft(padding/2).row();
        tableButtons.add(btnMortgage).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padBottom(padding).padRight(padding/2);
        tableButtons.add(btnExit).width(8 * bg.getWidth() / 20 - padding/2).padTop(padding/2).padBottom(padding).padLeft(padding/2);

        stage.addActor(tableInfo);
        stage.addActor(tableButtons);
    }

    public static void viewProperty() {
        Square sq = Board.getSquare(GameData.getPlayer().getPosition());
        if(sq instanceof Purchasable) {
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
        }
        else
            PlayScreen.exitPropertyScene();
    }

    public void render(SpriteBatch spb) {
        Gdx.input.setInputProcessor(this.stage);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.6f);
        shapeRenderer.rect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        shapeRenderer.setColor(set);
        shapeRenderer.rect((Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2, bg.getWidth(), bg.getHeight());
        shapeRenderer.end();
        shapeRenderer.end();
        spb.begin();
        spb.draw(bg, (Monopoly.WIDTH - bg.getWidth()) / 2, (Monopoly.HEIGHT - bg.getHeight()) / 2);
        spb.end();
        stage.act();
        stage.draw();
    }
}
