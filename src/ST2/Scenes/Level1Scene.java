package ST2.Scenes;

import Bases.Constraints;
import Bases.GameObject;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Settings.Settings;

public class Level1Scene extends Scene {
    Player player = new Player();
    Settings settings = Settings.instance;
    Background background = new Background();


    @Override
    public void init() {
        addBackground();
        addPlayer();
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
                settings.getGamePlayHeight() * 4 / 5);

        GameObject.add(player);
    }
}
