package uk.me.jadams.opah;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.me.jadams.opah.screenmanager.Game;
import uk.me.jadams.opah.screenmanager.Screen;
import uk.me.jadams.opah.screens.GameScreen;
import uk.me.jadams.opah.screens.MenuScreen;

public class Opah extends Game
{
    public Screen gameScreen;

    SpriteBatch batch;

    @Override
    public void create()
    {
        Assets.load();

        batch = new SpriteBatch();

        gameScreen = new GameScreen(batch);
        MenuScreen menuScreen = new MenuScreen(this, batch);
        this.setScreen(menuScreen);
    }

    @Override
    public void render()
    {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        // TODO Auto-generated method stub

    }
}
