package org.drumm.gdx.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleDrawable implements Drawable {

	protected Texture texture;
	protected ISpaceObject spaceObject;
	protected float scaleX;
	protected float scaleY;
	protected TextureRegion textureRegion;

	public SimpleDrawable(ISpaceObject spaceObject, Texture texture) {
		this.spaceObject = spaceObject;
		this.texture = texture;
		this.scaleX = spaceObject.getWidth() / texture.getWidth();
		this.scaleY = spaceObject.getHeight() / texture.getHeight();
	}

	public SimpleDrawable(ISpaceObject spaceObject, TextureRegion textureRegion) {
		this.spaceObject = spaceObject;
		this.textureRegion = textureRegion;
		this.scaleX = spaceObject.getWidth() / textureRegion.getRegionWidth();
		this.scaleY = spaceObject.getHeight() / textureRegion.getRegionHeight();
	}

	@Override
	public void draw(SpriteBatch batch) {
		float x = spaceObject.getX();
		float y = spaceObject.getY();
		float width = spaceObject.getWidth();
		float height = spaceObject.getHeight();
		float angle = -spaceObject.getAngleDegrees();
		if (texture != null) {
			batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, angle, 0, 0, texture.getWidth(),
					texture.getHeight(), false, false);
		}
		if (textureRegion!=null){
			batch.draw(textureRegion,x,y,width/2,height/2,width,height,1,1,angle);
		}
	}

	@Override
	public Rectangle getLocation() {
		return new Rectangle(spaceObject.getX(), spaceObject.getY(), spaceObject.getWidth(), spaceObject.getHeight());
	}

}
