package org.life;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel implements Runnable {
	
	private static final int SIZE_X = 100;
	private static final int SIZE_Y = 100;
	
	private boolean[][] cells = new boolean[SIZE_X][SIZE_Y];
	
	public Canvas() {
		new Thread(this).start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#2b2b2b"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.decode("#ff0000"));
		int width = getWidth() / SIZE_X;
		int height = getHeight() / SIZE_Y;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				g.fillRect(x * width, y * height, width - 1, height - 1);
			}
		}
	}
	
	@Override
	public void run() {
		do {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} while (true);
	}
}