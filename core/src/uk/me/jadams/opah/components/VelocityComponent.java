package uk.me.jadams.opah.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component
{
    public float x;

    public float y;

    public float max;

    public VelocityComponent(float x, float y, float max)
    {
        this.x = x;
        this.y = y;
        this.max = max;
    }
}
