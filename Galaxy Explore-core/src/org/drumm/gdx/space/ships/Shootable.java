package org.drumm.gdx.space.ships;

import org.drumm.gdx.space.Drawable;
import org.drumm.gdx.space.common.Updateable;
import org.drumm.gdx.space.weapons.ammunition.Ammunition;
import org.drumm.gdx.space.weapons.ammunition.IDamager;

import com.badlogic.gdx.math.Vector3;

public interface Shootable extends Updateable, Drawable {
	public void gotShot(IDamager hitBy, Vector3 location);

	public static interface HasShootable{
		public Shootable getShootable();
	}
}
