package Bases.physics;

import Bases.GameObject;

public class CircleCollider extends GameObject {
    private float R;

    public CircleCollider(float x, float y, float R) {
        super();
        this.position.set(x, y);
        this.R = R;
    }

    public CircleCollider(float R) {
        this(0, 0, R);
    }

    public float R() {
        return R;
    }
}
