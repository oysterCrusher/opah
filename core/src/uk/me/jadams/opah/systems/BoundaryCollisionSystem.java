package uk.me.jadams.opah.systems;

import uk.me.jadams.opah.Boundary;
import uk.me.jadams.opah.ParticleEffects;
import uk.me.jadams.opah.Wall;
import uk.me.jadams.opah.components.BoundaryCollisionComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.VelocityComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

public class BoundaryCollisionSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<SizeComponent> sizeMap = ComponentMapper.getFor(SizeComponent.class);

    private final ComponentMapper<VelocityComponent> velMap = ComponentMapper.getFor(VelocityComponent.class);
    
    private final ComponentMapper<BoundaryCollisionComponent> bcMap = ComponentMapper.getFor(BoundaryCollisionComponent.class);

    private final Boundary boundary;
    
    private final ParticleEffects effects;

    @SuppressWarnings("unchecked")
    public BoundaryCollisionSystem(Boundary boundary, ParticleEffects effects)
    {
        super(Family.all(PositionComponent.class, SizeComponent.class, VelocityComponent.class, BoundaryCollisionComponent.class).get());

        this.boundary = boundary;
        this.effects = effects;
    }

    @Override
    protected void processEntity(Entity entity, float delta)
    {
        PositionComponent pos = posMap.get(entity);
        SizeComponent size = sizeMap.get(entity);
        VelocityComponent vel = velMap.get(entity);
        BoundaryCollisionComponent bc = bcMap.get(entity);

        for (Wall wall : boundary)
        {
            // If we're about to move outside the boundary.
            if (wall.isOutside(pos.x, pos.y, size.r))
            {
                if (bc.bounce)
                {
                    // Everyone loves particle effects.
                    effects.bulletBounce(pos.x, pos.y, vel.x, vel.y);
                    
                    // Our new position is reflected in the boundary.
                    Vector2 reflection = wall.reflect(pos.x, pos.y, size.r);
                    pos.x = reflection.x;
                    pos.y = reflection.y;
                    
                    // Our new velocity is the old velocity reflected in the boundary axis.
                    reflection = wall.reflectV(vel.x, vel.y, bc.bounce);
                    vel.x = reflection.x;
                    vel.y = reflection.y;
                }
                else
                {
                    // Our new position is us pushed back to just within the boundary.
                    Vector2 position = wall.enclose(pos.x, pos.y, size.r);
                    pos.x = position.x;
                    pos.y = position.y;
                    
                    // TODO - Restrict velocity normal to the boundary? Essentially zero restitution? 
                    vel.x = 0;
                    vel.y = 0;
                }
            }
        }
    }
}
