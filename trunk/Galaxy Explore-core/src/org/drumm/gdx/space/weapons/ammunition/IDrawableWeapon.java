package org.drumm.gdx.space.weapons.ammunition;

import org.drumm.gdx.space.common.HasDrawable;

/**
 * Intended to represent the projectile or beam part of a weapon that is drawn. 
 *
 */
public interface IDrawableWeapon extends HasDrawable{

	void update(float delta);

}
