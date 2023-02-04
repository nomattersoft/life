package org.life;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel implements Runnable {
	
	private static final int CELL_SIZE = 10;
	
	private boolean[][] cells;
	
	public Canvas(int frameWidth, int frameHeight) {
		cells = new boolean[frameWidth / CELL_SIZE][frameHeight / CELL_SIZE];
		new Thread(this).start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#2b2b2b"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.decode("#ff0000"));
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
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