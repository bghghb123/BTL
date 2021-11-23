package com.mygdx.drop.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

import static com.mygdx.drop.Audio.clickButton;
import static com.mygdx.drop.DropGame.font;

public class MainMenuUI  {

    //Stage and Viewport Variables
    public Stage stage;
    private Viewport viewport;

    //Button Variables
    private TextButton startButton;
    private TextButton exitButton;

    private Label gameName;

    //Skin and TextureAlas Variables
    private Skin skin;
    private TextureAtlas textureAtlas;

    //Table Variables
    private Table table;

    private Image backgroundImage;



    public MainMenuUI(final DropGame dropGame){

        //Create new viewport with new camera for UI
        viewport = new ScreenViewport(new OrthographicCamera());

        //Define new Stage and set Input Processor for Handle input event
        stage = new Stage(viewport,dropGame.batch);
        Gdx.input.setInputProcessor(stage);

        backgroundImage = new Image(new Texture("Texture/rainBackground.jpg"));
        backgroundImage.setScale(3);

        gameName = new Label(String.format("DROP THE GAME"),new Label.LabelStyle(font, Color.WHITE));
        gameName.setFontScale(4);

        //Define Skin and TextureAtlas
        textureAtlas = new TextureAtlas("UI/Button.pack");
        skin = new Skin(Gdx.files.internal("ButtonStyle.json"),textureAtlas);

        //Define Buttons
        startButton = new TextButton("START",skin);
        startButton.getLabel().setFontScale(1.5f);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickButton.play();
                dropGame.setScreen(new GameScreen(dropGame));
            }
        });

        exitButton = new TextButton("EXIT",skin);
        exitButton.getLabel().setFontScale(1.5f);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickButton.play();
                Gdx.app.exit();
            }
        });
        //Define Table
        table = new Table();
        table.setFillParent(true);
        table.add(gameName);
        table.row();
        table.add(startButton).width(200).height(100).pad(10);
        table.row();
        table.add(exitButton).width(200).height(100).pad(10);

        stage.addActor(backgroundImage);
        stage.addActor(table);
    }
}
