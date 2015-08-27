package uk.me.jadams.opah.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.TextureComponent;

public class RenderSystem extends IteratingSystem
{
    private final ComponentMapper<PositionComponent> posMap = ComponentMapper.getFor(PositionComponent.class);

    private final ComponentMapper<SizeComponent> sizeMap = ComponentMapper.getFor(SizeComponent.class);

    private final ComponentMapper<TextureComponent> texMap = ComponentMapper.getFor(TextureComponent.class);

    private final SpriteBatch batch;

    @SuppressWarnings("unchecked")
    public RenderSystem(SpriteBatch batch)
    {
        super(Family.all(PositionComponent.class, SizeComponent.class, TextureComponent.class).get());

        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = posMap.get(entity);
        SizeComponent size = sizeMap.get(entity);
        TextureComponent tex = texMap.get(entity);

        batch.draw(tex.texture, pos.x - size.r, pos.y - size.r, 2 * size.r, 2 * size.r);
    }

}
