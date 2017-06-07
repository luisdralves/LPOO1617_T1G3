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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

public class DiceScene {
    private OrthographicCamera cam;
    private Viewport vp;
    private World world;
    private Texture die1;
    private Sprite sp1;
    private Body body;

    private Box2DDebugRenderer debugRenderer;

    public DiceScene() {
        world = new World(new Vector2(0, -9.8f), true);
        cam = new OrthographicCamera(Monopoly.WIDTH, Monopoly.HEIGHT);
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        die1 = new Texture("die.png");
        sp1 = new Sprite(die1);

        debugRenderer = new Box2DDebugRenderer();

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(0, 0);

        // Create our body in the world using our body definition
        body = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        PolygonShape hexagon = new PolygonShape();
        Vector2[] vertices = new Vector2[6];
        vertices[0] = new Vector2(32f,-55f);
        vertices[1] = new Vector2(-32f,-55f);
        vertices[2] = new Vector2(-64f,0f);
        vertices[3] = new Vector2(-32f,55f);
        vertices[4] = new Vector2(32f,55f);
        vertices[5] = new Vector2(64f,0f);
        hexagon.set(vertices);
        CircleShape circle = new CircleShape();
        circle.setRadius(sp1.getHeight()/2);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hexagon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        boxIt();
    }

    public void render(SpriteBatch spb) {
        debugRenderer.render(world, cam.combined);
        world.setGravity(new Vector2(Gdx.input.getAccelerometerY(), -Gdx.input.getAccelerometerX()));
        sp1.rotate(-sp1.getRotation());
        sp1.rotate(180 * body.getAngle() / (float)Math.PI);
        sp1.setPosition(body.getPosition().x + (Monopoly.WIDTH - sp1.getWidth())/2, body.getPosition().y + (Monopoly.HEIGHT - sp1.getHeight())/2);
        spb.begin();
        sp1.draw(spb);
        spb.end();
        world.step(1/60f, 6, 2);
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
    }
}
