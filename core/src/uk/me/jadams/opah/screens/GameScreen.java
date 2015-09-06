package uk.me.jadams.opah.screens;

import uk.me.jadams.opah.Opah;
import uk.me.jadams.opah.World;
import uk.me.jadams.opah.screenmanager.Screen;

public class GameScreen implements Screen
{
    private final Opah opah;

    private final World world;

    public GameScreen(Opah opah, World world)
    {
        this.opah = opah;
        this.world = world;
    }

    @Override
    public void show()
    {
        world.reset();
    }

    @Override
    public void render(float delta)
    {
        if (world.isComplete())
        {
            opah.setScreen(opah.menuScreen);
        }
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public float getEnterTime()
    {
        return 0;
    }

    @Override
    public float getExitTime()
    {
        return 3;
    }

    @Override
    public void renderEnter(float delta, float progress)
    {

    }

    @Override
    public void renderExit(float delta, float progress)
    {

    }

}
