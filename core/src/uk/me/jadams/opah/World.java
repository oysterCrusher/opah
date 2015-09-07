package uk.me.jadams.opah;

import uk.me.jadams.opah.stages.Stage;
import uk.me.jadams.opah.stages.StageA;
import uk.me.jadams.opah.stages.StageB;
import uk.me.jadams.opah.systems.BoundaryCollisionSystem;
import uk.me.jadams.opah.systems.BulletCollisionSystem;
import uk.me.jadams.opah.systems.FiringSystem;
import uk.me.jadams.opah.systems.InputSystem;
import uk.me.jadams.opah.systems.MovementSystem;
import uk.me.jadams.opah.systems.RenderSystem;
import uk.me.jadams.opah.systems.TargetingSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World
{
    private final Stage stageA;

    private final Stage stageB;

    private Stage currentStage;

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final PooledEngine engine;

    private final Boundary boundary;

    private final ParticleEffects particleEffects;

    private final Texture bg;

    private final Timer timer;

    private Entity player;

    private final Array<Entity> turrets;

    private boolean complete;

    public World(OrthographicCamera camera, SpriteBatch batch)
    {
        this.batch = batch;
        this.camera = camera;

        bg = Assets.bg;
        bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

        particleEffects = new ParticleEffects();

        engine = new PooledEngine();

        stageA = new StageA(0);
        stageB = new StageB(0);
        currentStage = stageA;

        boundary = new Boundary(currentStage.getVertices());
        stageA();

        // Add the systems.
        engine.addSystem(new InputSystem(camera));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundaryCollisionSystem(boundary, particleEffects));
        engine.addSystem(new TargetingSystem());
        engine.addSystem(new BulletCollisionSystem(this));
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new FiringSystem(engine));

        timer = new Timer();

        turrets = new Array<Entity>();
    }

    public void render(float delta)
    {
        batch.begin();

        batch.draw(bg, -camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth,
                camera.viewportHeight, 0, 0, 1, 720 / 32);

        particleEffects.render(batch, delta);
        boundary.render(batch, delta);
        engine.update(delta);

        timer.update(delta);

        batch.end();
    }

    public void reset()
    {
        engine.removeAllEntities();

        // Create the player entity.
        player = EntityFactory.player(engine, 0, 0);

        // Create the turrets.
        turrets.clear();
        for (Vector2 turret : currentStage.getTurrets())
        {
            turrets.add(EntityFactory.turret(engine, turret.x, turret.y, player));
        }

        timer.start();
        complete = false;
    }

    public void stageA()
    {
        currentStage = stageA;
        boundary.transitionTo(currentStage.getVertices());
    }

    public void stageB()
    {
        currentStage = stageB;
        boundary.transitionTo(currentStage.getVertices());
    }

    public void gameOver()
    {
        for (Entity turret : turrets)
        {
            engine.removeEntity(turret);
        }

        timer.stop();
        complete = true;
    }

    public boolean isComplete()
    {
        return complete;
    }
}
