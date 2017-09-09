package ST2;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ViewCam {
    private Vector2D position;
    private Vector2D followObj;
    public static final ViewCam instance = new ViewCam();


    private ViewCam(){
        position = Vector2D.ZERO;
        followObj = Vector2D.ZERO;
    }

    public void follow(GameObject gameObject){
        if (gameObject != null)
            position = gameObject.getPosition().add(followObj);
    }

    public Vector2D translate(Vector2D screenPosition){
        return screenPosition.subtract(this.position.x, 0);
    }

    public Vector2D getFollowObj() {
        return followObj;
    }
    public boolean IsActive(){
        return false;
    }
}
