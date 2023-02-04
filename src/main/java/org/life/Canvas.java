package org.life;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Canvas extends JPanel implements Runnable {
	
	private static final int CELL_ALIVE = 8000;
	private static final int CELL_SIZE = 10;
	private final int xCellSize;
	private final int yCellSize;
	
	private boolean[][] cells;
	
	public Canvas(int frameWidth, int frameHeight) {
		xCellSize = frameWidth / CELL_SIZE;
		yCellSize = frameHeight / CELL_SIZE;
		cells = new boolean[xCellSize][yCellSize];
		Random random = new Random();
		for (int cell = 0; cell < CELL_ALIVE; cell++) {
			cells[random.nextInt(xCellSize)][random.nextInt(yCellSize)] = true;
		}
		
		new Thread(this).start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#2b2b2b"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.decode("#ff0000"));
		for (int x = 0; x < xCellSize; x++) {
			for (int y = 0; y < yCellSize; y++) {
				if (cells[x][y]) {
					g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
				}
			}
		}
	}
	
	@Override
	public void run() {
		do {
			boolean[][] newCells = new boolean[xCellSize][yCellSize];
			for (int x = 0; x < xCellSize; x++) {
				for (int y = 0; y < yCellSize; y++) {
					newCells[x][y] = isCellAlive(x, y, getNeighborCount(x, y, cells));
				}
			}
			cells = newCells;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			repaint();
			
		} while (true);
	}
	
	//todo move to test
	public static void main(String[] args) {
		int neighborCount = getNeighborCount(1, 1, new boolean[][]{
				{true, true, true},
				{true, true, true},
				{true, true, true}});
		System.out.println("###:" + neighborCount);
	}
	
	private static int getNeighborCount(int x, int y, boolean[][] cells) {
		int neighborCount = 0;
		for (int nX = x - 1; nX <= x + 1; nX++) {
			for (int nY = y - 1; nY <= y + 1; nY++) {
			
				if (nX != x || nY != y) {
					if (nX > -1 && nX < cells.length && nY > -1 && nY < cells[x].length) {
						neighborCount += cells[nX][nY] ? 1 : 0;
					}
				}
			}
		}
		return neighborCount;
	}
	
	private boolean isCellAlive(int x, int y, int neighborCount) {
		return (cells[x][y] && (neighborCount == 2 || neighborCount == 3)) || (!cells[x][y] && neighborCount == 3);
	}
}