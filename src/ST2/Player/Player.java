package ST2.Player;

import Bases.Constraints;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.InputManager.InputManager;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Player extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
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
    }

    private void move() {
        velocity.set(0, 0);

        if (inputManager.upPressed)
            velocity.y -= SPEED;
        if (inputManager.downPressed)
            velocity.y += SPEED;
        if (inputManager.leftPressed)
            velocity.x -= SPEED;
        if (inputManager.rightPressed)
            velocity.x += SPEED;

        if (contraints != null) {
            contraints.make(position);
        }

        position.addUp(velocity);

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
