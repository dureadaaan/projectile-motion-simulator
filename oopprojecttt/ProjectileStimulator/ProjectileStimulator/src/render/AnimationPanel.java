
package render;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.InputData;
import physics.SimulationResult;

public class AnimationPanel extends JPanel {
    private final ProjectileRenderer renderer = new ProjectileRenderer();
    private BufferedImage background;
    private Timer timer;
    private InputData inputData;

    private Clip soundClip;


    public AnimationPanel() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(null); // for absolute positioning of the back button

        // load background
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Cannot load background image", e);
        }

        // set up animation timer (30fps)
        timer = new Timer(33, e -> {
    renderer.update();
    repaint();
    if (renderer.isDone()) {
        timer.stop();

        // Stop the sound when animation ends
        if (soundClip != null && soundClip.isRunning()) {
            soundClip.stop();
            soundClip.close(); // optional, to release resources
        }
    }
});

        // BACK BUTTON
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 520, 100, 30);
        backButton.setBackground(new Color(220, 53, 69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            Container parent = getTopLevelAncestor();
            if (parent instanceof JFrame f && f instanceof gui.MainFrame mf) {
                mf.showMenuPanel(); // show main menu
            }
        });
        add(backButton);
    }

    /** Called by MainFrame after physics simulation completes. */
    public void startAnimation(SimulationResult result, InputData input) {
        this.inputData = input;
        java.awt.Point[] raw = result.getTrajectoryPoints();
        renderer.setTrajectory(raw, getSize());
        playSound("/launch.wav");  // Correct path
 // play sound once
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        // draw animation
        renderer.draw(g, getSize());

        // draw inputData overlay if available
        if (inputData != null) {
            g.setColor(new Color(0, 0, 0, 180)); // semi-transparent background
            g.fillRect(10, 10, 230, 80);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            g.drawString(String.format("Angle: %.1f°", inputData.getAngle()), 20, 30);
            g.drawString(String.format("Velocity: %.1f m/s", inputData.getVelocity()), 20, 45);
            g.drawString(String.format("Height: %.1f m", inputData.getHeight()), 20, 60);
            g.drawString(String.format("Gravity: %.1f m/s²", inputData.getGravity()), 20, 75);
        }
    }

    // Simple sound effect player
    private void playSound(String soundPath) {
        // try {
        //     AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundPath));
        //     Clip clip = AudioSystem.getClip();
        //     clip.open(audioIn);
        //     clip.start();
        // } catch (Exception e) {
        //     System.err.println("Sound failed: " + e.getMessage());
        // }

          try {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundPath));
        soundClip = AudioSystem.getClip();
        soundClip.open(audioIn);
        soundClip.start();
    } catch (Exception e) {
        System.err.println("Sound failed: " + e.getMessage());
    }
    }
}

