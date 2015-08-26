package uk.me.jadams.opah.screenmanager;

public interface Screen extends com.badlogic.gdx.Screen
{
    public float getEnterTime();

    public float getExitTime();

    public void renderEnter(float delta, float progress);

    public void renderExit(float delta, float progress);
}
