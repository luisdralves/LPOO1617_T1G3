package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo1617t1g3.Monopoly;

public class DiceScene {
    private OrthographicCamera cam;
    private Sprite sprDie1, sprDie2, sprDie3;
    private Texture img;
    private World world;
    private Body bodyDie1, bodyDie2, bodyDie3;
    private Body bodyEdgeScreen;

    private float torque;

    public DiceScene() {
        img = new Texture("die.png");
        sprDie1 = new Sprite(img);
        sprDie2 = new Sprite(img);
        sprDie3 = new Sprite(img);

        sprDie1.setPosition(Monopoly.WIDTH / 4 - img.getWidth() / 2, Monopoly.HEIGHT - img.getHeight());
        sprDie2.setPosition(Monopoly.WIDTH / 2 - img.getWidth() / 2, Monopoly.HEIGHT - img.getHeight());
        sprDie3.setPosition(3 * Monopoly.WIDTH / 4 - img.getWidth() / 2, Monopoly.HEIGHT - img.getHeight());

        world = new World(new Vector2(0, -9.8f), true);


        BodyDef bodyDefDie1 = new BodyDef();
        bodyDefDie1.type = BodyDef.BodyType.DynamicBody;
        BodyDef bodyDefDie2 = new BodyDef();
        bodyDefDie2.type = BodyDef.BodyType.DynamicBody;
        BodyDef bodyDefDie3 = new BodyDef();
        bodyDefDie3.type = BodyDef.BodyType.DynamicBody;
        bodyDefDie1.position.set(sprDie1.getX(), sprDie1.getY());
        bodyDefDie2.position.set(sprDie2.getX(), sprDie2.getY());
        bodyDefDie3.position.set(sprDie3.getX(), sprDie3.getY());

        bodyDie1 = world.createBody(bodyDefDie1);
        bodyDie3 = world.createBody(bodyDefDie2);
        bodyDie2 = world.createBody(bodyDefDie3);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprDie1.getWidth()/2, sprDie1.getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;

        bodyDie1.createFixture(fixtureDef);
        bodyDie2.createFixture(fixtureDef);
        bodyDie3.createFixture(fixtureDef);
        shape.dispose();


        BodyDef worldLimit = new BodyDef();
        worldLimit.type = BodyDef.BodyType.StaticBody;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight()- 50;
        worldLimit.position.set(0,0);
        FixtureDef fixtureDefWorldLimit = new FixtureDef();

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-w/2,-h/2,w/2,-h/2);
        fixtureDefWorldLimit.shape = edgeShape;

        bodyEdgeScreen = world.createBody(worldLimit);
        bodyEdgeScreen.createFixture(fixtureDefWorldLimit);
        edgeShape.dispose();

        //Gdx.input.setInputProcessor(this);

        cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                getHeight());

        torque = 0.0f;
    }

    public void render(SpriteBatch spb) {
        cam.update();
        world.step(1/60f, 6, 2);
        world.setGravity(new Vector2(100 * Gdx.input.getAccelerometerY(), -100 * Gdx.input.getAccelerometerX()));

        bodyDie1.applyTorque(torque,true);
        bodyDie2.applyTorque(torque,true);
        bodyDie3.applyTorque(torque,true);
        //if (bodyDie1.getPosition().x <= 0)


        sprDie1.setPosition(bodyDie1.getPosition().x, bodyDie1.getPosition().y);
        sprDie1.setRotation((float)Math.toDegrees(bodyDie1.getAngle()));
        sprDie2.setPosition(bodyDie2.getPosition().x, bodyDie2.getPosition().y);
        sprDie2.setRotation((float)Math.toDegrees(bodyDie2.getAngle()));
        sprDie3.setPosition(bodyDie3.getPosition().x, bodyDie3.getPosition().y);
        sprDie3.setRotation((float)Math.toDegrees(bodyDie3.getAngle()));

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spb.setProjectionMatrix(cam.combined);
        spb.begin();
        spb.draw(sprDie1, sprDie1.getX(), sprDie1.getY());
        spb.draw(sprDie2, sprDie2.getX(), sprDie2.getY());
        spb.draw(sprDie3, sprDie3.getX(), sprDie3.getY());
        spb.end();
    }
}
