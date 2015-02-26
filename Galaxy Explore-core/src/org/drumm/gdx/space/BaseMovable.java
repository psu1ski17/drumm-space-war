package org.drumm.gdx.space;

import org.drumm.gdx.space.common.Updateable;

public class BaseMovable implements Updateable {
	protected float angularVelocity;
	protected float speed;
	protected ISpaceObject spaceObject;

	public BaseMovable(ISpaceObject spaceObject, float speed, float angularVelocity) {
		this.spaceObject=spaceObject;
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
		float radians = spaceObject.getAngleRadians();
		float speedX = (float) (Math.sin(radians) * speed);
		float speedY = (float) (Math.cos(radians) * speed);
		spaceObject.moveAngleDegrees(angularVelocity * delta);
		spaceObject.movePosition(speedX * delta, speedY * delta);
	}
}
