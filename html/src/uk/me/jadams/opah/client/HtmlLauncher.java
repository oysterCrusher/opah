package uk.me.jadams.opah.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import uk.me.jadams.opah.Opah;

public class HtmlLauncher extends GwtApplication
{
    @Override
    public GwtApplicationConfiguration getConfig()
    {
        return new GwtApplicationConfiguration(1280, 720);
    }

    @Override
    public ApplicationListener getApplicationListener()
    {
        return new Opah();
    }
}