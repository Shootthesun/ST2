package ST2.platform;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class SeaPlatform extends GameObject{
    private BoxCollider boxCollider;

    public SeaPlatform(){
        super();
        renderer = new Animation(
                SpriteUtils.loadImage("assets/image/platform/blueSquare.png")
        );
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

}
