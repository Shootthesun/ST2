package ST2.Player;


import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import Bases.renderers.Animation;
import ST2.Enemy.Enemy;
import tklibs.SpriteUtils;

import static java.lang.Math.*;

public class PlayerSpell extends GameObject implements PhysicsBody {
    private static final float SPEED = -10 ;
    private BoxCollider boxCollider;
    private int damage;
    private float typeBullet;
    private Player player;
    private final static float p = (float)PI;

    public PlayerSpell() {
        super();
        this.typeBullet = 0;
        damage = 10;
        renderer = new Animation(1,false,false,
                SpriteUtils.loadImage("assets/image/bullets/green.png")
                ) ;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }

    public void setTypeBullet(float typeBullet) {
        this.typeBullet = typeBullet;
    }

    public float getDamage() {
        return damage;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp((float) (SPEED*cos(typeBullet*p)), (float) (SPEED*sin(typeBullet*p)));
        hitEnemy();
        deActive();
    }

    private void deActive() {
        if(this.screenPosition.y < 0){
            this.isActive = false;
        }

    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if(enemy != null){
            enemy.setActive(false);
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
