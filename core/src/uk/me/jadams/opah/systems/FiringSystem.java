package uk.me.jadams.opah.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

import uk.me.jadams.opah.EntityFactory;
import uk.me.jadams.opah.components.AutoFireComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;

public class FiringSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<SizeComponent> sizeMap = ComponentMapper.getFor(SizeComponent.class);

    private final ComponentMapper<AutoFireComponent> afiMap = ComponentMapper.getFor(AutoFireComponent.class);
    
    private final PooledEngine engine;

    @SuppressWarnings("unchecked")
    public FiringSystem(PooledEngine engine)
    {
        super(Family.all(PositionComponent.class, SizeComponent.class, AutoFireComponent.class).get());
        
        this.engine = engine;
    }
    
    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = posMap.get(entity);
        SizeComponent size = sizeMap.get(entity);
        AutoFireComponent afi =  afiMap.get(entity);
        
        afi.timer += deltaTime;

        if (afi.timer > afi.interval)
        {
            afi.timer -= afi.interval;

            float x = pos.x + (float) (MathUtils.cosDeg(pos.a) * (size.r * 1.2));
            float y = pos.y + (float) (MathUtils.sinDeg(pos.a) * (size.r * 1.2));

            EntityFactory.bullet(engine, x, y, pos.a);
        }
    }

}
