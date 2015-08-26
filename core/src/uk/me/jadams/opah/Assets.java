package uk.me.jadams.opah;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets
{
    public static BitmapFont titleFont;

    private static BitmapFont loadFont(String file)
    {
        return new BitmapFont(Gdx.files.internal(file));
    }
    
    public static void load()
    {
        titleFont = loadFont("fonts/bpdotssquares120.fnt");
    }
}
