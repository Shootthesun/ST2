package ST2.platform;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class SeaPlatform extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;

    public SeaPlatform(){
        super();
        renderer = new Animation(
                SpriteUtils.loadImage("assets/image/platform/blueSquare.png")
        );
        boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
