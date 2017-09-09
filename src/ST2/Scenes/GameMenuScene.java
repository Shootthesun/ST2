package ST2.Scenes;

import Bases.Constraints;
import Bases.GameObject;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Settings.Settings;
import ST2.ViewCam;
import ST2.maps.Map;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class GameMenuScene extends Scene{
    Settings settings = Settings.instance;
    Menu menu;

    @Override
    public void init() {
        addMenu();
    }

    private void addMenu() {
        menu = new Menu();
        menu.setInputManager(InputManager.instance);
        menu.getPosition().set(0, 0);
        GameObject.add(menu);
    }

    public Menu getMenu() {
        return menu;
    }
}
