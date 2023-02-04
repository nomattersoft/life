package org.life;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		setLocationRelativeTo(null);
		setSize(new Dimension(1900, 1150));
		add(new Canvas(getWidth(), getHeight()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
