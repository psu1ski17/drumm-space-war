package org.drumm.gdx.space.common;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public interface IDrawableManager<T extends HasDrawable> extends Disposable {
	public void add(T object);

	public void remove(T Object);

	public Array<? extends T> getAll();

	public Array<? extends T> getInRect(Rectangle rect);
}
