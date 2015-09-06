package uk.me.jadams.opah.systems;

import uk.me.jadams.opah.World;
import uk.me.jadams.opah.components.BulletComponent;
import uk.me.jadams.opah.components.KeyboardMovementComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class BulletCollisionSystem extends EntitySystem
{
    private final World world;

    private final ComponentMapper<PositionComponent> posMap;
    
    private final ComponentMapper<SizeComponent> sizeMap;
    
    private ImmutableArray<Entity> bullets;
    
    private ImmutableArray<Entity> players;
    
    private Engine engine;
    
    public BulletCollisionSystem(World  world)
    {
        this.world = world;

        posMap = ComponentMapper.getFor(PositionComponent.class);
        sizeMap = ComponentMapper.getFor(SizeComponent.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;

        bullets = engine.getEntitiesFor(Family.all(BulletComponent.class, PositionComponent.class, SizeComponent.class).get());
        players = engine.getEntitiesFor(Family.all(KeyboardMovementComponent.class, PositionComponent.class, SizeComponent.class).get());
    }
    
    @Override
    public void update(float deltaTime)
    {
        for (int i = 0; i < bullets.size(); i++)
        {
            Entity bullet = bullets.get(i);
            
            PositionComponent bPos = posMap.get(bullet);
            SizeComponent bSize = sizeMap.get(bullet);
            
            for (int j = 0; j < players.size(); j++)
            {
                Entity player = players.get(j);
                
                PositionComponent pPos = posMap.get(player);
                SizeComponent pSize = sizeMap.get(player);
                
                if (overlaps(bPos.x, bPos.y, bSize.r, pPos.x, pPos.y, pSize.r))
                {
                    engine.removeEntity(player);
                    world.gameOver();
                }
            }
        }
    }
    
    private boolean overlaps(float x0, float y0, float r0, float x1, float y1, float r1)
    {
        float dx2 = (x1 - x0) * (x1 - x0);
        float dy2 = (y1 - y0) * (y1 - y0);
        
        return dx2 + dy2 < (r0 + r1) * (r0 + r1);
    }
}
