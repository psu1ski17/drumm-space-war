package org.drumm.gdx.space.weapons.guns;

import org.drumm.gdx.space.ships.BaseShip;

public abstract class BaseGun implements IGun {
	protected BaseShip owner;

	protected BaseGun(BaseShip owner){
		this.owner=owner;
	}
}
