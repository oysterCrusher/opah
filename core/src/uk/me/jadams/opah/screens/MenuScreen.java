package uk.me.jadams.opah.screens;

import uk.me.jadams.opah.Assets;
import uk.me.jadams.opah.Opah;
import uk.me.jadams.opah.World;
import uk.me.jadams.opah.screenmanager.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen
{
    private final Opah game;

    private final World world;

    private final Stage stage;

    public MenuScreen(Opah game, final World world, Viewport viewport)
    {
        this.game = game;
        this.world = world;

        stage = new Stage(viewport);
        stage.setDebugAll(true);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);

        Drawable buttonDrawable = new BaseDrawable();

        // Play button.
        TextButton playButton = new TextButton("Play", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));
        playButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(game.gameScreen);
            }
        });

        // Boundary one button.
        TextButton boundaryOne = new TextButton("A", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));
        boundaryOne.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                world.stageA();
            }
        });

        // Boundary two button.
        TextButton boundaryTwo = new TextButton("B", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));
        boundaryTwo.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                world.stageB();
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(boundaryOne);
        mainTable.add(boundaryTwo);
        mainTable.row();
        mainTable.add(playButton).colspan(2).pad(40);

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
