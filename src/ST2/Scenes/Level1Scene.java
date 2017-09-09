package ST2.Scenes;

import Bases.Constraints;
import Bases.GameObject;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Settings.Settings;
import ST2.ViewCam;
import ST2.maps.Map;
import ST2.platform.Platform;

public class Level1Scene extends Scene {
    Player player;
    Settings settings = Settings.instance;
    Background background;

    @Override
    public void init() {
        addBackground();
        addPlatform();
        addPlayer();
    }

    private void addPlatform() {
        Map map = Map.load("assets/image/platform/maprelease.json");
        map.generate();
    }

    private void addBackground() {
        background = new Background();
        background.getPosition().set(
                settings.getGamePlayWidth(),
                settings.getWindowHeight()
        );
        GameObject.add(background);
    }

    private void addPlayer() {
        player = new Player();
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
        );
        player.getPosition().set(
                settings.getGamePlayWidth() / 4,
                200);

        GameObject.add(player);

    }

    public Player getPlayer() {
        return player;
    }
}
