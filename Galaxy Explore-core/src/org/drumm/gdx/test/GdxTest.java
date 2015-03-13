package org.drumm.gdx.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.drumm.gdx.space.ForceBasedMovable;
import org.drumm.gdx.space.ShipController;
import org.drumm.gdx.space.SimpleMovable;
import org.drumm.gdx.space.bodies.BasePlanet;
import org.drumm.gdx.space.managers.PlanetManager;
import org.drumm.gdx.space.managers.RootManager;
import org.drumm.gdx.space.managers.ShipManager;
import org.drumm.gdx.space.ships.BaseShip;
import org.drumm.gdx.space.weapons.ammunition.WeaponDrawManager;
import org.drumm.gdx.space.weapons.guns.Laser;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GdxTest extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion[] shipTextures;
	private TextureRegion[] engineTextures;
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
	private Texture planetTxr;
	private boolean debug=true;
	private ShapeRenderer screenRenderer;
	private ShapeRenderer worldRenderer;

	@Override
	public void create() {
		ShipManager shipManager = new ShipManager();
		WeaponDrawManager weaponManager = new WeaponDrawManager();
		PlanetManager planetManager = new PlanetManager();
		RootManager.setShipManager(shipManager);
		RootManager.setWeaponDrawManager(weaponManager);
		RootManager.setPlanetManager(planetManager);

		shipsTexture = new Texture(Gdx.files.internal("ships.png"));
		numShips = 10;
		shipTextures = new TextureRegion[numShips];
		engineTextures = new TextureRegion[numShips];
		int shipWidth = 256;
		int shipHeight = 256;
		int engineWidth = shipWidth;
		float shipScale = .25f;
		for (int i = 0; i < numShips; i++) {
			shipTextures[i] = new TextureRegion(shipsTexture, 0, i * shipHeight, shipWidth, shipHeight);
			engineTextures[i] = new TextureRegion(shipsTexture, shipWidth, i * shipHeight, engineWidth, shipHeight);
		}
		rand = new Random(52);

		screenWidth = 800;
		screenHeight = 480;

		planetTxr = new Texture(Gdx.files.internal("planet1.png"));
		planetManager.add(new BasePlanet(0, 0, 0, 50, 50, planetTxr));
		planetManager.add(new BasePlanet(1000, 1500, 37, 75, 75, planetTxr));
		planetManager.add(new BasePlanet(1000, -1000, 194, 100, 100, planetTxr));

		playerShip = new BaseShip(shipTextures, engineTextures, numShips, shipScale);
		playerShip.addGun(new Laser(playerShip));
		BaseShip npcShip = new BaseShip(shipTextures, engineTextures, numShips, .125f);
		npcShip.setX(25);
		npcShip.setY(100);
		
		float thrust=0;
		float drag = 00f;
		float speed = 0;
		float maxSpeed = 300;
		float maxReverseSpeed = 0;
		float angularVelocity = 0;
		float angularDrag = 2000;
		float maxAngularVelocity = 250;
		float maxAngularAccelleration = 2000;
		float angularAccelleration = 0;
		float maxFowardAcceleration=500f;
		float maxReverseAcceleration=1000f;
		
		
		ShipController m = new ForceBasedMovable(npcShip, angularAccelleration, maxAngularAccelleration, angularDrag, angularVelocity, maxAngularVelocity, thrust, speed, drag, maxSpeed, maxReverseSpeed, maxFowardAcceleration, maxReverseAcceleration);
		npcShip.setController(m);
		if (m instanceof ForceBasedMovable) {
			ForceBasedMovable fbm = (ForceBasedMovable) m;
			fbm.setAngularVelocity(0);
			fbm.setSpeed(0);
			fbm.setDrag(0);
			fbm.setAngularDrag(0);
			fbm.moveTo(50, 50);
		}
		npcShip.setShipNumber(5);
		BaseShip npcShip2 = new BaseShip(shipTextures, engineTextures, numShips, .125f);
		 m = new ForceBasedMovable(npcShip2, angularAccelleration, maxAngularAccelleration, angularDrag, angularVelocity, maxAngularVelocity, thrust, speed, drag, maxSpeed, maxReverseSpeed, maxFowardAcceleration, maxReverseAcceleration);
		npcShip2.setController(m);
		npcShip2.setX(-150);
		npcShip2.setY(-250);
		if (m instanceof ForceBasedMovable) {
			ForceBasedMovable fbm = (ForceBasedMovable) m;
			fbm.setAngularVelocity(6);
			fbm.setSpeed(50);
			fbm.setDrag(0);
			fbm.setAngularDrag(0);
		}
		npcShip2.setShipNumber(2);
		BaseShip npcShip3 = new BaseShip(shipTextures, engineTextures, numShips, .125f);
		m = new ForceBasedMovable(npcShip3, angularAccelleration, maxAngularAccelleration, angularDrag, angularVelocity, maxAngularVelocity, thrust, speed, drag, maxSpeed, maxReverseSpeed, maxFowardAcceleration, maxReverseAcceleration);
		npcShip3.setController(m);
		npcShip3.setX(300);
		npcShip3.setY(-250);
		if (m instanceof ForceBasedMovable) {
			ForceBasedMovable fbm = (ForceBasedMovable) m;
			fbm.setAngularVelocity(200);
			fbm.setSpeed(200);
			fbm.setDrag(0);
			fbm.setAngularDrag(0);
		}
		npcShip3.setShipNumber(9);
		shipManager.add(playerShip);
		shipManager.add(npcShip);
		shipManager.add(npcShip2);
		shipManager.add(npcShip3);
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
		camera.position.set(playerShip.getCenterX(), playerShip.getCenterY(), 0);

		camera.update();

		worldRenderer=new ShapeRenderer();
		screenRenderer=new ShapeRenderer();
		worldRenderer.setProjectionMatrix(camera.combined);
		int numStars = 300;
		initStars(numStars);

		batch = new SpriteBatch();

		Gdx.input.setInputProcessor(new InputProcessor() {
			private Vector3 touchPos = new Vector3();
			private boolean leftDown;
			private boolean rightDown;
			private boolean middleDown;

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if (button == Input.Buttons.RIGHT) {
					System.out.println("stop");
					playerShip.disableFire();
					rightDown=false;
				}
				return true;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				touchPos.set(screenX, screenY, 0);
//				System.out.println("touched: " + touchPos.x + ", " + touchPos.y + " button=" + button);
				camera.unproject(touchPos);
				if (rightDown){
					playerShip.setTarget(touchPos.x, touchPos.y);
				}
				return true;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				touchPos.set(screenX, screenY, 0);
//				System.out.println("touched: " + touchPos.x + ", " + touchPos.y + " button=" + button);
				camera.unproject(touchPos);
				if (button == Input.Buttons.LEFT) {
					playerShip.getController().moveTo(touchPos.x, touchPos.y);
				} else if (button == Input.Buttons.RIGHT) {
					playerShip.enableFire();
					playerShip.setTarget(touchPos.x, touchPos.y);
					rightDown=true;
				}
				return true;
			}

			@Override
			public boolean scrolled(int amount) {
				playerShip.getController().accelerate(amount);
				return true;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean keyDown(int keycode) {
				return false;
			}
		});
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		processInputAndUpdate(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(playerShip.getCenterX(), playerShip.getCenterY(), 0);
		camera.update();
		worldRenderer.setProjectionMatrix(camera.combined);

		drawBackground();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		doBatch();
		batch.setProjectionMatrix(screenRenderer.getProjectionMatrix());
		drawInterface(batch, delta);
		batch.end();
	}

	private void processInputAndUpdate(float delta) {
		ShipController controller = playerShip.getController();

		if (Gdx.input.isTouched(0)) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			System.out.println("touched: " + touchPos.x + ", " + touchPos.y);
			camera.unproject(touchPos);

//			System.out.println("touched: " + touchPos.x + ", " + touchPos.y);
			// controller.moveTo(touchPos.x, touchPos.y);
			// shipAngleRad = (float) Math.atan2(touchPos.y - ship.y -
			// ship.height /
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
		if (Gdx.input.isTouched(1)) {
			// System.out.println("touched 2");
		}
		if (Gdx.input.isKeyPressed(Keys.R)) {
			resetPlayerShip();
		}
		float magnitude = 1;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			controller.overrideAuto();
			controller.turnLeft(magnitude);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			controller.overrideAuto();
			controller.turnRight(magnitude);
		} else {
			controller.turnLeft(0);
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			controller.overrideAuto();
			controller.accelerate(magnitude);
		} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			controller.overrideAuto();
			controller.accelerate(-magnitude);
		} else {
			controller.accelerate(0);
		}
		if (Gdx.input.isKeyJustPressed(Keys.TAB)) {
			shipNum++;
			shipNum %= numShips;
			playerShip.setShipNumber(shipNum);
		}
