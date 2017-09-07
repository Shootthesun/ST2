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
        this.boxCollider = new BoxCollider(96, 96);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public static Platform create(int platformType){
        Platform platform = new Platform();
        switch (platformType){
            case 1: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/tile1.png"));
            break;
            case 2: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/tile2.png"));
                break;
            case 13: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/blueSquare.png"));
                break;
            case 14: platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/image/platform/wave.png"));
            break;
        }
        return platform;
    }
}
