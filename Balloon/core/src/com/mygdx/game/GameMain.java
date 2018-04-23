package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Managers.MyAssetManager;
import Scenes.DeathScene;
import Scenes.GameScene;
import Scenes.MenuScene;
import Scenes.SettingsScene;


public class GameMain extends Game {
	public SpriteBatch batch;
	public Viewport screenPort;
	public MyAssetManager myAssetManager = new MyAssetManager();


	@Override
	public void create() {
		batch = new SpriteBatch();
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 1200);
		screenPort = new ScreenViewport();
		this.setScreen(new MenuScene(this));
	}
	public void gotoMenuScene(){
		MenuScene menuScene= new MenuScene(this);
		setScreen(menuScene);
	}

	public void gotoSettingsScene(){
		SettingsScene settingsScene = new SettingsScene(this);
		setScreen(settingsScene);
	}
	public void gotoDeathScene(){
		DeathScene deathScene = new DeathScene(this);
		setScreen(deathScene);
	}

	public void gotoGameScreen(){
		GameScene gameScene = new GameScene(this);
		setScreen(gameScene);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
