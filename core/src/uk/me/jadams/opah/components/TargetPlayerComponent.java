package uk.me.jadams.opah.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class TargetPlayerComponent implements Component
{
    public Entity target;
    
    public TargetPlayerComponent(Entity target)
    {
        this.target = target;
    }
}
