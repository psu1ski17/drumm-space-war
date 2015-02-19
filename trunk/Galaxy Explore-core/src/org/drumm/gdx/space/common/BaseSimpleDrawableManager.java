package org.drumm.gdx.space.common;

import org.drumm.gdx.space.Drawable;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class BaseSimpleDrawableManager<T extends HasDrawable> implements IDrawableManager<T> {

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
			Rectangle location = obj.getDrawable().getLocation();
			boolean overlap=location.overlaps(rect);
			if (overlap) {
				objects.add(obj);
			}
		}
		return objects;
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
