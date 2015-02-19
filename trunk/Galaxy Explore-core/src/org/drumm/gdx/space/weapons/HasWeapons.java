package org.drumm.gdx.space.weapons;

import org.drumm.gdx.space.weapons.guns.IGun;

import com.badlogic.gdx.utils.Array;

public interface HasWeapons {
	public void enableFire();
	public void disableFire();
	public void addGun(IGun gun);
	public void removeGun(IGun gun);
	public Array<IGun> getAllGuns();
}
