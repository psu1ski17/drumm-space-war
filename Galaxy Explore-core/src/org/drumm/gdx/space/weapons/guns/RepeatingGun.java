package org.drumm.gdx.space.weapons.guns;

import org.drumm.gdx.space.ships.BaseShip;

public abstract class RepeatingGun extends BaseGun {
	private float shotsPerSecond;
	private float maxAmmo;
	private boolean hasCapacity;
	private float currentAmmo;
	private float reloadTime;
	private float nextShotTime;
	private boolean firing;
	private float secondsPerShot;
	private boolean reloading;
	private float reloadCounter;
	private float reloadingAmount;

	/**
	 * 
	 * @param owner
	 * @param shotsPerSecond
	 * @param maxAmmo
	 *            - max rounds in gun. If 0 or negative unlimited.
	 */
	public RepeatingGun(BaseShip owner, float shotsPerSecond, float maxAmmo, float reloadTime) {
		super(owner);
		this.shotsPerSecond = shotsPerSecond;
		this.secondsPerShot = 1 / shotsPerSecond;
		this.maxAmmo = maxAmmo;
		this.hasCapacity = maxAmmo > 0;
		this.currentAmmo = 0;
		this.reloadTime = reloadTime;
		this.nextShotTime = 0;
		this.reloadCounter = 0;
		this.firing = false;
		this.reloading = false;
	}

	@Override
	public void enableFire() {
		firing = true;
	}

	@Override
	public void disableFire() {
		firing = false;
	}

	@Override
	public void reload(float ammo) {
		if (isAbleToReload()) {
			reloading = true;
			reloadCounter = 0;
			reloadingAmount = ammo;
		}
	}

	protected abstract boolean canFireWhileReloading();

	protected abstract boolean isAbleToReload();

	protected abstract void fire();

	@Override
	public void update(float delta) {
		if (reloading) {
			reloadCounter += delta;
			if (reloadCounter >= reloadTime) {
				reloadCounter = 0;
				reloading = false;
				currentAmmo += reloadingAmount;
			}
		}
		if (firing) {
			if (nextShotTime>0){
				nextShotTime-=delta;
			}
			System.out.println("Firing next shot time="+nextShotTime);
			if (canFireWhileReloading() && reloading || !reloading) {
				if(nextShotTime<=0){
					fire();
					nextShotTime+=shotsPerSecond;
				}
			}else{
				if (nextShotTime<0){
					nextShotTime=0;
				}
			}
		}

	}

	public float getCurrentAmmo() {
		return currentAmmo;
	}

	public void setCurrentAmmo(float currentAmmo) {
		this.currentAmmo = currentAmmo;
	}

	public float getMaxAmmo() {
		return maxAmmo;
	}

	public boolean isHasCapacity() {
		return hasCapacity;
	}

	public boolean isFiring() {
		return firing;
	}

	public boolean isReloading() {
		return reloading;
	}

}
