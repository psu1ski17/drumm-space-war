package org.drumm.gdx.space;

public class BaseMovable extends SpaceObject{
	protected float angularVelocity;
	protected float speed;

	public BaseMovable(float x, float y, float degrees, float width, float height, float speed, float angularVelocity) {
		super(x, y, degrees, width, height);
		this.speed=speed;
		this.angularVelocity=angularVelocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	
	@Override
	public void update(float delta){
//		super.update(delta);
		float radians = getAngleRadians();
		float speedX = (float) (Math.sin(radians) * speed);
		float speedY = (float) (Math.cos(radians) * speed);
		super.moveAngleDegrees(angularVelocity * delta);
		super.movePosition(speedX * delta, speedY * delta);
	}
}
