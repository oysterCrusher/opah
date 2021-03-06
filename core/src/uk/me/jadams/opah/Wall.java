package uk.me.jadams.opah;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Wall
{
    private final float w;
    private float x0;
    private float y0;
    private float x1;
    private float y1;
    private Vector2 nor; // Unit vector normal to boundary, angled inwards.
    private Vector2 v; // Unit vector along boundary.

    float angle;

    public Wall(float x0, float y0, float x1, float y1, float w)
    {
        this.w = w;

        setVertices(x0, y0, x1, y1);
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

    public float getX0()
    {
        return x0;
    }

    public float getY0()
    {
        return y0;
    }

    public float getX1()
    {
        return x1;
    }

    public float getY1()
    {
        return y1;
    }

    public float[] getVertices()
    {
        return new float[]
        { x0, y0, x1, y1 };
    }

    public void setVertices(float[] vertices)
    {
        this.x0 = vertices[0];
        this.y0 = vertices[1];
        this.x1 = vertices[2];
        this.y1 = vertices[3];
    }

    public void setVertices(float x0, float y0, float x1, float y1)
    {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;

        Vector2 a = new Vector2(x1 - x0, y1 - y0);
        angle = a.angle();

        nor = new Vector2(x1 - x0, y1 - y0).rotate90(1).nor();

        v = new Vector2(x1 - x0, y1 - y0).nor();
    }

    public boolean isOutside(float x, float y, float r)
    {
        float xr0 = x0 - nor.x * r;
        float yr0 = y0 - nor.y * r;
        float xr1 = x1 - nor.x * r;
        float yr1 = y1 - nor.y * r;

        return ((xr1 - xr0) * (y - yr0) - (yr1 - yr0) * (x - xr0)) > r;
    }

    public Vector2 enclose(float x, float y, float r)
    {
        float xr0 = x0 - nor.x * r;
        float yr0 = y0 - nor.y * r;

        Vector2 result = new Vector2();

        float A = (x - xr0) * v.x + (y - yr0) * v.y;

        result.x = xr0 + A * v.x;
        result.y = yr0 + A * v.y;

        return result;
    }

    public Vector2 reflect(float x, float y, float r)
    {
        float xr0 = x0 - nor.x * r;
        float yr0 = y0 - nor.y * r;
        float xr1 = x1 - nor.x * r;
        float yr1 = y1 - nor.y * r;

        float dx = xr1 - xr0;
        float dy = yr1 - yr0;

        float a = (dx * dx - dy * dy) / (dx * dx + dy * dy);
        float b = 2 * dx * dy / (dx * dx + dy * dy);

        Vector2 result = new Vector2();

        result.x = xr0 + a * (x - xr0) + b * (y - yr0);
        result.y = yr0 + b * (x - xr0) - a * (y - yr0);

        return result;
    }

    public Vector2 reflectV(float vx, float vy, boolean bounce)
    {
        Vector2 result = new Vector2();

        float a = Vector2.dot(vx, vy, nor.x, nor.y);

        result.x = vx - 2 * a * nor.x;
        result.y = vy - 2 * a * nor.y;

        return result;
    }
}
