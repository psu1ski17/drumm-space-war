package org.drumm.gdx.space;

public class ForceBasedMovable extends SpaceObject implements Movable, Thrustable {

	private float angularAccelleration;
	private float maxAngularAccelleration;
	private float angularDrag;
	private float angularVelocity;
	private float maxAngularVelocity;
	private float thrust;
	private float speed;
	private float drag;
	private float maxSpeed;
	private float maxReverseSpeed;
	private float maxFowardAcceleration;
	private float maxReverseAcceleration;

	public ForceBasedMovable(float x, float y, float degrees, float width, float height, float angularAccelleration,
			float maxAngularAccelleration, float angularDrag, float angularVelocity, float maxAngularVelocity,
			float thrust, float speed, float drag, float maxSpeed, float maxReverseSpeed, float maxForwardAcceleration,
			float maxReverseAcceleration) {
		super(x, y, degrees, width, height);
		this.angularAccelleration = angularAccelleration;
		this.maxAngularAccelleration = maxAngularAccelleration;
		this.angularDrag = angularDrag;
		this.angularVelocity = angularVelocity;
		this.maxAngularVelocity = maxAngularVelocity;
		this.thrust = thrust;
		this.speed = speed;
		this.drag = drag;
		this.maxSpeed = maxSpeed;
		this.maxReverseSpeed = maxReverseSpeed;
		this.maxFowardAcceleration = maxForwardAcceleration;
		this.maxReverseAcceleration = maxReverseAcceleration;
	}

	@Override
	public void accelerate(float percentage) {
		if (percentage == 0) {
			thrust = 0;
		} else {
			float maxAccel = (percentage > 0 ? maxFowardAcceleration : maxReverseAcceleration);
			thrust = percentage * maxAccel + drag;
		}
	}

	@Override
	public void turnLeft(float magnitude) {
		if (magnitude == 0) {
			angularAccelleration = 0;
		} else {
			angularAccelleration = magnitude * maxAngularAccelleration + angularDrag;
		}
	}

	@Override
	public void turnRight(float magnitude) {
		if (magnitude == 0) {
			angularAccelleration = 0;
		} else {
			angularAccelleration = -magnitude * maxAngularAccelleration - angularDrag;
		}
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		angularVelocity += angularAccelleration * delta;
		if (angularVelocity > 0) {
			angularVelocity -= angularDrag * delta;
			angularVelocity = Math.min(maxAngularVelocity, angularVelocity);
			if (angularVelocity < 0) {
				angularVelocity = 0;
			}
		}
		if (angularVelocity < 0) {
			angularVelocity += angularDrag * delta;
			angularVelocity = Math.max(-maxAngularVelocity, angularVelocity);
			if (angularVelocity > 0) {
				angularVelocity = 0;
			}
		}

		super.moveAngleDegrees(angularVelocity * delta);
		// shipAngleDegrees += angularVelocity * delta;
		// shipAngleDegrees %= 360;
		// updateRadians();

		speed += thrust * delta;
		if (speed > 0) {
			speed -= drag * delta;
			speed = Math.min(maxSpeed, speed);
			if (speed < 0) {
				speed = 0;
			}
		}
		if (speed < 0) {
			speed += drag * delta;
			speed = Math.max(-maxReverseSpeed, speed);
			if (speed > 0) {
				speed = 0;
			}
		}

		float radians = getAngleRadians();
		float speedX = (float) (-Math.sin(radians) * speed);
		float speedY = (float) (Math.cos(radians) * speed);

		// if (angularVelocity != 0)
//		System.out.println("ANGULAR: vel=" + angularVelocity + " acc=" + angularAccelleration + " degrees="
//				+ super.getAngleDegrees());
		// if (speed != 0)
////		System.out.println("speed=" + speed + " speedX=" + speedX + " speedY=" + speedY + " Angle="
//				+ super.getAngleDegrees() + " x=" + super.getX() + " y=" + super.getY());
		super.movePosition(speedX * delta, speedY * delta);
	}

	public float getAngularAccelleration() {
		return angularAccelleration;
	}

	public void setAngularAccelleration(float angularAccelleration) {
		this.angularAccelleration = angularAccelleration;
	}

	public float getMaxAngularAccelleration() {
		return maxAngularAccelleration;
	}

	public void setMaxAngularAccelleration(float maxAngularAccelleration) {
		this.maxAngularAccelleration = maxAngularAccelleration;
	}

	public float getAngularDrag() {
		return angularDrag;
	}

	public void setAngularDrag(float angularDrag) {
		this.angularDrag = angularDrag;
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public float getMaxAngularVelocity() {
		return maxAngularVelocity;
	}

	public void setMaxAngularVelocity(float maxAngularVelocity) {
		this.maxAngularVelocity = maxAngularVelocity;
	}

	public float getThrust() {
		return thrust;
	}

	public void setThrust(float thrust) {
		this.thrust = thrust;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDrag() {
		return drag;
	}

	public void setDrag(float drag) {
		this.drag = drag;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getMaxReverseSpeed() {
		return maxReverseSpeed;
	}

	public void setMaxReverseSpeed(float maxReverseSpeed) {
		this.maxReverseSpeed = maxReverseSpeed;
	}
}
