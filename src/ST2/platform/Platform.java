package ST2.platform;

import Bases.GameObject;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.Player.Player;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public Platform(){
        super();
        this.boxCollider = new BoxCollider(128, 128);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public static Platform create(int platformType){
        Platform platform = new Platform();
        switch (platformType){
            case 1: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/2.png"));
            break;
            case 17: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/15.png"));
            break;
        }
        return platform;
    }
}
