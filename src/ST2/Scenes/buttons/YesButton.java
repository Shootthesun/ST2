package ST2.Scenes.buttons;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.TextRenderer;

public class YesButton extends GameObject{
    private boolean isChosen;

    public YesButton(){
        this.isChosen = true;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (isChosen) {
            this.renderer = new TextRenderer("Yes", "#cc1423");
        } else {
            this.renderer = new TextRenderer("Yes", "#ffffff");
        }
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public boolean isChosen() {
        return isChosen;
    }
}
