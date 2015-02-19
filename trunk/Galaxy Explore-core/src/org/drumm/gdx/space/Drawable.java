package org.drumm.gdx.space;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Drawable {
	public void draw(SpriteBatch batch);
	public Rectangle getLocation();
}
