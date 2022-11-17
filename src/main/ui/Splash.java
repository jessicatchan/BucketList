package ui;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {
    private final int duration;

    public Splash(int d) {
        duration = d;
    }

    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash(String fileName) {

        JPanel content = (JPanel) getContentPane();

        // Set the window's bounds, centering the window
        int width = 700;
        int height = 450;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon(fileName));
        content.add(label, BorderLayout.CENTER);

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        setVisible(false);
    }
}

