package uk.me.jadams.opah.screenmanager;

public class RunningScreen implements TransitionScreen
{
    private Screen screen;

    public RunningScreen()
    {

    }

    @Override
    public void setScreen(Screen screen)
    {
        this.screen = screen;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void render(float delta)
    {
        if (screen != null)
        {
            screen.render(delta);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        if (screen != null)
        {
            screen.resize(width, height);
        }
    }

    @Override
    public void end()
    {
        // TODO Auto-generated method stub

    }

}
