package org.drumm.gdx.space;

import java.util.Collection;
import java.util.Iterator;

import org.drumm.gdx.space.bodies.BasePlanet;

import com.badlogic.gdx.math.Rectangle;

public class SimpleSpaceObjectManager {
	
	private Collection<BasePlanet> planets;

	public SimpleSpaceObjectManager(Collection<BasePlanet>planets) {
		this.planets=planets;
	}
	
	public Iterator<BasePlanet> getPlanetsInRect(Rectangle rect){
		Iterator<BasePlanet> itr = planets.iterator();
		return itr;
//		return new Iterator<BasePlanet>() {
//
//			@Override
//			public boolean hasNext() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public BasePlanet next() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public void remove() {
//			}
//		};
	}
}
