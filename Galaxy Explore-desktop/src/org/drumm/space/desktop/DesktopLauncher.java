package org.drumm.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.drumm.space.GalaxyExplore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Galaxy Explore";
		config.width=800;
		config.height=480;
		new LwjglApplication(new GalaxyExplore(), config);
	}
}
