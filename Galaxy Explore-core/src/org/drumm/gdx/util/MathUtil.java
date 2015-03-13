package org.drumm.gdx.util;

public class MathUtil {
	public static float angleTo(float srcx, float srcy, float dstx, float dsty) {
		float dx = (dstx - srcx);
		float dy = (dsty - srcy);
		float m = dx / dy;
		float angleToRadians = (float) Math.atan(m);
		float angleTo = (float) (angleToRadians * 180 / Math.PI);
		if (dy < 0) {
			angleTo += 180;
		}
		return angleTo;
	}

	public static float distance(float srcx, float srcy, float dstx, float dsty) {
		float distSquared = (dstx - srcx) * (dstx - srcx) + (dsty - srcy) * (dsty - srcy);
		float dist = (float) Math.sqrt(distSquared);
		return dist;
	}
	
	public static float distanceSquared(float srcx, float srcy, float dstx, float dsty) {
		float distSquared = (dstx - srcx) * (dstx - srcx) + (dsty - srcy) * (dsty - srcy);
		return distSquared;
	}

	public static float angularDistance(float ang1, float ang2) {
		float a = ang2 - ang1;
		a = (a + 180f) % 360f - 180f;
		return a;
	}
}
