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
    Background background = new Background();
    Menu menu = new Menu();

    @Override
    public void init() {
        addBackground();
        addPlatform();
        addMenu();
        //ViewCam.instance.follow(this.getMenu());
    }

    private void addMenu() {
        menu.setInputManager(InputManager.instance);
        menu.getPosition().set(400,200);
        GameObject.add(menu);


    }

    private void addPlatform() {
        Map map = Map.load("assets/image/platform/test.json");
        map.generate();
    }

    private void addBackground() {
        background.getPosition().set(
                settings.getGamePlayWidth(),
                settings.getWindowHeight()
        );
        GameObject.add(background);
    }


    public Menu getMenu() {
        return menu;
    }
}
