package graphicalElements;

import javax.swing.*;

import gameCommons.Direction;
import gameCommons.IFrog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerGraphicMulti extends JPanel implements IFroggerGraphics, KeyListener {


	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private ArrayList<IFrog> frogs;
	private JFrame frame;

	public FroggerGraphicMulti(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
		frogs = new ArrayList<IFrog>(0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		for (IFrog frog : frogs) {
			frog.moveFromInput(e.getKeyCode());
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {

		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frogs.add(frog);
	}
	
	public void endGameScreen(String s, int time_ms) {
		frame.remove(this);
		JLabel label = new JLabel(s + "//time : " +time_ms/60000 +"m"+ time_ms%60000/1000+"s"+time_ms%1000);
		label.setFont(new Font("Verdana", 1, 10));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
