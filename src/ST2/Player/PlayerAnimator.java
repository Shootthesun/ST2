package ST2.Player;


import Bases.Vector2D;
import Bases.renderers.Animation;
import Bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {
    private Animation leftAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/left/0.png"),
            SpriteUtils.loadImage("assets/images/players/left/1.png"),
            SpriteUtils.loadImage("assets/images/players/left/2.png"),
            SpriteUtils.loadImage("assets/images/players/left/3.png"),
            SpriteUtils.loadImage("assets/images/players/left/4.png"),
            SpriteUtils.loadImage("assets/images/players/left/5.png")
    );
    private Animation rightAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/right/0.png"),
            SpriteUtils.loadImage("assets/images/players/right/1.png"),
            SpriteUtils.loadImage("assets/images/players/right/2.png"),
            SpriteUtils.loadImage("assets/images/players/right/3.png"),
            SpriteUtils.loadImage("assets/images/players/right/4.png"),
            SpriteUtils.loadImage("assets/images/players/right/5.png")
    );
    private Animation straightAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/straight/0.png"),
            SpriteUtils.loadImage("assets/images/players/straight/1.png"),
            SpriteUtils.loadImage("assets/images/players/straight/2.png"),
            SpriteUtils.loadImage("assets/images/players/straight/3.png"),
            SpriteUtils.loadImage("assets/images/players/straight/4.png"),
            SpriteUtils.loadImage("assets/images/players/straight/5.png"),
            SpriteUtils.loadImage("assets/images/players/straight/6.png")
    );
    private Animation currentAnimation = straightAnimation;
    public void Update(Player player){
        Vector2D velocity = player.getVelocity();
        if(velocity.x < 0){
            currentAnimation = leftAnimation;
        }
        if(velocity.x > 0){
            currentAnimation = rightAnimation;
        }
        if(velocity.x == 0){
            currentAnimation = straightAnimation;
        }
    }
    public void render(Graphics2D g2d, Vector2D position){
        currentAnimation.render(g2d, position);
    }


}
