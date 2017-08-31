package ST2.Scenes;

import Bases.Constraints;
import Bases.GameObject;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Settings.Settings;
import ST2.platform.Platform;

public class Level1Scene extends Scene {
    Player player = new Player();
    Settings settings = Settings.instance;
    Background background = new Background();


    @Override
    public void init() {
        addBackground();
        addPlayer();
        addPlatform();
    }

    float startX = 30;
    float startY = 590;

    private void addPlatform() {
        for (int i = 0; i < 20; i++){
            Platform platform = new Platform();
            platform.getPosition().set(startX, startY);
            GameObject.add(platform);
            startX += 22;
        }
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
