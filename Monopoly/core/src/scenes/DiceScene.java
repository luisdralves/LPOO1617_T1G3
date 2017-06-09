package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import screens.PlayScreen;

public class DiceScene {
    private final static int PIXELS_IN_A_METER = 60;
    private OrthographicCamera cam;
    private Viewport vp;
    private World world;
    private Texture bg;
    private Texture die;
    private Sprite sp1;
    private Sprite sp2;
    private Body bodyDie1;
    private Body bodyDie2;
    private float offsetAlpha1, offsetAlpha2;

    private Box2DDebugRenderer debugRenderer;

    public DiceScene() {
        world = new World(new Vector2(0, -9.8f), true);
        cam = new OrthographicCamera(Monopoly.WIDTH/PIXELS_IN_A_METER, Monopoly.HEIGHT/PIXELS_IN_A_METER);
        vp = new FitViewport(Monopoly.WIDTH/PIXELS_IN_A_METER, Monopoly.HEIGHT/PIXELS_IN_A_METER, cam);
        bg = new Texture("mat.jpg");
        die = new Texture("die.png");
        sp1 = new Sprite(die);
        sp2 = new Sprite(die);


        debugRenderer = new Box2DDebugRenderer();

        boxIt();
    }

    public void render(SpriteBatch spb) {
        debugRenderer.render(world, cam.combined);
        if (checkIt()) {
            PlayScreen.rollingDice = false;
            PlayScreen.diceReady = true;
        }

        if (Gdx.input.getAccelerometerY() != 0.0f || Gdx.input.getAccelerometerX() != 0.0f)
            world.setGravity(new Vector2(Gdx.input.getAccelerometerY(), -Gdx.input.getAccelerometerX()));

        sp1.rotate(-sp1.getRotation());
        sp1.rotate(offsetAlpha1);
        sp1.rotate(180 * bodyDie1.getAngle() / (float)Math.PI);
        sp1.setPosition(bodyDie1.getPosition().x*PIXELS_IN_A_METER + (Monopoly.WIDTH - sp1.getWidth())/2, bodyDie1.getPosition().y*PIXELS_IN_A_METER + (Monopoly.HEIGHT - sp1.getHeight())/2);

        sp2.rotate(-sp2.getRotation());
        sp2.rotate(offsetAlpha2);
        sp2.rotate(180 * bodyDie2.getAngle() / (float)Math.PI);
        sp2.setPosition(bodyDie2.getPosition().x*PIXELS_IN_A_METER + (Monopoly.WIDTH - sp2.getWidth())/2, bodyDie2.getPosition().y*PIXELS_IN_A_METER + (Monopoly.HEIGHT - sp2.getHeight())/2);

        spb.begin();
        spb.draw(bg, 0,0, Monopoly.WIDTH, Monopoly.HEIGHT);
        sp1.draw(spb);
        sp2.draw(spb);
        spb.end();
        world.step(1/60f, 6, 2);
    }

