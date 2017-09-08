package ST2.Enemy;


import Bases.GameObject;
import Bases.Vector2D;
import Bases.actions.Action;
import Bases.actions.ActionRepeatForever;
import Bases.actions.SequenceAction;
import Bases.actions.WaitAction;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.pools.GameObjectPool;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import ST2.Player.Player;
import tklibs.SpriteUtils;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 10;
    private BoxCollider boxCollider;
    private int damage;
    private float distance;
    private boolean unlockAction;

    public Enemy() {
        super();
        renderer = new Animation(
                SpriteUtils.loadImage("assets/image/platform/blueSquare.png")
        );

        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        unlockAction = true;
    }

    private void configAction() {
        Action shootAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
                enemyBullet.getPosition().set(owner.getPosition());
                enemyBullet.setRenderer(ImageRenderer.create("assets/image/bullets/cyan.png"));
                enemyBullet.getVelocity().set(
                        direction(owner.getPosition(),Player.getInstance().getPosition())
                );

                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action action = new SequenceAction(
                new WaitAction(5),
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
        if(distance >0 && distance < 300 && unlockAction) {
            configAction();
            unlockAction = false;
        }


    }
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

}
