package Bases;

import Bases.actions.Action;
import Bases.physics.Physics;
import Bases.physics.PhysicsBody;
import Bases.pools.GameObjectPool;
import Bases.renderers.Renderer;
import ST2.Enemy.Enemy;
import ST2.ViewCam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;

    protected Renderer renderer;

    protected ArrayList<GameObject> children;
    protected ArrayList<Action> actions;
    protected ArrayList<Action> newActions;

    protected boolean isActive;
    protected boolean isRenewing;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    private static boolean unpause;

    public GameObject() {
        children = new ArrayList<>();
        actions = new ArrayList<>();
        position = new Vector2D();
        newActions = new ArrayList<>();
        screenPosition = new Vector2D();
        isActive = true;
        unpause = true;
    }

    public static void runAll() {
        if(unpause){
            for (GameObject gameObject : gameObjects) {
                if (gameObject.isActive)
                    gameObject.run(new Vector2D(0, 0)); // TODO: Optimize
            }

            for (GameObject newGameObject : newGameObjects) {
                if (newGameObject instanceof PhysicsBody) {
                    Physics.add((PhysicsBody)newGameObject);
                }
            }

            gameObjects.addAll(newGameObjects);
            newGameObjects.clear();
        }
    }

    public static void renderAll(Graphics2D g2d, ViewCam viewCam) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && !gameObject.isRenewing)
                gameObject.render(g2d, viewCam);
        }
    }

    public void run(Vector2D parentPosition) {
        screenPosition = parentPosition.add(position);
        isRenewing = false;
        for (GameObject child: children) {
            if (child.isActive)
                child.run(screenPosition);
        }
    }

    public void render(Graphics2D g2d, ViewCam viewCam) {
//        if (this instanceof Enemy){
//            System.out.println(renderer);
//        }
        if (renderer != null) {
            renderer.render(g2d, viewCam.translate(this.screenPosition));
        }

        for (GameObject child: children) {
            if (child.isActive)
                child.render(g2d, viewCam);
        }
    }

    public static void runAllActions() {
        for (GameObject gameObject: gameObjects){
            if (gameObject.isActive)
                gameObject.runActions();
        }
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    private void runActions() {
        actions.removeIf(action -> action.run(this));
        for (Action action :  this.actions){
            action.run(this);
        }
        actions.addAll(newActions);
        newActions.clear();
    }

    public void reset() {
        this.isActive = true;
        this.isRenewing = true;
        this.actions.clear();
        this.newActions.clear();
    }

    public static void clearAll() {
        gameObjects.clear();
        newGameObjects.clear();
        Physics.clearAll();
        GameObjectPool.clearAll();
    }

    public void addAction(Action action){
        newActions.add(action);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
            this.position = position;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public GameObject setRenderer(Renderer renderer) {
        if (renderer != null)
            this.renderer = renderer;
        return this;
    }

    public static boolean isUnpause() {
        return unpause;
    }

    public static void setUnpause(boolean unpause) {
        GameObject.unpause = unpause;
    }
}
