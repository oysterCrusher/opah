package uk.me.jadams.opah.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component
{
    public float x;

    public float y;

    public float a;

    public PositionComponent(float x, float y, float a)
    {
        this.x = x;
        this.y = y;
        this.a = a;
    }
}
