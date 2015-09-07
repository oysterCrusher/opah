package uk.me.jadams.opah;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;

import uk.me.jadams.opah.components.AutoFireComponent;
import uk.me.jadams.opah.components.BoundaryCollisionComponent;
import uk.me.jadams.opah.components.BulletComponent;
import uk.me.jadams.opah.components.KeyboardMovementComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.TargetPlayerComponent;
import uk.me.jadams.opah.components.TextureComponent;
import uk.me.jadams.opah.components.VelocityComponent;

public class EntityFactory
{
    private static final float BULLET_SPEED = 250f;

    public static Entity player(PooledEngine engine, float x, float y)
    {
        Entity player = engine.createEntity();
        player.add(new PositionComponent(x, y, 0));
        player.add(new VelocityComponent(0, 0, 50));
        player.add(new SizeComponent(12));
        player.add(new KeyboardMovementComponent());
        player.add(new TextureComponent(Assets.player));
        player.add(new BoundaryCollisionComponent(false));
        engine.addEntity(player);
        return player;
    }

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
        bullet.add(new BulletComponent());
        engine.addEntity(bullet);
        return bullet;
    }

    public static Entity turret(PooledEngine engine, float x, float y, Entity target)
    {
        Entity turret = engine.createEntity();
        turret.add(new PositionComponent(x, y, 0));
        turret.add(new SizeComponent(16f));
        turret.add(new TextureComponent(Assets.turret));
        turret.add(new AutoFireComponent(0.8f));
        turret.add(new TargetPlayerComponent(target));
        engine.addEntity(turret);
        return turret;
    }
}
