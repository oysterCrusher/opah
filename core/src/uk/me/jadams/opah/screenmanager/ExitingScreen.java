package uk.me.jadams.opah.screenmanager;

public class ExitingScreen implements TransitionScreen
{
    private final Game game;
    private Screen screen;
    private float exitTime;
    private float t;
    private float progress;

    public ExitingScreen(Game game)
    {
        this.game = game;
    }

    @Override
    public void setScreen(Screen screen)
    {
        this.screen = screen;
    }

    @Override
    public void start()
    {
        if (screen != null)
        {
            exitTime = screen.getExitTime();
        } else
        {
            exitTime = 0;
        }
        t = 0;
        progress = 0;
    }

    @Override
    public void render(float delta)
    {
        t += delta;
        progress = t / exitTime;

        if (screen != null)
        {
            screen.renderExit(delta, progress);
        }

        if (progress >= 1.0)
        {
            game.setTransitionScreen(game.entering);
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
        if (screen != null)
        {
            screen.hide();
        }
    }

}
