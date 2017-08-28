package ST2.Scenes;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import ST2.Settings.Settings;

public class Background extends GameObject {
    private ImageRenderer imageRenderer;
    private final float SPEED = 1;
    private final float imageHeight, imageWidth;

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
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.x += SPEED;
        if (position.y > imageWidth) {
            position.y = imageWidth;
        }
    }
}
