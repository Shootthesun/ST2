package ST2.maps;

import Bases.GameObject;
import Bases.renderers.Animation;
import Bases.renderers.ImageRenderer;
import Bases.specialPool.SpecialPool;
import ST2.Enemy.Enemy;
import ST2.SpecialObject.SpecialObject;
import ST2.platform.Platform;
import ST2.platform.SeaPlatform;
import tklibs.SpriteUtils;

import java.util.List;
import java.util.Vector;

public class Layer {
    private List<Integer> data;
    private int height;
    private int width;
//    private int countLoop = 0;

    @Override
    public String toString() {
        return "Layer{" +
                "data=" + data +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public void generate() {
//        countLoop ++;
//        if(countLoop > width - 1500){
//            countLoop = width - 1500;
//        }
        for(int i =0; i <= 5; i++){
            SpecialPool.specialPools.add(new Vector<>());
        }
        for (int tileY = 0; tileY < height; tileY++){
            for (int tileX = 0; tileX < width; tileX++){
                int mapData = data.get(tileY * width + tileX);
                if (mapData != 0){
                    SpecialPool specialPool = new SpecialPool();
                    specialPool.getPosition().set(tileX*32,tileY*32);
                    switch (mapData){
                        case 3:
                            Enemy enemy = new Enemy();
                            enemy.getPosition().set(tileX * 32, tileY * 32);
                            GameObject.add(enemy);
                            break;
                        case 2:
                            SeaPlatform seaPlatform = new SeaPlatform();
                            seaPlatform.getPosition().set(tileX*32,tileY*32);
                            GameObject.add(seaPlatform);
                            break;
                        case 16:
                            Enemy pike = new Enemy();
                            pike.getPosition().set(tileX * 32, tileY * 32);
                            GameObject.add(pike);
                            break;
                        case 20:
                            specialPool.type = 0;
                            SpecialPool.specialPools.get(0).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        case 24:
                            specialPool.type = 1;
                            SpecialPool.specialPools.get(1).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        case 28:
                            specialPool.type = 2;
                            SpecialPool.specialPools.get(2).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        case 32:
                            specialPool.type = 3;
                            SpecialPool.specialPools.get(3).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        case 36:
                            specialPool.type = 4;
                            SpecialPool.specialPools.get(4).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        case 40:
                            specialPool.type = 5;
                            SpecialPool.specialPools.get(5).add(specialPool);
                            GameObject.add(specialPool);
                            break;
                        default:
                            Platform platform = Platform.create(mapData);
                            platform.getPosition().set(tileX * 32, tileY * 32);
                            GameObject.add(platform);
                            break;


                    }
                }
            }
        }
        SpecialPool.sortToGroup();
        SpecialPool.addNextPosition();
    }
}
