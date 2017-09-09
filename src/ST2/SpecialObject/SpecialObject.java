package ST2.SpecialObject;

import Bases.GameObject;
import Bases.Vector2D;
import Bases.actions.WaitAction;
import ST2.ViewCam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SpecialObject extends GameObject {
    
    private int waitComboList;
    private ArrayList <Integer> comboList;
    private ArrayList <Vector2D> sequencePosition;
    private StateMachine stateMachine;
    private Vector2D nextPosition;

    public SpecialObject() {

    }

    public void init(int waitComboList, int hardLevel, Vector2D nextPosition){
        this.waitComboList = waitComboList;
        createRandomCombo(hardLevel);
        this.nextPosition = nextPosition;
    }

    private void createRandomCombo(int hardLevel) {
        for (int i=0; i<=hardLevel; ++i){
            Random random = new Random();
            switch (random.nextInt(4)){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        stateMachine.load(comboList);
        this.addAction(new WaitAction(waitComboList));
//        if (stateMachine.isComboFailed()){
        if (true){
            PlayerMoveTo(sequencePosition);
        }
    }

    private void PlayerMoveTo(ArrayList<Vector2D> sequencePosition) {
        if (sequencePosition == null){

        }
        else{

        }
    }

    @Override
    public void render(Graphics2D g2d, ViewCam viewCam) {
        super.render(g2d, viewCam);
//        stateMachine.render();
    }
}

