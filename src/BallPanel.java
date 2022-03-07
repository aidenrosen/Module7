
/**
 * Write a description of the class here.
 *
 * @author Aiden Rosen
 * @version 11/17/2021
 */

// NOTE: Chapter 7: BallPanel.java Creates the panel to be placed inside the
// BallApp window. Used (with modifications) in all programs later in this book.
// Version 3 of 3

import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.KeyEvent;
public class BallPanel extends javax.swing.JPanel
{
    private final int INIT_X = 75;
    private final int INIT_Y = 75;
    private final int DIAMETER = 60;
    private int mousePrevX, mousePrevY;
    private SmartEllipse _ball;

    public BallPanel()
    {
        super();
        this.setBackground(java.awt.Color.white);
        _ball = new SmartEllipse(java.awt.Color.red);
        _ball.setLocation(INIT_X, INIT_Y);
        _ball.setSize(DIAMETER, DIAMETER);
        
        DragListener mouseListener = new DragListener(this);
        
        this.setFocusable(true);
        
        this.addMouseMotionListener(mouseListener);
        this.addMouseListener(mouseListener);
        this.addKeyListener(new KeysListener(this));

        // The ball panel needs to add a new MouseListener, KeyListener, and
        // MouseMotionListener in its constructor. To receive key events, it must also
        // be focusable.
    }

    public void paintComponent(java.awt.Graphics aBrush)
    {
        super.paintComponent(aBrush);
        java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
        _ball.fill(betterBrush);
    }

    // You can create one or more private listener classes here. These classes must
    // implement MouseListener, KeyListener, and MouseMotionListener (you can make
    // one class for all three, a separate class for each, or have the BallPanel
    // implement them instead). Make sure they're working by printing a message in
    // the mousePressed, keyPressed, and mouseDragged methods.
    
    private class DragListener implements MouseMotionListener, MouseListener

    {
    	private JPanel panel;
    	private boolean clicked, dragging;
    	public DragListener(JPanel panel)
    	{
    		this.panel = panel;
    		this.clicked = false;
    	}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			if ((_ball.contains(e.getPoint()) && clicked) || dragging)
			{
				_ball.move(e.getX() - mousePrevX, e.getY() - mousePrevY);
				mousePrevX = e.getX();
				mousePrevY = e.getY();
				panel.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e)	{}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			if (_ball.contains(e.getPoint()))
			{
				clicked = true;
				dragging = true;
			}
			else clicked = false;
			mousePrevX = e.getX();
			mousePrevY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) { dragging = false; }

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
    	
    }
    
    private class KeysListener implements KeyListener
    {
    	private JPanel panel;
    	public KeysListener(JPanel panel)
    	{
    		this.panel = panel;
    	}
    	
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e)
		{
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_UP) _ball.move(0, -10);
			else if (code == KeyEvent.VK_DOWN) _ball.move(0, 10);
			else if (code == KeyEvent.VK_LEFT) _ball.move(-10, 0);
			else if (code == KeyEvent.VK_RIGHT) _ball.move(10, 0);
			panel.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {}
    	
    }
}


