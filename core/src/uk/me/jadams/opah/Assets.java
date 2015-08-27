package uk.me.jadams.opah;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets
{
    public static Texture player;
    public static Texture bg;

    public static BitmapFont titleFont;

    private static Texture loadTexture(String file)
    {
        return new Texture(Gdx.files.internal(file));
    }

    private static BitmapFont loadFont(String file)
    {
        return new BitmapFont(Gdx.files.internal(file));
    }

    public static void load()
    {
        player = loadTexture("images/player.png");
        bg = loadTexture("images/bg.png");
        titleFont = loadFont("fonts/bpdotssquares120.fnt");
    }
}
