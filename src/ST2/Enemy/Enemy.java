package ST2.Enemy;


import Bases.FrameCounter;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.actions.Action;
import Bases.actions.SequenceAction;
import Bases.actions.WaitAction;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.pools.GameObjectPool;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import ST2.Player.Player;
import ST2.explosion.Explosion;
import tklibs.SpriteUtils;

public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 10;
    private BoxCollider boxCollider;
    private final static int damage = 2;
    private float distance;
    private boolean animationOn;
    private FrameCounter unlockAction;
    private Animation animation1;
    private Animation animation2;

    public Enemy() {
        super();
        animationOn = false;
        animation1 = new Animation(
                SpriteUtils.loadImage("assets/image/enemies/1x.png")
        );
        animation2 = new Animation(
                SpriteUtils.loadImage("assets/image/enemies/1x.png"),
                SpriteUtils.loadImage("assets/image/enemies/2x.png"),
                SpriteUtils.loadImage("assets/image/enemies/3x.png"),
                SpriteUtils.loadImage("assets/image/enemies/4x.png")
        );
        renderer = animation1;

        this.boxCollider = new BoxCollider(20, 130);
        this.children.add(boxCollider);
        unlockAction = new FrameCounter(50);

    }

    private void configAction() {
        Action render = new Action() {
            @Override
            public boolean run(GameObject owner) {
                renderer = animation2;
                return true;
            }

            @Override
            public void reset() {
                renderer = animation1;
            }
        };
        Action shootAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
                enemyBullet.getPosition().set(owner.getPosition().add(-30,-12));
                enemyBullet.setRenderer(ImageRenderer.create("assets/image/enemies/5x.png"));
                enemyBullet.getVelocity().set(
                        -3,0
                );

                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action action = new SequenceAction(
                render,
                new WaitAction(90),
                shootAction

        );
        if(action != null){
            this.addAction(action);
        }
    }

    private Vector2D direction(Vector2D other, Vector2D vector2D) {
        return vector2D.subtract(other).normalize().multiply(SPEED);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        distance = this.getPosition().x - Player.getInstance().getPosition().x;
        if(distance >0 && distance < 1000 && unlockAction.run()) {
            configAction();
            unlockAction.reset();

        }



    }
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public static int getDamage() {
        return damage;
    }
    public void  getHit(){
        Explosion explosion = new Explosion();
        explosion.getPosition().set(this.position);
        explosion.getPosition().set(this.screenPosition);
        GameObject.add(explosion);
    }
}
