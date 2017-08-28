package Bases.actions;

import Bases.GameObject;

public interface Action {
    boolean run(GameObject owner);
    void reset();
}