    public void initDice() {
        if (bodyDie1 != null && bodyDie2 != null) {
            world.destroyBody(bodyDie1);
            world.destroyBody(bodyDie2);
        }

        int offsetX, offsetY;
        offsetX = (int)(Math.random() * ((Monopoly.WIDTH/PIXELS_IN_A_METER)/2 - 150/PIXELS_IN_A_METER) + 75/PIXELS_IN_A_METER);
        offsetY = (int)(Math.random() * ((Monopoly.HEIGHT/PIXELS_IN_A_METER)/2 - 150/PIXELS_IN_A_METER) + 75/PIXELS_IN_A_METER);

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef1.position.set(offsetX, offsetY);
        bodyDie1 = world.createBody(bodyDef1);

        offsetX = (int)(Math.random() * ((Monopoly.WIDTH/PIXELS_IN_A_METER)/2 - 150/PIXELS_IN_A_METER) + 75/PIXELS_IN_A_METER);
        offsetY = (int)(Math.random() * ((Monopoly.HEIGHT/PIXELS_IN_A_METER)/2 - 150/PIXELS_IN_A_METER) + 75/PIXELS_IN_A_METER);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(offsetX, offsetY);
        bodyDie2 = world.createBody(bodyDef2);

        offsetAlpha1 = (float)(Math.random() * 2 * Math.PI);
        offsetAlpha2 = (float)(Math.random() * 2 * Math.PI);

        PolygonShape hexagon = new PolygonShape();
        Vector2[] vertices = new Vector2[6];

        for(int i = 0; i < 6; i++) {
            vertices[i] = new Vector2((float)((64/PIXELS_IN_A_METER)*Math.cos(i * Math.PI/3 + offsetAlpha1)),
                    (float)((64/PIXELS_IN_A_METER)*Math.sin(i * Math.PI/3 + offsetAlpha1)));
        }
        offsetAlpha1 = (float)(offsetAlpha1 * 180/Math.PI);
        hexagon.set(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hexagon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;

        bodyDie1.createFixture(fixtureDef);

        for(int i = 0; i < 6; i++) {
            vertices[i] = new Vector2((float)((64/PIXELS_IN_A_METER)*Math.cos(i * Math.PI/3 + offsetAlpha2)),
                    (float)((64/PIXELS_IN_A_METER)*Math.sin(i * Math.PI/3 + offsetAlpha2)));
        }
        offsetAlpha2 = (float)(offsetAlpha2 * 180/Math.PI);
        hexagon.set(vertices);
        fixtureDef.shape = hexagon;

        bodyDie2.createFixture(fixtureDef);
    }

    private void boxIt() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, -(Monopoly.HEIGHT/PIXELS_IN_A_METER)/2));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(Monopoly.WIDTH/PIXELS_IN_A_METER, 10/PIXELS_IN_A_METER);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        BodyDef leftWallDef = new BodyDef();
        leftWallDef.position.set(new Vector2(-(Monopoly.WIDTH/PIXELS_IN_A_METER)/2, 0));
        Body leftWallBody = world.createBody(leftWallDef);
        PolygonShape leftWallBox = new PolygonShape();
        leftWallBox.setAsBox(10/PIXELS_IN_A_METER, Monopoly.HEIGHT/PIXELS_IN_A_METER);
        leftWallBody.createFixture(leftWallBox, 0.0f);
        leftWallBox.dispose();

        BodyDef ceilingBodyDef = new BodyDef();
        ceilingBodyDef.position.set(new Vector2(0, (Monopoly.HEIGHT/PIXELS_IN_A_METER)/2));
        Body ceilingBody = world.createBody(ceilingBodyDef);
        PolygonShape ceilingBox = new PolygonShape();
        ceilingBox.setAsBox((Monopoly.WIDTH/PIXELS_IN_A_METER), 10/PIXELS_IN_A_METER);
        ceilingBody.createFixture(ceilingBox, 0.0f);
        ceilingBox.dispose();

        BodyDef rightWallDef = new BodyDef();
        rightWallDef.position.set(new Vector2((Monopoly.WIDTH/PIXELS_IN_A_METER)/2, 0));
        Body rightWallBody = world.createBody(rightWallDef);
        PolygonShape rightWallBox = new PolygonShape();
        rightWallBox.setAsBox(10/PIXELS_IN_A_METER, (Monopoly.HEIGHT/PIXELS_IN_A_METER));
        rightWallBody.createFixture(rightWallBox, 0.0f);
        rightWallBox.dispose();

        //BodyDef groundContainerBodyDef = new BodyDef();
        //groundContainerBodyDef.position.set(new Vector2((Monopoly.WIDTH/PIXELS_IN_A_METER)/2, 0));
        //Body groundContainerBody = world.createBody(groundContainerBodyDef);
        //PolygonShape groundContainerBox = new PolygonShape();
        //groundContainerBox.setAsBox((Monopoly.WIDTH/PIXELS_IN_A_METER)/2, 10/PIXELS_IN_A_METER);
        //groundContainerBody.createFixture(groundContainerBox, 0.0f);
        //groundContainerBox.dispose();

        BodyDef obstacleBodyDef = new BodyDef();
        obstacleBodyDef.position.set(new Vector2(0, (Monopoly.HEIGHT/PIXELS_IN_A_METER)/4));
        Body obstacleBody = world.createBody(obstacleBodyDef);
        CircleShape obstacleCircle = new CircleShape();
        obstacleCircle.setRadius(12/PIXELS_IN_A_METER);
        obstacleBody.createFixture(obstacleCircle, 0.0f);
        obstacleCircle.dispose();
    }

    private boolean checkIt() {
        return bodyDie1.getPosition().y < 0 && bodyDie2.getPosition().y < 0 && bodyDie1.getLinearVelocity().len() + bodyDie1.getLinearVelocity().len() <= 0;
    }

    public Vector2 results() {
        int negativeCorrection1 = (int)sp1.getRotation() + 30;
        int negativeCorrection2 = (int)sp2.getRotation() + 30;
        while(negativeCorrection1 <= 0)
            negativeCorrection1 += 360;
        while(negativeCorrection2 <= 0)
            negativeCorrection2 += 360;

        int die1 = Math.round(((negativeCorrection1/60)%6)+1);
        int die2 = Math.round(((negativeCorrection2/60)%6)+1);
        if (die1 == 4 || die1 == 6)
            die1 = 10 - die1;
        if (die2 == 4 || die2 == 6)
            die2 = 10 - die2;

        return new Vector2(die1, die2);
    }
}
