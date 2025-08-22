package render;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage image;
    private Point position;

    /**
     * Loads the sprite image from the classpath.
     * Example: loadImage("/projectile.png") — image should be directly inside the 'assets' folder.
     */
    public void loadImage(String path) {
        try {
            System.out.println("Loading image from path: " + path);
            var url = getClass().getResource(path);
            System.out.println("Resolved resource URL: " + url);  // Debug

            image = ImageIO.read(Objects.requireNonNull(url));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Unable to load sprite image: " + path, e);
        }
    }

    public void draw(Graphics g) {
        if (image == null || position == null) return;

        int scaledW = 45;
        int scaledH = 45;
        g.drawImage(image, position.x - scaledW / 2, position.y - scaledH / 2, scaledW, scaledH, null);
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public Point getPosition() {
        return position;
    }
}
