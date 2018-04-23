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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameConstants;
import com.mygdx.game.GameMain;

import Managers.UserPref;

/**
 * Created by Dewa on 2/18/2017.
 */
public class MenuScene implements Screen {

    final GameMain game;
    private Texture title;
    private Texture balloon;
    private Skin mySkin;
    private Stage stage;

    public MenuScene(final GameMain game){
        this.game = game;
        game.myAssetManager.queueAddSkin();
        game.myAssetManager.manager.finishLoading();
        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);

        title = new Texture("Title.png");
        balloon = new Texture("player.png");


        TextButton easyBtn = new TextButton("Easy",mySkin);
        easyBtn.getLabel().setFontScale(5, 5);
        easyBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        easyBtn.setPosition(GameConstants.centerX - easyBtn.getWidth()/2,GameConstants.centerY - 15);
        easyBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.chlgLevel = "Easy";
                game.gotoGameScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        TextButton medBtn = new TextButton("Medium",mySkin);
        medBtn.getLabel().setFontScale(5, 5);
        medBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        medBtn.setPosition(GameConstants.centerX - medBtn.getWidth()/2,GameConstants.centerY - medBtn.getHeight() - 7);
        medBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.chlgLevel = "Med";
                game.gotoGameScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        TextButton hardBtn = new TextButton("Hard",mySkin);
        hardBtn.getLabel().setFontScale(5, 5);
        hardBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        hardBtn.setPosition(GameConstants.centerX - hardBtn.getWidth()/2,GameConstants.centerY - hardBtn.getHeight() * 2);
        hardBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                UserPref.chlgLevel = "Hard";
                game.gotoGameScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        TextButton settingsBtn = new TextButton("Change Song",mySkin);
        settingsBtn.getLabel().setFontScale(5, 5);
        settingsBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        settingsBtn.setPosition(GameConstants.centerX - settingsBtn.getWidth()/2,settingsBtn.getY() + settingsBtn.getHeight() + 7);
        settingsBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoSettingsScene();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });



        stage.addActor(easyBtn);
        stage.addActor(medBtn);
        stage.addActor(hardBtn);
        stage.addActor(settingsBtn);


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
        game.batch.draw(title, 300, 1300, 500, 400);

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
        /*badlogic.dispose();*/
        game.batch.dispose();
        title.dispose();
        balloon.dispose();
        mySkin.dispose();
        stage.dispose();

    }
}