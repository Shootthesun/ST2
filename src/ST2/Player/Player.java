package ST2.Player;

import Bases.Constraints;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;

import ST2.platform.Platform;
import tklibs.SpriteUtils;



public class Player extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private final float GRAVITY = 0.4f;
    private InputManager inputManager;
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Constraints contraints;

    public Player() {
        super();
        this.boxCollider = new BoxCollider(20,20);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/Player/Player.png"));
        velocity = Vector2D.ZERO;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        updateVerticalPhysics();
        updateHorizontalPhysics();

        position.addUp(velocity);

    }

    private void updateVerticalPhysics() {
        Vector2D checkPosition = screenPosition.add(0, velocity.x);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null){
            velocity.y = 0;
        }
    }


    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null){
            velocity.x = 0;
        }
    }

    private void move() {
        velocity.y += GRAVITY;
        velocity.x = 0;

        //jump
        if (inputManager.upPressed) {
            if (Physics.collideWith(screenPosition.add(0, 1),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Platform.class) != null) {
                velocity.y -= SPEED;
            }
        }

        //move horizontal
        if (inputManager.leftPressed)
            velocity.x -= SPEED;
        if (inputManager.rightPressed)
            velocity.x += SPEED;

        //constraints
        if (contraints != null) {
            contraints.make(position);
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void setContraints(Constraints contraints) {
        this.contraints = contraints;
    }
}
