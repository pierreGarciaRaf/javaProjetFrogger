package graphicalElements;

import javax.swing.*;
import gameCommons.IFrog;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FroggerGraphicMulti extends JPanel implements IFroggerGraphics, KeyListener {

	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	private int height;
	private ArrayList<IFrog> frogs;
	private JFrame frame;
	
	/**
	 * generates a graphic panel.
	 * @param width horizontal size.
	 * @param height vertical size.
	 */
	public FroggerGraphicMulti(int width, int height) {
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

	/**
	 * draws a component on
	 * @param g the canvas to be drawns on.
	 */
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
	/**
	 * Moves the frogs when key is pressed.
	 * Called by the engine.
	 */
	public void keyPressed(KeyEvent e) {
		for (IFrog frog : frogs) {
			frog.moveFromInput(e.getKeyCode());
		}
	}
	/**
	 * resets the predrawn list
	 */
	public void clear() {
		this.elementsToDisplay.clear();
	}

	/**
	 * adds an Element
	 * @param element to add to pre-drawn list.
	 */
	public void add(Element e) {

		this.elementsToDisplay.add(e);
	}

	/**
	 * allows the param frog to receive inputs.
	 */
	public void setFrog(IFrog frog) {
		this.frogs.add(frog);
	}

	/**
	 * display an endGameScreen. 
	 * @param s : text to display (has to contain score)
	 * @param time_ms : the time to display from ms to min:s.
	 */
	public void endGameScreen(String s, int time_ms) {
		frame.remove(this);
		JLabel label = new JLabel(s + "//time : " + time_ms / 60000 + "m" + time_ms % 60000 / 1000 + "s");
		label.setFont(new Font("Verdana", 1, 10));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
