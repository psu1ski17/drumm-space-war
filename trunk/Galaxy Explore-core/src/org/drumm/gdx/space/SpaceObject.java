package org.drumm.gdx.space;

import com.badlogic.gdx.math.Circle;

public class SpaceObject implements ISpaceObject {
	protected float x;
	protected float y;
	protected float degrees;
	private float width;
	private float height;
	private Circle location;
	private float radius;

	public SpaceObject() {
		this(0, 0, 0, 0, 0);
	}

	public SpaceObject(float x, float y, float degrees, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.degrees = degrees;
		this.width = width;
		this.height = height;
		location = new Circle(getCenterX(), getCenterY(), radius);
		updateRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setPositionOrientaiton(float,
	 * float, float)
	 */
	@Override
	public void setPositionOrientaiton(float x, float y, float degrees) {
		this.x = x;
		this.y = y;
		this.degrees = degrees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setPosition(float, float)
	 */
	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#movePosition(float, float)
	 */
	@Override
	public void movePosition(float deltaX, float deltaY) {
		this.x += deltaX;
		this.y += deltaY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setAngleDegrees(float)
	 */
	@Override
	public void setAngleDegrees(float degrees) {
		this.degrees = degrees;
		this.degrees %= 360;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#moveAngleDegrees(float)
	 */
	@Override
	public void moveAngleDegrees(float degreesDelta) {
		this.degrees += degreesDelta;
		this.degrees %= 360;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getAngleDegrees()
	 */
	@Override
	public float getAngleDegrees() {
		return this.degrees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setAngleRadians(float)
	 */
	@Override
	public void setAngleRadians(float radians) {
		this.degrees = (float) (radians * 180.0f / Math.PI);
		this.degrees %= 360;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#moveAngleRadians(float)
	 */
	@Override
	public void moveAngleRadians(float radiansDelta) {
		this.degrees += (float) (radiansDelta * 180.0f / Math.PI);
		this.degrees %= 360;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getAngleRadians()
	 */
	@Override
	public float getAngleRadians() {
		return (float) (this.degrees * Math.PI / 180.0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getX()
	 */
	@Override
	public float getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getY()
	 */
	@Override
	public float getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getCenterX()
	 */
	@Override
	public float getCenterX() {
		return x + width / 2.0f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getCenterY()
	 */
	@Override
	public float getCenterY() {
		return y + height / 2.0f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setWidth(float)
	 */
	@Override
	public void setWidth(float width) {
		this.width = width;
		updateRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setHeight(float)
	 */
	@Override
	public void setHeight(float height) {
		this.height = height;
		updateRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getDegrees()
	 */
	@Override
	public float getDegrees() {
		return degrees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setDegrees(float)
	 */
	@Override
	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getWidth()
	 */
	@Override
	public float getWidth() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#getHeight()
	 */
	@Override
	public float getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setX(float)
	 */
	@Override
	public void setX(float x) {
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drumm.gdx.space.ISpaceObject#setY(float)
	 */
	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void setCenterPosition(float x, float y) {
		setPosition(x - width / 2f, y - height / 2f);

	}

	@Override
	public final void update(float delta) {
		doSubUpdate(delta);
		updateLocation();
	}

	protected void doSubUpdate(float delta) {

	}

	protected void updateLocation() {
		location.x = getCenterX();
		location.y = getCenterY();
	}

	private void updateRadius() {
		if (height > width) {
			radius = height/2.0f;
		} else {
			radius = width/2.0f;
		}
		location.radius=radius;
	}

	@Override
	public Circle getLocation() {
		return location;
	}
}
