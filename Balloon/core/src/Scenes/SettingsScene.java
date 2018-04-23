package Scenes;

/**
 * Created by Justin on 4/16/2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GameConstants;
import com.mygdx.game.GameMain;

import javax.xml.soap.Text;

import Managers.UserPref;

/**
 * Created by Dewa on 2/19/2017.
 */
public class SettingsScene implements Screen {

    final GameMain game;
    private Skin mySkin;
    private Stage stage;
    private Texture balloon;

    public SettingsScene(final GameMain game){
        this.game = game;
        mySkin = new Skin(Gdx.files.internal(GameConstants.skin));
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);


        balloon = new Texture("player.png");

        TextButton homeBtn = new TextButton("home",mySkin);
        homeBtn.getLabel().setFontScale(5, 5);
        homeBtn.setSize(GameConstants.col_width * 2,GameConstants.row_height);
        homeBtn.setPosition(0,GameConstants.screenHeight - homeBtn.getHeight());
        homeBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoMenuScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        TextButton africaBtn = new TextButton("Africa",mySkin);
        africaBtn.getLabel().setFontScale(5, 5);
        africaBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        africaBtn.setPosition(GameConstants.centerX - africaBtn.getWidth()/2,GameConstants.centerY);
        africaBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.songChoice = "Africa_8_bit.mp3";
                UserPref.bgChoice = "Africa_bg.png";
                game.gotoMenuScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        TextButton blueBtn = new TextButton("Blue (Da Ba Dee)",mySkin);
        blueBtn.getLabel().setFontScale(5, 5);
        blueBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        blueBtn.setPosition(GameConstants.centerX - africaBtn.getWidth()/2,GameConstants.centerY - blueBtn.getHeight());
        blueBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.songChoice = "Blue(DaBaDee)_8_bit.mp3";
                UserPref.bgChoice = "blue_bg.png";
                game.gotoMenuScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        TextButton takeBtn = new TextButton("Take On Me",mySkin);
        takeBtn.getLabel().setFontScale(5, 5);
        takeBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        takeBtn.setPosition(GameConstants.centerX - africaBtn.getWidth()/2,GameConstants.centerY - (blueBtn.getHeight() * 2));
        takeBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.songChoice = "TakeOnMe_8_bit.mp3";
                UserPref.bgChoice = "takeOnMe_bg.png";
                game.gotoMenuScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        TextButton allBtn = new TextButton("All Star",mySkin);
        allBtn.getLabel().setFontScale(5, 5);
        allBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        allBtn.setPosition(GameConstants.centerX - africaBtn.getWidth()/2,GameConstants.centerY - (blueBtn.getHeight() * 3));
        allBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.songChoice = "AllStar_8_bit.mp3";
                UserPref.bgChoice = "shrek.png";
                game.gotoMenuScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        stage.addActor(homeBtn);
        stage.addActor(africaBtn);
        stage.addActor(blueBtn);
        stage.addActor(takeBtn);
        stage.addActor(allBtn);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(balloon, 160, 0, 800, 2000);

        game.batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        game.screenPort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mySkin.dispose();
        stage.dispose();
        balloon.dispose();

    }
}
