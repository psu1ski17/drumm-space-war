package org.drumm.gdx.space;

import org.drumm.gdx.space.common.Updateable;

public interface ShipController extends Updateable {
	public void accelerate(float percentage);

	public void turnRight(float percentage);

	public void turnLeft(float percentage);

	public void moveTo(float x, float y);

	public void turnTo(float angle);
	
	public void overrideAuto();
}
