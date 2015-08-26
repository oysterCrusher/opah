package uk.me.jadams.opah.screenmanager;

public interface TransitionScreen
{
    public void setScreen(Screen screen);

    public void start();
    
    public void render(float delta);
    
    public void resize(int width, int height);

    public void end();
}
