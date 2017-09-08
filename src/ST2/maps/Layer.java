package ST2.maps;

import Bases.GameObject;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import ST2.Enemy.Enemy;
import ST2.platform.Platform;
import tklibs.SpriteUtils;

import java.util.List;

public class Layer {
    private List<Integer> data;
    private int height;
    private int width;

    @Override
    public String toString() {
        return "Layer{" +
                "data=" + data +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public void generate() {
        for (int tileY = 0; tileY < height; tileY++){
            for (int tileX = 0; tileX < width; tileX++){
                int mapData = data.get(tileY * width + tileX);
                if (mapData != 0){
                    switch (mapData){
                        case 13:
                            Enemy enemy = new Enemy();
                            enemy.getPosition().set(tileX * 32, tileY * 32 + 32);
//                            enemy.setRenderer(ImageRenderer.create("assets/image/platform/blueSquare.png"));
                            GameObject.add(enemy);
                            break;
                        default:
//                            Platform platform = Platform.create(mapData);
                            Platform platform = new Platform();
                            platform.setRenderer(ImageRenderer.create("assets/image/platform/tile1.png"));
                            platform.getPosition().set(tileX * 32, tileY * 32 + 32);
                            GameObject.add(platform);
                            break;


                    }
                }
            }
        }
    }
}
