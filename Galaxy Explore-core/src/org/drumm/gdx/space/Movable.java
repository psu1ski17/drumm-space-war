package org.drumm.gdx.space;

public interface Movable extends ISpaceObject {
	public void accelerate(float percentage);
	public void turnRight(float percentage);
	public void turnLeft(float percentage);
}
