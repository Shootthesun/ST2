package ST2.InputManager;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputManager{

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enter;
    public boolean esc;


    public static final InputManager instance = new InputManager();
    public boolean xPressed;

    private InputManager() {

    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public interface InputListener {
        void onKeyPressed(int keyCode);
        void onKeyRealeased(int keyCode);
    }

    private InputListener inputListener = new InputListener() {
        @Override
        public void onKeyPressed(int keyCode) {

        }

        @Override
        public void onKeyRealeased(int keyCode) {

        }
    };

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downPressed = true;
                break;
            case VK_ENTER:
                enter = true;
                break;
            case VK_ESCAPE:
                esc = true;
                break;
            case VK_X:
                xPressed = true;
                break;
        }
        if (e != null)
            inputListener.onKeyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = false;
                break;
            case VK_LEFT:
                leftPressed = false;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downPressed = false;
                break;
            case VK_ENTER:
                enter = false;
                break;
            case VK_ESCAPE:
                esc = false;
                break;
            case VK_X:
                xPressed = false;
                break;
        }
        if (e != null)
            inputListener.onKeyRealeased(e.getKeyCode());
    }
}
