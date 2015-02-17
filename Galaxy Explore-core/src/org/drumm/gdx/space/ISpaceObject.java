package org.drumm.gdx.space;

public interface ISpaceObject {

	public abstract void setPositionOrientaiton(float x, float y, float degrees);

	public abstract void setPosition(float x, float y);

	public abstract void movePosition(float deltaX, float deltaY);

	public abstract void setAngleDegrees(float degrees);

	public abstract void moveAngleDegrees(float degreesDelta);

	public abstract float getAngleDegrees();

	public abstract void setAngleRadians(float radians);

	public abstract void moveAngleRadians(float radiansDelta);

	public abstract float getAngleRadians();

	public abstract float getX();

	public abstract float getY();

	public abstract float getCenterX();

	public abstract float getCenterY();

	public abstract void setWidth(float width);

	public abstract void setHeight(float height);

	public abstract float getDegrees();

	public abstract void setDegrees(float degrees);

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract void setX(float x);

	public abstract void setY(float y);

	void update(float delta);

	void setCenterPosition(int x, int y);

}