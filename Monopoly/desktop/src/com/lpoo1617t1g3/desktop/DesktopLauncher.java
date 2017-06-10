package com.lpoo1617t1g3.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lpoo1617t1g3.Monopoly;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Monopoly.WIDTH;
        config.height = Monopoly.HEIGHT;
        config.title = Monopoly.TITLE;
        new LwjglApplication(new Monopoly(), config);
    }
}
