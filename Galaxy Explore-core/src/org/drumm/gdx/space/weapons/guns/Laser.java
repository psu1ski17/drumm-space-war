package org.drumm.gdx.space.weapons.guns;

import org.drumm.gdx.space.Movable;
import org.drumm.gdx.space.common.RootManager;
import org.drumm.gdx.space.ships.BaseShip;
import org.drumm.gdx.space.weapons.ammunition.IDrawableWeapon;
import org.drumm.gdx.space.weapons.ammunition.LaserBeam;

import com.badlogic.gdx.utils.Pools;

public class Laser extends RepeatingGun{

	public Laser(BaseShip owner) {
		super(owner, .1f, 100000, 0);
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
		LaserBeam beam = Pools.obtain(LaserBeam.class);
		Movable m = owner.getMovable();
		beam.init(m.getCenterX(), m.getCenterY(), m.getAngleDegrees(), 2, 10, 1000,20);
		return beam;
	}
}
