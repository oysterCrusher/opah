package uk.me.jadams.opah.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
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
import com.badlogic.gdx.utils.viewport.Viewport;

import uk.me.jadams.opah.Assets;
import uk.me.jadams.opah.Opah;
import uk.me.jadams.opah.World;
import uk.me.jadams.opah.screenmanager.Screen;

public class MenuScreen implements Screen
{
    private final Opah game;

    private final World world;

    private final Stage stage;

    public MenuScreen(Opah game, World world, Viewport viewport)
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

        Label title = new Label("Opah", new LabelStyle(Assets.titleFont, Color.valueOf("CCCCCC")));

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
        final Vector2[] verticesOne = new Vector2[8];
        verticesOne[0] = new Vector2(-300, 0);
        verticesOne[1] = new Vector2(-300, 250);
        verticesOne[2] = new Vector2(0, 320);
        verticesOne[3] = new Vector2(300, 250);
        verticesOne[4] = new Vector2(300, 0);
        verticesOne[5] = new Vector2(300, -250);
        verticesOne[6] = new Vector2(0, -320);
        verticesOne[7] = new Vector2(-300, -250);
        TextButton boundaryOne = new TextButton("A", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));
        boundaryOne.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                world.moveBoundaries(verticesOne);
            }
        });

        // Boundary two button.
        final Vector2[] verticesTwo = new Vector2[8];
        verticesTwo[0] = new Vector2(-400, 0);
        verticesTwo[1] = new Vector2(-300, 250);
        verticesTwo[2] = new Vector2(0, 320);
        verticesTwo[3] = new Vector2(300, 250);
        verticesTwo[4] = new Vector2(400, 0);
        verticesTwo[5] = new Vector2(300, -250);
        verticesTwo[6] = new Vector2(0, -320);
        verticesTwo[7] = new Vector2(-300, -250);
        TextButton boundaryTwo = new TextButton("B", new TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Assets.titleFont));
        boundaryTwo.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                world.moveBoundaries(verticesTwo);
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(title).pad(40);
        mainTable.row();
        mainTable.add(boundaryOne);
        mainTable.add(boundaryTwo);
        mainTable.row();
        mainTable.add(playButton).pad(40);

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
