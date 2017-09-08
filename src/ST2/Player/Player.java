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
    private Boolean left;
    private float SPEED;

    private static Player instance;

    public static Player getInstance() {
        return instance;
    }

    public Player() {
        super();
        this.boxCollider = new BoxCollider(1,1);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/Player/Player.png"));
        velocity = new Vector2D(200, 0);
        Gravity =0.5f;
        left = false;
        leftLock = new FrameCounter(20);
        SPEED = 5;

        instance = this;
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
        move();
        updateHorizontalPhysics();

//        if (contraints != null) {
//            contraints.make(position);
//        }
    }

    private void move() {
        if(InputManager.instance.leftPressed && !left){
            left = true;
            leftLock.reset();
        }
        if(left){
            velocity.x = -SPEED;
        }
        else {
            velocity.x = +SPEED;
        }
        unlockmove();


    }

    private void unlockmove() {
        if(left){
            if(leftLock.run()){
                left = false;
            }
        }
    }

    private void jump() {
        if(InputManager.instance.upPressed){
            if(Physics.collideWith(screenPosition.add(0,Math.signum(velocity.y)),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class)!=null)
                velocity.y = -20f;
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

    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null){
            float dx = Math.signum(velocity.x);
            while (Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null){
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            velocity.x = 0;
        }
        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
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
