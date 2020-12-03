package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CircleComponent extends JPanel
{
    Ellipse2D.Double circle;
    int xposition;
    int yposition;
    int radius;

    public CircleComponent(int radius)
    {
        this.radius = radius;
        circle = new Ellipse2D.Double(0, 0, radius, radius);
        setOpaque(false);
    }

    public Dimension getPreferredSize()
    {
        Rectangle bounds = circle.getBounds();
        xposition = bounds.width;
        yposition = bounds.height;
        return new Dimension(bounds.width, bounds.height);
    }

    public void paintComponent(Graphics gr)
    {
        /*
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor( Color.blue );
        g2.fill(circle);
        */
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        Shape ring = createRingShape(xposition/2, yposition/2, radius, 50);
        g.setColor(Color.blue);
        g.fill(ring);
        g.setColor(Color.BLACK);
        g.draw(ring);
    }
    private static Shape createRingShape(
            double centerX, double centerY, double outerRadius, double thickness)
    {
        Ellipse2D outer = new Ellipse2D.Double(
                centerX - outerRadius,
                centerY - outerRadius,
                outerRadius + outerRadius,
                outerRadius + outerRadius);
        Ellipse2D inner = new Ellipse2D.Double(
                centerX - outerRadius + thickness,
                centerY - outerRadius + thickness,
                outerRadius + outerRadius - thickness - thickness,
                outerRadius + outerRadius - thickness - thickness);
        Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
    }
}