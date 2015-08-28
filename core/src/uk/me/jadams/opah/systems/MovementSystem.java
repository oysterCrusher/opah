package uk.me.jadams.opah.systems;

import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.VelocityComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class MovementSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<VelocityComponent> velMap = ComponentMapper.getFor(VelocityComponent.class);

    @SuppressWarnings("unchecked")
    public MovementSystem()
    {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = posMap.get(entity);
        VelocityComponent vel = velMap.get(entity);

        // TODO - Cap the velocities here?

        // Update positions from velocities.
        pos.x += vel.x * deltaTime;
        pos.y += vel.y * deltaTime;
    }

}
