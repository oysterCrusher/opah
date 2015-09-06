package uk.me.jadams.opah;

import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Boundary implements Iterable<Wall>
{
    private static final int N = 8;

    private static final float W = 8f;

    private static final float TRANSITION_TIME = 1f;

    private final Wall[] walls;

    private final Wall[] transitionFrom;

    private final Wall[] transitionTo;

    private float transitionProgress = 0.0f;

    public Boundary(Vector2[] vertices)
    {
        if (vertices.length != N)
        {
            throw new IllegalArgumentException("Boundary must have eight vertices.");
        }

        walls = new Wall[N];
        transitionFrom = new Wall[N];
        transitionTo = new Wall[N];

        for (int i = 0; i < N - 1; i++)
        {
            walls[i] = new Wall(vertices[i].x, vertices[i].y, vertices[i + 1].x, vertices[i + 1].y, W);
        }

        walls[N - 1] = new Wall(vertices[N - 1].x, vertices[N - 1].y, vertices[0].x, vertices[0].y, W);

        for (int i = 0; i < N; i++)
        {
            transitionFrom[i] = new Wall(0, 0, 0, 0, W);
            transitionTo[i] = new Wall(0, 0, 0, 0, W);
        }
    }

    public void render(SpriteBatch batch, float delta)
    {
        if (transitionProgress < TRANSITION_TIME)
        {
            transitionProgress += delta;

            if (transitionProgress < TRANSITION_TIME)
            {
                for (int i = 0; i < N; i++)
                {
                    float x0 = transitionFrom[i].getX0() + (transitionProgress / TRANSITION_TIME) * (transitionTo[i].getX0() - transitionFrom[i].getX0());
                    float y0 = transitionFrom[i].getY0() + (transitionProgress / TRANSITION_TIME) * (transitionTo[i].getY0() - transitionFrom[i].getY0());
                    float x1 = transitionFrom[i].getX1() + (transitionProgress / TRANSITION_TIME) * (transitionTo[i].getX1() - transitionFrom[i].getX1());
                    float y1 = transitionFrom[i].getY1() + (transitionProgress / TRANSITION_TIME) * (transitionTo[i].getY1() - transitionFrom[i].getY1());
                    walls[i].setVertices(x0, y0, x1, y1);
                }
            }
            else
            {
                for (int i = 0; i < N; i++)
                {
                    walls[i].setVertices(transitionTo[i].getVertices());
                }
            }
        }

        for (Wall wall : walls)
        {
            wall.render(batch);
        }
    }

    public void transitionTo(Vector2[] vertices)
    {
        transitionProgress = 0.0f;

        if (vertices.length != N)
        {
            throw new IllegalArgumentException("Boundary must have eight vertices.");
        }

        for (int i = 0; i < N - 1; i++)
        {
            transitionFrom[i].setVertices(walls[i].getVertices());
            transitionTo[i].setVertices(vertices[i].x, vertices[i].y, vertices[i + 1].x, vertices[i + 1].y);
        }

        transitionFrom[N - 1].setVertices(walls[N - 1].getVertices());
        transitionTo[N - 1].setVertices(vertices[N - 1].x, vertices[N - 1].y, vertices[0].x, vertices[0].y);
    }

    @Override
    public Iterator<Wall> iterator()
    {
        return Arrays.asList(walls).iterator();
    }
}
