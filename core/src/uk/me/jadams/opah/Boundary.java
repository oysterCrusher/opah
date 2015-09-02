package uk.me.jadams.opah;

import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Boundary implements Iterable<Wall>
{
    private static final float W = 8f;

    private final Wall[] walls;

    private final Joint[] joints;

    public Boundary(Vector2[] vertices)
    {
        walls = new Wall[vertices.length];

        for (int i = 0; i < vertices.length - 1; i++)
        {
            walls[i] = new Wall(vertices[i].x, vertices[i].y, vertices[i + 1].x, vertices[i + 1].y, W);
        }

        walls[vertices.length - 1] = new Wall(vertices[vertices.length - 1].x, vertices[vertices.length - 1].y, vertices[0].x, vertices[0].y, W);

        joints = new Joint[vertices.length];

        joints[0] = new Joint(vertices[0], walls[0].getOuterPoint1(), walls[vertices.length - 1].getOuterPoint0());

        for (int i = 1; i < vertices.length; i++)
        {
            joints[i] = new Joint(vertices[i], walls[i].getOuterPoint1(), walls[i - 1].getOuterPoint0());
        }
    }

    public void render(SpriteBatch batch)
    {
        for (Joint joint : joints)
        {
            if (joint != null)
            {
                joint.render(batch);
            }
        }

        for (Wall wall : walls)
        {
            wall.render(batch);
        }
    }

    @Override
    public Iterator<Wall> iterator()
    {
        return Arrays.asList(walls).iterator();
    }
}
