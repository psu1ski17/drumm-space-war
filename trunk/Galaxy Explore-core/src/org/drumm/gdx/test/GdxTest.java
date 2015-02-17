package org.drumm.gdx.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.Movable;
import org.drumm.gdx.space.SimpleSpaceObjectManager;
import org.drumm.gdx.space.bodies.BasePlanet;
import org.drumm.gdx.space.ships.BaseShip;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class GdxTest extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion[] ships;
	private TextureRegion[] engines;
	private Texture shipsTexture;
	private int numShips;
	private BaseShip playerShip;
	private int shipNum;
	private List<Vector3> stars;
	private int screenWidth;
	private int screenHeight;
	private Random rand;
	private BitmapFont font;
	private float fpsCumulativeDelta;
	private int fpsNumFrame;
	private float fps;
	private org.drumm.gdx.space.SimpleSpaceObjectManager somgr;
	private Texture planetTxr;

	@Override
	public void create() {
		shipsTexture = new Texture(Gdx.files.internal("ships.png"));
		numShips = 10;
		ships = new TextureRegion[numShips];
		engines = new TextureRegion[numShips];
		int shipWidth = 256;
		int shipHeight = 256;
		int engineWidth = shipWidth;
		float shipScale = .25f;
		for (int i = 0; i < numShips; i++) {
			ships[i] = new TextureRegion(shipsTexture, 0, i * shipHeight, shipWidth, shipHeight);
			engines[i] = new TextureRegion(shipsTexture, shipWidth, i * shipHeight, engineWidth, shipHeight);
		}
		rand = new Random(52);

		screenWidth = 800;
		screenHeight = 480;
		
		ArrayList<BasePlanet> planets = new ArrayList<BasePlanet>();
		 planetTxr = new Texture(Gdx.files.internal("planet1.png"));
		planets.add(new BasePlanet(0, 0, 0, 50, 50, planetTxr));
		planets.add(new BasePlanet(100, 150, 37, 150, 150, planetTxr));
		planets.add(new BasePlanet(100, 100, 194, 100, 100, planetTxr));
		
		 somgr = new SimpleSpaceObjectManager(planets);

		playerShip = new BaseShip(ships, engines, numShips, shipScale);
		font = new BitmapFont();

		// load the drop sound effect and the rain background "music"
		// dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		// rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		// rainMusic.setLooping(true);
		// rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
		resetPlayerShip();
		Movable pos = playerShip.getMovable();
		camera.position.set(pos.getCenterX(), pos.getCenterY(), 0);
		camera.update();

		int numStars = 300;
		initStars(numStars);

		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		processInputAndUpdate(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Movable pos = playerShip.getMovable();
		camera.position.set(pos.getCenterX(), pos.getCenterY(), 0);
		camera.update();

		drawBackground();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		doBatch();
		batch.setProjectionMatrix(camera.projection);
		drawInterface(batch, delta);
		batch.end();
	}

	private void processInputAndUpdate(float delta) {
		Movable pos = playerShip.getMovable();
		 if (Gdx.input.isTouched()) {
		 Vector3 touchPos = new Vector3();
		 touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		 System.out.println("touched: " + touchPos.x + ", " + touchPos.y);
		 camera.unproject(touchPos);
		 System.out.println("touched: " + touchPos.x + ", " + touchPos.y);
		// shipAngleRad = (float) Math.atan2(touchPos.y - ship.y - ship.height /
		// 2, touchPos.x - ship.x - ship.width
		// / 2);
		// System.out.println(touchPos.x - ship.x - ship.width / 2);
		// System.out.println(touchPos.y - ship.y - ship.height / 2);
		// shipAngleDegrees = (float) (shipAngleRad * 180.0 / Math.PI);
		// // ship.x=touchPos.x;
		// // ship.y=touchPos.y;
		// System.out.println("touched: " + touchPos.x + ", " + touchPos.y +
		// " ship: " + ship.x + ", " + ship.y
		// + " degrees=" + shipAngleDegrees);
		 }
		if (Gdx.input.isKeyPressed(Keys.R)) {
			resetPlayerShip();
		}
		float magnitude = 1;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			pos.turnLeft(magnitude);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pos.turnRight(magnitude);
		} else {
			pos.turnLeft(0);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			pos.accelerate(magnitude);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			pos.accelerate(-magnitude);
		} else {
			pos.accelerate(0);
		}
		if (Gdx.input.isKeyJustPressed(Keys.TAB)) {
			shipNum++;
			shipNum %= numShips;
			playerShip.setShipNumber(shipNum);
		}
		pos.update(delta);

	}

	private void resetPlayerShip() {
		playerShip.getMovable().setCenterPosition(0, 0);
	}

	private void doBatch() {
		
		Iterator<BasePlanet> itr = somgr.getPlanetsInRect(null);
		while(itr.hasNext()){
			BasePlanet p = itr.next();
			Drawable d = p.getDrawable();
			d.draw(batch);
		}
		playerShip.getDrawable().draw(batch);
//		batch.draw(planetTxr, -50, -50)
		
		// System.out.println(camera.position);
		// System.out.println(playerShip.getMovable().getCenterX()+", "+playerShip.getMovable().getCenterY());

	}

	private void initStars(int numStars) {
		float left = camera.position.x - screenWidth / 2;
		float top = camera.position.y - screenHeight / 2;
		float right = left + screenWidth;
		float bottom = top + screenHeight;

		stars = new ArrayList<Vector3>(numStars);
		for (int i = 0; i < numStars; i++) {
			float x = rand.nextFloat() * (right - left) + left;
			float y = rand.nextFloat() * (bottom - top) + top;
			stars.add(new Vector3(x, y, 0));
		}
	}

	private void drawBackground() {
		ShapeRenderer sr = new ShapeRenderer();

		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Line);
		sr.circle(0, 0, 50);
		sr.end();

		sr.begin(ShapeType.Line);
		float left = camera.position.x - screenWidth / 2 + 1;
		float top = camera.position.y - screenHeight / 2 + 1;
		float right = left + screenWidth - 2;
		float bottom = top + screenHeight - 2;
		sr.rect(left, top, screenWidth - 2, screenHeight - 2);
		sr.end();

		for (Vector3 star : stars) {
			if (star.x < left) {
				star.x = right;
				star.y = rand.nextFloat() * (bottom - top) + top;
			}
			if (star.y < top) {
				star.y = bottom;
				star.x = rand.nextFloat() * (right - left) + left;
			}
			if (star.x > right) {
				star.x = left;
				star.y = rand.nextFloat() * (bottom - top) + top;
			}
			if (star.y > bottom) {
				star.y = top;
				star.x = rand.nextFloat() * (right - left) + left;
			}
			sr.begin(ShapeType.Point);
			sr.setColor(Color.WHITE);
			sr.point(star.x, star.y, star.z);
			sr.end();
		}
	}

	private void drawInterface(SpriteBatch batch, float delta) {
		fpsCumulativeDelta += delta;
		fpsNumFrame++;
		if (fpsCumulativeDelta > 2) {
			int fpsInt = (int) (fpsNumFrame / fpsCumulativeDelta*10);
			fps= (fpsInt/10.0f);
			fpsCumulativeDelta = 0;
			fpsNumFrame = 0;
		}
		Movable pos = playerShip.getMovable();
		float x = Math.round(pos.getCenterX());
		float y = Math.round(pos.getCenterY());
		font.draw(batch, fps + " fps      X=" + x + " Y=" + y, 5 - screenWidth / 2, 20 - screenHeight / 2);
	}
}
