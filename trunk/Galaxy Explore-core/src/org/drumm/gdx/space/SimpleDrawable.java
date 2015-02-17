package org.drumm.gdx.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleDrawable implements Drawable {

	private Texture texture;
	private ISpaceObject spaceObject;
	private float scaleX;
	private float angle;
	private float scaleY;

	public SimpleDrawable(ISpaceObject spaceObject, Texture texture) {
		this.spaceObject=spaceObject;
		this.texture=texture;
		this.scaleX=spaceObject.getWidth()/texture.getWidth();
		this.scaleY=spaceObject.getHeight()/texture.getHeight();
		this.angle=spaceObject.getAngleDegrees();
	}

	@Override
	public void draw(SpriteBatch batch) {
		float x = spaceObject.getX();
		float y = spaceObject.getY();
		float width=spaceObject.getWidth();
		float height=spaceObject.getHeight();
		batch.draw(texture,x,y,width/2,height/2,width,height,1,1,angle,0,0,texture.getWidth(),texture.getHeight(),false,false);
	}

}
