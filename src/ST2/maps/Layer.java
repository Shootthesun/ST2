package ST2.maps;

import Bases.GameObject;
import ST2.platform.Platform;

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
                    Platform platform = new Platform();
                    platform.getPosition().set(tileX * 32, tileY * 32);
                    GameObject.add(platform);
                }
            }
        }
    }
}
