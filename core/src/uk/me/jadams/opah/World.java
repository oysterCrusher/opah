package uk.me.jadams.opah;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import uk.me.jadams.opah.components.AutoFireComponent;
import uk.me.jadams.opah.components.BoundaryCollisionComponent;
import uk.me.jadams.opah.components.KeyboardMovementComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.TargetPlayerComponent;
import uk.me.jadams.opah.components.TextureComponent;
import uk.me.jadams.opah.components.VelocityComponent;
import uk.me.jadams.opah.systems.BoundaryCollisionSystem;
import uk.me.jadams.opah.systems.BulletCollisionSystem;
import uk.me.jadams.opah.systems.FiringSystem;
import uk.me.jadams.opah.systems.InputSystem;
import uk.me.jadams.opah.systems.MovementSystem;
import uk.me.jadams.opah.systems.RenderSystem;
import uk.me.jadams.opah.systems.TargetingSystem;

public class World
{
    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final PooledEngine engine;

    private final Boundary boundary;

    private final ParticleEffects particleEffects;

    private final Texture bg;

    private Entity player;

    private Entity turret;

    private boolean complete;

    public World(OrthographicCamera camera, SpriteBatch batch)
    {
        this.batch = batch;
        this.camera = camera;

        bg = Assets.bg;
        bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

        particleEffects = new ParticleEffects();

        engine = new PooledEngine();

        // Create the boundary walls.
        Vector2[] vertices = new Vector2[8];
        vertices[0] = new Vector2(-400, 0);
        vertices[1] = new Vector2(-300, 250);
        vertices[2] = new Vector2(0, 320);
        vertices[3] = new Vector2(300, 250);
        vertices[4] = new Vector2(400, 0);
        vertices[5] = new Vector2(300, -250);
        vertices[6] = new Vector2(0, -320);
        vertices[7] = new Vector2(-300, -250);
        boundary = new Boundary(vertices);
        moveBoundaries(vertices);

        // Add the systems.
        engine.addSystem(new InputSystem(camera));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundaryCollisionSystem(boundary, particleEffects));
        engine.addSystem(new TargetingSystem());
        engine.addSystem(new BulletCollisionSystem(this));
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new FiringSystem(engine));
    }

    public void render(float delta)
    {
        batch.begin();

        batch.draw(bg, -camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight, 0, 0, 1, 720 / 32);

        particleEffects.render(batch, delta);
        boundary.render(batch, delta);
        engine.update(delta);

        batch.end();
    }

    public void reset()
    {
        engine.removeAllEntities();

        // Create the player entity.
        player = engine.createEntity();
        player.add(new PositionComponent(0, 0, 0));
        player.add(new VelocityComponent(0, 0, 50));
        player.add(new SizeComponent(12));
        player.add(new KeyboardMovementComponent());
        player.add(new TextureComponent(Assets.player));
        player.add(new BoundaryCollisionComponent(false));
        engine.addEntity(player);

        // Create a turret.
        turret = engine.createEntity();
        turret.add(new PositionComponent(-100f, 0, 0));
        turret.add(new SizeComponent(16f));
        turret.add(new TextureComponent(Assets.turret));
        turret.add(new AutoFireComponent(0.4f));
        turret.add(new TargetPlayerComponent(player));
        engine.addEntity(turret);

        complete = false;
    }

    public void moveBoundaries(Vector2[] vertices)
    {
        boundary.transitionTo(vertices);
    }

    public void gameOver()
    {
        engine.removeEntity(turret);
        complete = true;
    }

    public boolean isComplete()
    {
        return complete;
    }
}
