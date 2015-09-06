package uk.me.jadams.opah;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import uk.me.jadams.opah.screenmanager.Game;
import uk.me.jadams.opah.screenmanager.Screen;
import uk.me.jadams.opah.screens.GameScreen;
import uk.me.jadams.opah.screens.MenuScreen;

public class Opah extends Game
{
    private Viewport viewport;

    private OrthographicCamera worldCamera;

    private OrthographicCamera uiCamera;

    private SpriteBatch batch;

    private World world;

    public Screen gameScreen;

    public Screen menuScreen;

    @Override
    public void create()
    {
        Assets.load();

        batch = new SpriteBatch();

        worldCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.position.x = 0;
        worldCamera.position.y = 0;
        worldCamera.update();
        batch.setProjectionMatrix(worldCamera.combined);

        viewport = new FitViewport(1280, 720, worldCamera);

        uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.update();

        world = new World(worldCamera, batch);

        menuScreen = new MenuScreen(this, world, viewport);
        gameScreen = new GameScreen(this, world);

        this.setScreen(menuScreen);
    }

    @Override
    public void render()
    {
        long start = System.currentTimeMillis();

        float delta = 1 / 60f;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(delta);

        super.render(delta);

        long end = System.currentTimeMillis();

        if (end - start > 10)
        {
            System.out.println(end - start);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);

        super.resize(width, height);
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
    public void dispose()
    {

    }
}