//		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
//			playerShip.enableFire();
//		} else {
//			playerShip.disableFire();
//		}

		RootManager.updateAll(delta);
	}

	private void resetPlayerShip() {
		playerShip.setCenterPosition(0, 0);
	}

	private void doBatch() {
		float left = camera.position.x - camera.viewportWidth / 2;
		float top = camera.position.y - camera.viewportHeight / 2;
		Rectangle viewportRect = new Rectangle(left, top, camera.viewportWidth, camera.viewportHeight);
		RootManager.drawAll(batch, viewportRect, debug, worldRenderer);
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
		screenRenderer.setColor(Color.ORANGE);
		screenRenderer.begin(ShapeType.Line);
		screenRenderer.circle(50, 50, 10);
		screenRenderer.circle(100, 100, 10);
		screenRenderer.circle(-50, -50, 10);
		screenRenderer.circle(-100, -100, 10);
		screenRenderer.end();
		
		worldRenderer.setColor(Color.GREEN);
		worldRenderer.begin(ShapeType.Line);
		worldRenderer.circle(50, 50, 10);
		worldRenderer.circle(100, 100, 10);
		worldRenderer.circle(-50, -50, 10);
		worldRenderer.circle(-100, -100, 10);
		worldRenderer.end();
		
		
		
		ShapeRenderer sr = worldRenderer;

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
			int fpsInt = (int) (fpsNumFrame / fpsCumulativeDelta * 10);
			fps = (fpsInt / 10.0f);
			fpsCumulativeDelta = 0;
			fpsNumFrame = 0;
		}
		float x = Math.round(playerShip.getCenterX());
		float y = Math.round(playerShip.getCenterY());
		float angle = Math.round(playerShip.getAngleDegrees());
		font.draw(batch, fps + " fps      X=" + x + " Y=" + y + " Angle=" + angle, 5 - screenWidth / 2,
				20 - screenHeight / 2);
	}
}
