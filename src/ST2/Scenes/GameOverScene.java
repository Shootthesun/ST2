package ST2.Scenes;

import Bases.GameObject;
import Bases.renderers.ImageRenderer;
import ST2.Settings.Settings;


public class GameOverScene extends Scene {
    private Settings settings = Settings.instance;
    private GameOver gameOver;

    @Override
    public void init() {
        addGameOverScene();
    }


    private void addGameOverScene() {
        gameOver = new GameOver();
        gameOver.getPosition().set(0, 0);
        GameObject.add(gameOver);
    }
}
