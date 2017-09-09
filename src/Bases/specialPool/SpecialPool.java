package Bases.specialPool;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.physics.BoxCollider;
import Bases.physics.PhysicsBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SpecialPool extends GameObject implements PhysicsBody {

    public static Vector <Vector <SpecialPool>> specialPools = new Vector<>();
    private BoxCollider boxCollider;
    private Vector2D nextPosition;
    public int type;

    public SpecialPool() {
        boxCollider = new BoxCollider(1,1);
        children.add(boxCollider);
        type = -1;
    }

    public static void sortToGroup(){
        for (int index = 0; index < specialPools.size(); ++index) {
            Collections.sort(specialPools.get(index), new Comparator<SpecialPool>() {
                @Override
                public int compare(SpecialPool o1, SpecialPool o2) {
                    return o1.position.x > o2.position.x ? 1: -1;
                }
            });
//            for (SpecialPool specialPools1: specialPools.get(index))
//                System.out.println(specialPools1.position);
//            System.out.println();
        }
    }

    public static void addNextPosition(){
        for (int i=0; i<specialPools.size() - 1; ++i){
            for (int i2 = 0; i2<specialPools.get(i).size(); ++i2)
                specialPools.get(i).get(i2).nextPosition = specialPools.get(i + 1).get(i2).position;
        }
        for (Vector<SpecialPool> specialPools: specialPools){
            for (SpecialPool specialPool: specialPools)
                System.out.println(specialPool.nextPosition);
            System.out.println();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public Vector2D getNextPosition() {
        return nextPosition;
    }
}
