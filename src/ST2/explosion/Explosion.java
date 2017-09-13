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
        animation = new Animation(1,true,false,
                SpriteUtils.loadImage("assets/iceExplosion/C08spW51.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW52.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW53.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW54.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW55.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW56.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW57.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW58.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW59.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW60.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW61.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW62.png"),
                SpriteUtils.loadImage("assets/iceExplosion/C08spW63.png")
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