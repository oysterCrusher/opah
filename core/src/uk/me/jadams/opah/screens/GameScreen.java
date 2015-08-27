package uk.me.jadams.opah.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.me.jadams.opah.Assets;
import uk.me.jadams.opah.components.KeyboardMovementComponent;
import uk.me.jadams.opah.components.PositionComponent;
import uk.me.jadams.opah.components.SizeComponent;
import uk.me.jadams.opah.components.TextureComponent;
import uk.me.jadams.opah.screenmanager.Screen;
import uk.me.jadams.opah.systems.RenderSystem;

public class GameScreen implements Screen
{
    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    private final PooledEngine engine;

    private final Texture bg;

    public GameScreen(SpriteBatch batch)
    {
        this.batch = batch;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        bg = Assets.bg;
        bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

        engine = new PooledEngine();

        Entity player = engine.createEntity();
        player.add(new PositionComponent(0, 0));
        player.add(new SizeComponent(16));
        player.add(new KeyboardMovementComponent());
        player.add(new TextureComponent(Assets.player));
        engine.addEntity(player);

        engine.addSystem(new RenderSystem(batch));
    }

    @Override
    public void show()
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta)
    {
        long start = System.currentTimeMillis();

        batch.begin();
        batch.draw(bg, -camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight, 0, 0, 1, 720 / 32);
        engine.update(delta);
        batch.end();

        long end = System.currentTimeMillis();

        if (end - start > 10)
        {
            System.out.println(end - start);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
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
        return 0;
    }

    @Override
    public void renderEnter(float delta, float progress)
    {
        float x = -camera.viewportWidth / 2;
        float y = -camera.viewportHeight / 2;

        batch.begin();
        batch.setColor(1, 1, 1, progress);
        batch.draw(bg, x, y, camera.viewportWidth, camera.viewportHeight, 0, 0, 1, 720 / 32);
        batch.end();
    }

    @Override
    public void renderExit(float delta, float progress)
    {

    }

}
