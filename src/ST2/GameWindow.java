package ST2;

import Bases.GameObject;
import ST2.InputManager.InputManager;
import ST2.Player.Player;
import ST2.Scenes.GameMenuScene;
import ST2.Scenes.Level1Scene;
import ST2.Scenes.Scene;
import ST2.Scenes.SceneManager;
import ST2.Settings.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame{

    private long lastTimeUpdate, currentTime;
    private BufferedImage backBufferImage, blackBackground;
    private Graphics2D backBufferGraphics;
    InputManager inputManager = InputManager.instance;
    private GameMenuScene gameMenuScene;
    private boolean gameStart;




    public GameWindow() {
        pack();
        setupGameLoop();
        setupWindow();
        setupLevel();

    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024,768);
        this.setTitle("ST2");
        this.setVisible(true);
        this.backBufferImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backBufferGraphics = (Graphics2D) this.backBufferImage.getGraphics();
        this.blackBackground = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D backgroundGraphics = (Graphics2D) this.blackBackground.getGraphics();
        backgroundGraphics.setColor(Color.BLACK);
        backgroundGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
        Settings.instance.setWindowInsets(this.getInsets());
        ViewCam.instance.getFollowObj().set(
                -Settings.instance.getGamePlayWidth() / 2,
                -Settings.instance.getGamePlayHeight()/ 2);
    }

    private void setupLevel() {
        gameMenuScene = new GameMenuScene();
        SceneManager.changeScene(gameMenuScene);

    }

    public void loop() {
        while (true) {
            if (lastTimeUpdate == -1){
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000){
                run();
                render();
                SceneManager.changeSceneIfNeeded();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        GameObject.runAll();
        GameObject.runAllActions();
        if(inputManager.enter){
            Level1Scene level1Scene = new Level1Scene();
            SceneManager.changeScene(level1Scene);
        }
        ViewCam.instance.follow(Player.getInstance());

        //ViewCam.instance.follow(Player.getInstance());
//
//        if(gameStart){
//            ViewCam.instance.follow(level1Scene.getPlayer());
//
//        }
//        else {
//            ViewCam.instance.follow(gameMenuScene.getMenu());
//        }
    }

    public void update(Graphics g2d) {

        g2d.drawImage(backBufferImage, 0, 0, null);

    }
    private void render(){
        backBufferGraphics.drawImage(blackBackground, 0, 0, null);
        GameObject.renderAll(backBufferGraphics, ViewCam.instance);
        repaint();
    }
//    private void render() {
//        backBufferGraphics.drawImage(blackBackground, 0, 0, null);
//        GameObject.renderAll(backBufferGraphics);
//        getGraphics().drawImage(backBufferImage, 0, 0, null);
//    }
}
