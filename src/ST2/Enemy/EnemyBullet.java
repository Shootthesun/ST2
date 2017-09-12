package ST2.Enemy;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import ST2.Settings.Settings;
import ST2.platform.Platform;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private static final int damage = 1;

    public  EnemyBullet(){
        super();
        boxCollider = new BoxCollider(36,23);
        velocity = new Vector2D();
        this.children.add(boxCollider);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        setDeactive();
        move();
//        hitPlatform();
    }

    private void move() {
        this.position.addUp(velocity);
    }

    private Vector2D direction(Vector2D position, Vector2D position1) {
        return position.subtract(position1).normalize();
    }

    private void setDeactive(){
        if (position.x < 0 ) this.isActive = false;
    }

    private void hitPlatform(){
        Platform platform = Physics.collideWith(this.boxCollider, Platform.class);
        if (platform != null){
            this.isActive = false;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public static int getDamage() {
        return damage;
    }
}
