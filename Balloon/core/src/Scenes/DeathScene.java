package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
 * Created by Justin on 4/17/2018.
 */

public class DeathScene implements Screen {
    final GameMain game;
    private Skin mySkin;
    private Stage stage;

    public DeathScene(final GameMain game){
        this.game = game;
        game.myAssetManager.queueAddSkin();
        game.myAssetManager.manager.finishLoading();
        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);


        Label gameTitle = new Label("Score:"+ UserPref.score,mySkin);
        gameTitle.setFontScaleX(8f);
        gameTitle.setFontScaleY(8f);
        gameTitle.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gameTitle.setPosition(GameConstants.centerX - gameTitle.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        gameTitle.setAlignment(Align.center);

        TextButton startBtn = new TextButton("Play Again?",mySkin);
        startBtn.getLabel().setFontScale(5, 5);
        startBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        startBtn.setPosition(GameConstants.centerX - startBtn.getWidth()/2,GameConstants.centerY);
        startBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoGameScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        TextButton settingsBtn = new TextButton("Menu",mySkin);
        settingsBtn.getLabel().setFontScale(5, 5);
        settingsBtn.setSize(GameConstants.col_width*5,GameConstants.row_height);
        settingsBtn.setPosition(GameConstants.centerX - settingsBtn.getWidth()/2,startBtn.getY() - GameConstants.row_height -15);
        settingsBtn.addListener(new InputListener(){
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


        stage.addActor(gameTitle);
        stage.addActor(startBtn);
        stage.addActor(settingsBtn);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        /*game.batch.begin();
        game.batch.draw(badlogic,0,0);
        game.batch.end();*/


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
        mySkin.dispose();
        stage.dispose();

    }
}
