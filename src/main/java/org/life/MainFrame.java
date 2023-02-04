package org.life;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		add(new Canvas());
		setLocationRelativeTo(null);
		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
