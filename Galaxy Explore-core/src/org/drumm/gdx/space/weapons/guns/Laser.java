package org.drumm.gdx.space.weapons.guns;

import org.drumm.gdx.space.managers.RootManager;
import org.drumm.gdx.space.ships.BaseShip;
import org.drumm.gdx.space.weapons.ammunition.IDrawableWeapon;
import org.drumm.gdx.space.weapons.ammunition.LaserBeam;

import com.badlogic.gdx.utils.Pools;

public class Laser extends RepeatingGun{

	private float targetY;
	private float targetX;

	public Laser(BaseShip owner) {
		super(owner, 100f, 100000, 0);
	}

	@Override
	protected boolean canFireWhileReloading() {
		return true;
	}

	@Override
	protected boolean isAbleToReload() {
		return true;
	}

	@Override
	protected void fire() {
		IDrawableWeapon projectile=getProjectile();
		RootManager.getWeaponDrawManager().add(projectile);
	}

	private IDrawableWeapon getProjectile() {
//		float laserRadius=6;
//		float th=owner.getAngleRadians();
		LaserBeam beam = Pools.obtain(LaserBeam.class);
		float x=owner.getCenterX();
		float y=owner.getCenterY();
//		float r = owner.getLocation().radius+laserRadius;
//		float sin=(float) (Math.sin(th)*r);
//		float cos=(float) (Math.cos(th)*r);       
//		x+=sin;
//		y+=cos;
		beam.init(x, y, targetX, targetY, 3, .1f);
		return beam;
	}

	@Override
	public void setTarget(float x, float y) {
		targetX=x;
		targetY=y;
	}

	@Override
	public float getTargetX() {
		return targetX;
	}

	@Override
	public float getTargetY() {
		return targetY;
	}
}
