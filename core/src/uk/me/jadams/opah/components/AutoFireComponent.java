package uk.me.jadams.opah.components;

import com.badlogic.ashley.core.Component;

public class AutoFireComponent implements Component
{
    public float interval;
    
    public float timer;
    
    public AutoFireComponent(float interval)
    {
        this.interval = interval;
        this.timer = 0;
    }
}
