package ST2.Player;

import Bases.Constraints;
import Bases.FrameCounter;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import Bases.pools.GameObjectPool;
import Bases.renderers.ImageRenderer;
import ST2.Enemy.Enemy;
import ST2.Enemy.EnemyBullet;
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
    private int HP;
    private float typeBullet;

    private static Player instance;
    private boolean spellLock;
    private FrameCounter coolDownCounter;

    public static Player getInstance() {
        return instance;
    }

    public Player() {
        super();
        this.boxCollider = new BoxCollider(10,10);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/Player/Player.png"));
        velocity = new Vector2D(200, 0);
        Gravity =0.5f;
        left = false;
        leftLock = new FrameCounter(20);
        SPEED = 2;
        HP = 10;
        coolDownCounter = new FrameCounter(20);
        typeBullet = 2;

        instance = this;
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
        hitEnemy();
        hitEnemyBullet();
        if(this.getHP()<=0){
            this.isActive = false;
        }
        shoot();
    }

    private void shoot() {
        if (inputManager.xPressed && !spellLock) {
            creatSpell(0,0,typeBullet);
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();
    }
    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    private void creatSpell(float x,float y,float typebullet) {
        PlayerSpell newSpell = GameObjectPool.recycle(PlayerSpell.class);
        newSpell.setTypeBullet(typebullet);
        newSpell.getPosition().set(this.position.add(x, y));
    }

    private void updatePhysics(){
        velocity.y += Gravity;
        velocity.x =  SPEED;
        jump();
        updateVerticalPhysics();
        updateHorizontalPhysics();

//        if (contraints != null) {
//            contraints.make(position);
//        }
    }


    private void jump() {
        if(InputManager.instance.upPressed){
            if(Physics.collideWith(screenPosition.add(0,Math.signum(velocity.y)),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class)!=null)
                velocity.y = -15f;
            typeBullet = -0.75f;
        }
        else {
            typeBullet = 1;
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
    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), Enemy.class);
        if(enemy != null){
            enemy.setActive(false);
            this.setHP(this.getHP() - enemy.getDamage());

        }
    }
    private void hitEnemyBullet() {
        EnemyBullet bullet = Physics.collideWith(this.screenPosition,boxCollider.getWidth(),boxCollider.getHeight(), EnemyBullet.class);
        if(bullet != null){
            bullet.setActive(false);
            this.setHP(this.getHP() - bullet.getDamage());
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
