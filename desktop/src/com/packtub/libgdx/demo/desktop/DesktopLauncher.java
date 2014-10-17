package com.packtub.libgdx.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.packtub.libgdx.demo.MyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "demo";
        config.useGL30 = false;
        config.width = 480;
        config.height = 320;
		new LwjglApplication(new MyDemo(), config);
	}
}
