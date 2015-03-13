package org.drumm.gdx.space.weapons.guns;

public interface IGun {
	public void enableFire();
	public void disableFire();
	public void reload(float ammo);
	public void update(float delta);
	public void setTarget(float x, float y);
	public float getTargetX();
	public float getTargetY();
	public boolean isFiring();
	public boolean isReloading();
}
