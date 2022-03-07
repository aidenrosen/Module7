
/**
 * Write a description of the class here.
 *
 * @author Aiden Rosen
 * @version 11/17/2021
 */

// NOTE: Chapter 7: SmartEllipse.java Extends Java's Ellipse2D.Double class,
// adding the capabilities to set color, rotation, location, and size, to move
// to a specified location, and to display itself on a panel.

public class SmartEllipse extends java.awt.geom.Ellipse2D.Double
{
    private java.awt.Color _borderColor, _fillColor; // attributes
    private int _rotation;
    private final int STROKE_WIDTH = 2;

    public SmartEllipse(java.awt.Color aColor)
    {
        super();
        _borderColor = aColor;
        _fillColor = aColor; // solid color to start
        _rotation = 0; // no rotation for now
    }

    // methods not provided by the super class
    public void setBorderColor(java.awt.Color aColor)
    {
        _borderColor = aColor;
    }

    public void setFillColor(java.awt.Color aColor)
    {
        _fillColor = aColor;
    }

    public void setRotation(int aRotation)
    {
        _rotation = aRotation;
    }

    // more readable versions of methods provided by super class
    public void setLocation(double x, double y)
    {
        this.setFrame(x, y, this.getWidth(), this.getHeight());
    }

    public void setSize(int aWidth, int aHeight)
    {
        this.setFrame(this.getX(), this.getY(), aWidth, aHeight);
    }

    public void move(int dx, int dy)
    {
        this.setFrame((int) this.getX() + dx, (int) this.getY() + dy,
            this.getWidth(), this.getHeight());
    }
    

    /**
     * A method for painting the "inside" of the SmartEllipse
     *
     * @param aBetterBrush
     */
    public void fill(java.awt.Graphics2D aBetterBrush)
    {
        java.awt.Color savedColor = aBetterBrush.getColor();
        aBetterBrush.setColor(_fillColor);
        aBetterBrush.fill(this);
        aBetterBrush.setColor(savedColor);
    }

    /**
     * A method for painting the outline/border of the SmartEllipse
     *
     * @param aBrush
     */
    public void draw(java.awt.Graphics2D aBrush)
    {
        java.awt.Color savedColor = aBrush.getColor();
        aBrush.setColor(_borderColor);
        java.awt.Stroke savedStroke = aBrush.getStroke();
        aBrush.setStroke(new java.awt.BasicStroke(STROKE_WIDTH));
        aBrush.draw(this);
        aBrush.setStroke(savedStroke);
        aBrush.setColor(savedColor);
    }
}
