package org.drumm.gdx.space.common;

import org.drumm.gdx.space.bodies.IPlanetManager;
import org.drumm.gdx.space.ships.IShipManager;
import org.drumm.gdx.space.weapons.ammunition.IWeaponDrawManager;

public class RootManager {
	private static RootManager instance;
	private IShipManager shipManager;
	private IPlanetManager planetManager;
	private IWeaponDrawManager weaponDrawManager;

	static {
		RootManager.instance = new RootManager();
	}

	public static RootManager getInstance() {
		return instance;
	}

	public static IShipManager getShipManager() {
		return instance.shipManager;
	}

	public static void setShipManager(IShipManager shipManager) {
		instance.shipManager = shipManager;
	}

	public static IPlanetManager getPlanetManager() {
		return instance.planetManager;
	}

	public static void setPlanetManager(IPlanetManager planetManager) {
		instance.planetManager = planetManager;
	}

	public static IWeaponDrawManager getWeaponDrawManager() {
		return instance.weaponDrawManager;
	}

	public static void setWeaponDrawManager(IWeaponDrawManager weaponDrawManager) {
		instance.weaponDrawManager = weaponDrawManager;
	}

}
