package com.mygdx.drop.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.drop.DropGame;
import com.mygdx.drop.Screen.GameScreen;
import com.mygdx.drop.Screen.MainMenuScreen;

import static com.mygdx.drop.Audio.clickButton;
import static com.mygdx.drop.DropGame.font;
import static com.mygdx.drop.SaveGame.preferences;

public class GameOverUI {
    public Stage stage;
    private Viewport viewport;

    private Image backgroundImage;

    //Button Variables
    private TextButton backButton;
    private TextButton retryButton;

    //Label Variables
    private Label gameOver;
    private Label highScoreText;

    //Skin and TextureAlas Variables
    private Skin skin;
    private TextureAtlas textureAtlas;

    private int highScore;

    //Table Variables
    private Table table;

    public GameOverUI(final DropGame dropGame){
        //Create new viewport with new camera for UI
        viewport = new ScreenViewport(new OrthographicCamera());

        backgroundImage = new Image(new Texture("Texture/rainBackground.jpg"));
        backgroundImage.setScale(3);

        //Define new Stage and set Input Processor for Handle input event
        stage = new Stage(viewport,dropGame.batch);
        Gdx.input.setInputProcessor(stage);

        //Define Skin and TextureAtlas
        textureAtlas = new TextureAtlas("UI/Button.pack");
        skin = new Skin(Gdx.files.internal("ButtonStyle.json"),textureAtlas);

        highScore = preferences.getInteger("HighScore");

        gameOver = new Label(String.format("GAMEOVER"),new Label.LabelStyle(font, Color.WHITE));
        gameOver.setFontScale(4);

        highScoreText = new Label(String.format("HIGHSCORE: %d", highScore),new Label.LabelStyle(font, Color.WHITE));
        highScoreText.setFontScale(4);

        backButton = new TextButton("MENU",skin);
        backButton.getLabel().setFontScale(1.5f);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickButton.play();
                dropGame.setScreen(new MainMenuScreen(dropGame));
            }
        });

        retryButton = new TextButton("RETRY",skin);
        retryButton.getLabel().setFontScale(1.5f);
        retryButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickButton.play();
                dropGame.setScreen(new GameScreen(dropGame));
            }
        });

        table = new Table();
        table.setFillParent(true);
        table.add(gameOver);
        table.row();
        table.add(highScoreText);
        table.row();
        table.add(retryButton).width(200).height(100).pad(10);
        table.row();
        table.add(backButton).width(200).height(100).pad(10);

        stage.addActor(backgroundImage);
        stage.addActor(table);

    }
}
