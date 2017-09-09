package ST2.Scenes.buttons;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.TextRenderer;

public class NoButton extends GameObject{
    private boolean isChosen;

    public NoButton(){
        this.isChosen = false;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (isChosen) {
            this.renderer = new TextRenderer("No", "#cc1423");
        } else {
            this.renderer = new TextRenderer("No", "#ffffff");
        }
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public boolean isChosen() {
        return isChosen;
    }
}
