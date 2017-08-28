package Bases.actions;

import Bases.GameObject;

public class ActionRepeatForever implements Action {
    private Action action;

    public ActionRepeatForever(Action action) {
        this.action = action;
    }

    @Override
    public boolean run(GameObject owner) {
        if (action.run(owner)) action.reset();
        return false;
    }

    @Override
    public void reset() {
        action.reset();
    }
}
