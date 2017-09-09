package ST2.Scenes;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Scenes.buttons.QuitButton;
import ST2.Scenes.buttons.StartButton;
import ST2.Settings.Settings;
import ST2.ViewCam;
import tklibs.SpriteUtils;

public class Menu extends GameObject {
    private Settings settings = Settings.instance;
    private StartButton startButton;
    private QuitButton quitButton;
    private boolean gameStart;
    private InputManager inputManager;
    public static Level1Scene level1Scene;

    public Menu(){
        super();
        addButton();
        gameStart = false;
        level1Scene = new Level1Scene();
    }
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        getUserChoice();
        if(inputManager.enter){
            if (startButton.isChosen()) {
                gameStart = true;
                SceneManager.changeScene(level1Scene);
            }
            if (quitButton.isChosen()){
                System.exit(0);
            }
        }
//        }
    }

    private void addButton() {
        startButton = new StartButton();
        startButton.getPosition().set(-settings.getGamePlayWidth() / 4, 0);
        GameObject.add(startButton);

        quitButton = new QuitButton();
        quitButton.getPosition().set(settings.getGamePlayWidth() / 4, 0);
        GameObject.add(quitButton);
    }

    private void getUserChoice() {
        if (inputManager.leftPressed || inputManager.rightPressed) {
            if (inputManager.leftPressed) {
                startButton.setChosen(true);
                quitButton.setChosen(false);
            }
            if (inputManager.rightPressed) {
                startButton.setChosen(false);
                quitButton.setChosen(true);
            }
        }
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
