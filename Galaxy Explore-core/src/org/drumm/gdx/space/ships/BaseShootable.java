package org.drumm.gdx.space.ships;

import org.drumm.gdx.space.managers.IShipManager;
import org.drumm.gdx.space.managers.RootManager;
import org.drumm.gdx.space.weapons.ammunition.IDamager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;


public class BaseShootable implements Shootable {

	private float shield;
	private float hitpoints;
	private BaseShip owner;

	public BaseShootable(BaseShip owner,float hitpoints) {
		this(owner,0,hitpoints);
	}
	
	public BaseShootable(BaseShip owner,float shield,float hitpoints) {
		this.owner=owner;
		this.shield=shield;
		this.hitpoints=hitpoints;
		if (shield<0){
			this.shield=0;
		}
		if (hitpoints<0){
			this.hitpoints=0;
		}
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void gotShot(IDamager hitBy, Vector3 location) {
		float damage = hitBy.getDamage();
		if (shield>0){
			if (damage>shield){
				damage-=shield;
				shield=0;
			}else{
				shield=-damage;
				damage=0;
			}
		}
		hitpoints-=damage;
		if (hitpoints<0){
			doDestroy();
		}
	}

	private void doDestroy() {
		IShipManager shipMgr = RootManager.getShipManager();
		RootManager.getShipManager().remove(owner);
	}

	@Override
	public void draw(SpriteBatch batch) {
		//draw shield?  draw being hit?		
	}

	@Override
	public void drawDebug(ShapeRenderer sr) {
		// TODO Auto-generated method stub
		
	}
}
