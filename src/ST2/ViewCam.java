package ST2;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ViewCam {
    private Vector2D position;
    private Vector2D followObj;

    public ViewCam(){
        position = Vector2D.ZERO;
        followObj = Vector2D.ZERO;
    }

    public void follow(GameObject gameObject){
        position = gameObject.getPosition().add(followObj);
    }

    public Vector2D translate(Vector2D screenPosition){
        return screenPosition.subtract(this.position);
    }

    public Vector2D getFollowObj() {
        return followObj;
    }
}
