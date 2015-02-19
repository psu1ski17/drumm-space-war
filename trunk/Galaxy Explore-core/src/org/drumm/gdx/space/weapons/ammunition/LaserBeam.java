package org.drumm.gdx.space.weapons.ammunition;

import org.drumm.gdx.space.BaseMovable;
import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.SimpleDrawable;
import org.drumm.gdx.space.common.RootManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class LaserBeam extends BaseMovable implements IDrawableWeapon, Poolable {
	
	private static Texture texture;

	static{
		texture = new Texture(Gdx.files.internal("reddot.png"));
	}
	private float life;
	private float ttl;

	public LaserBeam() {
		super(0, 0, 0, 0, 0, 0, 0);
	}
	
	public void init(float x, float y, float degrees, float width, float height, float speed, float ttl){
		this.x=x;
		this.y=y;
		this.degrees=degrees;
		this.width=width;
		this.height=height;
		this.speed=speed;
		this.ttl=ttl;
		life=0;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		life+=delta;
		if (life>ttl){
			RootManager.getWeaponDrawManager().remove(this);
			Pools.free(this);
		}
	}

	@Override
	public Drawable getDrawable() {
		return new SimpleDrawable(this, texture);
	}

	@Override
	public void reset() {
		init(0,0,0,0,0,0, 0);
	}
}
