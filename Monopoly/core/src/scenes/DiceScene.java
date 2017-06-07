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
        cam = new OrthographicCamera(Monopoly.WIDTH, Monopoly.HEIGHT);
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
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

        System.out.println((((sp1.getRotation()/60)%6)+1) + ", \t" + (((sp2.getRotation()/60)%6)+1));

        if (Gdx.input.getAccelerometerY() != 0.0f || Gdx.input.getAccelerometerX() != 0.0f)
            world.setGravity(new Vector2(Gdx.input.getAccelerometerY(), -Gdx.input.getAccelerometerX()));

        sp1.rotate(-sp1.getRotation());
        sp1.rotate(offsetAlpha1);
        sp1.rotate(180 * bodyDie1.getAngle() / (float)Math.PI);
        sp1.setPosition(bodyDie1.getPosition().x + (Monopoly.WIDTH - sp1.getWidth())/2, bodyDie1.getPosition().y + (Monopoly.HEIGHT - sp1.getHeight())/2);

        sp2.rotate(-sp2.getRotation());
        sp2.rotate(offsetAlpha2);
        sp2.rotate(180 * bodyDie2.getAngle() / (float)Math.PI);
        sp2.setPosition(bodyDie2.getPosition().x + (Monopoly.WIDTH - sp2.getWidth())/2, bodyDie2.getPosition().y + (Monopoly.HEIGHT - sp2.getHeight())/2);

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
        offsetX = (int)(Math.random() * (Monopoly.WIDTH/2 - 150) + 75);
        offsetY = (int)(Math.random() * (Monopoly.HEIGHT/2 - 150) + 75);

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef1.position.set(offsetX, offsetY);
        bodyDie1 = world.createBody(bodyDef1);

        offsetX = (int)(Math.random() * (Monopoly.WIDTH/2 - 150) + 75);
        offsetY = (int)(Math.random() * (Monopoly.HEIGHT/2 - 150) + 75);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(offsetX, offsetY);
        bodyDie2 = world.createBody(bodyDef2);

        offsetAlpha1 = (float)(Math.random() * 2 * Math.PI);
        offsetAlpha2 = (float)(Math.random() * 2 * Math.PI);

        PolygonShape hexagon = new PolygonShape();
        Vector2[] vertices = new Vector2[6];

        for(int i = 0; i < 6; i++) {
            vertices[i] = new Vector2((float)(64*Math.cos(i * Math.PI/3 + offsetAlpha1)),
                    (float)(64*Math.sin(i * Math.PI/3 + offsetAlpha1)));
        }
        offsetAlpha1 = (float)(offsetAlpha1 * 180/Math.PI);
        hexagon.set(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hexagon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f;

        bodyDie1.createFixture(fixtureDef);

        for(int i = 0; i < 6; i++) {
            vertices[i] = new Vector2((float)(64*Math.cos(i * Math.PI/3 + offsetAlpha2)),
                    (float)(64*Math.sin(i * Math.PI/3 + offsetAlpha2)));
        }
        offsetAlpha2 = (float)(offsetAlpha2 * 180/Math.PI);
        hexagon.set(vertices);
        fixtureDef.shape = hexagon;

        bodyDie2.createFixture(fixtureDef);
    }

    private void boxIt() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, -Monopoly.HEIGHT/2));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(Monopoly.WIDTH, 10);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        BodyDef leftWallDef = new BodyDef();
        leftWallDef.position.set(new Vector2(-Monopoly.WIDTH/2, 0));
        Body leftWallBody = world.createBody(leftWallDef);
        PolygonShape leftWallBox = new PolygonShape();
        leftWallBox.setAsBox(10, Monopoly.HEIGHT);
        leftWallBody.createFixture(leftWallBox, 0.0f);
        leftWallBox.dispose();

        BodyDef ceilingBodyDef = new BodyDef();
        ceilingBodyDef.position.set(new Vector2(0, Monopoly.HEIGHT/2));
        Body ceilingBody = world.createBody(ceilingBodyDef);
        PolygonShape ceilingBox = new PolygonShape();
        ceilingBox.setAsBox(Monopoly.WIDTH, 10);
        ceilingBody.createFixture(ceilingBox, 0.0f);
        ceilingBox.dispose();

        BodyDef rightWallDef = new BodyDef();
        rightWallDef.position.set(new Vector2(Monopoly.WIDTH/2, 0));
        Body rightWallBody = world.createBody(rightWallDef);
        PolygonShape rightWallBox = new PolygonShape();
        rightWallBox.setAsBox(10, Monopoly.HEIGHT);
        rightWallBody.createFixture(rightWallBox, 0.0f);
        rightWallBox.dispose();

        BodyDef groundContainerBodyDef = new BodyDef();
        groundContainerBodyDef.position.set(new Vector2(Monopoly.WIDTH/2, 0));
        Body groundContainerBody = world.createBody(groundContainerBodyDef);
        PolygonShape groundContainerBox = new PolygonShape();
        groundContainerBox.setAsBox(Monopoly.WIDTH/2, 10);
        groundContainerBody.createFixture(groundContainerBox, 0.0f);
        groundContainerBox.dispose();

        BodyDef obstacleBodyDef = new BodyDef();
        obstacleBodyDef.position.set(new Vector2(0, Monopoly.HEIGHT/4));
        Body obstacleBody = world.createBody(obstacleBodyDef);
        CircleShape obstacleCircle = new CircleShape();
        obstacleCircle.setRadius(12);
        obstacleBody.createFixture(obstacleCircle, 0.0f);
        obstacleCircle.dispose();
    }

    private boolean checkIt() {
        if (bodyDie1.getPosition().y < 100 && bodyDie2.getPosition().y < 100) {
            if (bodyDie1.getLinearVelocity().len() + bodyDie1.getLinearVelocity().len() < 2) {
                return true;
            }
        }
        return false;
    }

    public Vector2 results() {
        return new Vector2(Math.round(((sp1.getRotation()/60)%6)+1),
                Math.round(((sp2.getRotation()/60)%6)+1));
    }
}
