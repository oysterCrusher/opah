package uk.me.jadams.opah;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticleEffects
{
    private Particle bullet_bounce;
    
    public ParticleEffects()
    {
        bullet_bounce = new Particle("bullet_bounce.p");
    }
    
    public void bulletBounce(float x, float y, float vx, float vy)
    {
        bullet_bounce.start(x, y, vx, vy);
    }
    
    public void render(SpriteBatch batch, float delta)
    {
        bullet_bounce.render(batch, delta);
    }
    
    public void dispose()
    {
        bullet_bounce.dispose();
    }
}
