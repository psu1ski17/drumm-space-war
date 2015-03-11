package org.drumm.gdx.space;

public class SimpleMovable extends BaseMovable implements ShipController, Thrustable {

	private float throttleDelta;
	private float maxSpeed;
	private float minSpeed = 0;

	public SimpleMovable(ISpaceObject spaceObject, float maxSpeed, int throttlePositions) {
		this(spaceObject, maxSpeed, throttlePositions, 0, 0);
	}

	public SimpleMovable(ISpaceObject spaceObject, float maxSpeed, int throttlePositions, float speed,
			float angularVelocity) {
		super(spaceObject, speed, angularVelocity);
		this.maxSpeed = maxSpeed;
		this.throttleDelta = maxSpeed / throttlePositions;
	}

	@Override
	public void accelerate(float percentage) {
//		System.out.print(speed);
		if (percentage > 0) {
			speed += throttleDelta;
			if (speed > maxSpeed) {
				speed = maxSpeed;
			}
		} else if (percentage < 0) {
			speed -= throttleDelta;
			if (speed < minSpeed) {
				speed = minSpeed;
			}
		}
//		System.out.println(" to " + speed);
	}

	@Override
	public void turnRight(float percentage) {

	}

	@Override
	public void turnLeft(float percentage) {
	}

	@Override
	public void moveTo(float x, float y) {
//		System.out.print(speed);
		if (speed < throttleDelta) {
			speed = throttleDelta;
		}
//		System.out.println(" to " + speed);
		float degrees = angleTo(x, y);
		turnTo(degrees);
	}

	private float angleTo(float x, float y) {
		float dx = (x - spaceObject.getCenterX());
		float dy = (y - spaceObject.getCenterY());
		float m = dx / dy;
		float angleToRadians = (float) Math.atan(m);
		float angleTo = (float) (angleToRadians * 180 / Math.PI);
		if (dy < 0) {
			angleTo += 180;
		}
		return angleTo;
	}

	@Override
	public void turnTo(float angle) {
		spaceObject.setAngleDegrees(angle);
	}

	@Override
	public void overrideAuto() {

	}

	@Override
	public float getThrust() {
		return speed;
	}

}
