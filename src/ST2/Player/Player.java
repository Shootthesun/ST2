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

    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Constraints contraints;
    private InputManager inputManager;
    private float Gravity;

    public Player() {
        super();
        this.boxCollider = new BoxCollider(20,20);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/Player/Player.png"));
        velocity = Vector2D.ZERO;
        Gravity =0.5f;
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
    }
    private void updatePhysics(){
        velocity.y += Gravity;
        velocity.x = 0;
        jump();
        updateVerticalPhysics();

    }

    private void jump() {
        if(InputManager.instance.upPressed){
            if(Physics.collideWith(screenPosition.add(0,Math.signum(velocity.y)),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class)!=null)
                velocity.y = -17f;
        }
    }

    private void updateVerticalPhysics() {
        Vector2D checkPosition = screenPosition.add(0,velocity.y);
        Platform platform = Physics.collideWith(checkPosition,boxCollider.getWidth(),boxCollider.getHeight(),Platform.class);
        if(platform != null){
            float dy = Math.signum(velocity.y);
            while (Physics.collideWith(screenPosition.add(0, dy),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class) == null){
                position.addUp(0, dy);
                screenPosition.addUp(0,dy);
            }
            velocity.y = 0;
        }
        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }
    //    @Override
//    public void run(Vector2D parentPosition) {
//        super.run(parentPosition);
////        updatePhysics();
//        position.addUp(velocity);

//    }

//    private void updatePhysics() {
//        velocity.y += GRAVITY;
//        velocity.x = 0;
//        move();
//        updateVerticalPhysics();
//    }

//    private void updateVerticalPhysics() {
//        Vector2D checkPosition = screenPosition.add(0, velocity.x);
//        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
//        if (platform != null){
//            while (Physics.collideWith(screenPosition.add(0, Math.signum(velocity.y)), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null){
//                position.add(0, Math.signum(velocity.y));
//                screenPosition.add(0, Math.signum(velocity.y));
//            }
//            velocity.y = 0;
//        }
//        this.position.y += velocity.y;
//        this.screenPosition.y += velocity.y;
//    }
//
//
//    private void updateHorizontalPhysics() {
//        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
//        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
//        if (platform != null){
//            velocity.x = 0;
//        }
//    }
//
//    private void move() {
//        if (inputManager.upPressed) {
//            if (Physics.collideWith(screenPosition.add(0, 1),
//                    boxCollider.getWidth(),
//                    boxCollider.getHeight(),
//                    Platform.class) != null) {
//                velocity.y -= SPEED;
//            }
//        }
//        //constraints
//        if (contraints != null) {
//            contraints.make(position);
//        }
//    }

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
