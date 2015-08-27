package uk.me.jadams.opah.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import uk.me.jadams.opah.Assets;
import uk.me.jadams.opah.Opah;
import uk.me.jadams.opah.screenmanager.Screen;

public class MenuScreen implements Screen
{
    private final Opah game;

    private final SpriteBatch batch;

    private final Stage stage;

    private final OrthographicCamera camera;

    private final Texture bg;

    public MenuScreen(Opah game, SpriteBatch batch)
    {
        this.game = game;
        this.batch = batch;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ExtendViewport(1280, 720, camera), batch);
        stage.setDebugAll(true);

        bg = Assets.bg;
        bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);

        Drawable buttonDrawable = new BaseDrawable();

        Label title = new Label("Opah", new LabelStyle(Assets.titleFont, Color.valueOf("F26937")));

        TextButton playButton = new TextButton("Play", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));

        playButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(game.gameScreen);
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(title).pad(40);
        mainTable.row();
        mainTable.add(playButton).pad(40);

        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta)
    {
        batch.begin();
        batch.draw(bg, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight(), 0, 0, 1, 720 / 32);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }

    @Override
    public float getEnterTime()
    {
        return 1;
    }

    @Override
    public float getExitTime()
    {
        return 0;
    }

    @Override
    public void renderEnter(float delta, float progress)
    {
        batch.begin();
        batch.setColor(1, 1, 1, progress);
        batch.draw(bg, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight(), 0, 0, 1, 720 / 32);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void renderExit(float delta, float progress)
    {
        stage.act();
        stage.draw();
    }

}
