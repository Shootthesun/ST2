package ST2.Player;


import Bases.Vector2D;
import Bases.renderers.Animation;
import Bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {
    private Animation jump = new Animation(8,false,false,
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_194951-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210114-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_204802-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_204802-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_204802-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_204802-1.png")

//            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_220738-1")



    );
    private Animation fall = new Animation(8,false,false,
//            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_220738-1"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210126-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210126-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210126-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210126-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_205644.png"),
            SpriteUtils.loadImage("assets/image/Player/Jump/20170909_210138.png"));
    private Animation straightAnimation = new Animation(5,false,false,
            SpriteUtils.loadImage("assets/image/Player/Run/20170909_223151-1.png"),
            SpriteUtils.loadImage("assets/image/Player/Run/1.png"),
            SpriteUtils.loadImage("assets/image/Player/Run/2.png"),
            SpriteUtils.loadImage("assets/image/Player/Run/5.png")
    );
    private Animation currentAnimation = straightAnimation;

    public void Update(Player player){
        player = Player.getInstance();
        Vector2D velocity = player.getVelocity();
        if(velocity.y < 0){
            currentAnimation = jump;
        }
        if(velocity.y > 0){
            currentAnimation =fall;
        }
        if(velocity.y == 0){
            currentAnimation = straightAnimation;
        }
    }
    public void render(Graphics2D g2d, Vector2D position){
        currentAnimation.render(g2d, position);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
}
