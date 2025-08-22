

package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class ProjectileRenderer {
    private final Sprite sprite = new Sprite();
    private final TrajectoryTrail trail = new TrajectoryTrail();
    private Point[] trajectory;
    private int currentIndex = 0;

    // Store raw max values for accurate axis labeling
    private int maxXRaw = 1;
    private int maxYRaw = 1;

    public ProjectileRenderer() {
        // load projectile sprite
    	sprite.loadImage("/projectile.png"); // ✅ Correct

   }

    /** Feed the raw trajectory (in simulation units) for rendering. */
    public void setTrajectory(Point[] rawTrajectory, Dimension panelSize) {
        maxXRaw = 1;
        maxYRaw = 1;
        for (Point p : rawTrajectory) {
            maxXRaw = Math.max(maxXRaw, p.x);
            maxYRaw = Math.max(maxYRaw, p.y);
        }

        trajectory = new Point[rawTrajectory.length];
        for (int i = 0; i < rawTrajectory.length; i++) {
            // map x:[0,maxX] → [50, panelSize.width-50]
            int x = 50 + (rawTrajectory[i].x * (panelSize.width - 100)) / Math.max(1, maxXRaw);
            // map y:[0,maxY] → [panelSize.height-50,50] (invert y)
            int y = panelSize.height - 50 - (rawTrajectory[i].y * (panelSize.height - 100)) / Math.max(1, maxYRaw);
            trajectory[i] = new Point(x, y);
        }
        trail.clear();
        currentIndex = 0;
    }

    /** Advance to next frame: update sprite & trail. */
    public void update() {
        if (trajectory == null || currentIndex >= trajectory.length) return;
        Point p = trajectory[currentIndex++];
        sprite.setPosition(p);
        trail.addPoint(p);
    }

    /** Draw background, axes with actual labels, trail and sprite. */
    public void draw(Graphics g, Dimension panelSize) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.DARK_GRAY);

        // Define margins
        int margin = 50;
        int x0 = margin;
        int y0 = panelSize.height - margin;
        int xMax = panelSize.width - margin;
        int yMax = margin;

        // Draw X and Y axes
        g2.drawLine(x0, y0, xMax, y0); // X axis
        g2.drawLine(x0, y0, x0, yMax); // Y axis

        // X-axis labels (range)
        for (int i = 0; i <= 10; i++) {
            int x = x0 + i * (xMax - x0) / 10;
            g2.drawLine(x, y0 - 5, x, y0 + 5);

            int value = i * maxXRaw / 10;
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.drawString(value + " m", x - 10, y0 + 20);
        }

        // Y-axis labels (height)
        for (int i = 0; i <= 10; i++) {
            int y = y0 - i * (y0 - yMax) / 10;
            g2.drawLine(x0 - 5, y, x0 + 5, y);

            int value = i * maxYRaw / 10;
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.drawString(value + " m", x0 - 40, y + 5);
        }

        g2.dispose();

        // draw trail
        trail.draw(g);
        // draw sprite on top
        sprite.draw(g);
    }

    public boolean isDone() {
        return trajectory != null && currentIndex >= trajectory.length;
    }
}
