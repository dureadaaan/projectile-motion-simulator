package render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TrajectoryTrail {
    private final List<Point> trailPoints = new ArrayList<>();

    /** Add a new point to the trail. */
    public void addPoint(Point p) {
        trailPoints.add(p);
    }

    /** Draws a red line connecting all points in the trail. */
    public void draw(Graphics g) {
        if (trailPoints.size() < 2) return;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.RED);
        for (int i = 1; i < trailPoints.size(); i++) {
            Point p0 = trailPoints.get(i - 1);
            Point p1 = trailPoints.get(i);
            g2.drawLine(p0.x, p0.y, p1.x, p1.y);
        }
        g2.dispose();
    }

    public void clear() {
        trailPoints.clear();
    }
}
