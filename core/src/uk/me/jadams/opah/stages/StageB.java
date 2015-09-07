package uk.me.jadams.opah.stages;

import com.badlogic.gdx.math.Vector2;

public class StageB implements Stage
{
    private static final Vector2[] VERTICES;
    
    private static final Vector2[] TURRETS;
    
    private float bestTime;
    
    static
    {
        VERTICES = new Vector2[8];
        VERTICES[0] = new Vector2(-450, 150);
        VERTICES[1] = new Vector2(-200, 320);
        VERTICES[2] = new Vector2(0, 320);
        VERTICES[3] = new Vector2(300, 250);
        VERTICES[4] = new Vector2(450, -150);
        VERTICES[5] = new Vector2(200, -320);
        VERTICES[6] = new Vector2(0, -320);
        VERTICES[7] = new Vector2(-300, -250);
        
        TURRETS = new Vector2[2];
        TURRETS[0] = new Vector2(-150, 100);
        TURRETS[1] = new Vector2(150, -100);
    }

    public StageB(float bestTime)
    {
        this.bestTime = bestTime;
    }
    
    @Override
    public Vector2[] getVertices()
    {
        return VERTICES;
    }

    @Override
    public Vector2[] getTurrets()
    {
        return TURRETS;
    }

    @Override
    public float getBestTime()
    {
        return bestTime;
    }

    @Override
    public void setBestTime(float time)
    {
        bestTime = time;
    }
    
}
