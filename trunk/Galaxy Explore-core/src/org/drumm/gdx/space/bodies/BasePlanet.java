package org.drumm.gdx.space.bodies;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.SimpleDrawable;
import org.drumm.gdx.space.SpaceObject;
import org.drumm.gdx.space.common.HasDrawable;

import com.badlogic.gdx.graphics.Texture;

public class BasePlanet extends SpaceObject implements HasDrawable{

	private Texture texture;
	private Drawable drawable;

	public BasePlanet(float centerx, float centery, float degrees, float width, float height, Texture texture) {
		super(centerx-width/2, centery-height/2, degrees, width, height);
		this.texture=texture;
		this.drawable=new SimpleDrawable(this, texture);
	}
	
	public Drawable getDrawable(){
		return drawable;
	}

}
