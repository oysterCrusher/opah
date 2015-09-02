package uk.me.jadams.opah;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Joint
{
    private final int x;
    private final int y;
    private final int w;
    private final int h;

    private final Texture tex;

    public Joint(float x0, float y0, float x1, float y1, float x2, float y2)
    {
        x = (int) Math.min(x0, Math.min(x1, x2));
        y = (int) Math.min(y0, Math.min(y1, y2));

        x0 -= x;
        y0 -= y;
        x1 -= x;
        y1 -= y;
        x2 -= x;
        y2 -= y;

        w = (int) Math.max(x0, Math.max(x1, x2)) + 1;
        h = (int) Math.max(y0, Math.max(y1, y2)) + 1;

        Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
        pixmap.setColor(Color.valueOf("CCCCCC"));
//        pixmap.setColor(Color.GREEN);

        pixmap.fillTriangle((int) x0, (int) (h - y0), (int) x1, (int) (h - y1), (int) x2, (int) (h - y2));

        tex = new Texture(pixmap);
        
        pixmap.dispose();
    }
    
    public Joint(Vector2 v0, Vector2 v1, Vector2 v2)
    {
        this(v0.x, v0.y, v1.x, v1.y, v2.x, v2.y);
    }
    
    public void render(SpriteBatch batch)
    {
        batch.draw(tex, x, y, w, h);
    }
}
