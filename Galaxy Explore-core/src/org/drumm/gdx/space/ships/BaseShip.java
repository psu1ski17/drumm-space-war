package org.drumm.gdx.space.ships;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.ForceBasedMovable;
import org.drumm.gdx.space.ShipController;
import org.drumm.gdx.space.SpaceObject;
import org.drumm.gdx.space.ThrustableDrawable;
import org.drumm.gdx.space.common.Updateable;
import org.drumm.gdx.space.ships.Shootable.HasShootable;
import org.drumm.gdx.space.weapons.HasWeapons;
import org.drumm.gdx.space.weapons.guns.IGun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BaseShip extends SpaceObject implements Drawable, HasWeapons, HasShootable, Updateable{
	protected TextureRegion[] ships;
	protected TextureRegion[] engines;
	private int shipNumber;
	private int numShips;
	private float shipScale;
	// float x;
	// float y;
	// float width;
	// float height;
	// private float drag;
	// private float speed;
	// private float thrust;
	// private float maxSpeed;
	// private float maxReverseSpeed;
	// private float maxThrust;
	// private float angularVelocity;
	// private float maxAngularVelocity;
	// private float maxAngularAccelleration;
	// private float angularAccelleration;
	// private int angularDrag;
	private ShipController controller;
	private Drawable drawable;
	private Array<IGun> guns;
	private Shootable shootable;

	public BaseShip(TextureRegion[] ships, TextureRegion[] engines, int numShips, float shipScale) {
		guns=new Array<IGun>();
		this.ships = ships;
		this.engines = engines;
		// shipAngleDegrees = 0;
		// updateRadians();

		this.numShips = numShips;
		this.shipScale = shipScale;

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
		this.controller = new ForceBasedMovable(this, angularAccelleration, maxAngularAccelleration,
				angularDrag, angularVelocity, maxAngularVelocity, thrust, speed, drag, maxSpeed, maxReverseSpeed, maxFowardAcceleration, maxReverseAcceleration);
		this.drawable=new ThrustableDrawable(this,controller, ships, engines);
		this.shootable=new BaseShootable(this, 100);
		setShipNumber(0);
	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
		int width = ships[shipNumber].getRegionWidth();
		int height = ships[shipNumber].getRegionHeight();
		setWidth(width*shipScale);
		setHeight(height*shipScale);
		if (drawable instanceof ThrustableDrawable){
			((ThrustableDrawable) drawable).setShipNumber(shipNumber);
		}
	}

	@Override
	public void enableFire() {
		for (IGun gun:guns){
			gun.enableFire();
		}
	}

	@Override
	public void disableFire() {
		for (IGun gun:guns){
			gun.disableFire();
		}
	}

	@Override
	public void addGun(IGun gun) {
		guns.add(gun);
	}

	@Override
	public void removeGun(IGun gun) {
		guns.removeValue(gun, true);
	}

	@Override
	public Array<IGun> getAllGuns() {
		return new Array<IGun>(guns);
	}

	@Override
	public Shootable getShootable() {
		return shootable;
	}

	@Override
	public void doSubUpdate(float delta) {
		super.doSubUpdate(delta);
		for(IGun gun:guns){
			gun.update(delta);
		}
		shootable.update(delta);
		controller.update(delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
		drawable.draw(batch);
		shootable.draw(batch);
	}

	public ShipController getController() {
		return controller;
	}

	// public void setShipPosition(float x, float y) {
	// this.x = x;
	// this.y = y;
	// }

	// public void setShipCenterPosition(int x, int y) {
	// setShipPosition(x - width / 2, y - height / 2);
	//
	// }

	// public void applyForwardThrust(float throttle) {
	// thrust = throttle * maxThrust;
	// }
	//
	// public void update(float delta) {
	// angularVelocity+=angularAccelleration*delta;
	// if (angularVelocity>0){
	// angularVelocity-=angularDrag*delta;
	// angularVelocity=Math.min(maxAngularVelocity,angularVelocity);
	// if (angularVelocity<0){
	// angularVelocity=0;
	// }
	// }
	// if (angularVelocity<0){
	// angularVelocity+=angularDrag*delta;
	// angularVelocity=Math.max(-maxAngularVelocity,angularVelocity);
	// if (angularVelocity>0){
	// angularVelocity=0;
	// }
	// }
	//
	//
	// shipAngleDegrees += angularVelocity * delta;
	// shipAngleDegrees %= 360;
	// updateRadians();
	//
	// speed+=thrust*delta;
	// if (speed > 0) {
	// speed -= drag * delta;
	// speed = Math.min(maxSpeed, speed);
	// if (speed < 0) {
	// speed = 0;
	// }
	// }
	// if (speed < 0) {
	// speed += drag * delta;
	// speed=Math.max(-maxReverseSpeed, speed);
	// if (speed > 0) {
	// speed = 0;
	// }
	// }
	//
	// double speedX = -Math.sin(shipAngleRad) * speed;
	// double speedY = Math.cos(shipAngleRad) * speed;
	//
	// if (angularVelocity!=0)
	// System.out.println("vel=" + angularVelocity + " acc=" +
	// angularAccelleration + " degrees=" + shipAngleDegrees );
	// if (speed != 0)
	// System.out.println("speed=" + speed + " speedX=" + speedX + " speedY=" +
	// speedY + " Angle="
	// + shipAngleDegrees + " x=" + x + " y=" + y);
	// x += speedX * delta;
	// y += speedY * delta;
	//
	// }

	// public float getCenterX() {
	// return x+width/2.0f;
	// }
	//
	// public float getCenterY() {
	// return y+height/2.0f;
	// }

}
