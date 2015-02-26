package org.drumm.gdx.space.managers;

import org.drumm.gdx.space.ISpaceObject;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public interface IDrawableManager<T extends ISpaceObject> extends Disposable {
	public void add(T object);

	public void remove(T Object);

	public Array<? extends T> getAll();

	public Array<? extends T> getInRect(Rectangle rect);
	
	public Array<? extends T> getInCircle(Circle circle);
}
