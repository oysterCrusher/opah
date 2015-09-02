package uk.me.jadams.opah;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.utils.Array;

public class Particle
{
    private ParticleEffect effect;
    
    private ParticleEffectPool pool;
    
    private Array<PooledEffect> effects;
    
    public Particle(String fileName)
    {
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/" + fileName), Gdx.files.internal("images"));
        
        pool = new ParticleEffectPool(effect, 15, 70);
        
        effects = new Array<PooledEffect>();
    }
    
    public void start(float x, float y)
    {
        PooledEffect effect = pool.obtain();
        effect.setPosition(x, y);
        effects.add(effect);
    }
    
    public void render(SpriteBatch batch, float delta)
    {
        for (PooledEffect e : effects)
        {
            e.draw(batch, delta);
            
            if (e.isComplete())
            {
                effects.removeValue(e, true);
                e.free();
            }
        }
    }
    
    public void dispose()
    {
        effect.dispose();

        for (PooledEffect e : effects)
        {
            e.dispose();
        }
    }
}
