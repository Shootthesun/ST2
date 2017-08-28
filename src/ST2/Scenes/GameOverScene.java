package ST2.Scenes;

import Bases.GameObject;
import Bases.renderers.ImageRenderer;
import ST2.Settings.Settings;


public class GameOverScene extends Scene {
    Settings settings = Settings.instance;
    @Override
    public void init() {
        ImageRenderer imageRenderer = ImageRenderer.create("");
        GameObject background = new GameObject().setRenderer(imageRenderer);
        background.getPosition().set(settings.getGamePlayWidth()/2,settings.getGamePlayHeight()/2);
        GameObject.add(background);
    }
}
