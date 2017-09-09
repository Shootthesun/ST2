package ST2.Scenes.buttons;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class QuitButton extends GameObject {
    private boolean isChosen;

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public QuitButton(){
        this.isChosen = false;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (isChosen) {
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/menus/quit-color.png"));
        } else {
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/menus/quit-white.png"));
        }
    }
}
