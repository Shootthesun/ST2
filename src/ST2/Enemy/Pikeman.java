package ST2.Enemy;

import Bases.FrameCounter;
import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.Animation;
import tklibs.SpriteUtils;

public class Pikeman extends GameObject implements PhysicsBody {
    private static final float SPEED = 10;
    private BoxCollider boxCollider;
    private int damage;
    private float distance;
    private boolean unlockAction;
    private int widthbox;
    private int heightbox;
    private FrameCounter frameCounter;
    int i = 0;

    public Pikeman() {
        super();
        renderer = new Animation(
                SpriteUtils.loadImage("assets/image/platform/blueSquare.png")
        );
        widthbox = 20;
        heightbox = 20;

        this.boxCollider = new BoxCollider(widthbox, heightbox);
        this.children.add(boxCollider);
        unlockAction = true;
        frameCounter = new FrameCounter(10);
    }
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if(frameCounter.run()){
            i++;
            widthbox += 2;
            heightbox += 2;
            if(i>4){
                i = 0;
                frameCounter.reset();
                widthbox =20;
                heightbox = 20;
            }
        }

    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }


}
