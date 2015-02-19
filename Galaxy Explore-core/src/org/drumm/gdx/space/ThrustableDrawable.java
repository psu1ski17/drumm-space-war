package org.drumm.gdx.space;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ThrustableDrawable extends SimpleDrawable {

	private Movable movable;
	private TextureRegion[] ships;
	private TextureRegion[] engines;
	private int shipNumber;
	private float shipScale=1f;

	public ThrustableDrawable(Movable movable, TextureRegion[] ships, TextureRegion[] engines) {
		super(movable, ships[0]);
		this.movable=movable;
		this.ships=ships;
		this.engines=engines;
		this.shipNumber=0;
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		float x = movable.getX();
		float y = movable.getY();
		float width=movable.getWidth();
		float height=movable.getHeight();
		float thrust=0;
		if (movable instanceof Thrustable){
			thrust=((Thrustable) movable).getThrust();
		}		
		float shipAngleDegrees=movable.getAngleDegrees();
//		batch.draw(ships[shipNumber], x, y, width / 2, height / 2, width, height, shipScale, shipScale,
//				shipAngleDegrees - 90f, false);
		if (thrust > 0) {
			batch.draw(engines[shipNumber], x, y, width / 2, height / 2, width, height, shipScale, shipScale,
					shipAngleDegrees - 90f, false);
		}

	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
		super.textureRegion=ships[shipNumber];
	}

}
