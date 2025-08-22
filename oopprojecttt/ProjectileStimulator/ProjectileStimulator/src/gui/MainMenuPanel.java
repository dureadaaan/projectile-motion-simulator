package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuPanel extends JPanel {

    private JButton btnDefault;
    private JButton btnCustom;
    private JButton btnExit;

    public MainMenuPanel() {
    	
        setLayout(new BorderLayout());  //border layout initialized and set

        // Title at the top
        JLabel title = new JLabel("Projectile Motion Simulator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(30, 30, 30));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // parameters are top, left, bottom, right
        add(title, BorderLayout.NORTH);

        // Gradient button panel in the center
        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(240, 240, 240),
                        0, getHeight(), new Color(210, 230, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));

        // Buttons
        btnDefault = createStyledButton("Default", new Color(255, 235, 59)); // Yellow
        btnCustom = createStyledButton("Custom", new Color(76, 175, 80));    // Green
        btnExit = createStyledButton("Exit", new Color(244, 67, 54));        // Red

        // Add buttons with spacing
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnDefault);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(btnCustom);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(btnExit);
        buttonPanel.add(Box.createVerticalGlue());

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setMaximumSize(new Dimension(200, 50));
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        return button;
    }

    public void setupMenuActions(MainFrame parent) {
        btnDefault.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showInputForm(false);
            }
        });

        btnCustom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showInputForm(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
