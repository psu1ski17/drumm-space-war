package org.drumm.gdx.space;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public interface Drawable {
	public void draw(SpriteBatch batch);
	
	public void drawDebug(ShapeRenderer sr);
}
