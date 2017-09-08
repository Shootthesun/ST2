package ST2.SpecialObject;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.actions.WaitAction;
import ST2.ViewCam;

import java.awt.*;
import java.util.ArrayList;

public class SpecialObject extends GameObject {
    
    private int waitComboList;
    private int comboList[];
    private ArrayList <Vector2D> successPosition;
    private ArrayList <Vector2D> failedPosition;
    private StateMachine stateMachine;
    public SpecialObject() {

    }

    public void init(int waitComboList, int comboList[], ArrayList <Vector2D> successPosition, ArrayList <Vector2D> failedPosition){
        this.waitComboList = waitComboList;
        this.comboList = comboList;
        this.successPosition = successPosition;
        this.failedPosition = failedPosition;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        stateMachine.load(comboList);
        this.addAction(new WaitAction(waitComboList));
        if (stateMachine.isComboFailed()){
            PlayerMoveTo(successPosition);
        }
        else
            PlayerMoveTo(failedPosition);
    }

    private void PlayerMoveTo(ArrayList<Vector2D> successPosition) {

    }

    @Override
    public void render(Graphics2D g2d, ViewCam viewCam) {
        super.render(g2d, viewCam);
        stateMachine.render();
    }
}

