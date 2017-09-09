package ST2.SpecialObject;

import ST2.InputManager.InputManager;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;

public class StateMachine implements InputManager.InputListener{

    private int currentKey = 0;
    private ArrayList<Integer> comboList = new ArrayList<>();
    private boolean comboFailed = false;
    ArrayList <String> urlpre= new ArrayList<>();
    ArrayList <String> urlsuccess= new ArrayList<>();
    ArrayList <String> urlfailed= new ArrayList<>();

    public StateMachine() {
        this.currentKey = 0;
        InputManager.instance.setInputListener(this);
        urlpre.add("assets/image/arrow/PrepreArrow/leftArrow.png");
        urlpre.add("assets/image/arrow/PrepreArrow/upArrow.png");
        urlpre.add("assets/image/arrow/PrepreArrow/rightArrow.png");
        urlpre.add("assets/image/arrow/PrepreArrow/downArrow.png");
        urlsuccess.add("assets/image/arrow/Success Arrow/leftArrow.png");
        urlsuccess.add("assets/image/arrow/Success Arrow/upArrow.png");
        urlsuccess.add("assets/image/arrow/Success Arrow/rightArrow.png");
        urlsuccess.add("assets/image/arrow/Success Arrow/downArrow.png");
        urlfailed.add("assets/image/arrow/Failed Arrow/leftArrow.png");
        urlfailed.add("assets/image/arrow/Failed Arrow/upArrow.png");
        urlfailed.add("assets/image/arrow/Failed Arrow/rightArrow.png");
        urlfailed.add("assets/image/arrow/Failed Arrow/downArrow.png");
    }

    public void load(ArrayList<Integer> comboList){
        this.comboList.clear();
        this.comboList.addAll(comboList);
        currentKey = 0;
        comboFailed = false;
    }

    @Override
    public void onKeyPressed(int keyCode) {
    }

    @Override
    public void onKeyRealeased(int keyCode) {
        if (!comboFailed)
        if (currentKey < comboList.size())
            if (keyCode == comboList.get(currentKey)) currentKey++;
            else comboFailed = true;
    }

    public void render(Graphics2D g2d){
        int i;
        int dx = 250;
        for (i = 0; i < currentKey; ++i) {
            g2d.drawImage(SpriteUtils.loadImage(urlsuccess.get(comboList.get(i) - 37)), dx + (i + 1) * 150, 500, null);
        }
        if (!comboFailed) {
            for (i = currentKey; i < comboList.size(); ++i) {
                g2d.drawImage(SpriteUtils.loadImage(urlpre.get(comboList.get(i) - 37)), dx + (i + 1) * 150, 500, null);
            }
        }
        else{
            g2d.drawImage(SpriteUtils.loadImage(urlfailed.get(comboList.get(currentKey) - 37)),dx + (currentKey + 1) * 150, 500, null);
            for (i = currentKey + 1; i < comboList.size(); ++i){
                g2d.drawImage(SpriteUtils.loadImage(urlfailed.get(comboList.get(i) - 37)), dx + (i + 1) * 150, 500, null);
            }
        }
    }

    public void setComboFailed() {
        this.comboFailed = !(currentKey >= comboList.size());
    }

    public boolean isComboFailed() {
        return comboFailed;
    }
}
