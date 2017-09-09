package ST2.Scenes;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;
import ST2.Scenes.buttons.NoButton;
import ST2.Scenes.buttons.YesButton;
import ST2.Settings.Settings;
import tklibs.SpriteUtils;

public class GameOver extends GameObject{
    private Settings settings = Settings.instance;
    private YesButton yesButton;
    private NoButton noButton;
    private InputManager inputManager = InputManager.instance;

    public GameOver(){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/menus/game-over.jpg"));
        addButton();
    }

    private void addButton() {
        yesButton = new YesButton();
        yesButton.getPosition().set(-settings.getGamePlayWidth() / 4, settings.getGamePlayHeight() / 3);
        GameObject.add(yesButton);

        noButton = new NoButton();
        noButton.getPosition().set(settings.getGamePlayWidth() / 4, settings.getGamePlayHeight() / 3);
        GameObject.add(noButton);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        getUserChoice();
        if (inputManager.enter){
            if (yesButton.isChosen()){
                SceneManager.changeScene(new Level1Scene());
            }
            if (noButton.isChosen()){
                SceneManager.changeScene(new GameMenuScene());
            }
        }
    }

    private void getUserChoice() {
        if (inputManager.leftPressed) {
            yesButton.setChosen(true);
            noButton.setChosen(false);
        }
        if (inputManager.rightPressed) {
            yesButton.setChosen(false);
            noButton.setChosen(true);
        }
    }
}
