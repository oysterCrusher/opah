package uk.me.jadams.opah.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import uk.me.jadams.opah.Assets;
import uk.me.jadams.opah.screenmanager.Game;
import uk.me.jadams.opah.screenmanager.Screen;

public class MenuScreen implements Screen
{
    private final Game game;

    private final Stage stage;

    public MenuScreen(Game game, SpriteBatch batch)
    {
        this.game = game;

        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport(camera), batch);
        stage.setDebugAll(true);
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
                System.out.println("Let's play this fucker!");
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(title);
        mainTable.row();
        mainTable.add(playButton);
        
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta)
    {
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
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        stage.dispose();
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
