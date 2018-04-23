package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameConstants;
import com.mygdx.game.GameMain;

import java.util.Iterator;

import Managers.UserPref;

public class GameScene implements Screen {
    final GameMain game;

    private Sprite balloonImg;
    private Rectangle balloonHitBox;

    private Array<Rectangle> coins;

    private Sprite birdImg;
    private Sprite birdMdImg;
    private Sprite birdLgImg;
    public static Array<Rectangle> birds;

    private Sprite playerSpeedBoostImg;
    public static Array<Rectangle> boosts;

    private Texture gameOverImg;
    private Texture bgImg;
    private Sprite coinImg;

    private Button playBtn;
    private Button menuBtn;

    private Music bgMusic;
    private Music deathMusic;
    private Music CoinSound;

    private OrthographicCamera camera;

    private long lastDropTime;
    private int playerSpeed;

    private boolean isDead = false;
    private boolean speedBoostCollected;
    private boolean speedBoostUsed;

    private long sinceBoostCollected;

    private int birdSpeed;
    private int birdSizePicker;
    private int score;
    private int spawnRate;

    private Skin mySkin;
    private Stage stage;

    //Constructor for Scene
    public GameScene(final GameMain game)
    {
        this.game = game;
        balloonImg = new Sprite(new Texture("player.png"));
        balloonHitBox = new Rectangle();
        balloonHitBox.height = 600;
        balloonHitBox.width = 265;
        balloonHitBox.x = 400;
        balloonHitBox.y = 1;

        birdImg = new Sprite(new Texture("bird.png"));
        birdMdImg = new Sprite(new Texture("bird_md.png"));
        birdLgImg = new Sprite(new Texture("bird_lrg.png"));
        birds = new Array<Rectangle>();

        coinImg = new Sprite(new Texture("coin.png"));
        coins = new Array<Rectangle>();

        CoinSound = Gdx.audio.newMusic(Gdx.files.internal("CoinSound.wav"));
        CoinSound.setVolume(3f);
        CoinSound.setLooping(false);


        playerSpeedBoostImg = new Sprite(new Texture("speedBoost.png"));
        boosts = new Array<Rectangle>();

        gameOverImg = new Texture("gameover2.png");

        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        if (UserPref.songChoice == "" || UserPref.songChoice == null)
        {
            bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Africa_8_bit.mp3"));
            bgImg = new Texture("Africa_bg.png");
        }
        else
        {
            bgMusic = Gdx.audio.newMusic(Gdx.files.internal(UserPref.songChoice));
            bgImg = new Texture(UserPref.bgChoice);

        }

        if (UserPref.chlgLevel == "Easy")
        {
            spawnRate = 1900000000;
            birdSpeed = 400;
        }
        else if(UserPref.chlgLevel == "Med")
        {
            spawnRate = 1600000000;
            birdSpeed = 700;
        }
        else if(UserPref.chlgLevel == "Hard")
        {
            spawnRate = 699999999;
            birdSpeed = 1100;
        }
        else
        {
            spawnRate = 1900000000;
            birdSpeed = 1100;
        }

        bgMusic.setLooping(true);
        bgMusic.setVolume(0.2f);
        bgMusic.play();

        deathMusic = Gdx.audio.newMusic(Gdx.files.internal("DeathSound.wav"));
        deathMusic.setLooping(false);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 1200);

        playerSpeed = 10;

        speedBoostCollected = false;
        speedBoostUsed = false;

        score = 0;


