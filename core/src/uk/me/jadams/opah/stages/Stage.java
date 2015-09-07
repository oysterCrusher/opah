package uk.me.jadams.opah.stages;

import com.badlogic.gdx.math.Vector2;

public interface Stage
{
    public Vector2[] getVertices();
    
    public Vector2[] getTurrets();
    
    public float getBestTime();
    
    public void setBestTime(float time);
}
