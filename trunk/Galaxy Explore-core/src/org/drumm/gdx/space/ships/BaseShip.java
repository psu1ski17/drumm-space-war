package org.drumm.gdx.space.ships;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.ForceBasedMovable;
import org.drumm.gdx.space.Movable;
import org.drumm.gdx.space.ThrustableDrawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class BaseShip {
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
	private Movable movable;
	private Drawable drawable;

	public BaseShip(TextureRegion[] ships, TextureRegion[] engines, int numShips, float shipScale) {

		this.ships = ships;
		this.engines = engines;
		// shipAngleDegrees = 0;
		// updateRadians();

		this.numShips = numShips;
		this.shipScale = shipScale;

		float x = 0;
		float y = 0;
		float angularDegrees=0;
		float width = 0;
		float height = 0;

		float thrust=0;
		float drag = 00f;
		float speed = 0;
		float maxSpeed = 500;
		float maxReverseSpeed = 0;
		float angularVelocity = 0;
		float angularDrag = 2000;
		float maxAngularVelocity = 150;
		float maxAngularAccelleration = 2000;
		float angularAccelleration = 0;
		float maxFowardAcceleration=500f;
		float maxReverseAcceleration=1000f;
		this.movable = new ForceBasedMovable(x, y, angularDegrees, width*shipScale, height*shipScale, angularAccelleration, maxAngularAccelleration,
				angularDrag, angularVelocity, maxAngularVelocity, thrust, speed, drag, maxSpeed, maxReverseSpeed, maxFowardAcceleration, maxReverseAcceleration);
		this.drawable=new ThrustableDrawable(movable, ships, engines);
		setShipNumber(0);

	}

	// private void updateRadians() {
	// shipAngleRad = (float) (shipAngleDegrees * Math.PI / 180.0);
	//
	// }

	public Drawable getDrawable() {
		return drawable;
	}

	// public void turnLeft(float magnitude) {
	// angularAccelleration = +magnitude * maxAngularAccelleration+angularDrag;
	// }
	//
	// public void turnRight(float magnitude) {
	// angularAccelleration = -magnitude * maxAngularAccelleration-angularDrag;
	// }

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
		int width = ships[shipNumber].getRegionWidth();
		int height = ships[shipNumber].getRegionHeight();
		movable.setWidth(width*shipScale);
		movable.setHeight(height*shipScale);
	}

	public Movable getMovable() {
		return movable;
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
