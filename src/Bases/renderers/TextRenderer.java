package Bases.renderers;

import Bases.Vector2D;

import java.awt.*;

public class TextRenderer implements Renderer{
    private String text;
    private int textSize;
    private Color color;

    public TextRenderer(String text, String color){
        this.text = text;
        this.textSize = 30;
        this.color = Color.decode(color.toUpperCase());
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        g2d.setFont(new Font("TimesNewRoman", Font.PLAIN, textSize));
        g2d.setColor(color);
        g2d.drawString(text, (int) position.x, (int) position.y);
    }
}
