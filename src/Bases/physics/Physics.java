package Bases.physics;

import Bases.Vector2D;
import Bases.specialPool.SpecialPool;
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import java.util.Vector;

/**
 * Created by huynq on 8/12/17.
 */
public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>(); // Generics

    public static <T extends PhysicsBody> T collideWith(BoxCollider boxCollider, Class<T> classz) {
        for(PhysicsBody body : bodies) {
            if (body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }

        return null;
    }

    public static <T extends PhysicsBody> T collideWith(Vector2D center, float width, float height, Class<T> classz){
        for(PhysicsBody body : bodies) {
            if (body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(center, width, height)) {
                    return (T) body;
                }
            }
        }

        return null;
    }

    // TODO: collide with many

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static void clearAll() {
        bodies.clear();
    }

    public static SpecialPool collideWithSpecial(Vector2D screenPosition, float width, float height, Class<SpecialPool> specialPoolClass, int type) {
        for(PhysicsBody body : bodies) {
            if (body.getClass().equals(specialPoolClass)) {
                SpecialPool specialPool = (SpecialPool) body;
                if (specialPool.getBoxCollider().intersects(screenPosition, width, height) && specialPool.type == type) {
                    return specialPool;
                }
            }
        }
        return null;
    }


}
