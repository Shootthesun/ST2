package ST2.Scenes;

import Bases.GameObject;

public abstract class Scene {

    public void destroy() {
        GameObject.clearAll();
    }

    public abstract void init();


}
