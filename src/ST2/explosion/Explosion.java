package ST2.explosion;



import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.Animation;
import ST2.ViewCam;
import tklibs.SpriteUtils;

import java.awt.*;

public class Explosion extends GameObject {
    Animation animation;
    public Explosion(){
        super();
        animation = new Animation(2,true,false,
//                SpriteUtils.loadImage("assets/images/enemies/explosion/0.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/1.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/2.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/3.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/4.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/5.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/6.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/7.png")
        );
        this.renderer = animation;
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (animation.isStopped()){
            this.setActive(false);
        }
    }
//    @Override
    public void render(Graphics2D g2d){
        super.render(g2d, ViewCam.instance);
        if(animation.isStopped()){
            this.isActive = false;
        }
    }
    public void reset(){
        super.reset();
        animation.reset();
    }

    public Animation getAnimation() {
        return animation;
    }
}