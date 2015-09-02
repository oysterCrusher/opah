package uk.me.jadams.opah.components;

import com.badlogic.ashley.core.Component;

public class BoundaryCollisionComponent implements Component
{
    public boolean bounce;
    
    public BoundaryCollisionComponent(boolean bounce)
    {
        this.bounce = bounce;
    }
}
