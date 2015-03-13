package org.drumm.gdx.space;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ThrustableDrawable implements Drawable{

	private ShipController controller;
	private TextureRegion[] ships;
	private TextureRegion[] engines;
	private int shipNumber;
	private ISpaceObject object;

	public ThrustableDrawable(ISpaceObject object, ShipController controller, TextureRegion[] ships, TextureRegion[] engines) {
		this.ships=ships;
		this.controller=controller;
		this.object=object;
		this.ships=ships;
		this.engines=engines;
		this.shipNumber=0;
	}

	@Override
	public void draw(SpriteBatch batch) {
		SimpleDrawer.draw(batch, object, ships[shipNumber]);
		float thrust = 0;
		if (controller instanceof Thrustable){
			thrust=((Thrustable) controller).getThrust();
		}		
		if (thrust > 0) {
			SimpleDrawer.draw(batch, object, engines[shipNumber]);
//			batch.draw(engines[shipNumber], x, y, width / 2, height / 2, width, height, shipScale, shipScale,
//					shipAngleDegrees - 90f, false);
		}

	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
	}

	@Override
	public void drawDebug(ShapeRenderer worldRenderer) {
		// TODO Auto-generated method stub
		
	}

}
