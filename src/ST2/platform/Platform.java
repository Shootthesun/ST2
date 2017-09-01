package ST2.platform;

import Bases.GameObject;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;

public class Platform extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public Platform(){
        super();
        this.renderer = ImageRenderer.create("assets/image/platform/yellow_square.jpg");
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
