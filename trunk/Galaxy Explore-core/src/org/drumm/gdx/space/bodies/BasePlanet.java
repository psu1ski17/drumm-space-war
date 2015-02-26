package org.drumm.gdx.space.bodies;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.SimpleDrawer;
import org.drumm.gdx.space.SpaceObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasePlanet extends SpaceObject implements Drawable{

	private Texture texture;

	public BasePlanet(float centerx, float centery, float degrees, float width, float height, Texture texture) {
		super(centerx-width/2, centery-height/2, degrees, width, height);
		this.texture=texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		SimpleDrawer.draw(batch, this, texture);
	}
}
