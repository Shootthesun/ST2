package ST2.platform;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.Player.Player;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public Platform(){
        super();
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    public static Platform create(int platformType){
        Platform platform = new Platform();
        switch (platformType){
            case 4: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/blueSquare.png"));
            break;
            case 2: platform.renderer = new ImageRenderer(SpriteUtils.loadImage( "assets/image/ground.png"));
                break;
            case 1: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/tile2.png"));
            break;
        }
        return platform;
    }
}
