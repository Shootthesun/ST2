package ST2.Scenes;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import ST2.Settings.Settings;

public class Background extends GameObject implements PhysicsBody {
    private ImageRenderer imageRenderer;
    private final float SPEED = 1;
    private final float imageHeight, imageWidth;
    private BoxCollider boxCollider;

    public Background() {
        super();
        this.imageRenderer = new ImageRenderer(
                SpriteUtils.loadImage("assets/image/Background/Background.png")
        );
        this.imageRenderer.getAnchor().set(1, 1);
        this.position.set(0, Settings.instance.getGamePlayHeight());
        this.imageHeight = imageRenderer.image.getHeight();
        this.imageWidth = imageRenderer.image.getWidth();
        this.renderer = imageRenderer;
        this.boxCollider = new BoxCollider(imageWidth, imageHeight);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.x += SPEED;
        if (position.y > imageWidth) {
            position.y = imageWidth;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