        spawnBird();
    }
    private void choseSpawnItem()
    {
        if (MathUtils.random(0, 1000) % 14 == 0)
        {
            spawnPlayerSpeedBoost();
        }
        else if(MathUtils.random(0, 1000) % 4 == 0)
        {
            spawnCoin();
        }
        else
        {
            spawnBird();
        }
    }
    private void spawnCoin()
    {
        Rectangle newCoin = new Rectangle();
        newCoin.x = (MathUtils.random(5, 795));
        newCoin.y = Gdx.graphics.getHeight() + 20;
        newCoin.width = 96;
        newCoin.height = 96;
        coins.add(newCoin);
        lastDropTime = TimeUtils.nanoTime();
    }
    private void spawnBird()
    {
        Rectangle newBird = new Rectangle();
        newBird.x = (MathUtils.random(5, 795));
        newBird.y = Gdx.graphics.getHeight() + 20;
        newBird.width = 90;
        newBird.height = 232;
        birds.add(newBird);
        lastDropTime = TimeUtils.nanoTime();
    }
    private void spawnPlayerSpeedBoost()
    {
        Rectangle newPlayerSpeedBoost = new Rectangle();
        newPlayerSpeedBoost.x = (MathUtils.random(5, 795));
        newPlayerSpeedBoost.y = Gdx.graphics.getHeight() + 20;
        newPlayerSpeedBoost.width = 100;
        newPlayerSpeedBoost.height = 100;
        boosts.add(newPlayerSpeedBoost);
        lastDropTime = TimeUtils.nanoTime();
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.4f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


        if (Gdx.input.getAccelerometerX() > 1)
        {
            balloonHitBox.x -= playerSpeed;
        }
        else if (Gdx.input.getAccelerometerX() < -1)
        {
            balloonHitBox.x += playerSpeed;
        }



        game.batch.begin();

        game.batch.draw(bgImg, 0,0, 1100, 2100);

        if (!isDead)
        {
            if (speedBoostCollected)
            {
                game.batch.draw(playerSpeedBoostImg, 820, 5, 200, 200);
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean touchDown (int x, int y, int pointer, int button) {
                        if (speedBoostCollected)
                        {
                            speedBoostUsed = true;
                            speedBoostCollected = false;
                            sinceBoostCollected = TimeUtils.nanoTime();
                        }
                        return true; // return true to indicate the event was handled
                    }
                });

            }
            for (Rectangle bird: birds)
            {
                game.batch.draw(birdImg, bird.x, bird.y, bird.width, bird.height);
            }

            for (Rectangle boost: boosts)
            {
                game.batch.draw(playerSpeedBoostImg, boost.x, boost.y, boost.width, boost.height);
            }

            for(Rectangle coin:coins)
            {
                game.batch.draw(coinImg, coin.x, coin.y, coin.width, coin.height);
            }

            game.batch.draw(balloonImg, balloonHitBox.x, 1, balloonHitBox.width, balloonHitBox.height);

            if (balloonHitBox.x <= 0){
                balloonHitBox.x = 0;
            }
            if (balloonHitBox.x > 800){
                balloonHitBox.x = 800;
            }
            if(TimeUtils.nanoTime() - lastDropTime > spawnRate)
            {
                choseSpawnItem();
            }
            Iterator<Rectangle> iterBird = birds.iterator();
            while(iterBird.hasNext())
            {
                Rectangle object = iterBird.next();
                object.y -= birdSpeed * Gdx.graphics.getDeltaTime();
                if(object.y < -120)
                {
                    iterBird.remove();
                }
                if(object.overlaps(balloonHitBox))
                {
                    iterBird.remove();
                    isDead = true;
                }
            }
            Iterator<Rectangle> iterBoost = boosts.iterator();
            while(iterBoost.hasNext())
            {
                Rectangle object = iterBoost.next();
                object.y -= 300 * Gdx.graphics.getDeltaTime();
                if(object.y < -120)
                {
                    iterBoost.remove();
                }
                if(object.overlaps(balloonHitBox))
                {
                    speedBoostCollected = true;
                    CoinSound.play();
                    iterBoost.remove();
                }
            }
            Iterator<Rectangle> iterCoin = coins.iterator();
            while(iterCoin.hasNext())
            {
                Rectangle object = iterCoin.next();
                object.y -= 500 * Gdx.graphics.getDeltaTime();
                if(object.y < -120)
                {
                    iterCoin.remove();
                }
                if(object.overlaps(balloonHitBox))
                {
                    score++;
                    CoinSound.play();
                    iterCoin.remove();
                }
            }
            if (speedBoostUsed)
            {

                playerSpeed = 30;
                if (TimeUtils.nanoTime() - sinceBoostCollected > 1000000000)
                {
                    playerSpeed = 10;
                    speedBoostUsed = false;
                }
            }
        }
        else
        {
            bgMusic.stop();
            deathMusic.play();
            UserPref.score = score;
            game.gotoDeathScene();
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose () {
        game.batch.dispose();
        balloonImg.getTexture().dispose();
        coinImg.getTexture().dispose();
        bgImg.dispose();
        playerSpeedBoostImg.getTexture().dispose();
        bgMusic.dispose();
        deathMusic.dispose();
        CoinSound.dispose();
//        Iterator<Rectangle> iterBird = birds.iterator();
//        while(iterBird.hasNext())
//        {
//            iterBird.remove();
//        }
//        Iterator<Rectangle> iterBoost = boosts.iterator();
//        while(iterBoost.hasNext())
//        {
//            iterBoost.remove();
//        }

    }
}
