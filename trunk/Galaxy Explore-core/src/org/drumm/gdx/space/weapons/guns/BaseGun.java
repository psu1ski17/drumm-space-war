package org.drumm.gdx.space.weapons.guns;

import org.drumm.gdx.space.ships.BaseShip;

public class BaseGun implements IGun {
	private BaseShip owner;

	protected BaseGun(BaseShip owner){
		this.owner=owner;
	}
}
