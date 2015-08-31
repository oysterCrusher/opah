package uk.me.jadams.opah.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import uk.me.jadams.opah.Boundary;
import uk.me.jadams.opah.Wall;
import uk.me.jadams.opah.components.BoundaryCollisionComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.VelocityComponent;

public class BoundaryCollisionSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<SizeComponent> sizeMap = ComponentMapper.getFor(SizeComponent.class);

    private final ComponentMapper<VelocityComponent> velMap = ComponentMapper.getFor(VelocityComponent.class);
    
    private final ComponentMapper<BoundaryCollisionComponent> bccMap = ComponentMapper.getFor(BoundaryCollisionComponent.class);

    private final Boundary boundary;

    @SuppressWarnings("unchecked")
    public BoundaryCollisionSystem(Boundary boundary)
    {
        super(Family.all(PositionComponent.class, SizeComponent.class, VelocityComponent.class, BoundaryCollisionComponent.class).get());

        this.boundary = boundary;
    }

    @Override
    protected void processEntity(Entity entity, float delta)
    {
        PositionComponent pos = posMap.get(entity);
        SizeComponent size = sizeMap.get(entity);
        VelocityComponent vel = velMap.get(entity);

        for (Wall wall : boundary)
        {
            // If we're about to move outside the boundary.
            if (wall.isAbove(pos.x + vel.x, pos.y + vel.y, size.r))
            {
                // Our new position is reflected in the boundary.
                pos.x = wall.reflectX(pos.x + vel.x, size.r);
                pos.y = wall.reflectY(pos.y + vel.y, size.r);
                
                // Our new velocity is the old velocity reflected in the boundary axis.
                vel.x = wall.reflectVX(vel.x, size.r);
                vel.y = wall.reflectVY(vel.y, size.r);
                
            }
        }
    }
}
