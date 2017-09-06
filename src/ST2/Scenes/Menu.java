package ST2.Scenes;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.ViewCam;
import tklibs.SpriteUtils;

public class Menu extends GameObject implements PhysicsBody {
    private ImageRenderer Start;
    private ImageRenderer Quit;
    private BoxCollider boxCollider;
    private InputManager inputManager;
    Player player;
    public static Level1Scene level1Scene;
    public Menu(){
        super();
        this.Start = new ImageRenderer(SpriteUtils.loadImage("assets/menus/start-color.png"));
        this.renderer = Start;
        boxCollider = new BoxCollider(10,10);
        player = new Player();
        level1Scene = new Level1Scene();
    }
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if(inputManager.enter){
            SceneManager.changeScene(level1Scene);
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
