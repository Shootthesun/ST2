package Bases.dusting;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.renderers.Animation;
import ST2.ViewCam;
import tklibs.SpriteUtils;

import java.awt.*;

public class Dusting extends GameObject{
    Animation animation;
    public Dusting(){
        super();
        animation = new Animation(4,true,false,
                SpriteUtils.loadImage("assets/image/enemies/explosion/1.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/3.png"),
                SpriteUtils.loadImage("assets/image/enemies/explosion/5.png"),
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
