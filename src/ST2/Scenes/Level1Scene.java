package ST2.Scenes;

import Bases.Constraints;
import Bases.GameObject;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Settings.Settings;
import ST2.maps.Map;
import ST2.platform.Platform;

public class Level1Scene extends Scene {
    Player player = new Player();
    Settings settings = Settings.instance;
    Background background = new Background();


    @Override
    public void init() {
        addBackground();
        addPlatform();
        addPlayer();
    }

    private void addPlatform() {
        Map map = Map.load("assets/image/platform/ST2..json");
        map.generate();
    }

    private void addBackground() {
        background.getPosition().set(
                settings.getGamePlayWidth(),
                settings.getWindowHeight()
        );
        GameObject.add(background);
    }

    private void addPlayer() {
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
        );
        player.getPosition().set(
                settings.getGamePlayWidth() / 4,
                570);

        GameObject.add(player);
    }
}
