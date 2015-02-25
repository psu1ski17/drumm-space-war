package org.drumm.gdx.space;

public interface Movable extends ISpaceObject {
	public void accelerate(float percentage);
	public void turnRight(float percentage);
	public void turnLeft(float percentage);
	public void moveTo(float x, float y);
	public void turnTo(float angle);
}
