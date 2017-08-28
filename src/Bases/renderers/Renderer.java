package Bases.renderers;

import Bases.Vector2D;

import java.awt.*;

public interface Renderer {
    void render(Graphics2D g2d, Vector2D position);
}
