package org.drumm.gdx.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleDrawer {

	public static void draw(SpriteBatch batch, ISpaceObject spaceObject, Texture texture){
		draw(batch, spaceObject, texture,1);
	}
	public static void draw(SpriteBatch batch, ISpaceObject spaceObject, Texture texture, float scale){
		float x = spaceObject.getX();
		float y = spaceObject.getY();
		float width = spaceObject.getWidth();
		float height = spaceObject.getHeight();
		float angle = -spaceObject.getAngleDegrees();
		batch.draw(texture, x, y, width / 2, height / 2, width, height, scale, scale, angle, 0, 0, texture.getWidth(),
				texture.getHeight(), false, false);
	}
	
	public static void draw(SpriteBatch batch, ISpaceObject spaceObject, TextureRegion textureRegion){
		draw(batch, spaceObject, textureRegion, 1);
	}
	
	public static void draw(SpriteBatch batch, ISpaceObject spaceObject, TextureRegion textureRegion, float scale){
		float x = spaceObject.getX();
		float y = spaceObject.getY();
		float width = spaceObject.getWidth();
		float height = spaceObject.getHeight();
		float angle = -spaceObject.getAngleDegrees();
		batch.draw(textureRegion,x,y,width/2,height/2,width,height,scale,scale,angle);
	}
}
