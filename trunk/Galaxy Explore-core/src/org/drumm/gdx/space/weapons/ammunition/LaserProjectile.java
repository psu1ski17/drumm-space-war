package org.drumm.gdx.space.weapons.ammunition;

import org.drumm.gdx.space.BaseMovable;
import org.drumm.gdx.space.SimpleDrawer;
import org.drumm.gdx.space.SpaceObject;
import org.drumm.gdx.space.managers.RootManager;
import org.drumm.gdx.space.ships.BaseShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class LaserProjectile extends SpaceObject implements IDamager, IDrawableWeapon, Poolable {

	private static Texture texture;

	static {
		texture = new Texture(Gdx.files.internal("reddot.png"));
	}
	private float life;
	private float ttl;
	private BaseMovable movable;

	public LaserProjectile() {
		movable=new BaseMovable(this, 0, 0);
	}

	public void init(float x, float y, float degrees, float width, float height, float speed, float ttl) {
		this.setCenterPosition(x, y);
		this.setAngleDegrees(degrees);
		this.setWidth(width);
		this.setHeight(height);
		movable.setSpeed(speed);
		this.ttl = ttl;
		life = 0;
	}

	@Override
	public void doSubUpdate(float delta) {
		super.doSubUpdate(delta);
		Array<? extends BaseShip> hitShips = RootManager.getShipManager().getInCircle(this.getLocation());
		if (hitShips.size>=1){
			hitShips.get(0).getShootable().gotShot(this, new Vector3(getCenterX(), getCenterY(), 0));
			life+=ttl;
		}
		movable.update(delta);
		life += delta;
		if (life > ttl) {
			RootManager.getWeaponDrawManager().remove(this);
			Pools.free(this);
		}
	}

	@Override
	public void reset() {
		init(0, 0, 0, 0, 0, 0, 0);
	}

	@Override
	public void draw(SpriteBatch batch) {
		SimpleDrawer.draw(batch, this, texture);
	}

	@Override
	public float getDamage() {
		return 10;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.LASER;
	}

	@Override
	public void drawDebug(ShapeRenderer sr) {
		// TODO Auto-generated method stub
		
	}

}
