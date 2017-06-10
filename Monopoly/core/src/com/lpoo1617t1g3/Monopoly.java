package com.lpoo1617t1g3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import screens.MainMenu;

public class Monopoly extends Game {
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    public final static String TITLE = "Monopoly";
    public static BitmapFont kabelBlack;
    public static TextureAtlas atlas;
    public static Skin skin;
    public static Label.LabelStyle lblStyle;
    public static TextButton.TextButtonStyle btnStyle;
    public static TextButton.TextButtonStyle btnStyleInvisible;
    public static ImageButton.ImageButtonStyle ibtnStyleRight;
    public static ImageButton.ImageButtonStyle ibtnStyleLeft;
    public static TextField.TextFieldStyle tflStyle;
    public static CheckBox.CheckBoxStyle cbxStyle;
    public static Slider.SliderStyle sldStyle;
    public static ShapeRenderer shapeRenderer;
    public Music musicIntro;
    public Music musicLoop;
    public SpriteBatch spb;
    public boolean isHappening;
    private Screen screen;

    @Override
    public void create() {
        spb = new SpriteBatch();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        isHappening = false;

        atlas = new TextureAtlas("tpack/tpack.atlas");
        skin = new Skin(atlas);
        kabelBlack = new BitmapFont(Gdx.files.internal("Kabel.fnt"));
        lblStyle = new Label.LabelStyle(kabelBlack, Color.BLACK);

        btnStyle = new TextButton.TextButtonStyle();
        btnStyle.up = skin.getDrawable("btn_up");
        btnStyle.down = skin.getDrawable("btn_down");
        btnStyle.over = skin.getDrawable("btn_hover");
        btnStyle.disabled = skin.getDrawable("btn_dis");
        btnStyle.font = kabelBlack;
        btnStyleInvisible = new TextButton.TextButtonStyle();
        btnStyleInvisible.up = skin.getDrawable("btn_up_alpha");
        btnStyleInvisible.down = skin.getDrawable("btn_down_alpha");
        btnStyleInvisible.over = skin.getDrawable("btn_hover_alpha");
        btnStyleInvisible.font = kabelBlack;

        ibtnStyleRight = new ImageButton.ImageButtonStyle();
        // TODO: 07/06/2017 add to atlas
        ibtnStyleRight.up = new TextureRegionDrawable(new TextureRegion(new Texture("ibtn_right.png")));
        ibtnStyleRight.down = new TextureRegionDrawable(new TextureRegion(new Texture("ibtn_right_down.png")));

        ibtnStyleLeft = new ImageButton.ImageButtonStyle();
        // TODO: 07/06/2017 add to atlas
        ibtnStyleLeft.up = new TextureRegionDrawable(new TextureRegion(new Texture("ibtn_left.png")));
        ibtnStyleLeft.down = new TextureRegionDrawable(new TextureRegion(new Texture("ibtn_left_down.png")));

        tflStyle = new TextField.TextFieldStyle();
        tflStyle.cursor = skin.getDrawable("tfl_cur");
        tflStyle.selection = skin.getDrawable("tfl_sel");
        tflStyle.background = skin.getDrawable("tfl_bg");
        tflStyle.font = new BitmapFont(Gdx.files.internal("Kabel.fnt"));
        tflStyle.font.getData().setScale(0.5f);
        tflStyle.fontColor = Color.BLACK;

        cbxStyle = new CheckBox.CheckBoxStyle();
        cbxStyle.checkboxOff = skin.getDrawable("cbx_off");
        cbxStyle.checkboxOn = skin.getDrawable("cbx_on");
        cbxStyle.font = kabelBlack;
        cbxStyle.fontColor = Color.BLACK;

        sldStyle = new Slider.SliderStyle();
        sldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture("sld_bg.png")));
        sldStyle.knobDown = new TextureRegionDrawable(new TextureRegion(new Texture("sld3.png")));
        sldStyle.knobOver = new TextureRegionDrawable(new TextureRegion(new Texture("sld2.png")));
        sldStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture("sld.png")));

        shapeRenderer = new ShapeRenderer();

        screen = new MainMenu(this);
        setScreen(screen);
        musicInit();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spb.dispose();
    }

    private void musicInit() {
        musicIntro = Gdx.audio.newMusic(Gdx.files.internal("money_intro.ogg"));
        musicLoop = Gdx.audio.newMusic(Gdx.files.internal("money_loop.ogg"));
        musicLoop.setLooping(true);
        musicIntro.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music aMusic) {
                musicLoop.play();
            }
        });
        musicIntro.play();
    }
}
