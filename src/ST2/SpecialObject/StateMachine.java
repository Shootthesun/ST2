package ST2.SpecialObject;

import ST2.InputManager.InputManager;

public class StateMachine implements InputManager.InputListener{

    private int currentKey = 0;
    private int comboList[];
    private boolean comboFailed = false;

    public StateMachine() {
        this.currentKey = 0;
        InputManager.instance.setInputListener(this);
    }

    public void load(int comboList[]){
        this.comboList = comboList;
        currentKey = 0;
        comboFailed = false;
    }

    @Override
    public void onKeyPressed(int keyCode) {
    }

    @Override
    public void onKeyRealeased(int keyCode) {
        if (currentKey < comboList.length)
            if (keyCode == comboList[currentKey]) currentKey++;
            else comboFailed = true;
    }

    public void render(){
        if (!comboFailed) {
            for (int i = 1; i <= currentKey; ++i) {
//            todo: DRAW ARROW SUCCESS
            }
        }
        else {
            for (int i = 1; i < currentKey; ++i){
//                todo: DRAW ARROW SUCCESS
            }

//                todo: currentkey: DRAW ARROW FAILED
        }
        for (int i = currentKey + 1; i < comboList.length; ++i) {
//            todo: DRAW ARROW PREPARE
        }
    }

    public void setComboFailed(boolean comboFailed) {
        this.comboFailed = comboFailed;
    }

    public boolean isComboFailed() {
        return comboFailed;
    }
}
