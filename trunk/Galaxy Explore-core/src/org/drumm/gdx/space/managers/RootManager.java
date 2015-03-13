package org.drumm.gdx.space.managers;

import org.drumm.gdx.space.bodies.BasePlanet;
import org.drumm.gdx.space.ships.BaseShip;
import org.drumm.gdx.space.weapons.ammunition.IDrawableWeapon;
import org.drumm.gdx.space.weapons.ammunition.IWeaponDrawManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

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

	public static void updateAll(float delta) {
		Array<? extends BasePlanet> allPlanets = instance.planetManager.getAll();
		for (BasePlanet p : allPlanets) {
			p.update(delta);
		}
		Array<? extends BaseShip> allShips = instance.shipManager.getAll();
		for (BaseShip s : allShips) {
			s.update(delta);
		}
		Array<? extends IDrawableWeapon> allProj = instance.weaponDrawManager.getAll();
		for (IDrawableWeapon proj : allProj) {
			proj.update(delta);
		}
	}

	public static void drawAll(SpriteBatch batch, Rectangle viewportRect, boolean debug, ShapeRenderer sr) {
		Array<? extends BasePlanet> planets = instance.planetManager.getInRect(viewportRect);
		for (BasePlanet p : planets) {
			p.draw(batch);
			if (debug)
				p.drawDebug(sr);
		}
		Array<? extends BaseShip> drawableShips = instance.shipManager.getInRect(viewportRect);
		for (BaseShip s : drawableShips) {
			s.draw(batch);
			if (debug)
				s.drawDebug(sr);
		}
		Array<? extends IDrawableWeapon> allProj = instance.weaponDrawManager.getInRect(viewportRect);
		for (IDrawableWeapon proj : allProj) {
			proj.draw(batch);
			if (debug)
				proj.drawDebug(sr);
		}

	}
}
