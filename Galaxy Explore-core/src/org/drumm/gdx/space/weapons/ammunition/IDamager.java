package org.drumm.gdx.space.weapons.ammunition;

public interface IDamager {
	public abstract float getDamage();

	public abstract DamageType getDamageType();

	public static enum DamageType {
		LASER, EXPLOSIVE, PROJECTILE
	}
}
