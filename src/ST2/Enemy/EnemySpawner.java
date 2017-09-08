package ST2.Enemy;


import Bases.FrameCounter;
import Bases.GameObject;
import Bases.Vector2D;

import java.util.Random;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject {
    private FrameCounter spawnCounter;
    private Random random;

    public EnemySpawner() {
        spawnCounter = new FrameCounter(70);
        random = new Random();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        spawn();
    }

    public void spawn() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
            Enemy enemy = new Enemy();
            enemy.getPosition().set(random.nextInt(384), 40);
            GameObject.add(enemy);
        }
    }
}
