package uk.me.jadams.opah.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.TargetPlayerComponent;

public class TargetingSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);
    
    private final ComponentMapper<TargetPlayerComponent> targetMap = ComponentMapper.getFor(TargetPlayerComponent.class);
    
    @SuppressWarnings("unchecked")
    public TargetingSystem()
    {
        super(Family.all(PositionComponent.class, TargetPlayerComponent.class).get());
    }
    
    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = posMap.get(entity);
        TargetPlayerComponent target = targetMap.get(entity);
        
        if (target != null)
        {
            PositionComponent targetPos = posMap.get(target.target);
            
            if (targetPos != null)
            {
                Vector2 vec = new Vector2(targetPos.x - pos.x, targetPos.y - pos.y);
                pos.a = vec.angle();
            }
        }
    }

}
