package ST2.Player;

import Bases.Constraints;
import Bases.FrameCounter;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.dusting.Dusting;
import Bases.physics.BoxCollider;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import Bases.pools.GameObjectPool;
import Bases.renderers.ImageRenderer;
import Bases.specialPool.SpecialPool;
import ST2.Enemy.Enemy;
import ST2.Enemy.EnemyBullet;
import ST2.InputManager.InputManager;
import ST2.Scenes.GameOverScene;
import ST2.Scenes.SceneManager;
import ST2.SpecialObject.StateMachine;
import ST2.ViewCam;
import ST2.explosion.Explosion;
import ST2.platform.Platform;
import ST2.platform.SeaPlatform;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


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
    StateMachine stateMachine = null;

    private static Player instance;
    private boolean spellLock;
    private FrameCounter coolDownCounter;
    private Vector2D nextPosition = Vector2D.ZERO;
    private ArrayList<Integer> comboList = new ArrayList<>();
    private boolean unlockMove = true;
    private boolean isjumping;
    private PlayerAnimator animator;
    private float SPEED2;

    public static Player getInstance() {
        return instance;
    }

    public Player() {
        super();
        this.boxCollider = new BoxCollider(20,60);
        animator = new PlayerAnimator();
        renderer = animator;
        velocity = new Vector2D(200, 0);
        Gravity =0.8f;
        left = false;
        leftLock = new FrameCounter(20);
        SPEED = 6;
        HP = 20;
        unlockMove = true;
        coolDownCounter = new FrameCounter(10);
        typeBullet = 2;
        isjumping = true;

        instance = this;
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        hitSpecialObject();
        updatePhysics();
        hitEnemy();
        hitEnemyBullet();
        if(this.getHP()<=0){
            this.isActive = false;
        }
        drowningSea();
        shoot();
        if(isjumping){
            typeBullet = 0.25f;
        }
        else {
            typeBullet = 0;
        }
        animator.Update(this);
        gameOver();
    }

    private void hitSpecialObject() {
        for (int i=0; i<=5; ++i) {
            SpecialPool specialPool = Physics.collideWithSpecial(screenPosition, boxCollider.getWidth(), boxCollider.getHeight(), SpecialPool.class, i);
            if (specialPool != null) {
                if (i < 5) {
                    if (i==0) {
                        createRandomCombo(2);
                        stateMachine = new StateMachine();
                        stateMachine.load(comboList);
//                        this.screenPosition = specialPool.getScreenPosition();
                        lockMove();
                        SPEED2 = 1;
                        nextPosition =
                                specialPool.getNextPosition();
                    }
                    else {
                        if (i == 1) {
                            stateMachine.setComboFailed();
                            if (!stateMachine.isComboFailed()) {
                                nextPosition = specialPool.getNextPosition();
                                SPEED2 = 1.9f;
                            }
                            else {
                                unlockMove();
                            }
                        }
                        else nextPosition = specialPool.getNextPosition();
                    }
                }
                else {
                    stateMachine = null;
//                    this.screenPosition = specialPool.getScreenPosition();
                    velocity = new Vector2D(SPEED, Gravity);
                    nextPosition = Vector2D.ZERO;
                    unlockMove();
                }
            }
        }
    }
    private void createRandomCombo(int hardLevel) {
        comboList.clear();
        for (int i=0; i<=hardLevel; ++i){
            Random random = new Random();
            comboList.add(random.nextInt(4)+37);
        }
    }

    private void unlockMove() {
        unlockMove = true;
    }

    private void moveToNextPoint(Vector2D nextPosition) {
        velocity = nextPosition.subtract(screenPosition).normalize().multiply(SPEED*SPEED2);
    }

    private void lockMove() {
        unlockMove = false;
    }

    private void shoot() {
        if (inputManager.xPressed && !spellLock) {
//            creatSpell(0,0,typeBullet);
            creatSpell(0,0,0);
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
        if (unlockMove) {
            velocity.y += Gravity;
            velocity.x = SPEED;
            jump();
        }
        else moveToNextPoint(nextPosition);
        updateVerticalPhysics();
        updateHorizontalPhysics();
        Dusting dusting = new Dusting();
        dusting.getPosition().set(this.position);
        dusting.getPosition().set(this.screenPosition.add(0,30));
//        GameObject.add(dusting);
    }


    private void jump() {
        if(InputManager.instance.upPressed && !isjumping){
            if(Physics.collideWith(screenPosition.add(0,Math.signum(velocity.y)),boxCollider.getWidth(),boxCollider.getHeight(),Platform.class)!=null) {
                velocity.y = -14f;
                isjumping = true;
            }
        }
        if(InputManager.instance.downPressed){
            Gravity = 1.5f;
        }else {
            Gravity = 0.8f;
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
            isjumping = false;
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

    private void drowningSea(){
        SeaPlatform seaPlatform = Physics.collideWith(this.screenPosition, boxCollider.getWidth(), boxCollider.getHeight(), SeaPlatform.class);
        if (seaPlatform != null){
            this.isActive = false;
        }
    }

    private void gameOver(){
        if (!this.isActive){
            SceneManager.changeScene(new GameOverScene());
            Player.clearInstance();
        }
    }

    private static void clearInstance() {
        instance = null;
    }


    @Override
    public void render(Graphics2D g2d, ViewCam viewCam) {
        super.render(g2d, viewCam);
        if(stateMachine != null){
            stateMachine.render(g2d);
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
