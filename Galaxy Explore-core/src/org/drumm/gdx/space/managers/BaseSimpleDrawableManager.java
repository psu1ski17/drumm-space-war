package org.drumm.gdx.space.managers;

import org.drumm.gdx.space.ISpaceObject;
import org.drumm.gdx.space.weapons.ammunition.IDrawableWeapon;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class BaseSimpleDrawableManager<T extends ISpaceObject> implements IDrawableManager<T> {

	private ObjectSet<T> list;

	public BaseSimpleDrawableManager() {
		this.list = new ObjectSet<T>();
	}

	@Override
	public void add(T object) {
		list.add(object);
	}

	@Override
	public void remove(T object) {
		list.remove(object);
	}

	@Override
	public Array<? extends T> getAll() {
		Array<T> objects = new Array<T>(list.size);
		for (T obj : list) {
			objects.add(obj);
		}
		return objects;
	}

	@Override
	public Array<? extends T> getInRect(Rectangle rect) {
		Array<T> objects = new Array<T>();
		for (T obj : list) {
			Circle location = obj.getLocation();
			boolean overlap=overlap(location, rect);
			if (overlap) {
				objects.add(obj);
			}
		}
		return objects;
	}
	
	@Override
	public Array<? extends T> getInCircle(Circle circle) {
		Array<T> objects = new Array<T>();
		for (T obj : list) {
			Circle location = obj.getLocation();
			boolean overlap=circle.overlaps(location);
			if (overlap) {
				objects.add(obj);
			}
		}
		return objects;
	}

	private boolean overlap(Circle location, Rectangle rect) {
		float cx = location.x;
		float cy=location.y;
		float cr=location.radius;
		boolean outsideXBounds = cx<rect.x-cr || cx>rect.x+cr+rect.width;
		boolean outsideYBounds = cy<rect.y-cr || cy>rect.y+cr+rect.height;
		return !(outsideXBounds || outsideYBounds);
	}

	@Override
	public void dispose() {
		doDispose();

	}

	/**
	 * Forces implementations to also have their own dispose that is called.
	 */
	protected abstract void doDispose();
}
