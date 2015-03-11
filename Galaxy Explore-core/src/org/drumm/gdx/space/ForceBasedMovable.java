package org.drumm.gdx.space;

public class ForceBasedMovable extends BaseMovable implements ShipController,Thrustable {

	private float angularAccelleration;
	private float maxAngularAccelleration;
	private float angularDrag;
	private float maxAngularVelocity;
	private float thrust;
	private float drag;
	private float maxSpeed;
	private float maxReverseSpeed;
	private float maxFowardAcceleration;
	private float maxReverseAcceleration;
	private float destAngle;
	private float destX;
	private float destY;
	private boolean activeMoveTo;
	private boolean activeTurnTo;
	private boolean cheatTurnTo = true;

	public ForceBasedMovable(ISpaceObject object, float angularAccelleration,
			float maxAngularAccelleration, float angularDrag, float angularVelocity, float maxAngularVelocity,
			float thrust, float speed, float drag, float maxSpeed, float maxReverseSpeed, float maxForwardAcceleration,
			float maxReverseAcceleration) {
		super(object, speed, angularVelocity);
		this.angularAccelleration = angularAccelleration;
		this.maxAngularAccelleration = maxAngularAccelleration;
		this.angularDrag = angularDrag;
		this.maxAngularVelocity = maxAngularVelocity;
		this.thrust = thrust;
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
	public void turnRight(float magnitude) {
		if (magnitude == 0) {
			angularAccelleration = 0;
		} else {
			angularAccelleration = magnitude * maxAngularAccelleration + angularDrag;
		}
	}

	@Override
	public void turnLeft(float magnitude) {
		if (magnitude == 0) {
			angularAccelleration = 0;
		} else {
			angularAccelleration = -magnitude * maxAngularAccelleration - angularDrag;
		}
	}

	@Override
	public void update(float delta) {
		doAutopilot();
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

		// super.update(delta);
	}

	private void doAutopilot() {
		final float degrees=spaceObject.getAngleDegrees();
		final float x=spaceObject.getCenterX();
		final float y =spaceObject.getCenterY();
		boolean doTurnTo = activeTurnTo;
		float angleTo = destAngle;
		if (activeMoveTo) {
			angleTo = angleTo(destX, destY);
			float angDiff = angDist(degrees, angleTo);
//			if (angDiff>45){
//			System.out.println("anglediff= "+angDiff+ " angle to:" + angleTo + " from " + x + ", " + y + " to: " + destX + "," + destY
//					+ "current:" + degrees);
//			}
			float dist = dist(destX, destY, x, y);

			if (Math.abs(dist) < 0.01) {
				activeMoveTo = false;
				spaceObject.setCenterPosition(destX, destY);
				accelerate(0);
			} else {
				doTurnTo = true;
				float targetVelocity = dist * 2;
				float velDiff = targetVelocity - speed;
				float neededThurst = velDiff / maxFowardAcceleration * 5;
				if (angDiff < 45) {
					accelerate(neededThurst);
				} else {
					accelerate(-0.5f);
				}
			}

		}
		// positive angular velocity means turning right
		if (doTurnTo) {
			// Do turn to if there is an active turn to command OR the move to
			// needs it.
			if (cheatTurnTo) {
				spaceObject.setAngleDegrees(angleTo);
			} else {
				float angDiff = angDist(degrees, angleTo);
				if (Math.abs(angDiff) < 0.001) {
					activeTurnTo = false;
					spaceObject.setAngleDegrees(angleTo);
					angularVelocity = 0;
					turnLeft(0);
				} else {
					float targetVelocity = angDiff * 2;
					float velDiff = Math.abs(targetVelocity - angularVelocity);
					float neededThrust = velDiff / maxAngularAccelleration * 5;
					if (targetVelocity > angularVelocity) {
						turnRight(neededThrust);
					} else if (targetVelocity < angularVelocity) {
						turnLeft(neededThrust);
					}
				}
			}
		}

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

	public float getMaxAngularVelocity() {
		return maxAngularVelocity;
	}

	public void setMaxAngularVelocity(float maxAngularVelocity) {
		this.maxAngularVelocity = maxAngularVelocity;
	}

	@Override
	public float getThrust() {
		return thrust;
	}

	public void setThrust(float thrust) {
		this.thrust = thrust;
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

	@Override
	public void moveTo(float x, float y) {
		activeMoveTo = true;
		activeTurnTo = false;
		destX = x;
		destY = y;
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

	private float dist(float x, float y, float x2, float y2) {
		float distSquared = (x2 - x) * (x2 - x) + (y2 - y) * (y2 - y);
		float dist = (float) Math.sqrt(distSquared);
		return dist;
	}

	private float angDist(float ang1, float ang2) {
		float a = ang2 - ang1;
		a = (a + 180f) % 360f - 180f;
		return a;
	}

	@Override
	public void turnTo(float angle) {
		activeMoveTo = false;
		activeTurnTo = true;
		destAngle = angle;
		if (cheatTurnTo) {
			spaceObject.setAngleDegrees(angle);
		}
	}

	@Override
	public void overrideAuto() {
		activeMoveTo=false;
		activeTurnTo=false;
	}
}
