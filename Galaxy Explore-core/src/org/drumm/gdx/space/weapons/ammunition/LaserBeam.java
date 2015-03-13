package org.drumm.gdx.space.weapons.ammunition;

import org.drumm.gdx.space.BaseMovable;
import org.drumm.gdx.space.SimpleDrawer;
import org.drumm.gdx.space.SpaceObject;
import org.drumm.gdx.space.managers.RootManager;
import org.drumm.gdx.space.ships.BaseShip;
import org.drumm.gdx.util.MathUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class LaserBeam extends SpaceObject implements IDamager, IDrawableWeapon, Poolable {

	private static Texture texture;
	private static Pool<Vector3> vectorPool;
	private float srcx;
	private float srcy;
	private float dstx;
	private float dsty;
	private float ttl;
	private float life;
	private Circle hitbox = new Circle();

	static {
		texture = new Texture(Gdx.files.internal("reddot.png"));
		vectorPool = Pools.get(Vector3.class);
	}

	private BaseMovable movable;
	private float totalDamage = 10;
	private float damage;
	private float dps = 10;

	public LaserBeam() {
		movable = new BaseMovable(this, 0, 0);
	}

	public void init(float srcx, float srcy, float dstx, float dsty, float width, float ttl) {
		this.srcx = srcx;
		this.srcy = srcy;
		this.dstx = dstx;
		this.dsty = dsty;
		this.ttl = ttl;
		this.life = 0;
		hitbox.x = dstx;
		hitbox.y = dsty;
		hitbox.radius = 3;
		this.setCenterPosition(dstx - srcx, dsty - srcy);
		this.setAngleDegrees(MathUtil.angleTo(srcx, srcy, dstx, dsty));
		this.setWidth(width);
		this.setHeight(MathUtil.distance(srcx, srcy, dstx, dsty));
		movable.setSpeed(0);
	}

	@Override
	public void doSubUpdate(float delta) {
		super.doSubUpdate(delta);
		Array<? extends BaseShip> hitShips = RootManager.getShipManager().getInCircle(hitbox);
		if (hitShips.size >= 1) {
			Vector3 v = vectorPool.obtain();
			v.x = dstx;
			v.y = dsty;
			v.z = 0;
			hitShips.get(0).getShootable().gotShot(this, new Vector3(getCenterX(), getCenterY(), 0));
			damage = dps * delta;
			totalDamage -= damage;
			damage = Math.min(damage, totalDamage);
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
		init(0, 0, 0, 0, 0, 0);
	}

	@Override
	public void draw(SpriteBatch batch) {
		SimpleDrawer.draw(batch, this, texture);
	}

	@Override
	public float getDamage() {
		if (damage > 0) {
			System.out.println(damage + " " + totalDamage);
		}
		return damage;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.LASER;
	}

	@Override
	public void drawDebug(ShapeRenderer sr) {
		sr.begin(ShapeType.Line);
		sr.circle(hitbox.x, hitbox.y, hitbox.radius);
		sr.line(srcx, srcy, dstx, dsty);
		sr.end();
	}

}
