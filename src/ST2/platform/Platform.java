package ST2.platform;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;
import Bases.renderers.ImageRenderer;
import ST2.Player.Player;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public Platform(){
        super();
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    public static Platform create(int platformType){
        Platform platform = new Platform();
        switch (platformType){
            case 1:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/5_Fotor.png"));
                break;
            case 4:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/13_Fotor.png"));
                break;
            case 5:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/14_Fotor.png"));
                break;
            case 6:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/15_Fotor.png"));
                break;
            case 7:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/12_Fotor.png"));
                break;
            case 8:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/3_Fotor.png"));
                break;
            case 9:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/2_Fotor.png"));
                break;
            case 10:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/1_Fotor.png"));
                break;
            case 11:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/10_Fotor.png"));
                break;
            case 12:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/9_Fotor.png"));
                break;
            case 13:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/16_Fotor.png"));
                break;
            case 14:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/6_Fotor.png"));
                break;
            case 15:
                platform.renderer = new ImageRenderer(
                        SpriteUtils.loadImage( "assets/map/map/Tilesresize/4_Fotor.png"));
                break;













        }
        return platform;
    }
}
