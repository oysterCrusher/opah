package uk.me.jadams.opah.screenmanager;

import com.badlogic.gdx.ApplicationListener;

public abstract class Game implements ApplicationListener
{
    public final TransitionScreen entering;
    public final TransitionScreen running;
    public final TransitionScreen exiting;
    public TransitionScreen current;
    
    public Screen currentScreen;

    public Game()
    {
        entering = new EnteringScreen(this);
        running = new RunningScreen();
        exiting = new ExitingScreen(this);
    }

    public void setScreen(Screen nextScreen)
    {
        if (current != null)
        {
            exiting.setScreen(currentScreen);
        }
        entering.setScreen(nextScreen);
        running.setScreen(nextScreen);
        this.setTransitionScreen(exiting);
        currentScreen = nextScreen;
    }

    protected void setTransitionScreen(TransitionScreen newScreen)
    {
        if (current != null)
        {
            current.end();
        }

        current = newScreen;

        if (current != null)
        {
            current.start();
        }
    }
    
    protected void render(float delta)
    {
        if (current != null)
        {
            current.render(delta);
        }
    }
}
