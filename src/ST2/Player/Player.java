package ST2.Player;

import Bases.Constraints;
import Bases.FrameCounter;
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
    private FrameCounter leftLock;
    private int i;
    private Boolean left;

    public Player() {
        super();
        this.boxCollider = new BoxCollider(20,20);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/Player/Player.png"));
        velocity = new Vector2D(200, 0);
        Gravity =0.5f;
        left = false;
        leftLock = new FrameCounter(50);
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
        this.position.addUp(velocity);
    }
    private void updatePhysics(){
        velocity.y += Gravity;
        velocity.x = 0;
        jump();
        updateVerticalPhysics();
        move();
//        updateHorizontalPhysics();

//        if (contraints != null) {
//            contraints.make(position);
//        }
    }

    private void move() {
        if (InputManager.instance.leftPressed && !leftLock.run()){
            left = true;
            leftLock.reset();
        }
        else {
            velocity.x = 5;
        }
        if(left){
            velocity.x = -5;
            i++;
            if(i>30) {
//                leftLock.reset();
                left = false;
                i =0;
            }
        }



//        if (InputManager.instance.rightPressed){
//            velocity.x = 5;
//        }
    }

    private void jump() {
        if(InputManager.instance.upPressed){
            if(Physics.collideWith(screenPosition.add(0,Math.signum(velocity.y)),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class)!=null)
                velocity.y = -10f;
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
    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null){
            velocity.x = 0;
        }
    }
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
