package ST2.Enemy;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Enemy enemy;
    private float SPEED;
    private static final int damage = 1;

    public  EnemyBullet(){
        super();
        boxCollider = new BoxCollider(12,12);
        velocity = new Vector2D();
        SPEED = 5;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);

        this.position.addUp(velocity);
    }

    private Vector2D direction(Vector2D position, Vector2D position1) {
        return position.subtract(position1).normalize();
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public static int getDamage() {
        return damage;
    }
}
