package org.drumm.gdx.space;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ThrustableDrawable implements Drawable {

	private Movable movable;
	private TextureRegion[] ships;
	private TextureRegion[] engines;
	private int shipNumber;
	private float shipScale=1f;

	public ThrustableDrawable(Movable movable, TextureRegion[] ships, TextureRegion[] engines) {
		this.movable=movable;
		this.ships=ships;
		this.engines=engines;
		this.shipNumber=0;
	}

	@Override
	public void draw(SpriteBatch batch) {
		float x = movable.getX();
		float y = movable.getY();
		float width=movable.getWidth();
		float height=movable.getHeight();
		float thrust=1;
		
		float shipAngleDegrees=movable.getAngleDegrees();
		batch.draw(ships[shipNumber], x, y, width / 2, height / 2, width, height, shipScale, shipScale,
				shipAngleDegrees - 90f, false);

		if (thrust > 0) {
			batch.draw(engines[shipNumber], x, y, width / 2, height / 2, width, height, shipScale, shipScale,
					shipAngleDegrees - 90f, false);
		}

	}

}
