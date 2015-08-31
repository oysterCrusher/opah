package uk.me.jadams.opah;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Wall
{
    private final float w;
    private final float x0;
    private final float y0;
    private final float x1;
    private final float y1;

    float angle;

    public Wall(float x0, float y0, float x1, float y1, float w)
    {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.w = w;

        Vector2 a = new Vector2(x1 - x0, y1 - y0);
        angle = a.angle();
    }
    
    public Vector2 getOuterPoint0()
    {
        float x = x1 + w * MathUtils.cosDeg(angle + 90);
        float y = y1 + w * MathUtils.sinDeg(angle + 90);
        return new Vector2(x, y);
    }

    public Vector2 getOuterPoint1()
    {
        float x = x0 + w * MathUtils.cosDeg(angle + 90);
        float y = y0 + w * MathUtils.sinDeg(angle + 90);
        return new Vector2(x, y);
    }

    public void render(SpriteBatch batch)
    {
        float mx = Math.abs(x1 - x0);
        float my = Math.abs(y1 - y0);

        float l = (float) Math.sqrt(mx * mx + my * my);

        batch.draw(Assets.player, x0, y0, 0, 0, l, w, 1, 1, angle, 30, 30, 1, 1, false, false);
    }
    
    public boolean isAbove(float x, float y, float r)
    {
        return ((x1 - x0) * (y - y0) - (y1 - y0) * (x - x0)) > r;
    }
}
