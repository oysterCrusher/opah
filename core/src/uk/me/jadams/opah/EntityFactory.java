package uk.me.jadams.opah;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;

import uk.me.jadams.opah.components.BoundaryCollisionComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.TextureComponent;
import uk.me.jadams.opah.components.VelocityComponent;

public class EntityFactory
{
    private static final float BULLET_SPEED = 250f;

    public static Entity bullet(PooledEngine engine, float x, float y, float a)
    {

        float vx = (float) MathUtils.cosDeg(a) * BULLET_SPEED;
        float vy = (float) MathUtils.sinDeg(a) * BULLET_SPEED;
        
        Entity bullet = engine.createEntity();
        bullet.add(new PositionComponent(x, y, 0));
        bullet.add(new SizeComponent(4));
        bullet.add(new TextureComponent(Assets.bullet));
        bullet.add(new VelocityComponent(vx, vy, BULLET_SPEED));
        bullet.add(new BoundaryCollisionComponent(true));
        engine.addEntity(bullet);
        return bullet;
    }
}
