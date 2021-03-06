package uk.me.jadams.opah.screenmanager;

public class EnteringScreen implements TransitionScreen
{
    private final Game game;
    private Screen screen;
    private float enterTime;
    private float t;
    private float progress;

    public EnteringScreen(Game game)
    {
        this.game = game;
    }

    public void setScreen(Screen screen)
    {
        this.screen = screen;
    };

    @Override
    public void start()
    {
        enterTime = screen.getEnterTime();
        t = 0;
        progress = 0;
        screen.show();
    }

    @Override
    public void render(float delta)
    {
        t += delta;
        progress = t / enterTime;

        if (progress > 1.0f)
        {
            progress = 1;
        }

        if (screen != null)
        {
            screen.renderEnter(delta, progress);
        }

        if (progress >= 1.0f)
        {
            game.setTransitionScreen(game.running);
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

    }
}
