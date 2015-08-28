package uk.me.jadams.opah.systems;

import uk.me.jadams.opah.components.KeyboardMovementComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.VelocityComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<VelocityComponent> velMap = ComponentMapper.getFor(VelocityComponent.class);

    private final ComponentMapper<KeyboardMovementComponent> kmcMap =
            ComponentMapper.getFor(KeyboardMovementComponent.class);

    private final OrthographicCamera camera;

    private final Vector3 vec3;

    private final Vector2 vec2;

    @SuppressWarnings("unchecked")
    public InputSystem(OrthographicCamera camera)
    {
        super(Family.all(PositionComponent.class, VelocityComponent.class, KeyboardMovementComponent.class).get());

        this.camera = camera;

        vec3 = new Vector3();
        vec2 = new Vector2();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = posMap.get(entity);
        VelocityComponent vel = velMap.get(entity);
        KeyboardMovementComponent kmc = kmcMap.get(entity);

        // Get the mouse coordinates unprojected into game space.
        vec3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(vec3);

        // Get the normalised relative position of the mouse coordinates to the entity.
        vec3.sub(pos.x, pos.y, 0);
        vec3.nor();

        // Set the entities angle to the angle of this relative vector.
        vec2.set(vec3.x, vec3.y);
        pos.a = vec2.angle();

        int vx = 0;
        int vy = 0;

        // Move up/down with w/s keys.
        if (Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S))
        {
            vy = 1;
        }
        else if (Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W))
        {
            vy = -1;
        }

        // Move left/right with a/d keys.
        if (Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D))
        {
            vx = -1;
        }
        else if (Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A))
        {
            vx = 1;
        }

        // Scale the velocity to this entities movement speed.
        vec2.set(vx, vy);
        vec2.setLength(kmc.speed);

        // Set the entities velocity.
        vel.x = vec2.x;
        vel.y = vec2.y;
    }

}
