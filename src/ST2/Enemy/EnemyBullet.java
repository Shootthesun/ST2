package ST2.Enemy;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.actions.Action;
import Bases.actions.SequenceAction;
import Bases.actions.WaitAction;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import ST2.Player.Player;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Enemy enemy;
    private float SPEED;

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
}
