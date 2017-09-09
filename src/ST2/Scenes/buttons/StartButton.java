package ST2.Scenes.buttons;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class StartButton extends GameObject{
    private boolean isChosen;

    public StartButton(){
        this.isChosen = true;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (isChosen) {
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/menus/start-color.png"));
        } else {
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/menus/start-white.png"));
        }
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public boolean isChosen() {
        return isChosen;
    }
}
